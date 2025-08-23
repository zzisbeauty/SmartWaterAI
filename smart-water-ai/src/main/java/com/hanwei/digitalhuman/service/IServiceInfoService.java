package com.hanwei.digitalhuman.service;

import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 数字人服务信息
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
public interface IServiceInfoService extends IService<ServiceInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param serviceInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ServiceInfo serviceInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param serviceInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, ServiceInfo serviceInfo);

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
    Result<?> removeServiceInfoById(String id);
}
