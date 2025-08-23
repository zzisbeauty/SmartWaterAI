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
 * @Description: api授权信息
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
@Data
@TableName("sys_api_access")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiAccess对象", description="api授权信息")
public class ApiAccess {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    @NotEmpty(message = "id不能为空")
	private String id;
	/**API密钥*/
    @Schema(description = "API密钥")
    @NotEmpty(message = "API密钥不能为空")
	private String apisecret;
	/**授权应用程序ID*/
    @Schema(description = "授权应用程序ID")
    @NotEmpty(message = "授权应用程序ID不能为空")
	private String appid;
	/**授权应用程序密钥*/
    @Schema(description = "授权应用程序密钥")
    @NotEmpty(message = "授权应用程序密钥不能为空")
	private String appsecret;
	/**授权日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "授权日期")
    @NotEmpty(message = "授权日期不能为空")
	private java.util.Date authorizeDate;
	/**授权人*/
    @Schema(description = "授权人")
    @NotEmpty(message = "授权人不能为空")
	private String authorizeBy;
	/**租户GUID*/
    @Schema(description = "租户GUID")
    @NotEmpty(message = "租户GUID不能为空")
	private String tenementId;
	/**0:测试;1:预发;2:线上*/
    @Schema(description = "0:测试;1:预发;2:线上")
    @NotEmpty(message = "0:测试;1:预发;2:线上不能为空")
	private Integer apiEnvironment;
}
