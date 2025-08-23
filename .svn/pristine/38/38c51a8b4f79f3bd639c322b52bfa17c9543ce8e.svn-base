package com.hanwei.core.annotation;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [用于定义API失败返回]
 * @createTime : [2025/1/23 10:30]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApiFailReturn {
    String message() default "";
    String returnJson() default "";
}
