package com.hanwei.core.annotation;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [自动注册注解]
 * @createTime : [2025/1/23 10:30]
 * @updateUser : [zht]
 * @updateTime : [2025/1/23 10:30]
 * @updateRemark : [说明本次修改内容]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface AutoRegister {

}
