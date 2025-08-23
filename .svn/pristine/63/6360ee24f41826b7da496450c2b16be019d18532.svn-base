package com.hanwei.core.mybatis;

import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.util.APIHelper;
import com.hanwei.core.util.oConvertUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;


/**
 * @author
 * @Description mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @Date 2024/1/18 16:06
 * @Since version-1.0
 */
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {
	@Resource
	private CommonAPI commonAPI;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		log.debug("------sqlId------" + sqlId);
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		log.debug("------sqlCommandType------" + sqlCommandType);

		if (parameter == null) {
			return invocation.proceed();
		}
		if (SqlCommandType.INSERT == sqlCommandType) {
			LoginUser sysUser = commonAPI.getLoginUser();
			Field[] fields = oConvertUtils.getAllFields(parameter);
			for (Field field : fields) {
				log.debug("------field.name------" + field.getName());
				try {
					if ("createBy".equals(field.getName())) {
						field.setAccessible(true);
						Object localCreateBy = field.get(parameter);
						field.setAccessible(false);
						if (localCreateBy == null || "".equals(localCreateBy)) {
							if (sysUser != null) {
								// 登录人账号
								field.setAccessible(true);
								field.set(parameter, sysUser.getUsername());
								field.setAccessible(false);
							}
						}
					}
					// 注入创建时间
					if ("createTime".equals(field.getName())) {
						field.setAccessible(true);
						Object localCreateDate = field.get(parameter);
						field.setAccessible(false);
						if (localCreateDate == null || "".equals(localCreateDate)) {
							field.setAccessible(true);
							field.set(parameter, new Date());
							field.setAccessible(false);
						}
					}
					if ("modifieBy".equals(field.getName())) {
						field.setAccessible(true);
						Object localCreateBy = field.get(parameter);
						field.setAccessible(false);
						if (localCreateBy == null || "".equals(localCreateBy)) {
							if (sysUser != null) {
								// 登录人账号
								field.setAccessible(true);
								field.set(parameter, sysUser.getUsername());
								field.setAccessible(false);
							}
						}
					}
					// 注入创建时间
					if ("modifieTime".equals(field.getName())) {
						field.setAccessible(true);
						Object localCreateDate = field.get(parameter);
						field.setAccessible(false);
						if (localCreateDate == null || "".equals(localCreateDate)) {
							field.setAccessible(true);
							field.set(parameter, new Date());
							field.setAccessible(false);
						}
					}

					// 注入租户id
					if ("tenementId".equals(field.getName())) {
						field.setAccessible(true);
						Object localtenementguid = field.get(parameter);
						field.setAccessible(false);
						if (localtenementguid == null || "".equals(localtenementguid)) {
							field.setAccessible(true);
							field.set(parameter,sysUser.getTenementguid());
							field.setAccessible(false);
						}
					}
				} catch (Exception e) {
				}
			}
		}
		if (SqlCommandType.UPDATE == sqlCommandType) {
			LoginUser sysUser = commonAPI.getLoginUser();
			Field[] fields = null;
			if (parameter instanceof ParamMap) {
				ParamMap<?> p = (ParamMap<?>) parameter;

                String et = "et";
				if (p.containsKey(et)) {
					parameter = p.get(et);
				} else {
					parameter = p.get("param1");
				}

				if (parameter == null) {
					return invocation.proceed();
				}

				fields = oConvertUtils.getAllFields(parameter);
			} else {
				fields = oConvertUtils.getAllFields(parameter);
			}

			for (Field field : fields) {
				log.debug("------field.name------" + field.getName());
				try {
					if ("updateBy".equals(field.getName())) {
						//获取登录用户信息
						if (sysUser != null) {
							// 登录账号
							field.setAccessible(true);
							field.set(parameter, sysUser.getUsername());
							field.setAccessible(false);
						}
					}
					if ("updateTime".equals(field.getName())) {
						field.setAccessible(true);
						field.set(parameter, new Date());
						field.setAccessible(false);
					}
//					if ("tenementId".equals(field.getName())) {
//						field.setAccessible(true);
//						field.set(parameter, sysUser.getTenementguid());
//						field.setAccessible(false);
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}


}
