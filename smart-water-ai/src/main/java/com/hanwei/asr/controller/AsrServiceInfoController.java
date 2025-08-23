package com.hanwei.asr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.asr.entity.AsrServiceInfo;
import com.hanwei.asr.service.IAsrServiceInfoService;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;


/**
 * @Description: ASR服务信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "ASR服务信息")
@RestController
@RequestMapping("/asr/asrServiceInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-ASR管理-ASR服务渠道管理")
public class AsrServiceInfoController extends BaseController<AsrServiceInfo, IAsrServiceInfoService> {
    @Autowired
    private IAsrServiceInfoService asrServiceInfoService;

    /**
     * 分页列表查询
     *
     * @param asrServiceInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "ASR服务信息-分页列表查询")
    @Operation(summary = "ASR服务信息-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(AsrServiceInfo asrServiceInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AsrServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(asrServiceInfo, req.getParameterMap());
        Page<AsrServiceInfo> page = new Page<AsrServiceInfo>(pageNo, pageSize);
        IPage<AsrServiceInfo> pageList = asrServiceInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param asrServiceInfo
     * @return
     */
    @AutoLog(value = "ASR服务信息-添加")
    @Operation(summary = "ASR服务信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody AsrServiceInfo asrServiceInfo) {
        try {
            Boolean flag = asrServiceInfoService.save(asrServiceInfo);
            if (flag) {
                return Result.OK("添加成功！");
            } else {
                return Result.error(200,"添加失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }

    }

    /**
     * 编辑
     *
     * @param asrServiceInfo
     * @return
     */
    @AutoLog(value = "ASR服务信息-编辑")
    @Operation(summary = "ASR服务信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody AsrServiceInfo asrServiceInfo) {
        Boolean flag = asrServiceInfoService.updateById(asrServiceInfo);
        if (flag) {
            return Result.OK("编辑成功！");
        } else {
            return Result.error("编辑失败！");
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ASR服务信息-通过id删除")
    @Operation(summary = "ASR服务信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ASR服务ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return asrServiceInfoService.removeAsrServiceInfoById(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ASR服务信息-通过id查询")
    @Operation(summary = "ASR服务信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ASR服务ID", required = true, demovalue = "1",
                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        AsrServiceInfo asrServiceInfo = asrServiceInfoService.getById(id);
        return Result.OK(asrServiceInfo);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param asrServiceInfo
     * @param fileName
     */
    @AutoLog(value = "ASR服务信息-文件流导出")
    @Operation(summary = "ASR服务信息-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, AsrServiceInfo asrServiceInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("ASR服务信息"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            asrServiceInfoService.exportData(response.getOutputStream(), request.getParameterMap(), asrServiceInfo);
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
     * @param asrServiceInfo
     */
    @AutoLog(value = "ASR服务信息-base64方式导出")
    @Operation(summary = "ASR服务信息-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, AsrServiceInfo asrServiceInfo) {
        try {
            String data = asrServiceInfoService.exportDataToBase64(request.getParameterMap(), asrServiceInfo);
            return Result.OK("导出成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }

    /**
     * 通过excel导入数据
     *
     * @param file
     * @return
     */
    @AutoLog(value = "ASR服务信息-excel文件导入")
    @Operation(summary = "ASR服务信息-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam MultipartFile file) {
        return asrServiceInfoService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "ASR服务信息-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = asrServiceInfoService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

}
