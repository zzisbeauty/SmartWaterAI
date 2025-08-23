package com.hanwei.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 接口参数信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("api_param_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiParamConfig对象", description="接口参数信息")
public class ApiParamConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

    /**关联接口ID*/
    @Schema(description = "关联接口ID")
    @NotEmpty(message = "关联接口ID不能为空")
    @ApiParameter(name = "apiId", description = "关联接口ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiId;

	/**参数名*/
    @Schema(description = "参数名")
    @NotEmpty(message = "参数名不能为空")
    @ApiParameter(name = "paramName", description = "参数名",location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String paramName;

	/**参数类型*/
    @Schema(description = "参数类型")
    @NotEmpty(message = "参数类型不能为空")
    @ApiParameter(name = "paramType", description = "参数类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String paramType;

	/**参数描述*/
    @Schema(description = "参数描述")
    @NotEmpty(message = "参数描述不能为空")
    @ApiParameter(name = "paramDesc", description = "参数描述", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String paramDesc;

    /**参数位置*/
    @Schema(description = "参数位置")
    @NotEmpty(message = "参数位置不能为空")
    @ApiParameter(name = "paramLocation", description = "参数位置", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String paramLocation;

	/**参数顺序*/
    @Schema(description = "参数顺序")
    @NotNull(message = "参数顺序不能为空")
    @ApiParameter(name = "paramOrder", description = "参数顺序", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer paramOrder;

    /**是否必填*/
    @Schema(description = "是否必填")
    @ApiParameter(name = "required", description = "是否必填", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "yes_no")
    private java.lang.Integer required;

    /**参数示例*/
    @Schema(description = "参数示例")
    @ApiParameter(name = "sample", description = "参数示例", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String sample;

	/**创建人*/
    @Schema(description = "创建人")
	private java.lang.String createBy;

	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
	private java.util.Date createTime;

	/**更新人*/
    @Schema(description = "更新人")
	private java.lang.String updateBy;

	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
	private java.util.Date updateTime;
}
