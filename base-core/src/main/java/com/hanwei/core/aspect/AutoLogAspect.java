package com.hanwei.core.aspect;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.service.BaseCommonService;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.ModuleType;
import com.hanwei.core.common.OperateTypeEnum;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.dto.LogDTO;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.core.util.IpUtils;
import com.hanwei.core.util.SpringContextUtils;
import com.hanwei.core.util.oConvertUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;


import java.lang.reflect.Method;
import java.util.Date;


/**
 * @Description:系统日志，切面处理类
 */
@Aspect
@Component
public class AutoLogAspect {

    @Resource
    private BaseCommonService baseCommonService;

    @Resource
    private CommonAPI commonAPI;

    @Pointcut("@annotation(com.hanwei.core.annotation.AutoLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time, result);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time, Object obj) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogDTO dto = new LogDTO();
        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if(syslog != null){
            //update-begin-author:taoyan date:
            String content = syslog.value();
            if(syslog.module()== ModuleType.ONLINE){
                content = getOnlineLogContent(obj, content);
            }
            //注解上的描述,操作日志内容
            dto.setLogType(syslog.logType());
            dto.setLogContent(content);
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        dto.setMethod(className + "." + methodName + "()");


        //设置操作类型
        if (CommonConstant.LOG_TYPE_2 == dto.getLogType()) {
            dto.setOperateType(getOperateType(methodName, syslog.operateType()));
        }


        //获取request
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //请求的参数
        dto.setRequestParam(getReqestParams(request,joinPoint));
        //设置IP地址
        dto.setIp(IpUtils.getIpAddr(request));
        //获取登录用户信息
        LoginUser sysUser = commonAPI.getLoginUser();
        if(sysUser!=null){
            dto.setUserid(sysUser.getId());
            dto.setUsername(sysUser.getUsername());

        }
        //耗时
        dto.setCostTime(time);
        dto.setCreateTime(new Date());
        //保存系统日志
        baseCommonService.addLog(dto);
    }


    /**
     * 获取操作类型
     */
    private int getOperateType(String methodName,int operateType) {
        if (operateType > 0) {
            return operateType;
        }
        return OperateTypeEnum.getTypeByMethodName(methodName);
    }

    /**
     * @Description: 获取请求参数
     * @author: scott
     * @date: 2020/4/16 0:10
     * @param request:  request
     * @param joinPoint:  joinPoint
     * @Return: java.lang.String
     */
    private String getReqestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        String params = "";
        if (CommonConstant.HTTP_POST.equals(httpMethod) || CommonConstant.HTTP_PUT.equals(httpMethod) || CommonConstant.HTTP_PATCH.equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            // java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
            //  https://my.oschina.net/mengzhang6/blog/2395893
            Object[] arguments  = new Object[paramsArray.length];
            for (int i = 0; i < paramsArray.length; i++) {
                if (paramsArray[i] instanceof BindingResult || paramsArray[i] instanceof ServletRequest || paramsArray[i] instanceof ServletResponse || paramsArray[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }
                arguments[i] = paramsArray[i];
            }
            //for:日志数据太长的直接过滤掉
            PropertyFilter profilter = new PropertyFilter() {
                @Override
                public boolean apply(Object o, String name, Object value) {
                    int length = 500;
                    if(value!=null && value.toString().length()>length){
                        return false;
                    }
                    return true;
                }
            };
            params = JSONObject.toJSONString(arguments, profilter);
            //for:日志数据太长的直接过滤掉
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            StandardReflectionParameterNameDiscoverer u = new StandardReflectionParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }
        }
        return params;
    }

    /**
     * online日志内容拼接
     * @param obj
     * @param content
     * @return
     */
    private String getOnlineLogContent(Object obj, String content){
        if (Result.class.isInstance(obj)){
            Result res = (Result)obj;
            String msg = res.getMessage();
            String tableName = res.getOnlTable();
            if(oConvertUtils.isNotEmpty(tableName)){
                content+=",表名:"+tableName;
            }
            if(res.isSuccess()){
                content+= ","+(oConvertUtils.isEmpty(msg)?"操作成功":msg);
            }else{
                content+= ","+(oConvertUtils.isEmpty(msg)?"操作失败":msg);
            }
        }
        return content;
    }
}
