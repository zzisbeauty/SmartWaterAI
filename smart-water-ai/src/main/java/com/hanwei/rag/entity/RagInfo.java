package com.hanwei.rag.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * @Description: 知识库基础信息管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Data
@TableName("rag_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="RagInfo对象", description="知识库基础信息管理")
public class RagInfo {

	/**主键*/
	@TableId(type = IdType.INPUT)
    @Schema(description = "主键")
	private java.lang.String id;
	/**知识库名称*/
    @Schema(description = "知识库名称")
    @NotEmpty(message = "知识库名称不能为空")
    @ExcelProperty("知识库名称")
    @ApiParameter(name = "name", description = "知识库名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String name;
	/**知识库缩略图*/
    @Schema(description = "知识库缩略图")
    @ExcelProperty("知识库缩略图")
    @ApiParameter(name = "avatar", description = "知识库缩略图", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String avatar;
    /**知识库缩略图*/
    @Schema(description = "图片地址")
    @ExcelProperty("图片地址")
    @ApiParameter(name = "iconUrl", description = "图片地址", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String iconUrl;
    /**知识库缩略图*/
    @Schema(description = "图标文件ID")
    @ExcelProperty("图标文件ID")
    @ApiParameter(name = "fileId", description = "图标文件ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String fileId;
	/**总分块数量*/
    @Schema(description = "总分块数量")
    @ExcelProperty("总分块数量")
    @ApiParameter(name = "chunkNum", description = "总分块数量", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer chunkNum;
	/**知识库表述*/
    @Schema(description = "知识库表述")
    @ExcelProperty("知识库表述")
    @ApiParameter(name = "description", description = "知识库表述", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String description;
	/**向量化模型*/
    @Schema(description = "向量化模型")
    @ExcelProperty("向量化模型")
    @ApiParameter(name = "embdId", description = "向量化模型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String embdId;
	/**文档解析器*/
    @Schema(description = "文档解析器")
    @ExcelProperty("文档解析器")
    @ApiParameter(name = "docParser", description = "文档解析器", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String docParser;
	/**切片方法*/
    @Schema(description = "切片方法")
    @ExcelProperty("切片方法")
    @ApiParameter(name = "slicingMethod", description = "切片方法", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String slicingMethod;
	/**知识库花费总token数(不包含知识图谱)*/
    @Schema(description = "知识库花费总token数(不包含知识图谱)")
    @ExcelProperty("知识库花费总token数(不包含知识图谱)")
    @ApiParameter(name = "tokenNum", description = "知识库花费总token数(不包含知识图谱)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer tokenNum;
	/**文本块大小*/
    @Schema(description = "文本块大小")
    @ExcelProperty("文本块大小")
    @ApiParameter(name = "pagerank", description = "文本块大小", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer pagerank;

    /**是否启用知识图谱*/
    @Schema(description = "是否启用知识图谱")
    @ExcelProperty("是否启用知识图谱")
    @ApiParameter(name = "isUseGraph", description = "是否启用知识图谱", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Boolean isUseGraph;
    /**实体名称*/
    @Schema(description = "实体名称")
    @ExcelProperty("实体名称")
    @ApiParameter(name = "entityTypes", description = "实体名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private List entityTypes;
    /**生成方法*/
    @Schema(description = "生成方法")
    @ExcelProperty("生成方法")
    @ApiParameter(name = "methodGraph", description = "生成方法", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String methodGraph;
    /**是否实体归一化*/
    @Schema(description = "是否实体归一化")
    @ExcelProperty("是否实体归一化")
    @ApiParameter(name = "isResolution", description = "是否实体归一化", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Boolean isResolution;
    /**是否生成社区报告*/
    @Schema(description = "是否生成社区报告")
    @ExcelProperty("是否生成社区报告")
    @ApiParameter(name = "isGeneralReport", description = "是否生成社区报告", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Boolean isGeneralReport;
	/**相似度阈值*/
    @Schema(description = "相似度阈值")
    @ExcelProperty("相似度阈值")
    @ApiParameter(name = "similarityThreshold", description = "相似度阈值", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double similarityThreshold;
	/**向量相似度权重*/
    @Schema(description = "向量相似度权重")
    @ExcelProperty("向量相似度权重")
    @ApiParameter(name = "vectorSimilarityWeight", description = "向量相似度权重", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Double vectorSimilarityWeight;
	/**返回结果数*/
    @Schema(description = "返回结果数")
    @ExcelProperty("返回结果数")
    @ApiParameter(name = "topN", description = "返回结果数", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer topN;
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


    //  ==============================  水务新增 ================================== \\
    /**索引技术*/
    @Schema(description = "索引技术")
    @ExcelProperty("索引技术")
    @ApiParameter(name = "indexing_technique", description = "索引技术", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String indexingTechnique;

    /**embedding提供商*/
    @Schema(description = "embedding提供商")
    @ExcelProperty("embedding提供商")
    @ApiParameter(name = "embedding_provider_name", description = "embedding提供商", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String embeddingProviderName;

    /**检索方法*/
    @Schema(description = "检索方法")
    @ExcelProperty("检索方法")
    @ApiParameter(name = "search_method", description = "检索方法", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String searchMethod;

    /**是否启用重排序*/
    @Schema(description = "是否启用重排序")
    @ExcelProperty("是否启用重排序")
    @ApiParameter(name = "reranking_enable", description = "是否启用重排序", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private Boolean rerankingEnable;

    /**重排序模式*/
    @Schema(description = "重排序模式")
    @ExcelProperty("重排序模式")
    @ApiParameter(name = "reranking_mode", description = "重排序模式", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String rerankingMode;

    /**重排序模型名称*/
    @Schema(description = "重排序模型名称")
    @ExcelProperty("重排序模型名称")
    @ApiParameter(name = "reranking_model_name", description = "重排序模型名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String rerankingModelName;

    /**重排序提供商*/
    @Schema(description = "重排序提供商")
    @ExcelProperty("重排序提供商")
    @ApiParameter(name = "reranking_provider_name", description = "重排序提供商", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private String rerankingProviderName;

    /**是否启用相似度阈值*/
    @Schema(description = "是否启用相似度阈值")
    @ExcelProperty("是否启用相似度阈值")
    @ApiParameter(name = "score_threshold_enabled", description = "是否启用相似度阈值", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @TableField(exist = false)
    private Boolean scoreThresholdEnabled;

}
