package com.hanwei.process.vo;


import lombok.Data;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [返回前端VO]
 * @createTime : [2024/11/6 15:33]
 * @updateTime : [2024/11/6 15:33]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class ResultForFrontVo {

    /**
     * 结果归属
     * customer-用户
     * system-机器人
     */
    private String resultTo;

    /**
     * 返回类型
     * 0-语音翻译
     * 1-纯文本
     * 2-图标数据
     * 3-列表数据
     * 4-图文数据
     * 5-链接数据
     * 6-排行数据
     */
    private Integer resultType;

    /**
     * 翻译后的文本
     */
    private String translatedText;

    /**
     * 结果文本
     */
    private TextResult textResult;

    /**
     * 图表数据
     */
    private ChartResult chartResult;

    /**
     * 表格数据
     */
    private TableResult tableResult;

    /**
     * 图文数据
     */
    private ImageTextResult textAndImageResult;

    /**
     * 链接URL数据
     */
    private UrlResult urlResult;

    /**
     * 排行数据
     */
    private RankResult rankResult;
}
