package com.hanwei.style.entity;

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
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 展示样式管理
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("style_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="StyleInfo对象", description="展示样式管理")
public class StyleInfo {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

    /**属于返回结果类型*/
    @Schema(description = "属于返回结果类型")
    @NotEmpty(message = "属于返回结果类型不能为空")
    @ExcelProperty("属于返回结果类型")
    @Dict(dicCode = "result_type")
    @ApiParameter(name = "resultType", description = "属于返回结果类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String resultType;

    /**样式名称*/
    @Schema(description = "样式名称")
    @NotEmpty(message = "样式名称不能为空")
    @ExcelProperty("样式名称")
    @ApiParameter(name = "styleName", description = "样式名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String styleName;

	/**样式类型*/
    @Schema(description = "样式类型")
    @NotEmpty(message = "样式类型不能为空")
    @ExcelProperty("样式类型")
    @ApiParameter(name = "styleType", description = "样式类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String styleType;

    /**样式方案*/
    @Schema(description = "样式方案")
    @ExcelProperty("样式方案")
    @ApiParameter(name = "styleScheme", description = "样式方案", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String styleScheme;

	/**样式描述*/
    @Schema(description = "样式描述")
    @ExcelProperty("样式描述")
    @ApiParameter(name = "styleDesc", description = "样式描述", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String styleDesc;

	/**样式内容*/
    @Schema(description = "样式内容")
    @NotEmpty(message = "样式内容不能为空")
    @ExcelProperty("样式内容")
    @Size(max = 20480, message = "样式内容长度不能超过20480字符")
    @ApiParameter(name = "styleContent", description = "样式内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String styleContent;

	/**示例数据*/
    @Schema(description = "示例数据")
    @ExcelProperty("示例数据")
    @Size(max = 20480, message = "样式内容长度不能超过20480字符")
    @ApiParameter(name = "sampleData", description = "示例数据", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String sampleData;

    /**应用图标*/
    @Schema(description = "应用图标")
    @ApiParameter(name = "iconUrl", description = "应用图标", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String iconUrl;
    /**应用图标*/
    @Schema(description = "应用图标id")
    @ApiParameter(name = "fileId", description = "应用图标id", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String fileId;

    /**启停状态*/
    @Schema(description = "启停状态")
    @ExcelProperty("启停状态")
    @ApiParameter(name = "status", description = "启停状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "until_status")
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
