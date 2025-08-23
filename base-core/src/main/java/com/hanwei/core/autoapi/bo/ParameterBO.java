package com.hanwei.core.autoapi.bo;

import lombok.Data;

import java.lang.reflect.Parameter;
import java.util.List;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [参数BO]
 * @createTime : [2025/1/23 15:14]
 */
@Data
public class ParameterBO {
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 示例
     */
    private String demovalue;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 所属位置
     */
    private String location;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 默认值
     */
    private String defaultvalue;
}
