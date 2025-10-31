package com.hanwei.process.modelinvoking.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.modelinvoking.service.IModelInvokingService;
import com.hanwei.process.modelrule.service.ICompanyModelHandleService;
import com.hanwei.process.modelrule.service.INoModelHandleService;
import com.hanwei.process.util.AuthenticationUtil;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.TextResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 模型调用信息ServiceImpl
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class ModelInvokingServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IModelInvokingService {
    /**
     * 模型选择 抽取在配置文件中 参数见常量类CommonConstant中MODEL_CONTROLLER_CHOICE配置
     */
    @Value("${model.controller.choice}")
    private String MODEL_CONTROLLER_CHOICE;

    @Value("${DigitalHuman.appId}")
    private String DIGITAL_HUMAN_APPID;

    @Value("${DigitalHuman.appKey}")
    private String DISGITAL_HUMAN_APPKEY;

    @Value("${DigitalHuman.expireTime}")
    private Integer DIGITAL_HUMAN_EXPIRETIME;

    @Resource
    private INoModelHandleService noModelHandleService;
    @Resource
    private ICompanyModelHandleService companyModelHandleService;

    public String getMODEL_CONTROLLER_CHOICE() {
        return MODEL_CONTROLLER_CHOICE;
    }

    public void setMODEL_CONTROLLER_CHOICE(String MODEL_CONTROLLER_CHOICE) {
        this.MODEL_CONTROLLER_CHOICE = MODEL_CONTROLLER_CHOICE;
    }

    /**
     * 模型请求控制器
     * @param message
     * @return
     */
    @Override
    public ResultForFrontVo messageHandleController(String channelCode, String message) {
        // 模型切换命令规则
        ResultForFrontVo changeModelResult = this.changeModel(message);
        if(Optional.ofNullable(changeModelResult).isPresent()){
            return changeModelResult;
        }
        if(Optional.ofNullable(message).isPresent()){
            switch (MODEL_CONTROLLER_CHOICE){ // 处理请求的分支选择
                case CommonConstant.MODEL_CONTROLLER_CHOICE_NO_MODEL:
                    return this.noModelHandleService.noModelHandle(channelCode,message);
                case CommonConstant.MODEL_CONTROLLER_CHOICE_COMPANY:
                    return this.companyModelHandleService.companyModelHandle(channelCode,message);
                default:
                    break;
            }
        }
        int a = 1;
        return null;
    }

    /**
     * 数字人鉴权接口 获取token
     * @return
     */
    @Override
    public String getDigitalHumanToken() {
        String expiredTime = ZonedDateTime.now().plusHours(DIGITAL_HUMAN_EXPIRETIME).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String authorization = DIGITAL_HUMAN_APPID + "/" + AuthenticationUtil.hmacSha256(DISGITAL_HUMAN_APPKEY, DIGITAL_HUMAN_APPID + expiredTime) + "/" + expiredTime;
        return authorization;
    }

    /**
     * 模型切换
     * @param message
     * @return
     */
    @Override
    public ResultForFrontVo changeModel(String message) {
        /**切换模型*/
        if (message.contains("指标大模型")) {
            this.MODEL_CONTROLLER_CHOICE = CommonConstant.MODEL_CONTROLLER_CHOICE_NO_MODEL;
            ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("已成功切换到指标大模型模式");
            textResult.setSpeechText("已成功切换到指标大模型模式");
            resultForFrontVo.setTextResult(textResult);
            return resultForFrontVo;
        }

        if(message.contains("汉威集团大模型")){
            this.MODEL_CONTROLLER_CHOICE = CommonConstant.MODEL_CONTROLLER_CHOICE_COMPANY;
            ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("已成功切换到汉威集团大模型模式");
            textResult.setSpeechText("已成功切换到汉威集团大模型模式");
            resultForFrontVo.setTextResult(textResult);
            return resultForFrontVo;
        }

        /**模型提示*/
        if((message.contains("换") || message.contains("改") || message.contains("变更"))  && message.contains("模型")){
            ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("请选择要切换的模型");
            textResult.setSpeechText("请选择要切换的模型");
            List<String> modelList = new ArrayList<>();
            modelList.add("指标大模型");
            modelList.add("汉威集团大模型");
            textResult.setTextData(modelList);
            resultForFrontVo.setTextResult(textResult);
            return resultForFrontVo;
        }

        /**暂不支持模型*/
        if((message.contains("换") || message.contains("改") || message.contains("变更"))  && (message.contains("文心一言") || message.contains("星火") || message.contains("通义千问")|| message.contains("豆包")|| message.contains("盘古"))){
            ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("小威暂不支持此模型");
            textResult.setSpeechText("小威暂不支持此模型");
            resultForFrontVo.setTextResult(textResult);
            return resultForFrontVo;
        }

        return null;
    }
}
