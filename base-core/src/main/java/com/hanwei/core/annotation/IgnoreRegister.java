package com.hanwei.core.annotation;

import java.lang.annotation.*;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [用于类中需要忽略的方法上]
 * @createTime : [2025/1/23 10:30]
 * @updateUser : [zht]
 * @updateTime : [2025/1/23 10:30]
 * @updateRemark : [说明本次修改内容]
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IgnoreRegister {

}
