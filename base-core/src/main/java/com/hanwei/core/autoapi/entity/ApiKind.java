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
 * @Description: Api分类信息
 * @Author: hanwei
 * @Date:   2025-03-09
 * @Version: V1.0
 */
@Data
@TableName("sys_api_kind")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiKind对象", description="Api分类信息")
public class ApiKind {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    @NotEmpty(message = "id不能为空")
	private String id;
	/**功能名称*/
    @Schema(description = "功能名称")
    @NotEmpty(message = "功能名称不能为空")
	private String kindName;
	/**层级编码*/
    @Schema(description = "层级编码")
    @NotEmpty(message = "层级编码不能为空")
	private String hierarchyCode;
	/**层级数*/
    @Schema(description = "层级数")
    @NotEmpty(message = "层级数不能为空")
	private Integer kindLevel;
	/**应用系统*/
    @Schema(description = "应用系统")
    @NotEmpty(message = "应用系统不能为空")
	private String applicationId;
	/**父级ID*/
    @Schema(description = "父级ID")
    @NotEmpty(message = "父级ID不能为空")
	private String parentKindId;
	/**说明*/
    @Schema(description = "说明")
    @NotEmpty(message = "说明不能为空")
	private String comments;
	/**appid*/
    @Schema(description = "appid")
    @NotEmpty(message = "appid不能为空")
	private String appid;
	/**app密钥*/
    @Schema(description = "app密钥")
    @NotEmpty(message = "app密钥不能为空")
	private String appsecret;
	/**租户GUID*/
    @Schema(description = "租户GUID")
    @NotEmpty(message = "租户GUID不能为空")
	private String tenementId;
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
}
