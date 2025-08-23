package com.hanwei.core.common.api.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *   @Description:网关接口返回数据格式
 */
@Data
public class HttpResult {
    /**
     * 错误消息
     * 当有错误发生的时候 这里不为空，当没有系统错误的时候这里为null
     */
    @JSONField(name = "ErrorMessage")
    @JsonProperty("ErrorMessage")
    private String ErrorMessage;
    /**
     * 默认值为true 返回状态
     */
    @JSONField(name = "Result")
    @JsonProperty("Result")
    private Boolean Result=true;

    /**
     * 返回方法体
     */
    @JSONField(name = "KeyValue")
    @JsonProperty("KeyValue")
    private String KeyValue;
}
