package com.hanwei.digitalhuman.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 数字人服务信息
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Data
@TableName("digital_human_service_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ServiceInfo对象", description="数字人服务信息")
@ExcelIgnoreUnannotated
@ColumnWidth(25)
public class ServiceInfo {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

	/**服务名称*/
    @Schema(description = "服务名称")
    @NotEmpty(message = "服务名称不能为空")
    @ExcelProperty("服务名称")
    @ApiParameter(name = "serviceName", description = "服务名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String serviceName;

	/**服务商*/
    @Schema(description = "服务商")
    @NotEmpty(message = "服务商不能为空")
    @ExcelProperty("服务商")
    @ApiParameter(name = "serviceProvider", description = "服务商", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String serviceProvider;

	/**开通路数*/
    @Schema(description = "开通路数")
    @ExcelProperty("开通路数")
    @Min(value = 1, message = "开通路数不能小于1")
    @ApiParameter(name = "routeNum", description = "开通路数", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer routeNum;

	/**开通时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "开通时间")
    @ExcelProperty("开通时间")
    @ApiParameter(name = "openingTime", description = "开通时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date openingTime;

	/**到期时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "到期时间")
    @ExcelProperty("到期时间")
    @ApiParameter(name = "expirationTime", description = "到期时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date expirationTime;

	/**费用*/
    @Schema(description = "费用")
    @ExcelProperty("费用")
    @NumberFormat("#.##")
    @Min(value = 0, message = "费用不能小于0")
    @NotNull(message = "服务名称不能为空")
    @ApiParameter(name = "cost", description = "费用", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double cost;

	/**认证签名信息*/
    @Schema(description = "认证签名信息")
    @NotEmpty(message = "认证签名信息不能为空")
    @ExcelProperty("认证签名信息")
    @Size(max = 512, message = "认证签名信息长度不能大于512字符")
    @ApiParameter(name = "signatrue", description = "认证签名信息", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String signatrue;

	/**状态(正常，临期，过期，禁用)*/
    @Schema(description = "状态(正常，临期，过期)")
    @ExcelProperty("状态")
    @Dict( dicCode= "until_status")
    @ApiParameter(name = "status", description = "状态(正常，临期，过期)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;

	/**创建人*/
    @Schema(description = "创建人")
    @ExcelProperty("创建人")
	private java.lang.String createBy;

	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
	private java.util.Date createTime;

	/**更新人*/
    @Schema(description = "更新人")
    @ExcelProperty("更新人")
	private java.lang.String updateBy;

	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;
}
