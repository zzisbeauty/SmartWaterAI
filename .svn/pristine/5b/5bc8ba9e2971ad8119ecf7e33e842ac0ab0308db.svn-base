package com.hanwei.rag.entity;

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
 * @Description: 知识库文档管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Data
@TableName("rag_file_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="RagFileInfo对象", description="知识库文档管理")
public class RagFileInfo {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
	private java.lang.String id;
	/**知识库id*/
    @Schema(description = "知识库id")
    @NotEmpty(message = "知识库id不能为空")
    @ExcelProperty("知识库id")
    @ApiParameter(name = "kbId", description = "知识库id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String kbId;
	/**文档名称*/
    @Schema(description = "文档名称")
    @NotEmpty(message = "文档名称不能为空")
    @ExcelProperty("文档名称")
    @ApiParameter(name = "name", description = "文档名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String name;
	/**分块数*/
    @Schema(description = "分块数")
    @ExcelProperty("分块数")
    @ApiParameter(name = "chunkNum", description = "分块数", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer chunkNum;
	/**文档解析类型*/
    @Schema(description = "文档解析类型")
    @NotEmpty(message = "文档解析类型不能为空")
    @ExcelProperty("文档解析类型")
    @ApiParameter(name = "docParser", description = "文档解析类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String docParser;
	/**解析开始时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "解析开始时间")
    @ExcelProperty("解析开始时间")
    @ApiParameter(name = "processBegin", description = "解析开始时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date processBegin;
	/**解析持续时间(毫秒)*/
    @Schema(description = "解析持续时间(毫秒)")
    @ExcelProperty("解析持续时间(毫秒)")
    @ApiParameter(name = "processDuation", description = "解析持续时间(毫秒)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer processDuation;
	/**解析进度*/
    @Schema(description = "解析进度")
    @ExcelProperty("解析进度")
    @ApiParameter(name = "progress", description = "解析进度", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double progress;
	/**解析信息*/
    @Schema(description = "解析信息")
    @ExcelProperty("解析信息")
    @ApiParameter(name = "progressMsg", description = "解析信息", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String progressMsg;
	/**解析状态*/
    @Schema(description = "解析状态")
    @ExcelProperty("解析状态")
    @ApiParameter(name = "parserStatus", description = "解析状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String parserStatus;
	/**文档大小*/
    @Schema(description = "文档大小")
    @ExcelProperty("文档大小")
    @ApiParameter(name = "size", description = "文档大小", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer size;
	/**来源类型*/
    @Schema(description = "来源类型")
    @ExcelProperty("来源类型")
    @ApiParameter(name = "sourceType", description = "来源类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String sourceType;
	/**文档状态*/
    @Schema(description = "文档状态")
    @ExcelProperty("文档状态")
    @ApiParameter(name = "status", description = "文档状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
	/**缩略图*/
    @Schema(description = "缩略图")
    @ExcelProperty("缩略图")
    @ApiParameter(name = "thumbnail", description = "缩略图", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String thumbnail;
	/**花费token*/
    @Schema(description = "花费token")
    @ExcelProperty("花费token")
    @ApiParameter(name = "tokenNum", description = "花费token", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer tokenNum;
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
