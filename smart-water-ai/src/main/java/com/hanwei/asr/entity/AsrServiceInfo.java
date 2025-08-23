package com.hanwei.asr.entity;

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
 * @Description: ASR服务信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("asr_service_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="AsrServiceInfo对象", description="ASR服务信息")
public class AsrServiceInfo {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**名称*/
    @Schema(description = "服务名称")
    @NotEmpty(message = "服务名称不能为空")
    @ExcelProperty("服务名称")
    @ApiParameter(name = "name", description = "服务名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String name;
	/**服务商*/
    @Schema(description = "服务商")
    @NotEmpty(message = "服务商不能为空")
    @ExcelProperty("服务商")
    @ApiParameter(name = "serviceProvider", description = "服务商", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String serviceProvider;
	/**当前可用时长(分钟)*/
    @Schema(description = "当前可用时长(分钟)")
    @ExcelProperty("当前可用时长(分钟)")
    @ApiParameter(name = "availableTime", description = "当前可用时长(分钟)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer availableTime;
	/**总可用时长(分钟)*/
    @Schema(description = "总可用时长(分钟)")
    @NotNull(message = "总可用时长(分钟)不能为空")
    @ExcelProperty("总可用时长(分钟)")
    @ApiParameter(name = "totalTime", description = "总可用时长(分钟)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer totalTime;
	/**开通时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "开通时间")
    @NotNull(message = "开通时间不能为空")
    @ExcelProperty("开通时间")
    @ApiParameter(name = "openingTime", description = "开通时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date openingTime;
	/**到期时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "到期时间")
    @NotNull(message = "到期时间不能为空")
    @ExcelProperty("到期时间")
    @ApiParameter(name = "expirationTime", description = "到期时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date expirationTime;
	/**费用*/
    @Schema(description = "费用")
    @NotNull(message = "费用不能为空")
    @ExcelProperty("费用")
    @NumberFormat("#.##")
    @Min(value = 0, message = "费用不能小于0")
    @ApiParameter(name = "cost", description = "费用", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double cost;
	/**认证签名信息*/
    @Schema(description = "认证签名信息")
    @NotEmpty(message = "认证签名信息不能为空")
    @ExcelProperty("认证签名信息")
    @Size(max = 512)
    @ApiParameter(name = "signatrue", description = "认证签名信息", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String signatrue;
	/**启停状态*/
    @Schema(description = "启停状态")
    @ExcelProperty("启停状态")
    @Dict(dicCode = "until_status")
    @ApiParameter(name = "status", description = "启停状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
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
