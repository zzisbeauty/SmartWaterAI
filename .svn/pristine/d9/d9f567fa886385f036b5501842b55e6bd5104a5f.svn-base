package com.hanwei.rag.controller;

import cn.hutool.http.HttpInputStream;
import cn.hutool.http.HttpResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.rag.bo.FileParsingParamVO;
import com.hanwei.rag.bo.RagFileSwitchBO;
import com.hanwei.rag.entity.RagFileInfo;
import com.hanwei.rag.service.IRagFileInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @Description: 知识库文档管理
 * @Author: hanwei
 * @Date: 2025-05-26
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "知识库文档管理")
@RestController
@RequestMapping("/rag/ragFileInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-知识库管理-知识库文件")
public class RagFileInfoController extends BaseController<RagFileInfo, IRagFileInfoService> {
    @Autowired
    private IRagFileInfoService ragFileInfoService;

    /**
     * 分页列表查询
     *
     * @param ragFileInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "知识库文档管理-分页列表查询")
    @Operation(summary = "知识库文档管理-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(RagFileInfo ragFileInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RagFileInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragFileInfo, req.getParameterMap());
        Page<RagFileInfo> page = new Page<RagFileInfo>(pageNo, pageSize);
        IPage<RagFileInfo> pageList = ragFileInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param ragFileInfo
     * @return
     */
    @AutoLog(value = "知识库文档管理-添加")
    @Operation(summary = "知识库文档管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody RagFileInfo ragFileInfo) {
        Boolean flag = ragFileInfoService.save(ragFileInfo);
        if (flag) {
            return Result.OK("添加成功！");
        } else {
            return Result.error(200,"添加失败！");
        }

    }

    /**
     * 编辑
     *
     * @param ragFileInfo
     * @return
     */
    @AutoLog(value = "知识库文档管理-编辑")
    @Operation(summary = "知识库文档管理-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody RagFileInfo ragFileInfo) {
        Boolean flag = ragFileInfoService.updateById(ragFileInfo);
        if (flag) {
            return Result.OK("编辑成功！");
        } else {
            return Result.error(200,"编辑失败！");
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识库文档管理-通过id删除")
    @Operation(summary = "知识库文档管理-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true) String id) {
        Boolean flag = ragFileInfoService.removeById(id);
        if (flag) {
            return Result.OK("删除成功！");
        } else {
            return Result.error(200,"删除失败！");
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "知识库文档管理-批量删除")
    @Operation(summary = "知识库文档管理-批量删除")
    @RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true)
                                 @ApiParameter(name = "ids", description = "IDs", required = true) String ids) {
        Boolean flag = this.ragFileInfoService.removeByIds(Arrays.asList(ids.split(",")));
        if (flag) {
            return Result.OK("批量删除成功！");
        } else {
            return Result.error("批量删除失败！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识库文档管理-通过id查询")
    @Operation(summary = "知识库文档管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true) String id) {
        RagFileInfo ragFileInfo = ragFileInfoService.getById(id);
        return Result.OK(ragFileInfo);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param ragFileInfo
     * @param fileName
     */
    @AutoLog(value = "知识库文档管理-文件流导出")
    @Operation(summary = "知识库文档管理-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, RagFileInfo ragFileInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("知识库文档管理"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ragFileInfoService.exportData(response.getOutputStream(), request.getParameterMap(), ragFileInfo);
            return Result.OK("导出成功");
        } catch (IOException e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }

    /**
     * 不支持文件流的情况下直接使用该方式
     * base64文件
     *
     * @param request
     * @param ragFileInfo
     */
    @AutoLog(value = "知识库文档管理-base64方式导出")
    @Operation(summary = "知识库文档管理-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, RagFileInfo ragFileInfo) {
        try {
            String data = ragFileInfoService.exportDataToBase64(request.getParameterMap(), ragFileInfo);
            return Result.OK("导出成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }


    /**
     * 研究院:文档上传
     *
     * @param ragId
     * @param file
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档上传")
    @Operation(summary = "知识库文档管理-研究院:文档上传")
    @RequestMapping(value = "/uploadFileByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> uploadFileByYanjiuyuan(@RequestParam
                                            @ApiParameter(name = "ragId", description = "知识库ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                    String ragId,
                                            @RequestParam
                                            @ApiParameter(name = "file", description = "文件流", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                    MultipartFile file) {
        return ragFileInfoService.uploadFileByYanjiuyuan(ragId, file);
    }

    /**
     * 研究院:文档查询
     *
     * @param pageNo
     * @param pageSize
     * @param fileName
     * @param ragId
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档查询")
    @Operation(summary = "知识库文档管理-研究院:文档查询")
    @RequestMapping(value = "/getFileListByYanjiuyuan", method = {RequestMethod.GET})
    public Result<?> getFileListByYanjiuyuan(@RequestParam(name = "pageNo", defaultValue = "1")
                                             @ApiParameter(name = "pageNo", description = "页码")
                                                     String pageNo,
                                             @RequestParam(name = "pageSize", defaultValue = "10")
                                             @ApiParameter(name = "pageSize", description = "数量")
                                                     String pageSize,
                                             @RequestParam(required = false)
                                             @ApiParameter(name = "fileName", description = "文档名称")
                                                     String fileName,
                                             @RequestParam
                                             @ApiParameter(name = "ragId", description = "知识库ID")
                                                     String ragId) {
        return ragFileInfoService.getFileListByYanjiuyuan(pageNo, pageSize, fileName, ragId);
    }

    /**
     * 研究院:文档解析
     *
     * @param fileParsingParamVO
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档解析")
    @Operation(summary = "知识库文档管理-研究院:文档解析")
    @RequestMapping(value = "/ragFileParsingByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> ragFileParsing(@RequestBody FileParsingParamVO fileParsingParamVO) {
        return ragFileInfoService.ragFileParsingByYanjiuyuan(fileParsingParamVO.getDocIdList(), fileParsingParamVO.getDeleteFlag(), fileParsingParamVO.getRunModel());
    }

    /**
     * 知识库文件状态切换
     *
     * @param ragFileSwitchBO
     * @return
     */
    @AutoLog(value = "知识库文档管理-知识库文件状态切换")
    @Operation(summary = "知识库文档管理-知识库文件状态切换")
    @RequestMapping(value = "/ragFileSwitch", method = {RequestMethod.POST})
    public Result<?> ragFileSwitch(@RequestBody RagFileSwitchBO ragFileSwitchBO) {
        return ragFileInfoService.ragFileSwitch(ragFileSwitchBO.getDocId(), ragFileSwitchBO.getStatus());
    }

    /**
     * 研究院:文档下载
     *
     * @param fileId
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档下载")
    @Operation(summary = "知识库文档管理-研究院:文档下载")
    @RequestMapping(value = "/downLoadRagFileByYanjiuyuan", method = {RequestMethod.GET})
    public void downLoadRagFileByYanjiuyuan(@RequestParam
                                                 @ApiParameter(name = "fileId", description = "文档ID")
                                                         String fileId,
                                                 HttpServletResponse httpServletResponse) {
        ServletOutputStream servletOutputStream = null;
        try {
            HttpResponse httpResponse = ragFileInfoService.downLoadRagFileByYanjiuyuan(fileId);
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=file" );
            servletOutputStream = httpServletResponse.getOutputStream();
            byte[] tmp = httpResponse.bodyBytes();
            int len = 0;
            int size = tmp.length;
            int step = 1024;
            while(len < size){
                if(len + step >= size){
                    step = size - len -1;
                    servletOutputStream.write(tmp, len, len + step);
                    break;
                }else{
                    servletOutputStream.write(tmp, len, len + step);
                    len += step;
                }
            }
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                servletOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 研究院:文档删除
     *
     * @param docIdLis
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档删除")
    @Operation(summary = "知识库文档管理-研究院:文档删除")
    @RequestMapping(value = "/deleteRagFileByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> deleteRagFileByYanjiuyuan(@RequestBody
                                               @ApiParameter(name = "docIdLis", description = "文档IDs", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                       List<String> docIdLis) {
        return ragFileInfoService.deleteRagFileByYanjiuyuan(docIdLis);
    }

    /**
     * 研究院:文档切片查询
     *
     * @param pageNo
     * @param pageSize
     * @param docId
     * @param keyWord
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档切片查询")
    @Operation(summary = "知识库文档管理-研究院:文档切片查询")
    @RequestMapping(value = "/getDocumentSlicingListByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> getDocumentSlicingListByYanjiuyuan(@RequestParam(name = "pageNo", defaultValue = "1")
                                                        @ApiParameter(name = "pageNo", description = "页码", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                String pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10")
                                                        @ApiParameter(name = "pageSize", description = "分页大小", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                String pageSize,
                                                        @RequestParam(name = "docId")
                                                        @ApiParameter(name = "docId", description = "文档ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                String docId,
                                                        @RequestParam(name = "keyWord",required = false)
                                                        @ApiParameter(name = "keyWord", description = "查询词语", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                String keyWord) {
        return ragFileInfoService.getDocumentSlicingListByYanjiuyuan(pageNo, pageSize, docId, keyWord);
    }

    /**
     * 研究院:文档切片状态切换
     *
     * @param status
     * @param docId
     * @param slicingIdList
     * @return
     */
    @AutoLog(value = "知识库文档管理-研究院:文档切片状态切换")
    @Operation(summary = "知识库文档管理-研究院:文档切片状态切换")
    @RequestMapping(value = "/documentSlicingStatusSwitchByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> documentSlicingStatusSwitchByYanjiuyuan(@RequestParam(name = "status")
                                                             @ApiParameter(name = "status", description = "状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                     Integer status,
                                                             @RequestParam(name = "docId")
                                                             @ApiParameter(name = "docId", description = "文档ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                     String docId,
                                                             @RequestParam(name = "slicingIdList",required = false)
                                                             @ApiParameter(name = "slicingIdList", description = "切分数据", location = ApiEnum.PARAMETER_LOCATION_BODY)
                                                                     List<String> slicingIdList) {
        return ragFileInfoService.documentSlicingStatusSwitchByYanjiuyuan(status, docId, slicingIdList);
    }

}
