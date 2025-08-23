package com.hanwei.application.service;

import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: 应用基础信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
public interface IApplicationInfoService extends IService<ApplicationInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param applicationInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ApplicationInfo applicationInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param applicationInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, ApplicationInfo applicationInfo);

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
     * 通过id查询应用全部信息
     * @param id
     * @return
     */
    Result getApplicationAllInfo(String id);

    /**
     * 保存应用信息及新增配置信息
     * @param applicationInfo
     * @return
     */
    Result<?> saveApplicationInfo(ApplicationInfo applicationInfo);

    /**
     * 删除应用信息及相关配置信息
     * @param id
     * @return
     */
    Result<?> removeByApplicationId(String id);

    /**
     * 更新应用信息
     * @param applicationInfo
     * @return
     */
    Boolean updateApplicationInfo(ApplicationInfo applicationInfo);
}
