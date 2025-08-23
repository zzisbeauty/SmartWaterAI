package com.hanwei.rag.vo;

import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import lombok.Data;

import java.util.List;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [知识库召回测试VO]
 * @createTime : [2025/5/26 14:23]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class RagRecallVO {

    /**
     * 相似度阈值
     */
    @ApiParameter(name = "similarityThreshold", description = "相似度阈值", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double similarityThreshold;

    /**
     *向量相似度权重
     */
    @ApiParameter(name = "vectorSimilarityWeight", description = "向量相似度权重", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double vectorSimilarityWeight;

    /**
     * 是否启用知识图谱
     */
    @ApiParameter(name = "useGraphRag", description = "是否启用知识图谱", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Boolean useGraphRag;

    /**
     * 召回问题
     */
    @ApiParameter(name = "question", description = "召回问题", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String question;

    /**
     * 包含文档ID 暂时不用
     */
    @ApiParameter(name = "doc_ids", description = "包含文档ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private List<String> doc_ids;

    /**
     * 知识库Id
     */
    @ApiParameter(name = "ragId", description = "知识库Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String ragId;

    /**
     * 分页 页码
     */
    @ApiParameter(name = "pageNo", description = "页码", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Integer pageNo;

    /**
     * 分页 条数
     */
    @ApiParameter(name = "pageSize", description = "分页条数", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Integer pageSize;
}
