package com.hanwei.model.service;

import com.hanwei.model.entity.LargeModelInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.rag.bo.MessageBO;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: 大模型基础信息
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
public interface ILargeModelInfoService extends IService<LargeModelInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param largeModelInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, LargeModelInfo largeModelInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param largeModelInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, LargeModelInfo largeModelInfo);



    /**
     * 同步研究院模型数据，按名称同步 不存在则新增
     */
    void synYjyLLM();

    /**
     * 更新大模型信息
     * @param largeModelInfo
     * @return
     */
    Result<?> updateLargeModelInfo(LargeModelInfo largeModelInfo);

    /**
     * 根据ID删除大模型信息
     * @param id
     * @return
     */
    Result<?> deleteLargeModelInfo(String id);

    /**
     * 设置模型
     * @param largeModelName
     * @return
     */
    Result<?> choiceLargeModelByYanjiuyuan(String largeModelName);

    /**
     * 调用模型对话
     * @param conversationId
     * @param messageBOList
     * @return
     */
    Result<?> modelDialogueByYanjiuyuan(String conversationId, List<MessageBO> messageBOList);
}
