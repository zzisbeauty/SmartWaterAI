package com.hanwei.asr.service;

import com.hanwei.asr.entity.AsrServiceInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: ASR服务信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IAsrServiceInfoService extends IService<AsrServiceInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param asrServiceInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, AsrServiceInfo asrServiceInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param asrServiceInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, AsrServiceInfo asrServiceInfo);

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
     * 删除服务信息
     * @param id
     * @return
     */
    Result<?> removeAsrServiceInfoById(String id);
}
