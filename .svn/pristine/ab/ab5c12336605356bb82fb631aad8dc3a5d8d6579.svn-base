package com.hanwei.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 接口关联问题信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("api_relation_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiRelationConfig对象", description="接口关联问题信息")
public class ApiRelationConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

	/**接口Id*/
    @Schema(description = "接口Id")
    @NotEmpty(message = "接口Id不能为空")
	@ApiParameter(name = "apiId", description = "接口Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String apiId;

	/**提问语id*/
    @Schema(description = "提问语id")
	@ApiParameter(name = "questionId", description = "提问语id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String questionId;

	/**是否系统内api*/
    @Schema(description = "是否系统内api")
    @NotEmpty(message = "是否系统内api不能为空")
    @Dict(dicCode = "yes_no")
	@ApiParameter(name = "isSystem", description = "是否系统内api", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String isSystem;

	/**提问内容*/
    @Schema(description = "提问内容")
    @NotEmpty(message = "提问内容不能为空")
	@ApiParameter(name = "questionContent", description = "提问内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String questionContent;

	/**
	 * 接口名称
	 */
	@TableField(exist = false)
	private java.lang.String apiName;

	/**
	 * 所属分类
	 */
	@TableField(exist = false)
	@Dict(dicCode = "api_categray")
	private java.lang.String categoryId;

	/**
	 * 业务系统编码
	 */
	@TableField(exist = false)
	@Dict(dicCode = "business_code")
	private java.lang.String businessSystemCode;

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
	private java.util.Date updateTime;
}
