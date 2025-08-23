package com.hanwei.core.autoapi.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Description:
 * @Date: 2025/03/31/8:56
 */
@Data
public class ApiInfoVoCore {

    private String id;          //  主键ID

    private String apiId;       // APiSIX中的APIID

    private String assortId;   // API分类ID

    private String name;            // API 名称

    private String apiSecret;   // API密匙

    private String apiType;         // API类型：1公开 0私有（预留）

    private String descInfo;        // API描述

    private String method;      // 请求方式

    private String requestAgreement;  // 请求协议 HTTP HTTPS

    private Integer isCheck;    // 是否校验 0 校验 1 不校验

    private String url;         // 真实请求路径

    private Integer state;      // API状态 0 未发布 1 已发布 2  已修改未发布

    private String serviceType;  // 服务端请求方式 0 常规模式 1 集群服务方式 2 选择服务 3 选择统一规则

    private String servicePath;     // 后端请求路径

    private JSONArray requestParameter;    // 请求参数

    private Integer parameterType;           // 1: form-data 2: x-www-form-urlencoded 3: json

    private JSONArray queryParameter;          // query 参数

    private JSONArray headerParameter;          // header 参数

    private JSONObject serviceParameter;     // 预留: 前后端映射参数

    private JSONArray constantParameter;   // 常量参数参数

    private JSONArray resultNotes; // 返回参数注释json

    private JSONArray customParameter; // 自定义系统参数

    private String resultType;          // 返回类型

    private JSONObject plugins;             // 策略配置信息

    private String versionNum;              // 版本号

    private Integer delFlag = 0;        // 删除标记  0 未删除 1已删除

    private Integer tenementGuid;       // 租户ID

}
