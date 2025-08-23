package com.hanwei.digitalhuman.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 数字人配置实例
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Data
@TableName("digital_human_service_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ServiceConfig对象", description="数字人配置实例")
@ExcelIgnoreUnannotated
@ColumnWidth(25)
public class ServiceConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**服务Id*/
    @Schema(description = "服务Id")
    @Dict(dictTable = "digital_human_service_info", dicCode = "id", dicText = "service_name")
	@ApiParameter(name = "serviceId", description = "服务Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String serviceId;
	/**名称*/
    @Schema(description = "实例名称")
    @ExcelProperty("实例名称")
	@ApiParameter(name = "name", description = "实例名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String name;
	/**服务商数字人id*/
    @Schema(description = "服务商数字人id")
	@ApiParameter(name = "digitalHumanId", description = "服务商数字人id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	@Dict(dictTable = "external_service_config",dicCode = "key", dicText = "text")
	private java.lang.String digitalHumanId;
	/**形象文件id*/
    @Schema(description = "形象文件id")
	@ApiParameter(name = "imageId", description = "形象文件id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String imageId;
	/**形象文件路径*/
	@Schema(description = "形象文件路径")
	@ApiParameter(name = "imageUrl", description = "形象文件路径", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String imageUrl;
	/**音色*/
	@Schema(description = "音色")
	@ApiParameter(name = "voice", description = "音色", location = ApiEnum.PARAMETER_LOCATION_BODY)
	@Dict(dictTable = "external_service_config",dicCode = "key", dicText = "text")
	private java.lang.String voice;
	/**token过期时间(秒)*/
    @Schema(description = "token过期时间(秒)")
	@ApiParameter(name = "tokenExpirationTime", description = "token过期时间(秒)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer tokenExpirationTime;
	/**是否支持远程失效*/
    @Schema(description = "是否支持远程失效")
    @NotEmpty(message = "是否支持远程失效不能为空")
	@Dict(dicCode = "yes_no")
	@ApiParameter(name = "supportLoseEfficacy", description = "是否支持远程失效", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String supportLoseEfficacy;
	/**启停状态*/
    @Schema(description = "启停状态")
    @NotNull(message = "启停状态不能为空")
	@Digits(integer = 1, fraction = 0, message = "启停状态只能是整数")
    @Dict(dicCode = "until_status")
	@ApiParameter(name = "status", description = "启停状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
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
