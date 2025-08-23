package com.hanwei.core.annotation;





import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.ModuleType;

import java.lang.annotation.*;

/**
 * @Description:系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

	/**
	 * 日志内容
	 *
	 * @return
	 */
	String value() default "";

	/**
	 * 日志类型
	 *
	 * @return 1:登录日志;2:操作日志;
	 */
	int logType() default CommonConstant.LOG_TYPE_2;

	/**
	 * 操作日志类型
	 *
	 * @return （1查询，2添加，3修改，4删除，5导入，6导出）
	 */
	int operateType() default 0;

	/**
	 * 模块类型 默认为common
	 * @return
	 */
	ModuleType module() default ModuleType.COMMON;
}
