package com.hanwei.rag.service;

import com.hanwei.rag.bo.ChoiceRagBO;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.rag.vo.RagRecallVO;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 知识库基础信息管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
public interface IRagInfoService extends IService<RagInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param ragInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, RagInfo ragInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param ragInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, RagInfo ragInfo);

    /**
     * 保存知识库信息
     * @param ragInfo
     * @return
     */
    Result<?> saveRagInfo(com.hanwei.rag.entity.RagInfo ragInfo);

    /**
     * 更新知识库信息
     * @param ragInfo
     * @return
     */
    Result<?> updateRagInfo(com.hanwei.rag.entity.RagInfo ragInfo);

    /**
     * 删除知识库信息
     * @param id
     * @return
     */
    Result<?> removeRagInfoById(String id);

    /**
     * 知识库召回测试
     * @param ragRecallVO
     * @return
     */
    Result<?> ragRecall(RagRecallVO ragRecallVO);

    /**
     * 获取知识库知识图谱
     *
     * @param id
     * @return
     */
    Result<?> getRagGraph(String id);

    /**
     * 设置知识库
     * @param choiceRagBO
     * @return
     */
    Result<?> choiceRagByYanjiuyuan(ChoiceRagBO choiceRagBO);
}
