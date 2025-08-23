package com.hanwei.model.bo;

import lombok.Data;

/**
 * @version : [v1.0]
 * @description : [研究院模型数据]
 * @createTime : [2025/5/27 9:24]
 * @updateUser : [CX]
 * @updateTime : [2025/5/27 9:24]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class YjyLargeModelBO {

    /**
     * 所属类别
     */
    private String category;

    /**
     * 标签
     */
    private String tags;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 所用token数量
     */
    private Integer usedToken;

}
