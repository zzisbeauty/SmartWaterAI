package com.hanwei.model.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 大模型基础信息
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Data
@TableName("large_model_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="LargeModelInfo对象", description="大模型基础信息")
public class LargeModelInfo {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
	private java.lang.String id;
	/**模型类别ID*/
    @Schema(description = "模型类别ID")
    @ExcelProperty("模型类别ID")
    @ApiParameter(name = "modelCategoryId", description = "模型类别ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String modelCategoryId;
	/**模型名称*/
    @Schema(description = "模型名称")
    @NotEmpty(message = "模型名称不能为空")
    @ExcelProperty("模型名称")
    @ApiParameter(name = "name", description = "模型名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String name;
	/**模型类型*/
    @Schema(description = "模型类型")
    @ExcelProperty("模型类型")
    @ApiParameter(name = "type", description = "模型类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String type;
	/**已花费token数*/
    @Schema(description = "已花费token数")
    @ExcelProperty("已花费token数")
    @ApiParameter(name = "usedToken", description = "已花费token数", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer usedToken;
	/**频率惩罚*/
    @Schema(description = "频率惩罚")
    @ExcelProperty("频率惩罚")
    @ApiParameter(name = "frequencyPenalty", description = "频率惩罚", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double frequencyPenalty;
	/**存在惩罚*/
    @Schema(description = "存在惩罚")
    @ExcelProperty("存在惩罚")
    @ApiParameter(name = "presencePenalty", description = "存在惩罚", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double presencePenalty;
	/**模型温度*/
    @Schema(description = "模型温度")
    @ExcelProperty("模型温度")
    @ApiParameter(name = "temperature", description = "模型温度", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double temperature;
	/**核心采样率*/
    @Schema(description = "核心采样率")
    @ExcelProperty("核心采样率")
    @ApiParameter(name = "topP", description = "核心采样率", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double topP;
	/**提示词字符串(暂定，后期重新规划这一块）*/
    @Schema(description = "提示词字符串(暂定，后期重新规划这一块）")
    @ApiParameter(name = "promptConfigStr", description = "提示词字符串", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String promptConfigStr;
    /**模型来源 本地或三方如研究院*/
    @Schema(description = "模型来源 本地或三方如研究院")
    @ApiParameter(name = "source", description = "模型来源", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String source;
    /**模型备注*/
    @Schema(description = "模型备注")
    @ApiParameter(name = "remark", description = "模型备注", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String remark;
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
