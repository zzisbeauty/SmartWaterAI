package com.hanwei.rag.bo;

import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @version : [v1.0]
 * @description : [文件解析入参VO]
 * @createTime : [2025/7/23 8:55]
 */
@Data
public class FileParsingParamVO {
    /**
     * 文档ID列表
     */
    private List<String> docIdList;

    /**
     * 删除标记，默认true，删除文档之前分块
     */
    private Boolean deleteFlag;

    /**
     * runModel 模式 1:文件解析 2:终止解析
     */
    private Integer runModel;

}
