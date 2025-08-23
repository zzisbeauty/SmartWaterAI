package com.hanwei.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.service.IApiServiceInfoService;
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
import java.util.Arrays;
import java.util.Optional;


/**
 * @Description: api基本信息
 * @Author: hanwei
 * @Date: 2025-05-15
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "api基本信息")
@RestController
@RequestMapping("/api/apiServiceInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-接口管理-基础信息管理")
public class ApiServiceInfoController extends BaseController<ApiServiceInfo, IApiServiceInfoService> {
    @Autowired
    private IApiServiceInfoService apiServiceInfoService;

    /**
     * 分页列表查询
     *
     * @param apiServiceInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "api基本信息-分页列表查询")
    @Operation(summary = "api基本信息-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(ApiServiceInfo apiServiceInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1") Integer pageNo,

                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ApiServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(apiServiceInfo, req.getParameterMap());
        Page<ApiServiceInfo> page = new Page<ApiServiceInfo>(pageNo, pageSize);
        IPage<ApiServiceInfo> pageList = apiServiceInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 接口提问语信息
     * @param apiId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Operation(summary = "api基本信息-获取当前系统内所有提问语VO")
    @GetMapping(value = "/getQuestionPage")
    public Result<?> getQuestionPage(@RequestParam(name="apiId",required = false) String apiId,
                                     @RequestParam(name = "pageNo", defaultValue = "1")
                                     @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                             location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1") Integer pageNo,

                                     @RequestParam(name = "pageSize", defaultValue = "10")
                                     @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "10",
                                             location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10") Integer pageSize) {

        return Result.OK(apiServiceInfoService.getQuestionPage(apiId,pageNo,pageSize));
    }

    /**
     * 添加
     *
     * @param apiServiceInfo
     * @return
     */
    @AutoLog(value = "api基本信息-添加")
    @Operation(summary = "api基本信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody ApiServiceInfo apiServiceInfo) {
        try {
            return apiServiceInfoService.saveApiServiceAllInfo(apiServiceInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param apiServiceInfo
     * @return
     */
    @AutoLog(value = "api基本信息-编辑")
    @Operation(summary = "api基本信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody ApiServiceInfo apiServiceInfo) {
        return apiServiceInfoService.updateApiServiceInfo(apiServiceInfo);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "api基本信息-通过id删除")
    @Operation(summary = "api基本信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return apiServiceInfoService.removeApiServiceInfo(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "api基本信息-批量删除")
    @Operation(summary = "api基本信息-批量删除")
    @RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true)
                                 @ApiParameter(name = "ids", description = "接口ID", required = true, demovalue = "1",
                                         location = ApiEnum.PARAMETER_LOCATION_QUERY) String ids) {
        Boolean flag = this.apiServiceInfoService.removeApiServiceInfoIdList(Arrays.asList(ids.split(",")));
        if (flag) {
            return Result.OK("批量删除成功！");
        } else {
            return Result.error(200,"批量删除失败！");
        }
    }

    /**
     * 通过id查询api全部信息
     *
     * @param id
     * @return
     */
    @AutoLog(value = "api基本信息-查询api全部信息")
    @Operation(summary = "api基本信息-查询api全部信息")
    @GetMapping(value = "/queryAllApiInfoById")
    public Result<?> queryAllApiInfoById(@RequestParam(name = "id", required = true)
                                         @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                                 location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return Result.OK(apiServiceInfoService.queryAllApiInfoById(id));
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param apiServiceInfo
     * @param fileName
     */
    @AutoLog(value = "api基本信息-文件流导出")
    @Operation(summary = "api基本信息-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, ApiServiceInfo apiServiceInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("api基本信息"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            apiServiceInfoService.exportData(response.getOutputStream(), request.getParameterMap(), apiServiceInfo);
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
     * @param apiServiceInfo
     */
    @AutoLog(value = "api基本信息-base64方式导出")
    @Operation(summary = "api基本信息-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, ApiServiceInfo apiServiceInfo) {
        try {
            String data = apiServiceInfoService.exportDataToBase64(request.getParameterMap(), apiServiceInfo);
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
    @AutoLog(value = "api基本信息-excel文件导入")
    @Operation(summary = "api基本信息-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam
                                 @ApiParameter(name = "file", description = "导入文件", required = true, demovalue = "1",
                                         location = ApiEnum.PARAMETER_LOCATION_QUERY) MultipartFile file) {
        return apiServiceInfoService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "api基本信息-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = apiServiceInfoService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

}
