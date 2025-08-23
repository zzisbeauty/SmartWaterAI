package com.hanwei.asr.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.asr.entity.AsrHotWord;
import com.hanwei.asr.entity.AsrKeyWord;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.hanwei.asr.service.IAsrServiceConfigService;
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
 * @Description: ASR实例配置信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "ASR实例配置信息")
@RestController
@RequestMapping("/asr/asrServiceConfig")
@AutoRegister
@ApiKind(value = "AI数字助手-ASR管理-ASR配置管理")
public class AsrServiceConfigController extends BaseController<AsrServiceConfig, IAsrServiceConfigService> {
    @Autowired
    private IAsrServiceConfigService asrServiceConfigService;

    /**
     * 分页列表查询
     *
     * @param asrServiceConfig
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-分页列表查询")
    @Operation(summary = "ASR实例配置信息-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(AsrServiceConfig asrServiceConfig,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1",
                                           location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AsrServiceConfig> queryWrapper = QueryGenerator.initQueryWrapper(asrServiceConfig, req.getParameterMap());
        Page<AsrServiceConfig> page = new Page<AsrServiceConfig>(pageNo, pageSize);
        IPage<AsrServiceConfig> pageList = asrServiceConfigService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param asrServiceConfig
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-添加")
    @Operation(summary = "ASR实例配置信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody AsrServiceConfig asrServiceConfig) {
        try {
            Boolean flag = asrServiceConfigService.save(asrServiceConfig);
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
     * @param asrServiceConfig
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-编辑")
    @Operation(summary = "ASR实例配置信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody AsrServiceConfig asrServiceConfig) {
        Boolean flag = asrServiceConfigService.updateById(asrServiceConfig);
        if (flag) {
            return Result.OK("编辑成功！");
        } else {
            return Result.error(200,"编辑失败！");
        }
    }

    /**
     * 启停状态变更
     *
     * @param asrServiceConfig
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-启停状态变更")
    @Operation(summary = "ASR实例配置信息-启停状态变更")
    @RequestMapping(value = "/switchStatus", method = {RequestMethod.POST})
    public Result<?> switchStatus(@RequestBody AsrServiceConfig asrServiceConfig) {
        Boolean flag = asrServiceConfigService.switchStatus(asrServiceConfig.getId(),asrServiceConfig.getStatus());
        if (flag) {
            return Result.OK("切换成功！");
        } else {
            return Result.error(200,"切换失败！");
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-通过id删除")
    @Operation(summary = "ASR实例配置信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "配置ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        return asrServiceConfigService.removeAsrConfigInfoById(id);
    }

    /**
     * 配置热词表
     *
     * @param id
     * @param asrHotWord
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-配置热词表")
    @Operation(summary = "ASR实例配置信息-配置热词表")
    @RequestMapping(value = "/addhotword", method = {RequestMethod.POST})
    public Result<?> AddHotWord(@RequestParam(name = "id", required = true)
                                @ApiParameter(name = "id", description = "配置ID", required = true, demovalue = "1",
                                        location = ApiEnum.PARAMETER_LOCATION_QUERY) String id, @Valid @RequestBody AsrHotWord asrHotWord) {
        try {
            return asrServiceConfigService.AddHotWord(id, asrHotWord);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }
    }

    /**
     * 配置关键词
     *
     * @param id
     * @param asrKeyWord
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-配置关键词")
    @Operation(summary = "ASR实例配置信息-配置关键词")
    @RequestMapping(value = "/addkeyword", method = {RequestMethod.POST})
    public Result<?> AddKeyWord(@RequestParam(name = "id", required = true)
                                @ApiParameter(name = "id", description = "配置ID", required = true, demovalue = "1",
                                        location = ApiEnum.PARAMETER_LOCATION_QUERY) String id, @Valid @RequestBody AsrKeyWord asrKeyWord) {
        try {
            return asrServiceConfigService.AddKeyWord(id, asrKeyWord);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ASR实例配置信息-通过id查询")
    @Operation(summary = "ASR实例配置信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "配置ID", required = true, demovalue = "1",
                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        AsrServiceConfig asrServiceConfig = asrServiceConfigService.getById(id);
        return Result.OK(asrServiceConfig);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param asrServiceConfig
     * @param fileName
     */
    @AutoLog(value = "ASR实例配置信息-文件流导出")
    @Operation(summary = "ASR实例配置信息-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, AsrServiceConfig asrServiceConfig, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("ASR实例配置信息"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            asrServiceConfigService.exportData(response.getOutputStream(), request.getParameterMap(), asrServiceConfig);
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
     * @param asrServiceConfig
     */
    @AutoLog(value = "ASR实例配置信息-base64方式导出")
    @Operation(summary = "ASR实例配置信息-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, AsrServiceConfig asrServiceConfig) {
        try {
            String data = asrServiceConfigService.exportDataToBase64(request.getParameterMap(), asrServiceConfig);
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
    @AutoLog(value = "ASR实例配置信息-excel文件导入")
    @Operation(summary = "ASR实例配置信息-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam MultipartFile file) {
        return asrServiceConfigService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "ASR实例配置信息-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = asrServiceConfigService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

}
