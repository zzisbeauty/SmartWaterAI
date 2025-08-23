package com.hanwei.rag.service;

import cn.hutool.http.HttpResponse;
import com.hanwei.rag.entity.RagFileInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: 知识库文档管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
public interface IRagFileInfoService extends IService<RagFileInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param ragFileInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, RagFileInfo ragFileInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param ragFileInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, RagFileInfo ragFileInfo);

    /**
     * 知识库文件上传
     * @param ragId
     * @param file
     * @return
     */
    Result<?> uploadFileByYanjiuyuan(String ragId, MultipartFile file);

    /**
     * 知识库文件查询
     * @param pageNo
     * @param pageSize
     * @param fileName
     * @param ragId
     * @return
     */
    Result<?> getFileListByYanjiuyuan(String pageNo, String pageSize, String fileName, String ragId);

    /**
     * 知识库文件解析
     * @param docIdList
     * @param deleteFlag
     * @param runModel
     * @return
     */
    Result<?> ragFileParsingByYanjiuyuan(List<String> docIdList, Boolean deleteFlag, Integer runModel);

    /**
     * 知识库文件下载
     * @param fileId
     * @return
     */
    HttpResponse downLoadRagFileByYanjiuyuan(String fileId);

    /**
     * 知识库文件删除
     * @param docIdLis
     * @return
     */
    Result<?> deleteRagFileByYanjiuyuan(List<String> docIdLis);

    /**
     * 文档切片查询
     * @param pageNo
     * @param pageSize
     * @param docId
     * @param keyWord
     * @return
     */
    Result<?> getDocumentSlicingListByYanjiuyuan(String pageNo, String pageSize, String docId, String keyWord);

    /**
     * 文档切片状态切换
     * @param status
     * @param docId
     * @param slicingIdList
     * @return
     */
    Result<?> documentSlicingStatusSwitchByYanjiuyuan(Integer status, String docId, List<String> slicingIdList);

    /**
     * 知识库文件状态切换
     * @param docId
     * @param status
     * @return
     */
    Result<?> ragFileSwitch(String docId, Integer status);
}
