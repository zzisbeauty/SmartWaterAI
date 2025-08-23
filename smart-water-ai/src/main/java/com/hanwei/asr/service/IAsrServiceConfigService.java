package com.hanwei.asr.service;

import com.hanwei.asr.entity.AsrHotWord;
import com.hanwei.asr.entity.AsrKeyWord;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: ASR实例配置信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IAsrServiceConfigService extends IService<AsrServiceConfig> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param asrServiceConfig
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, AsrServiceConfig asrServiceConfig);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param asrServiceConfig
     * @return String
     */
    String exportDataToBase64(Map paramMap, AsrServiceConfig asrServiceConfig);

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

    /**
     * 删除实例配置信息
     * @param id
     * @return
     */
    Result<?> removeAsrConfigInfoById(String id);

    /**
     * 配置热词
     * @param id
     * @param asrHotWord
     * @return
     */
    Result<?> AddHotWord(String id, AsrHotWord asrHotWord);

    /**
     * 配置关键词
     * @param id
     * @param asrKeyWord
     * @return
     */
    Result<?> AddKeyWord(String id, AsrKeyWord asrKeyWord);

    /**
     * 启停状态切换
     * @param id
     * @param status
     * @return
     */
    Boolean switchStatus(String id,Integer status);
}
