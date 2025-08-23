package com.hanwei.rag.bo;

import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2025/5/26 16:02]
 * @updateUser : [CX]
 * @updateTime : [2025/5/26 16:02]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class ChoiceRagBO {
    /**
     * 显示图
     */
    @ApiParameter(name = "icon", description = "显示图", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String icon;

    /**
     * 知识库ID列表
     */
    @ApiParameter(name = "RagIdList", description = "知识库ID列表", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private List<String> RagIdList;

    /**
     * 语言
     */
    @ApiParameter(name = "language", description = "语言", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String language;

    /**
     * 模型名称
     */
    @ApiParameter(name = "largeModelName", description = "模型名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String largeModelName;

    /**
     * 频率惩罚
     */
    @ApiParameter(name = "frequencyPenalty", description = "频率惩罚", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double frequencyPenalty;

    /**
     * 存在惩罚
     */
    @ApiParameter(name = "presencePenalty", description = "存在惩罚", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double presencePenalty;

    /**
     * 模型温度
     */
    @ApiParameter(name = "temperature", description = "模型温度", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double temperature;

    /**
     * 核心采样率
     */
    @ApiParameter(name = "topP", description = "核心采样率", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double topP;

    /**
     * 配置名称
     */
    @ApiParameter(name = "name", description = "配置名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String name;


    //==================================提示词相关 目前研究院这一块没用，参数含义不清晰，后期进行确认补全
    /**
     * 空响应
     */
    private String emptyResponse;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 参数
     */
    private List<Map> parameters;

    /**
     * 开场词
     */
    private String prologue;

    /**
     * 引号
     */
    private Boolean quote;

    /**
     * 原因
     */
    private Boolean reasoning;

    /**
     * 细化多次请求
     */
    private Boolean refineMultiTurn;

    /**
     * 词语内容
     */
    private String system;

    /**
     * tavily认证密钥
     */
    private String tavilyApiKey;

    /**
     * tts
     */
    private String tts;

    /**
     *
     */
    private String useKg;


    //==================================提示词相关 目前研究院这一块没用，参数含义不清晰，后期进行确认补全

    /**
     *相似度阈值
     */
    @ApiParameter(name = "similarityThreshold", description = "相似度阈值", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double similarityThreshold;

    /**
     *向量相似度权重
     */
    @ApiParameter(name = "vectorSimilarityWeight", description = "向量相似度权重", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Double vectorSimilarityWeight;

    /**
     *返回结果数
     */
    @ApiParameter(name = "topN", description = "返回结果数", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Integer topN;
}
