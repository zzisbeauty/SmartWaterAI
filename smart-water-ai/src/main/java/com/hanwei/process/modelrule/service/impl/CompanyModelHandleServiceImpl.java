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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 集团研究院模型分配器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class CompanyModelHandleServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo>
        implements ICompanyModelHandleService {

    /**
     * 集团研究院模型规则匹配器
     * @param message
     * @return
     */
    @Override
    public ResultForFrontVo companyModelHandle(String channelCode, String message) {
        log.info("=== 开始处理外部接口调用 ===");
        log.info("输入参数 - channelCode: {}, message: {}", channelCode, message);
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        try {
            // 清理消息前缀
            String cleanMessage = message;
            if (message.startsWith("CMD_TEXT:")) {
                cleanMessage = message.substring("CMD_TEXT:".length());
                log.info("清理消息前缀后: {}", cleanMessage);
            }

            // 1. 生成认证信息
            log.info("=== 步骤1: 生成认证信息 ===");
            long timestamp = System.currentTimeMillis();
            // String username = "Y0126";
            String username = "H05583";
            log.info("认证参数 - username: {}, timestamp: {}", username, timestamp);
            String salt = AuthenticationUtil.encryptToSHA256(username + timestamp);
            String authentication = AuthenticationUtil.encryptToSHA256(username + timestamp + salt);
            log.info("认证结果 - salt: {}, authentication: {}", salt, authentication);

            // 2. 构建请求体
            log.info("=== 步骤2: 构建请求体 ===");
            JSONObject inputs = new JSONObject();
            inputs.put("query", cleanMessage);

            JSONObject payload = new JSONObject();
            payload.put("inputs", inputs);
            payload.put("response_mode", "blocking");
            payload.put("user", username);

            String jsonPayload = payload.toJSONString();
            log.info("请求体JSON: {}", jsonPayload);

            // 3. 创建HTTP客户端
            log.info("=== 步骤3: 创建HTTP客户端 ===");
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(90, TimeUnit.SECONDS)
                    .build();
            log.info("HTTP客户端创建成功，超时设置: connect=30s, read=90s, write=30s, call=90s");

            // 4. 构建请求
            log.info("=== 步骤4: 构建HTTP请求 ===");
            RequestBody body = RequestBody.create(
                    jsonPayload,
                    MediaType.parse("application/json; charset=utf-8")
            );

            String url = "http://ailma.ai-test.xiangyuniot.com/ailma-gw/ailma-workflow/v1/workflows/run";
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("username", username)
                    .addHeader("timestamp", String.valueOf(timestamp))
                    .addHeader("authentication", authentication)
                    .addHeader("Api-Key", "Bearer app-t6fG01UwJZHUrHDxOf0wLUCt")
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            log.info("请求URL: {}", url);
            log.info("请求头信息:");
            log.info("  - username: {}", username);
            log.info("  - timestamp: {}", timestamp);
            log.info("  - authentication: {}", authentication);
            log.info("  - Api-Key: Bearer ... ...");
            log.info("  - Content-Type: application/json");

            // 5. 执行请求
            log.info("=== 步骤5: 执行HTTP请求 ===");
            log.info("开始发送请求到外部接口...");
            long startTime = System.currentTimeMillis();
            try (Response response = client.newCall(request).execute()) {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                log.info("HTTP请求完成，耗时: {}ms", duration);
                String resultStr = response.body().string();
                int statusCode = response.code();

                log.info("=== 步骤6: 处理响应 ===");
                log.info("响应状态码: {}", statusCode);
                log.info("响应头信息: {}", response.headers());
                log.info("响应体长度: {} 字符", resultStr != null ? resultStr.length() : 0);
                log.info("响应体内容: {}", resultStr);

                if (statusCode == 200) {
                    log.info("=== 步骤7: 解析成功响应 ===");
                    try {
                        JSONObject resultJson = JSON.parseObject(resultStr);
                        log.info("JSON解析成功");

                        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                        TextResult textResult = new TextResult();

                        // 解析 data.outputs.text 字段
                        String responseText = "接口调用成功，但未获取到有效响应内容";
                        if (resultJson.containsKey("data")) {
                            log.info("找到data字段");
                            JSONObject data = resultJson.getJSONObject("data");
                            if (data != null && data.containsKey("outputs")) {
                                log.info("找到outputs字段");
                                JSONObject outputs = data.getJSONObject("outputs");
                                if (outputs != null && outputs.containsKey("text")) {
                                    responseText = outputs.getString("text");
                                    log.info("成功提取text字段: {}", responseText);
                                } else {
                                    log.warn("outputs中没有text字段，outputs内容: {}", outputs);
                                }
                            } else {
                                log.warn("data中没有outputs字段，data内容: {}", data);
                            }
                        } else {
                            log.warn("响应中没有data字段");
                        }
                        textResult.setText(responseText);
                        textResult.setSpeechText(responseText);
                        resultForFrontVo.setTextResult(textResult);
                        log.info("成功构建返回结果");
                    } catch (Exception parseException) {
                        log.error("JSON解析失败", parseException);
                        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                        TextResult textResult = new TextResult();
                        textResult.setText("接口调用成功，但响应格式异常: " + resultStr);
                        resultForFrontVo.setTextResult(textResult);
                    }
                } else {
                    log.error("=== HTTP请求失败 ===");
                    log.error("状态码: {}, 响应: {}", statusCode, resultStr);
                    resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
                    resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
                    TextResult textResult = new TextResult();
                    textResult.setText("外部接口调用失败，状态码: " + statusCode + ", 响应: " + resultStr);
                    resultForFrontVo.setTextResult(textResult);
                }
            }
        } catch (Exception e) {
            log.error("=== 调用外部接口发生异常 ===", e);
            log.error("异常类型: {}", e.getClass().getSimpleName());
            log.error("异常消息: {}", e.getMessage());
            log.error("异常堆栈:", e);
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("接口调用异常: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            resultForFrontVo.setTextResult(textResult);
        }

        // === 新增的关键检查点代码 ===
        log.info("=== 步骤8: 返回对象完整性检查 ===");

        // 检查返回对象的基本属性
        log.info("验证返回对象 - resultTo: {}, resultType: {}",
                resultForFrontVo.getResultTo(), resultForFrontVo.getResultType());
        log.info("textResult是否为null: {}", resultForFrontVo.getTextResult() == null);

        if (resultForFrontVo.getTextResult() != null) {
            log.info("textResult内容 - text: {}, speechText: {}",
                    resultForFrontVo.getTextResult().getText(),
                    resultForFrontVo.getTextResult().getSpeechText());
        }

        // 测试JSON序列化
        log.info("=== 步骤9: JSON序列化测试 ===");
        try {
            String jsonTest = JSON.toJSONString(resultForFrontVo);
            log.info("JSON序列化测试成功，长度: {} 字符", jsonTest.length());
            log.info("序列化后的JSON: {}", jsonTest);

            // 验证序列化后的JSON是否包含必要字段
            if (jsonTest.contains("\"resultTo\"") && jsonTest.contains("\"resultType\"") && jsonTest.contains("\"textResult\"")) {
                log.info("JSON包含所有必要字段");
            } else {
                log.warn("JSON缺少必要字段");
            }

        } catch (Exception jsonEx) {
            log.error("JSON序列化失败", jsonEx);
            log.error("序列化失败的对象状态:");
            log.error("  - resultTo: {}", resultForFrontVo.getResultTo());
            log.error("  - resultType: {}", resultForFrontVo.getResultType());
            log.error("  - textResult: {}", resultForFrontVo.getTextResult());

            // 如果序列化失败，创建一个简单的返回对象
            resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("返回对象序列化失败: " + jsonEx.getMessage());
            resultForFrontVo.setTextResult(textResult);
            log.info("已创建备用返回对象");
        }

        // 最终验证
        log.info("=== 步骤10: 最终返回验证 ===");
        boolean isValidReturn = resultForFrontVo != null &&
                resultForFrontVo.getResultTo() != null &&
                resultForFrontVo.getResultType() != null &&
                resultForFrontVo.getTextResult() != null;
        log.info("返回对象有效性: {}", isValidReturn);
        if (!isValidReturn) {
            log.error("返回对象无效，强制创建有效对象");
            resultForFrontVo = new ResultForFrontVo();
            resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
            resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
            TextResult textResult = new TextResult();
            textResult.setText("系统内部错误，返回对象构建失败");
            resultForFrontVo.setTextResult(textResult);
        }
        log.info("=== 外部接口调用处理完成 ===");
        log.info("最终返回结果类型: {}", resultForFrontVo.getResultType());
        log.info("最终返回结果内容: {}", resultForFrontVo.getTextResult() != null ? resultForFrontVo.getTextResult().getText() : "null");
        return resultForFrontVo;
    }
}
