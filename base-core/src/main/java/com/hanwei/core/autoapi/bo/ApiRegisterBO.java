package com.hanwei.core.autoapi.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [api注册BO]
 * @createTime : [2025/1/23 15:14]
 */
@Data
public class ApiRegisterBO {
    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称(spring内定义)
     */
    private String appName;

    /**
     * 应用密钥
     */
    private String appSercet;

    /**
     * API分类
     */
    private String apiCategory;

    /**
     * API密钥
     */
    private String apiSecret;

    /**
     * API名称
     */
    private String apiName;

    /**
     * API类型
     */
    private String apiType = "1";

    /**
     * API描述
     */
    private String apiDesc;

    /**
     * 服务协议 HTTP、HTTPS、HTTP 和 HTTPS(默认 HTTP,可手动写)
     */
    private String apiProtocol = "HTTP";

    /**
     * 服务IP
     */
    private String serviceIp;

    /**
     * 服务端口
     */
    private Integer servicePort;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 服务路由
     */
    private String servicePath;

    /**
     * 租户ID
     */
    private String tenementId;

    /**
     * 请求编码方式
     */
    private Integer formEncType;

    /**
     * 服务入参
     */
    private List<Parameter> serviceParams;

    /**
     * 返回类型:JSON、文本、二进制、XML、HTML(手动写)
     */
    private String returnType = "JSON";

    /**
     * 请求时间
     */
    private Integer requestTimeOut = 30000;

    /**
     * 服务返回
     */
    private Class returnResult;

    /**
     * API分类ID
     */
    private String functionId;

    /**
     * 命名空间Id
     */
    private String namespaceId;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 成功返回结果示例
     */
    private java.lang.String resultSuccessexample;
    /**
     * 失败返回结果示例
     */
    private java.lang.String resultFailexample;


}
