package com.hanwei.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.vo.ApiRelationConfigVO;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.style.entity.StyleInfo;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: api基本信息
 * @Author: hanwei
 * @Date:   2025-05-15
 * @Version: V1.0
 */
public interface IApiServiceInfoService extends IService<ApiServiceInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param apiServiceInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ApiServiceInfo apiServiceInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param apiServiceInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, ApiServiceInfo apiServiceInfo);

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
     * 根据apiId获取样式信息
     * @param id
     * @return
     */
    StyleInfo getStyleInfoByApiId(String id);

    /**
     * 保存api基本信息以及样式问题等各类信息
     * @param apiServiceInfo
     * @return
     */
    Result<?> saveApiServiceAllInfo(ApiServiceInfo apiServiceInfo);

//    /**
//     * 补充api基本信息
//     * @param records
//     * @return
//     */
//    List<ApiServiceInfo> completeApiServiceInfo(List<ApiServiceInfo> records);

    /**
     * 删除api基本信息
     * @param apiServiceInfoId
     * @return
     */
    Result<?> removeApiServiceInfo(String apiServiceInfoId);

    /**
     * 批量删除api基本信息
     * @param asList
     * @return
     */
    Boolean removeApiServiceInfoIdList(List<String> asList);

    /**
     * 更新api基本信息
     * @param apiServiceInfo
     * @return
     */
    Result<?> updateApiServiceInfo(ApiServiceInfo apiServiceInfo);

    /**
     * 通过id查询api全部信息
     * @param id
     * @return
     */
    Object queryAllApiInfoById(String id);

    /**
     * 获取问题分页信息
     * @param apiId
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<ApiRelationConfigVO> getQuestionPage(String apiId, Integer pageNo, Integer pageSize);
}
