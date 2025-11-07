package com.hanwei.process.server.util;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/10/28 10:50]
 * @updateUser : [CX]
 * @updateTime : [2024/10/28 10:50]
 * @updateRemark : [说明本次修改内容]
 *
 */

import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.server.server.WebSocketServer;
import com.huawei.sis.bean.*;
import com.huawei.sis.bean.base.RasrSentence;
import com.huawei.sis.bean.request.RasrRequest;
import com.huawei.sis.bean.response.RasrResponse;
import com.huawei.sis.bean.response.StateResponse;
import com.huawei.sis.client.RasrClient;
import com.huawei.sis.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 实时语音识别工具类
 *
 * @author CX
 */
@Slf4j
@Component
public class RasrSisUtil {

    /**默认头部静音时间，单位ms*/
    private static final int DEFAULT_HEAD_SILENCE_TIME = 1000;
    /**默认尾部静音时间，单位*/
    private static final int DEFAULT_TAIL_SILENCE_TIME = 1000;
     /**默认连续语音持续时间(1-60)，单位秒*/
    private static final int DEFAULT_CONTINUE_SECONDS = 15;

    private WebSocketServer webSocketServer;

    public RasrSisUtil(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    /**认证用的ak和sk硬编码到代码中或者明文存储都有很大的安全风险，建议在配置文件或者环境变量中密文存放，使用时解密，确保安全*/
    private static String ak;
    @Value("${sis.ak}")
    public void setAk(String ak){
        RasrSisUtil.ak = ak;
    }

    private static String sk;
    @Value("${sis.sk}")
    public void setSk(String sk){
        RasrSisUtil.sk = sk;
    }
    /**区域*/
    private static String region;
    @Value("${sis.region}")
    public void setRegion(String region){
        RasrSisUtil.region = region;
    }
    /**项目id 登录管理控制台，鼠标移动到右上角的用户名上，在下拉列表中选择我的凭证，在项目列表中查看项目id。多项目时，展开“所属区域”，从“项目ID”列获取子项目ID*/
    private static String projectId;
    @Value("${sis.projectId}")
    public void setProjectId(String projectId){
        RasrSisUtil.projectId = projectId;
    }
    /**音频格式，如pcm16k16bit*/
    private static String audioFormat;
    @Value("${sis.audioFormat}")
    public void setAudioFormat(String audioFormat){
        RasrSisUtil.audioFormat = audioFormat;
    }
    /**属性字符串，language_sampleRate_domain，16模型推荐使用chinese_16k_general*/
    private static String property;
    @Value("${sis.property}")
    public void setProperty(String property){
        RasrSisUtil.property = property;
    }

    /**热词表ID*/
    private static String vocabularyId;
    @Value("${sis.vocabularyId}")
    public void setVocabularyId(String vocabularyId){
        RasrSisUtil.vocabularyId = vocabularyId;
    }

    /**
     * 实时语音识别参数设置，所有参数设置均为可选，均有默认值。用户根据需求设置参数。
     *
     * @param request request请求，包含各种参数
     */
    private void setParameters(RasrRequest request) {

        // 1. 设置是否添加标点符号，yes 或 no， 默认"no"
        request.setAddPunc("no");
        // 2. 设置头部的最大静音时间，[0,60000], 默认10000ms
        request.setVadHead(DEFAULT_HEAD_SILENCE_TIME);
        // 3. 设置尾部最大静音时间，[0, 3000], 默认500ms，
        request.setVadTail(DEFAULT_TAIL_SILENCE_TIME);
        // 4. 设置最长持续时间，仅在continue-stream，sentence-stream模式下起作用，[1, 60], 默认30s
        request.setMaxSeconds(DEFAULT_CONTINUE_SECONDS);
        // 5. 设置是否显示中间结果，yes或no，默认“no”。例如分3次发送音频，选择no结果一次性返回，选择yes分三次返回。
        request.setIntermediateResult("no");
        // 6. 设置热词表id, 若没有则设置，否则会报错。
         request.setVocabularyId(vocabularyId);
        // 7. 设置是否将音频中数字转写为阿拉伯数字，yes or no，默认yes
        request.setDigitNorm("yes");
    }

    /**
     * 定义config，所有参数可选，设置超时时间等。
     *
     * @return SisConfig
     */
    private SisConfig getConfig() {
        SisConfig config = new SisConfig();
        // 设置连接超时，默认10000ms
        config.setConnectionTimeout(SisConstant.DEFAULT_CONNECTION_TIMEOUT);
        // 设置读取超时，默认10000ms
        config.setReadTimeout(SisConstant.DEFAULT_READ_TIMEOUT);
        // 设置pingInterval，默认5000ms，当并发较大时，建议把此值设置大一些。如果不需要ping，可设置为-1
        // config.setPingInterval(-1);
        // 设置代理, 一定要确保代理可用才启动此设置。 代理初始化也可用不加密的代理，new ProxyHostInfo(host, port);
        // ProxyHostInfo proxy = new ProxyHostInfo(host, port, username, password);
        // config.setProxy(proxy);
        return config;
    }


    /**
     * 获取监听器，监听器的监听函数。
     *
     * @return RasrListener，用于监听实时语音识别的开始、识别结果、结束以及失败响应
     */
    private RasrListener getRasrListener() {
        RasrListener rasrListener = new RasrListener() {
            @Override
            /**
             * 连接成功回调
             */
            public void onTranscriptionConnect() {
                log.info("websocket connected");
            }

            @Override
            /**
             * 断开连接回调
             */
            public void onTranscriptionClose() {
                log.info("websocket closed");
            }

            @Override
            /**
             * 响应结果回调
             */
            public void onTranscriptionResponse(RasrResponse response) {
                try {
                    printResponse(response);
                    String result = "";
                    response.getSentenceList().get(0).getResult().getText();
                    for(RasrSentence rasrSentence : response.getSentenceList()) {
                        result += rasrSentence.getResult().getText();
                    }
                    webSocketServer.handleMessage(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            /**
             * 识别开始回调
             */
            public void onTranscriptionBegin(StateResponse response) {
                printResponse(response);
            }

            @Override
            /**
             * 识别结束回调
             */
            public void onSTranscriptionEnd(StateResponse response) {
                printResponse(response);
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            /**
             * 识别出错回调
             */
            public void onTranscriptionFail(StateResponse response) {
                printResponse(response);
            }

            @Override
            public void onVoiceStart() {
                RasrEventListener.log.info("voice start event");
                System.out.println("voice start event");

            }

            @Override
            public void onVoiceEnd() {
                RasrEventListener.log.info("voice end event");
                System.out.println("voice end event");
            }

            @Override
            public void onExcceededSilence() {
                RasrEventListener.log.error("exceeded silence event");
            }

            @Override
            public void onExceededAudio() {
                RasrEventListener.log.error("exceeded audio event");
            }

            @Override
            public void onEvent(String event) {
                RasrEventListener.log.warn("receive event {}", event);
                System.out.println("receive event {"+event+"}");
            }
        };
        return rasrListener;
    }


    private void printResponse(Object response) {
        try {
            System.out.println(JsonUtils.obj2Str(response, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化RasrClient
     * @param type 1 流式一句话模式 2 实时语音识别单句模式 3 实时语音识别连续模式
     * @return
     */
    public RasrClient initRasrClient(String type){
        // 1. 实现监听器接口RasrListener，用户自定义收到响应的处理逻辑。
        RasrListener rasrListener = getRasrListener();
        // 2. 初始化RasrClient
        AuthInfo authInfo = new AuthInfo(ak, sk, region, projectId);
        RasrClient rasrClient = new RasrClient(authInfo, rasrListener, getConfig());

        try {

            // 3. 配置参数
            // audioFormat为支持格式、property为属性字符串
            RasrRequest request = new RasrRequest(audioFormat, property);
            setParameters(request);

            if (CommonConstant.SIS_SHORT_STREAM.equals(type)) {
                // 选择1 流式一句话连接
                rasrClient.shortStreamConnect(request);
            } else if (CommonConstant.SIS_SENTENCE_STREAM.equals(type)) {
                // 选择2，实时语音识别单句模式
                rasrClient.sentenceStreamConnect(request);
            } else if (CommonConstant.SIS_CONTINUE_STREAM.equals(type)) {
                // 选择3，实时语音识别连续模式
                rasrClient.continueStreamConnect(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return rasrClient;
    }

    public static void main(String[] args) {

    }
}

