package com.hanwei.rag.bo;

import lombok.Data;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [对话消息BO]
 * @createTime : [2025/5/26 16:28]
 * @updateUser : [CX]
 * @updateTime : [2025/5/26 16:28]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class MessageBO {
    /**
     * 消息id
     */
    private String id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 对话角色
     */
    private String role;


    /**
     * 用户ID - dify API 需要
     */
    private String user_id;

    /**
     * 知识库ID - dify API 需要
     */
    private String kb_id;

    /**
     * 是否流式输出 - dify API 需要
     */
    private Boolean streaming;
}
