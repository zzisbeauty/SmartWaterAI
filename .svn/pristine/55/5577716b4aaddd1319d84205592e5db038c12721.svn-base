package com.hanwei.system.service;

import com.hanwei.system.entity.DictItem;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 字典明细表表
 * @Author: hanwei
 * @Date:   2025-05-13
 * @Version: V1.0
 */
public interface IDictItemService extends IService<DictItem> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param dictItem
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, DictItem dictItem);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param dictItem
     * @return String
     */
    String exportDataToBase64(Map paramMap, DictItem dictItem);

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
