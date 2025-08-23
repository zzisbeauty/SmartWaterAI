package com.hanwei.api.vo;

import com.hanwei.core.annotation.Dict;
import lombok.Data;

/**
 * @version : [v1.0]
 * @description : [api关联问题VO]
 * @createTime : [2025/7/15 14:40]
 * @updateUser : [CX]
 */
@Data
public class ApiRelationConfigVO {
    /**
     * 提问语句ID
     */
    private String id;

    /**
     * 提示语
     */
    private String questionContent;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 接口编号
     */
    private String apiCode;

    /**
     * 所属分类
     */
    @Dict(dicCode = "api_categray")
    private String categoryId;

    /**
     * 所属业务系统
     */
    @Dict(dicCode = "business_code")
    private String businessSystemCode;

    /**
     * 是否当前api选择
     */
    private Boolean isChoose;
}
