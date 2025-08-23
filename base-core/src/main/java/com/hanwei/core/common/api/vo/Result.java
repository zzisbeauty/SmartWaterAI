package com.hanwei.core.common.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwei.core.common.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

/**
 *   @Description:接口返回数据格式
 */
@Data
@Schema(title="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	/**
	 * 成功标志
	 */
	@Schema(description = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@Schema(description = "返回处理消息")
	private String message = "";

	/**
	 * 返回代码
	 */
	@Schema(description = "返回代码")
	private Integer code = 0;

	/**
	 * 返回数据对象 data
	 */
	@Schema(description = "返回数据对象")
	private T result;

	/**
	 * 时间戳
	 */
	@Schema(description = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public Result() {
	}

    /**
     * 兼容VUE3版token失效不跳转登录页面
     * @param code
     * @param message
     */
	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result<T> success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
		return this;
	}

	public static<T> Result<T> ok() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		return r;
	}

	public static<T> Result<T> ok(String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		//Result OK(String msg)方法会造成兼容性问题 issues/I4IP3D
		r.setResult((T) msg);
		r.setMessage(msg);
		return r;
	}

	public static<T> Result<T> ok(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK() {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		return r;
	}

	/**
	 * 此方法是为了兼容升级所创建
	 *
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static<T> Result<T> OK(String msg) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		//Result OK(String msg)方法会造成兼容性问题 issues/I4IP3D
		r.setResult((T) msg);
		return r;
	}

	public static<T> Result<T> OK(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> OK(String msg, T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> error(String msg, T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(false);
		r.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
		r.setMessage(msg);
		r.setResult(data);
		return r;
	}

	public static<T> Result<T> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}

	/**
	 * 创建一个错误的结果对象
	 * 该方法用于当操作失败时，返回错误信息和对应的错误码
	 *
	 * @param code 错误码，表示具体的错误类型
	 * @param msg 错误信息，描述错误的详细情况
	 * @return 返回一个包含错误信息的Result对象
	 */
	public static<T> Result<T> error(int code, String msg) {
	    Result<T> r = new Result<T>();
	    r.setCode(code);
	    r.setMessage(msg);
	    r.setSuccess(false);
	    return r;
	}

	public Result<T> error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
		return this;
	}

	/**
	 * 无权限访问返回结果
	 */
	public static<T> Result<T> noauth(String msg) {
		return error(CommonConstant.SC_NO_AUTHZ_510, msg);
	}

	@JsonIgnore
	private String onlTable;

}
