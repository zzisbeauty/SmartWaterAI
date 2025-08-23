package com.hanwei.rag.vo;

import lombok.Data;

/**
 * @version : [v1.0]
 * @description : [文件切片VO]
 * @createTime : [2025/7/23 8:40]
 */
@Data
public class FileChunkVO {
    /**
     * 文件切片id
     */
    private String id;

    /**
     * 切片内容
     */
    private String content;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 文档id
     */
    private String docId;

    /**
     * 文档名称
     */
    private String docName;
}
