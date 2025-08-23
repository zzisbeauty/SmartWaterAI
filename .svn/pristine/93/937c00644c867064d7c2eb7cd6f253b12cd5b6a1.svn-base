package com.hanwei.process.modelrule.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.modelrule.service.ICompanyModelHandleService;
import com.hanwei.process.util.AuthenticationUtil;
import com.hanwei.process.util.CommonUtils;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.TextResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * @Description: 集团研究院模型分配器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class CompanyModelHandleServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements ICompanyModelHandleService {

    /**
     * 集团研究院模型规则匹配器
     * @param message
     * @return
     */
    @Override
    public ResultForFrontVo companyModelHandle(String channelCode, String message) {
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        // 获取鉴权token 后期增加redis缓存 目前有效期10分钟
        long timestamp = System.currentTimeMillis();
        String userName = "H05583";
        String tmpToken = "";
        try {
            String salt= AuthenticationUtil.encryptToSHA256(userName + timestamp );
            tmpToken = AuthenticationUtil.encryptToSHA256(userName + timestamp + salt);
        } catch (NoSuchAlgorithmException e) {
            log.error("集团研究院模型鉴权失败",e.getMessage());
            e.printStackTrace();
        }
        if(StrUtil.isEmpty(tmpToken)){
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("集团研究院模型鉴权失败,请联系管理员");
            resultForFrontVo.setTextResult(textResult);
            return resultForFrontVo;
        }

        log.info("集团研究院模型鉴权成功" );


        // 调用集团研究院模型服务
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", message);
        jsonObject.put("conversation_id", RandomUtil.randomString(30));
        jsonObject.put("is_agent", true);
        jsonObject.put("stream", false);
        HttpResponse httpResponse = HttpUtil.createPost("http://www-k8s.xiangyuniot.com/ailma-gw/ailma-agent/agent_chat")
                .header("Authentication", tmpToken)
                .header("Username", userName)
                .header("Timestamp", String.valueOf(timestamp))
                .body(jsonObject.toJSONString())
                .execute();

        // 处理返回结果
        String resultStr = httpResponse.body();
        JSONObject resultJson = JSON.parseObject(resultStr);
        String code = resultJson.getString("code");
        if (StrUtil.isEmpty(code) || !"200".equals(code)) {
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("集团研究院模型调用失败,请联系管理员");
            resultForFrontVo.setTextResult(textResult);
            log.error("集团研究院模型调用失败,请联系管理员 调用结果" + resultStr);
            return resultForFrontVo;
        }

        JSONArray array = resultJson.getJSONArray("data");


        //结果
        for(Object object : array){
           if(Optional.ofNullable(object).isPresent()){
               JSONObject resObject = (JSONObject) object;
               log.info("集团研究院模型调用成功 返回:" +resObject);
               if("CALL_RESULT".equals(resObject.get("res_type"))){
                   String trans = resObject.getString("content").replace("\\\"","\"");
                   resObject.put("content", trans);
                   if(isJson(resObject.getString("content"))){
                       resultForFrontVo = JSON.toJavaObject(resObject.getJSONObject("content"), ResultForFrontVo.class);
                   }else{
                       if(StrUtil.isEmpty(resObject.getString("content"))){
                           break;
                       }
                       resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                       resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                       TextResult textResult = new TextResult();
                       textResult.setText(resObject.getString("content"));
                       resultForFrontVo.setTextResult(textResult);
                   }

                   return resultForFrontVo;
               }
           }
        }

        //用户提示
        for(Object object : array){
            if(Optional.ofNullable(object).isPresent()){
                JSONObject resObject = (JSONObject) object;
                log.info("集团研究院模型调用成功 返回:" +resObject);
                if("USER_ROMPT".equals(resObject.get("res_type"))){
                    if(StrUtil.isEmpty(resObject.getString("content"))){
                        break;
                    }
                    resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                    resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                    TextResult textResult = new TextResult();
                    textResult.setText(resObject.getString("content"));
                    resultForFrontVo.setTextResult(textResult);
                    return resultForFrontVo;
                }
            }
        }

        //结束
        for(Object object : array){
            if(Optional.ofNullable(object).isPresent()){
                JSONObject resObject = (JSONObject) object;
                log.info("集团研究院模型调用成功 返回:" +resObject);
                if("FINISH".equals(resObject.get("res_type"))){
                    if(StrUtil.isEmpty(resObject.getString("content"))){
                        break;
                    }
                    resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                    resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                    TextResult textResult = new TextResult();
                    textResult.setText(resObject.getString("content"));
                    resultForFrontVo.setTextResult(textResult);
                    return resultForFrontVo;
                }
            }
        }

        //无结果
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
        TextResult textResult = new TextResult();
        String reply = CommonUtils.getNoAnswerReply();
        textResult.setText(reply);
        textResult.setSpeechText(reply);
        resultForFrontVo.setTextResult(textResult);
        return resultForFrontVo;
    }


    private Boolean isJson(String message){
        try {
            JSON.parseObject(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
