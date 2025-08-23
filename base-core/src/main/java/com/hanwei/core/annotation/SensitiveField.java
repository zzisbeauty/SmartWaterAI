package com.hanwei.core.annotation;


import com.hanwei.core.common.SensitiveEnum;

import java.lang.annotation.*;

/**
 * @Description:在字段上定义 标识字段存储的信息是敏感的
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SensitiveField {

    /**
     * 不同类型处理不同
     * @return
     */
    SensitiveEnum type() default SensitiveEnum.ENCODE;
}
