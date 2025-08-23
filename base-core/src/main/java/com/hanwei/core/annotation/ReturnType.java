package com.hanwei.core.annotation;

import com.hanwei.core.common.ApiEnum;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [用于定义返回方法 JSON、文本、二进制、XML、HTML]
 * @createTime : [2025/1/23 10:30]
 * @updateUser : [zht]
 * @updateTime : [2025/1/23 10:30]
 * @updateRemark : [说明本次修改内容]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ReturnType {
    String value() default ApiEnum.JSON;

}
