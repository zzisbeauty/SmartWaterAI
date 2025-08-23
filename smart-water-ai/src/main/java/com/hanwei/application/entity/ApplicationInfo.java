package com.hanwei.application.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.hanwei.asr.entity.AsrServiceInfo;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.autoapi.entity.ApiInfo;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import com.hanwei.digitalhuman.entity.ServiceInfo;
import com.hanwei.model.entity.LargeModelInfo;
import com.hanwei.rag.entity.RagInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * @Description: 应用基础信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Data
@TableName("application_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ApplicationInfo对象", description="应用基础信息表")
public class ApplicationInfo {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**应用名称*/
    @Schema(description = "应用名称")
    @NotEmpty(message = "应用名称不能为空")
    @ExcelProperty("应用名称")
    @ApiParameter(name = "applicationName", description = "应用名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String applicationName;
	/**应用编号*/
    @Schema(description = "应用编号")
    @ExcelProperty("应用编号")
    @ApiParameter(name = "applicationCode", description = "应用编号", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String applicationCode;
	/**应用描述*/
    @Schema(description = "应用描述")
    @ExcelProperty("应用描述")
    @ApiParameter(name = "applicationDesc", description = "应用描述", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String applicationDesc;
	/**帮助链接*/
    @Schema(description = "帮助链接")
    @ExcelProperty("帮助链接")
    @ApiParameter(name = "helpLink", description = "帮助链接", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String helpLink;
	/**应用图标*/
    @Schema(description = "应用图标")
    @NotEmpty(message = "应用图标不能为空")
    @ApiParameter(name = "iconUrl", description = "应用图标", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String iconUrl;
    /**应用图标*/
    @Schema(description = "应用图标id")
    @ApiParameter(name = "fileId", description = "应用图标id", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String fileId;
	/**创建人*/
    @Schema(description = "创建人")
    @ExcelProperty("创建人")
	private java.lang.String createBy;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
	private java.util.Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    @ExcelProperty("更新人")
	private java.lang.String updateBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;

    /**应用数字人配置信息*/
    @Schema(description = "应用数字人配置信息")
    @TableField(exist = false)
    private List<ServiceConfig> digitalHumanServiceConfig;

    /**应用ASR配置信息*/
    @Schema(description = "应用ASR配置信息")
    @TableField(exist = false)
    private List<AsrServiceConfig> asrServiceConfig;

    /**应用模型配置信息*/
    @Schema(description = "应用模型配置信息")
    @TableField(exist = false)
    private List<LargeModelInfo> largeModelInfo;

    /**应用知识库配置信息*/
    @Schema(description = "应用知识库配置信息")
    @TableField(exist = false)
    private List<RagInfo> ragInfoList;

    /**应用接口配置信息*/
    @Schema(description = "应用接口配置信息")
    @TableField(exist = false)
    private List<ApiServiceInfo> apiInfoList;
}
