package com.hanwei.model.vo;

import com.hanwei.rag.bo.MessageBO;
import lombok.Data;

import java.util.List;

/**
 * @Description: 模型对话请求VO
 * @Author: hanwei
 * @Date: 2025-09-05
 * @Version: V1.0
 */
@Data
public class ModelDialogueRequestVO {
    /**
     * 会话ID
     */
    private String conversationId;

    /**
     * 对话消息列表
     */
    private List<MessageBO> messageBOList;
}