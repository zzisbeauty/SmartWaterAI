package com.hanwei.core.exception;

import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @version : [v1.0]
 * @description : [全局异常处理]
 * @createTime : [2025/5/13 9:04]
 * @updateUser : [zht]
 * @updateTime : [2025/5/13 9:04]
 * @updateRemark : [说明本次修改内容]
 */
@ControllerAdvice(basePackages = "com.hanwei")
@Slf4j
public class GlobalExceptionHandle {
    /**
     * 全局异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("全局异常捕获 ERROR: ").append(e.getMessage()).append("\n");
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            sb.append(element).append("\n");
        }
        log.error("全局异常捕获 ERROR:" + sb);
        return Result.error(sb.toString());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result exceptionHandler(BindException e) {
        String failMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("绑定异常:{} ",failMsg);
        return Result.error(200,failMsg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result exceptionHandler(MissingServletRequestParameterException e) {
        String failMsg = e.getMessage();
        log.error("参数异常:{} ",failMsg);
        return Result.error(CommonConstant.SC_BAD_REQUEST_400,"参数异常:{} "+failMsg);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseBody
    public Result exceptionHandler(HttpClientErrorException.Unauthorized e) {
        String failMsg = e.getMessage();
        log.error("未授权:{} ",failMsg);
        return Result.error(CommonConstant.SC_UNAUTHORIZED_401,"未授权:{} "+failMsg);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseBody
    public Result exceptionHandler(HttpClientErrorException.Forbidden e) {
        String failMsg = e.getMessage();
        log.error("无权访问:{} ",failMsg);
        return Result.error(CommonConstant.SC_FORBIDDEN_403,"无权访问:{} "+failMsg);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseBody
    public Result exceptionHandler(HttpClientErrorException.NotFound e) {
        String failMsg = e.getMessage();
        log.error("未找到资源:{} ",failMsg);
        return Result.error(CommonConstant.SC_NOT_FOUND_404,"未找到资源:{} "+failMsg);
    }




}
