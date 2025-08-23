package com.hanwei.process.server.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.modelinvoking.service.impl.ModelInvokingServiceImpl;
import com.hanwei.process.server.util.RasrSisUtil;
import com.hanwei.process.util.CommonUtils;
import com.hanwei.process.vo.ResultForFrontVo;

import com.huawei.sis.client.RasrClient;
import com.huawei.sis.exception.SisException;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * websocket服务器
 *
 * @author CX
 */
@ServerEndpoint("/SmartWaterModelServer/{channelCode}/{userId}/{userCode}")
@Component
public class WebSocketServer {
    static Log log = LogFactory.get(WebSocketServer.class);
    /**
     * 语音交互处理中间容器容量
     */
    private static final int SIS_HANDLE_BUFFER_SIZE = 102400;

    /**
     * 唤醒超时时间 毫秒
     */
    private static final int WAKE_UP_TIME_THRESHOLD = 600000;

    /**
     * 一次语音交互最大切片数量
     */
    private static final int MAX_SLICE_NUM = 50;

    /**
     * 静态变量，用来记录当前在线连接数。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 用来解决websocket无法注入问题
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId;
    /**
     * 唯一识别Id uesrId+sessionId
     */
    private String clientId;
    /**
     * 访问渠道
     */
    private String channelCode;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 语音交互处理中间容器
     */
    private ByteBuffer handleBuffer = ByteBuffer.allocate(SIS_HANDLE_BUFFER_SIZE);
    /**
     * 华为语音工具类
     */
    private RasrClient rasrClient;
    /**
     * 唤醒标志
     */
    private Boolean wakeUpFlag = false;
    /**
     * 流开关
     */
    private Boolean isSend = false;
    /**
     * 唤醒时间
     */
    private Date wakeUpTime;
    /**
     * 切片数
     */
    private Integer sliceCount = 0;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("channelCode") String channelCode, @PathParam("userId") String userId, @PathParam("userCode") String userCode) {
        System.out.println("建立websocket连接");

        this.session = session;
        this.channelCode = channelCode;
        this.userId = userId;
        this.userCode = userCode;
        this.clientId = userId + session.getId();
        if (webSocketMap.containsKey(clientId)) {
            webSocketMap.remove(clientId);
            webSocketMap.put(clientId, this);
        } else {
            webSocketMap.put(clientId, this);
            //在线数加1
            addOnlineCount();

        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (Exception e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(clientId)) {
            webSocketMap.remove(clientId);
            //从set中删除
            subOnlineCount();
        }
        isSend = false;

        //关闭连接时，关闭语音交互
        this.rasrClient.close();
        log.info("用户退出:" + clientId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage(maxMessageSize = 5242880)
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + clientId + ",报文:" + message);
        System.out.println("收到来自客户端的消息:" + message);
        try {
            //处理文字消息
            //调用规则控制器
            if (CommonConstant.SIS_START.equals(message)) {
                //初始化语音交互服务
                this.rasrClient = new RasrSisUtil(this).initRasrClient(CommonConstant.SIS_CONTINUE_STREAM);
                this.rasrClient.sendStart();
                isSend = true;

            }

            if (CommonConstant.SIS_END.equals(message)) {
                isSend = false;
                //初始化语音交互服务
                this.rasrClient.sendEnd();
                this.rasrClient.close();
            }

            //前端命令唤醒
            if (CommonConstant.CMD_WAKE_UP.equals(message)) {
                wakeUpFlag = true;
            }

            //前端命令休眠
            if (CommonConstant.CMD_SLEEP.equals(message)) {
                wakeUpFlag = false;
            }

            //前端发送命令
            if (message.startsWith(CommonConstant.CMD_TEXT) && StrUtil.isNotEmpty(message)) {
                //调用规则控制器
                ModelInvokingServiceImpl modelInvokingService = applicationContext.getBean(ModelInvokingServiceImpl.class);
                ResultForFrontVo resultToCustomer = modelInvokingService.messageHandleController(this.channelCode, message);

                if (Optional.ofNullable(resultToCustomer).isPresent()) {
                    //发送结果到客户端
                    sendMessage(JSON.toJSONString(resultToCustomer));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage(maxMessageSize = 5242880)
    public void onMessage(ByteBuffer bytes) {
        // 接收到二进制消息的处理逻辑
        try {
            if(isSend){
                this.rasrClient.sendByte(bytes.array());
            }

            //处理长时间没有语音进行休眠逻辑
            if (wakeUpFlag) {
                //判断唤醒时间是否超过阈值
                if (DateUtil.date().getTime() - wakeUpTime.getTime() > WAKE_UP_TIME_THRESHOLD) {
                    wakeUpFlag = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.clientId + ",原因:" + error.getMessage());
        try {
            this.rasrClient.sendEnd();
        } catch (SisException e) {
            e.printStackTrace();
        }
        this.rasrClient.close();
        error.printStackTrace();
    }

    /**
     * 服务器消息推送
     */
    public void sendMessage(String message) throws IOException {
        log.info("发送消息到:" + clientId + "，报文:" + message);
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 消息处理
     */
    public void handleMessage(String message) throws IOException {
        log.info("接收到处理消息" + message);
        /**过滤一个字信息*/
        if(StrUtil.isNotEmpty(message) && message.length() <2){
            return;
        }


        //处理唤醒休眠逻辑
        if (CommonUtils.preWakeUp(message)) {
            //封装提示结果
            ResultForFrontVo result = new ResultForFrontVo();
            result.setResultTo(CommonConstant.RESULT_TO_CUSTOMER);
            result.setResultType(CommonConstant.RESULT_TYPE_TRANSLATED_TEXT);
            result.setTranslatedText(message);
            //发送结果到客户端
            sendMessage(JSON.toJSONString(result));

            //封装提示结果
            ResultForFrontVo result1 = new ResultForFrontVo();
            result1.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            result1.setResultType(CommonConstant.RESULT_TYPE_TRANSLATED_TEXT);
            result1.setTranslatedText(CommonUtils.getWakeUpReply());

            //发送结果到客户端
            sendMessage(JSON.toJSONString(result1));

            //记录唤醒标志
            wakeUpFlag = true;
            wakeUpTime = DateUtil.date();

        } else if (CommonUtils.preSleep(message)) {
            //封装提示结果
            ResultForFrontVo result = new ResultForFrontVo();
            result.setResultTo(CommonConstant.RESULT_TO_CUSTOMER);
            result.setResultType(CommonConstant.RESULT_TYPE_TRANSLATED_TEXT);
            result.setTranslatedText(message);
            //发送结果到客户端
            sendMessage(JSON.toJSONString(result));
            //封装提示结果
            ResultForFrontVo result1 = new ResultForFrontVo();
            result1.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            result1.setResultType(CommonConstant.RESULT_TYPE_TRANSLATED_TEXT);
            result1.setTranslatedText(CommonUtils.getSleepReply());

            //发送结果到客户端
            sendMessage(JSON.toJSONString(result1));

            //记录唤醒标志
            wakeUpFlag = false;

        }else if(wakeUpFlag){
            //返回翻译后文字
            //封装提示结果
            ResultForFrontVo result = new ResultForFrontVo();
            result.setResultTo(CommonConstant.RESULT_TO_CUSTOMER);
            result.setResultType(CommonConstant.RESULT_TYPE_TRANSLATED_TEXT);
            result.setTranslatedText(message);
            //发送结果到客户端
            sendMessage(JSON.toJSONString(result));

            //更新唤醒时间
            wakeUpTime = DateUtil.date();

            //调用规则控制器
            ModelInvokingServiceImpl modelInvokingService = applicationContext.getBean(ModelInvokingServiceImpl.class);
            ResultForFrontVo resultToCustomer = modelInvokingService.messageHandleController(this.channelCode, message);

            if (Optional.ofNullable(resultToCustomer).isPresent()) {
                //发送结果到客户端
                sendMessage(JSON.toJSONString(resultToCustomer));
            }
        }


    }


    /**
     * 发送指定客户段消息
     */
    public void sendInfo(String message, @PathParam("clientId") String clientId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(clientId) && webSocketMap.containsKey(clientId)) {
            webSocketMap.get(clientId).sendMessage(message);
        } else {
            log.error("用户" + clientId + ",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }


}

