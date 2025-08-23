package com.hanwei.digitalhuman.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceInfo;
import com.hanwei.digitalhuman.service.IServiceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Optional;


/**
 * @Description: 数字人服务信息
 * @Author: hanwei
 * @Date: 2025-05-09
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "数字人服务信息")
@RestController
@RequestMapping("/digitalhuman/serviceInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-数字人管理-数字人服务渠道管理")
public class ServiceInfoController extends BaseController<ServiceInfo, IServiceInfoService> {
    @Autowired
    private IServiceInfoService serviceInfoService;

    /**
     * 分页列表查询
     *
     * @param serviceInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "数字人服务信息-分页列表查询")
    @Operation(summary = "数字人服务信息-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(ServiceInfo serviceInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(serviceInfo, req.getParameterMap());
        Page<ServiceInfo> page = new Page<ServiceInfo>(pageNo, pageSize);
        IPage<ServiceInfo> pageList = serviceInfoService.page(page, queryWrapper);
        return Result.OK(pageList);

    }

    /**
     * 添加
     *
     * @param serviceInfo
     * @return
     */
    @AutoLog(value = "数字人服务信息-添加")
    @Operation(summary = "数字人服务信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody ServiceInfo serviceInfo) {
        try {
            Boolean flag = serviceInfoService.save(serviceInfo);
            if (flag) {
                return Result.OK("添加成功！");
            } else {
                return Result.error(200,"添加失败！");
            }
        } catch (Exception e) {
            return Result.error(200,e.getMessage());
        }

    }

    /**
     * 编辑
     *
     * @param serviceInfo
     * @return
     */
    @AutoLog(value = "数字人服务信息-编辑")
    @Operation(summary = "数字人服务信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody ServiceInfo serviceInfo) {
        if (StrUtil.isEmpty(serviceInfo.getId())) {
            return Result.error(200,"id不能为空");
        }
        Boolean flag = serviceInfoService.updateById(serviceInfo);
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
    @AutoLog(value = "数字人服务信息-通过id删除")
    @Operation(summary = "数字人服务信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true) String id) {
        return serviceInfoService.removeServiceInfoById(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "数字人服务信息-通过id查询")
    @Operation(summary = "数字人服务信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true) String id) {
        ServiceInfo serviceInfo = serviceInfoService.getById(id);
        return Result.OK(serviceInfo);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param serviceInfo
     * @param fileName
     */
    @AutoLog(value = "数字人服务信息-文件流导出")
    @Operation(summary = "数字人服务信息-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, ServiceInfo serviceInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("数字人服务信息"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            serviceInfoService.exportData(response.getOutputStream(), request.getParameterMap(), serviceInfo);
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
     * @param serviceInfo
     */
    @AutoLog(value = "数字人服务信息-base64方式导出")
    @Operation(summary = "数字人服务信息-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, ServiceInfo serviceInfo) {
        try {
            String data = serviceInfoService.exportDataToBase64(request.getParameterMap(), serviceInfo);
            return Result.OK("导出成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }
}
