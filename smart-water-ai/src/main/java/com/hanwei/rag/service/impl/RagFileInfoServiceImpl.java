package com.hanwei.rag.service.impl;


import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.rag.entity.RagFileInfo;
import com.hanwei.rag.mapper.RagFileInfoMapper;
import com.hanwei.rag.service.IRagFileInfoService;
import com.hanwei.rag.vo.FileChunkVO;
import com.hanwei.rag.vo.RagFileListVO;

import com.hanwei.util.yanjiuyuan.YanjiuyuanHelper;
import com.hanwei.util.shuiwu.PublicHelperFunc;

import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;


/**
 * @Description: 知识库文档管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Service
@Slf4j
public class RagFileInfoServiceImpl extends ServiceImpl<RagFileInfoMapper, RagFileInfo> implements IRagFileInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    //private PublicHelperFunc yanjiuyuanHelper;
    private YanjiuyuanHelper yanjiuyuanHelper;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     * @param outputStream
     * @param paramMap
     * @param ragFileInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, RagFileInfo ragFileInfo) {
        QueryWrapper<RagFileInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragFileInfo, paramMap);
        List<RagFileInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, RagFileInfo.class).sheet("知识库文档管理").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param ragFileInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, RagFileInfo ragFileInfo) {
        QueryWrapper<RagFileInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragFileInfo, paramMap);
        List<RagFileInfo> list = list(queryWrapper);
        //字典值转换
        // List<JSON> listJson = commonApi.translateResultByDict(list);
        // List<RagFileInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,RagFileInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, RagFileInfo.class).sheet("知识库文档管理").doWrite(list);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }




    // =================================================================================================================

    /**
     * 知识库文件上传
     * @param ragId
     * @param file
     * @return
     */
    @Override
    public Result<?> uploadFileByYanjiuyuan(String ragId, MultipartFile file) {
        //调用研究院知识库上传
        try {
            Result result = yanjiuyuanHelper.ragFileUpload(ragId,file);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院知识库文件上传失败 "+ result.getMessage());
                return Result.error(200,"调用研究院知识库文件上传失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库文件上传失败 "+ e.getMessage());
            return Result.error(200,"调用研究院知识库文件上传失败 "+ e.getMessage());
        }
        return Result.ok("文件上传成功");
    }



    /**
     * 知识库文件查询
     * @param pageNo
     * @param pageSize
     * @param fileName
     * @param ragId
     * @return
     */
    @Override
    public Result<?> getFileListByYanjiuyuan(String pageNo, String pageSize, String fileName, String ragId) {
        //调用研究院知识库文件查询
        Result result = null;
        //组装返回前端VO
        RagFileListVO ragFileListVO = new RagFileListVO();
        try {
            result = yanjiuyuanHelper.getRagFileList(pageNo,pageSize,fileName,ragId);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院知识库文件查询失败 "+ result.getMessage());
                return Result.error(200,"调用研究院知识库文件查询失败 "+ result.getMessage());
            }

            List<RagFileInfo> list = new ArrayList<>();
            JSONObject data = (JSONObject) result.getResult();
            ragFileListVO.setTotal(data.getInt("total"));
            JSONArray array = data.getJSONArray("docs");
            for(Object object : array){
                JSONObject jsonObject = (JSONObject) object;
                RagFileInfo ragFileInfo = new RagFileInfo();
                ragFileInfo.setId(jsonObject.getStr("id"));
                ragFileInfo.setKbId(jsonObject.getStr("kb_id"));
                ragFileInfo.setName(jsonObject.getStr("name"));
                ragFileInfo.setChunkNum(jsonObject.getInt("chunk_num"));
                ragFileInfo.setDocParser(jsonObject.getStr("parser_id"));
                ragFileInfo.setProcessBegin(jsonObject.getDate("process_begin_at"));
                ragFileInfo.setProcessDuation(jsonObject.getInt("process_duation"));
                ragFileInfo.setProgress(jsonObject.getDouble("progress"));
                ragFileInfo.setProgressMsg(jsonObject.getStr("progress_msg"));
                ragFileInfo.setStatus(jsonObject.getInt("status"));
                ragFileInfo.setSize(jsonObject.getInt("size"));

                Integer parserStatus = jsonObject.getInt("run");
                if(Optional.ofNullable(parserStatus).isPresent()){
                    if(0 == parserStatus){
                        ragFileInfo.setParserStatus("未解析");
                    }
                    if(1 == parserStatus){
                        ragFileInfo.setParserStatus("解析中");
                    }
                    if(4 == parserStatus){
                        ragFileInfo.setParserStatus("解析失败");
                    }
                    if(3 == parserStatus){
                        ragFileInfo.setParserStatus("已解析");
                    }
                }
                Date date = new Date();
                date.setTime(jsonObject.getLong("create_time"));
                ragFileInfo.setCreateTime(date);
                list.add(ragFileInfo);
            }
            ragFileListVO.setRecords(list);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库文件查询失败 "+ e.getMessage());
            return Result.error(200,"调用研究院知识库文件查询失败 "+ e.getMessage());
        }
        return Result.ok(ragFileListVO);
    }

    /**
     * 知识库文件下载
     * @param fileId
     * @return
     */
    @Override
    public HttpResponse downLoadRagFileByYanjiuyuan(String fileId) {
        //调用研究院知识库文件下载
        HttpResponse response;
        try {
            response = yanjiuyuanHelper.downLoadRagFile(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库文件下载失败 "+ e.getMessage());
            return null;
        }
        return response;
    }

    /**
     * 知识库文件删除
     * @param docIdLis
     * @return
     */
    @Override
    public Result<?> deleteRagFileByYanjiuyuan(List<String> docIdLis) {
        //调用研究院知识库文件删除
        Result result = null;
        try {
            result = yanjiuyuanHelper.deleteRagFile(docIdLis);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院知识库文件删除失败 "+ result.getMessage());
                return Result.error(200,"调用研究院知识库文件删除失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库文件删除失败 "+ e.getMessage());
            return Result.error(200,"调用研究院知识库文件删除失败 "+ e.getMessage());
        }
        return Result.ok("文件删除成功");
    }

    /**
     * 文档切片查询
     * @param pageNo
     * @param pageSize
     * @param docId
     * @param keyWord
     * @return
     */
    @Override
    public Result<?> getDocumentSlicingListByYanjiuyuan(String pageNo, String pageSize, String docId, String keyWord) {
        //调用研究院文档切片查询
        Result result;
        List<FileChunkVO> fileChunkVOList = new ArrayList<>();
        try {
            result = yanjiuyuanHelper.getDocumentSlicingList(pageNo, pageSize, docId, keyWord);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院文档切片查询失败 "+ result.getMessage());
                return Result.error(200,"调用研究院文档切片查询失败 "+ result.getMessage());
            }
            JSONObject jsonObject = (JSONObject) result.getResult();
            List<JSONObject> list = jsonObject.getJSONArray("chunks").toList(JSONObject.class);
            for(JSONObject object : list){
                FileChunkVO fileChunkVO = new FileChunkVO();
                fileChunkVO.setId(object.getStr("chunk_id"));
                fileChunkVO.setContent(object.getStr("content_with_weight"));
                fileChunkVO.setDocId(object.getStr("c8715cae630611f0b5560242ac1a0002"));
                fileChunkVO.setDocName(object.getStr("docnm_kwd"));
                fileChunkVO.setStatus(object.getInt("available_int"));
                fileChunkVOList.add(fileChunkVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院文档切片查询失败 "+ e.getMessage());
            return Result.error(200,"调用研究院文档切片查询失败 "+ e.getMessage());
        }
        return Result.ok(fileChunkVOList);
    }

    /**
     * 文档切片状态切换
     * @param status
     * @param docId
     * @param slicingIdList
     * @return
     */
    @Override
    public Result<?> documentSlicingStatusSwitchByYanjiuyuan(Integer status, String docId, List<String> slicingIdList) {
        //调用研究院文档切片状态切换
        Result result = null;
        try {
            result = yanjiuyuanHelper.documentSlicingStatusSwitch(status, docId, slicingIdList);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院文档切片状态切换失败 "+ result.getMessage());
                return Result.error(200,"调用研究院文档切片状态切换失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院文档切片状态切换失败 "+ e.getMessage());
            return Result.error(200,"调用研究院文档切片状态切换失败 "+ e.getMessage());
        }
        return Result.ok("文档切片状态切换成功");
    }

    /**
     * 知识库文件状态切换
     * @param docId
     * @param status
     * @return
     */
    @Override
    public Result<?> ragFileSwitch(String docId, Integer status) {
        //调用研究院文档状态切换
        Result result = null;
        try {
            result = yanjiuyuanHelper.ragFileSwitch(docId, status);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院文档状态切换失败 "+ result.getMessage());
                return Result.error(200,"调用研究院文档切片状态切换失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院文档切片状态切换失败 "+ e.getMessage());
            return Result.error(200,"调用研究院文档状态切换失败 "+ e.getMessage());
        }

        return Result.ok("文档状态切换成功");
    }




    // ============================= 暂缺实现 =================================== \\

    /**
     * 知识库文件解析
     * @param docIdList
     * @param deleteFlag
     * @param runModel
     * @return
     */
    @Override
    public Result<?> ragFileParsingByYanjiuyuan(List<String> docIdList, Boolean deleteFlag, Integer runModel) {
        //调用研究院知识库文件解析
        Result result = null;
        try {
            result = yanjiuyuanHelper.ragFileParsing(docIdList,deleteFlag,runModel);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院知识库文件解析失败 "+ result.getMessage());
                return Result.error(200,"调用研究院知识库文件解析失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库文件解析失败 "+ e.getMessage());
            return Result.error(200,"调用研究院知识库文件解析失败 "+ e.getMessage());
        }
        //模式 1:文件解析 2:终止解析
        String message = "";
        if(1 == runModel){
            message = "文件解析成功";
        }else if(2 == runModel){
            message = "停止解析成功";
        }else{
            message = "操作成功";
        }
        return Result.ok(message);
    }
}
