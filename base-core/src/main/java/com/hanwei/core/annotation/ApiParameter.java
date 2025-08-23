package com.hanwei.core.annotation;

import com.hanwei.core.common.ApiEnum;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [用于定义参数信息]
 * @createTime : [2025/1/23 10:30]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
public @interface ApiParameter {

    /**
     * 名称
     */
    String name() default "";

    /**
     * 描述
     */
    String description() default "";

    /**
     * 示例
     */
    String demovalue() default "";

    /**
     * 是否必填
     */
    boolean required() default false;

    /**
     * 所属位置
     */
    String location() default ApiEnum.PARAMETER_LOCATION_QUERY;

    /**
     * 字段类型
     */
    String type() default ApiEnum.PARAMETER_TYPE_STRING;

    /**
     * 默认值
     */
    String defaultvalue() default "";

}
