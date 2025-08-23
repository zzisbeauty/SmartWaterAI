package com.hanwei.digitalhuman.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: 数字人配置实例
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
public interface IServiceConfigService extends IService<ServiceConfig> {

    /**
     * 根据服务信息ID获取数字人配置实例
     * @param id
     * @return
     */
    List<ServiceConfig> getByServiceInfoId(String id);

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param serviceConfig
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ServiceConfig serviceConfig);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param serviceConfig
     * @return String
     */
    String exportDataToBase64(Map paramMap, ServiceConfig serviceConfig);

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
     * 删除数字人配置信息
     * @param id
     * @return
     */
    Result<?> removeConfigById(String id);

    /**
     * 启停状态切换
     * @param id
     * @param status
     * @return
     */
    Boolean switchStatus(String id,Integer status);

    /**
     * 保存数字人配置信息
     * @param serviceConfig
     * @return
     */
    Boolean saveDigitalHumanConfig(ServiceConfig serviceConfig);


    /**
     * 更新数字人配置信息
     * @param serviceConfig
     * @return
     */
    Boolean updateDigitalHumanConfig(ServiceConfig serviceConfig);
}
