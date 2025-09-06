package com.hanwei.rag.bo;

import lombok.Data;
import java.util.List;

/**
 * @Description: 删除文档请求VO
 * @Author: hanwei
 * @Date: 2025-09-05
 * @Version: V1.0
 */
@Data
public class DeleteDocumentRequestBO {
    /**
     * 文档ID列表
     */
    private List<String> docIdLis;
}