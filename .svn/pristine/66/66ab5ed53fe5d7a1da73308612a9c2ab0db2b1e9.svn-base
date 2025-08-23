package com.hanwei.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @Description 字典转换注释
 * @author zhuht
 * @Date 2024/1/30 13:27
 * @Since version-1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {
    /**
     * 字典编号
     */
    String dicCode();

    /**
     * 数据test
     */
    String dicText() default "";

    /**
     * 数据字典表
     */
    String dictTable() default "";
}
