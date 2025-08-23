package com.hanwei.rag.bo;

import lombok.Data;

/**
 * @version : [v1.0]
 * @description : [知识库文档状态切换VO]
 * @createTime : [2025/7/23 16:22]
 */
@Data
public class RagFileSwitchBO {
    /**
     * 文档ID
     */
    private String docId;

    /**
     * 状态
     */
    private Integer status;
}
