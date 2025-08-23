package com.hanwei.log.service;

import com.hanwei.log.entity.DialogueLog;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 模型对话日志
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
public interface IDialogueLogService extends IService<DialogueLog> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param dialogueLog
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, DialogueLog dialogueLog);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param dialogueLog
     * @return String
     */
    String exportDataToBase64(Map paramMap, DialogueLog dialogueLog);

    /**
     * 通过excel导入数据
     *
     * @param file
     * @return
     */
    Result<?> importData(MultipartFile file);

    /**
     * 下载导入模板
     * @return
     */
    String getImportTemplate();
}
