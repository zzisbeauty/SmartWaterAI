package com.hanwei.core.annotation;

import com.hanwei.core.common.ApiEnum;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [用于定义API调用协议]
 * @createTime : [2025/1/23 10:30]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiProtocol {
    String value() default ApiEnum.HTTP;

}
