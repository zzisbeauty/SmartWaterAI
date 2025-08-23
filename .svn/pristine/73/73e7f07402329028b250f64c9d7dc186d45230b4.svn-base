package com.hanwei.application.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.application.service.IApplicationInfoService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;


/**
 * @Description: 应用基础信息表
 * @Author: hanwei
 * @Date: 2025-05-28
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "应用基础信息表")
@RestController
@RequestMapping("/application/applicationInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-应用管理-基础信息管理")
public class ApplicationInfoController extends BaseController<ApplicationInfo, IApplicationInfoService> {
    @Autowired
    private IApplicationInfoService applicationInfoService;

    /**
     * 分页列表查询
     *
     * @param applicationInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "应用基础信息表-分页列表查询")
    @Operation(summary = "应用基础信息表-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(ApplicationInfo applicationInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1") Integer pageNo,

                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ApplicationInfo> queryWrapper = QueryGenerator.initQueryWrapper(applicationInfo, req.getParameterMap());
        Page<ApplicationInfo> page = new Page<ApplicationInfo>(pageNo, pageSize);
        IPage<ApplicationInfo> pageList = applicationInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param applicationInfo
     * @return
     */
    @AutoLog(value = "应用基础信息表-添加")
    @Operation(summary = "应用基础信息表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ApplicationInfo applicationInfo) {
        return applicationInfoService.saveApplicationInfo(applicationInfo);
    }

    /**
     * 编辑
     *
     * @param applicationInfo
     * @return
     */
    @AutoLog(value = "应用基础信息表-编辑")
    @Operation(summary = "应用基础信息表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody ApplicationInfo applicationInfo) {
        Boolean flag = applicationInfoService.updateApplicationInfo(applicationInfo);
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
    @AutoLog(value = "应用基础信息表-通过id删除")
    @Operation(summary = "应用基础信息表-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return applicationInfoService.removeByApplicationId(id);
    }


    /**
     * 通过id查询应用全部信息
     *
     * @param id
     * @return
     */
    @Operation(summary = "应用基础信息表-通过id查询应用全部信息")
    @GetMapping(value = "/queryAllInfoById")
    public Result<?> queryAllInfoById(@RequestParam(name = "id")
                                      @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                              location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return applicationInfoService.getApplicationAllInfo(id);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param applicationInfo
     * @param fileName
     */
    @AutoLog(value = "应用基础信息表-文件流导出")
    @Operation(summary = "应用基础信息表-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, ApplicationInfo applicationInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("应用基础信息表"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            applicationInfoService.exportData(response.getOutputStream(), request.getParameterMap(), applicationInfo);
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
     * @param applicationInfo
     */
    @AutoLog(value = "应用基础信息表-base64方式导出")
    @Operation(summary = "应用基础信息表-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, ApplicationInfo applicationInfo) {
        try {
            String data = applicationInfoService.exportDataToBase64(request.getParameterMap(), applicationInfo);
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
    @AutoLog(value = "应用基础信息表-excel文件导入")
    @Operation(summary = "应用基础信息表-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam MultipartFile file) {
        return applicationInfoService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "应用基础信息表-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = applicationInfoService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

}
