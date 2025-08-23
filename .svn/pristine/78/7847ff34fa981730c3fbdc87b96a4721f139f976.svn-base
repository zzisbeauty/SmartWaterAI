package com.hanwei.core.autoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: api基本信息
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
@Data
@TableName("sys_api")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiInfo对象", description="api基本信息")
public class ApiInfo {

	/**APIid*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "APIid")
    @NotEmpty(message = "APIid不能为空")
	private String id;
	/**应用GUID*/
    @Schema(description = "应用GUID")
    @NotEmpty(message = "应用GUID不能为空")
	private String applicationId;
	/**API名称*/
    @Schema(description = "API名称")
    @NotEmpty(message = "API名称不能为空")
	private String apiName;
	/**API类型（1:公开，0:私有）*/
    @Schema(description = "API类型（1:公开，0:私有）")
    @NotEmpty(message = "API类型（1:公开，0:私有）不能为空")
	private String apiType;
	/**API描述*/
    @Schema(description = "API描述")
    @NotEmpty(message = "API描述不能为空")
	private String apiDescription;
	/**请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）*/
    @Schema(description = "请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）")
    @NotEmpty(message = "请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）不能为空")
	private String requestProtocol;
	/**API密钥*/
    @Schema(description = "API密钥")
    @NotEmpty(message = "API密钥不能为空")
	private String apisecret;
	/**请求参数json*/
    @Schema(description = "请求参数json")
    @NotEmpty(message = "请求参数json不能为空")
	private String requestParameter;
	/**后端服务地址*/
    @Schema(description = "后端服务地址")
    @NotEmpty(message = "后端服务地址不能为空")
	private String serviceHost;
	/**后端路径*/
    @Schema(description = "后端路径")
    @NotEmpty(message = "后端路径不能为空")
	private String servicePath;
	/**后端请求方式*/
    @Schema(description = "后端请求方式")
    @NotEmpty(message = "后端请求方式不能为空")
	private String serviceMethod;
	/**后端请求时长*/
    @Schema(description = "后端请求时长")
    @NotEmpty(message = "后端请求时长不能为空")
	private Integer serviceRequesttime;
	/**前后端映射参数*/
    @Schema(description = "前后端映射参数")
    @NotEmpty(message = "前后端映射参数不能为空")
	private String serviceParameter;
	/**常量参数参数*/
    @Schema(description = "常量参数参数")
    @NotEmpty(message = "常量参数参数不能为空")
	private String constantParameter;
	/**自定义系统参数*/
    @Schema(description = "自定义系统参数")
    @NotEmpty(message = "自定义系统参数不能为空")
	private String customParameter;
	/**返回类型*/
    @Schema(description = "返回类型")
    @NotEmpty(message = "返回类型不能为空")
	private String resultType;
	/**返回参数注释json*/
    @Schema(description = "返回参数注释json")
    @NotEmpty(message = "返回参数注释json不能为空")
	private String resultNotes;
	/**状态*/
    @Schema(description = "状态")
    @NotEmpty(message = "状态 不能为空")
	private java.lang.String state;
	/**服务端请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）*/
    @Schema(description = "服务端请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）")
    @NotEmpty(message = "服务端请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）不能为空")
	private java.lang.String serviceProtocol;
	/**前端请求方式*/
    @Schema(description = "前端请求方式")
    @NotEmpty(message = "前端请求方式不能为空")
	private java.lang.String requestMethod;
	/**成功返回结果示例*/
    @Schema(description = "成功返回结果示例")
    @NotEmpty(message = "成功返回结果示例不能为空")
	private java.lang.String resultSuccessexample;
	/**失败返回结果示例*/
    @Schema(description = "失败返回结果示例")
    @NotEmpty(message = "失败返回结果示例不能为空")
	private java.lang.String resultFailexample;
	/**请求enctype.0:form-data;1:x-www-form-urlencoded;2:raw;3:binary*/
    @Schema(description = "请求enctype.0:form-data;1:x-www-form-urlencoded;2:raw;3:binary")
    @NotEmpty(message = "请求enctype.0:form-data;1:x-www-form-urlencoded;2:raw;3:binary不能为空")
	private java.lang.String requestEnctype;
	/**post请求body描述*/
    @Schema(description = "post请求body描述")
    @NotEmpty(message = "post请求body描述不能为空")
	private java.lang.String bodyDescreption;
	/**api分类 （功能guid）*/
    @Schema(description = "api分类 （功能guid）")
    @NotEmpty(message = "api分类 （功能guid）不能为空")
	private java.lang.String functionId;
	/**appid*/
    @Schema(description = "appid")
    @NotEmpty(message = "appid不能为空")
	private java.lang.String appid;
	/**app密钥*/
    @Schema(description = "app密钥")
    @NotEmpty(message = "app密钥不能为空")
	private java.lang.String appsecret;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "创建时间")
    @NotEmpty(message = "创建时间不能为空")
	private java.util.Date createTime;
	/**创建人*/
    @Schema(description = "创建人")
    @NotEmpty(message = "创建人不能为空")
	private String createBy;
	/**修改时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "修改时间")
    @NotEmpty(message = "修改时间不能为空")
	private java.util.Date modifieTime;
	/**修改人*/
    @Schema(description = "修改人")
    @NotEmpty(message = "修改人不能为空")
	private String modifieBy;
	/**租户GUID*/
    @Schema(description = "租户GUID")
    @NotEmpty(message = "租户GUID不能为空")
	private String tenementId;
}
