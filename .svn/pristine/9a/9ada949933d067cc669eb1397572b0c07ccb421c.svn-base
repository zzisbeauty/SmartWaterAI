package com.hanwei.application.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 应用配置信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Data
@TableName("application_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ApplicationConfig对象", description="应用配置信息表")
public class ApplicationConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**应用id*/
    @Schema(description = "应用id")
    @NotEmpty(message = "应用id不能为空")
	private java.lang.String applicationId;
	/**数字人配置ID*/
    @Schema(description = "数字人配置ID")
	private java.lang.String digitalHumanConfigId;
	/**ASR配置ID*/
    @Schema(description = "ASR配置ID")
	private java.lang.String asrConfigId;
	/**模型ID*/
    @Schema(description = "模型ID")
	private java.lang.String modelId;
	/**知识库ID*/
    @Schema(description = "知识库ID")
	private java.lang.String ragIds;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "更新时间")
	private java.util.Date updateTime;
	/**创建人*/
    @Schema(description = "创建人")
	private java.lang.String createBy;
}
