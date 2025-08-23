package com.hanwei.system.controller;

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
import com.hanwei.system.entity.DictItem;
import com.hanwei.system.service.IDictItemService;
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
import java.util.Arrays;
import java.util.Optional;


/**
 * @Description: 字典明细表表
 * @Author: hanwei
 * @Date: 2025-05-13
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "字典明细表表")
@RestController
@RequestMapping("/system/dictItem")
@AutoRegister
@ApiKind(value = "AI数字助手-系统管理-字典项目管理")
public class DictItemController extends BaseController<DictItem, IDictItemService> {
    @Autowired
    private IDictItemService dictItemService;

    /**
     * 分页列表查询
     *
     * @param dictItem
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "字典明细表表-分页列表查询")
    @Operation(summary = "字典明细表表-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(DictItem dictItem,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<DictItem> queryWrapper = QueryGenerator.initQueryWrapper(dictItem, req.getParameterMap());
        Page<DictItem> page = new Page<DictItem>(pageNo, pageSize);
        IPage<DictItem> pageList = dictItemService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param dictItem
     * @return
     */
    @AutoLog(value = "字典明细表表-添加")
    @Operation(summary = "字典明细表表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody DictItem dictItem) {
        Boolean flag = dictItemService.save(dictItem);
        if (flag) {
            return Result.OK("添加成功！");
        } else {
            return Result.error("添加失败！");
        }

    }

    /**
     * 编辑
     *
     * @param dictItem
     * @return
     */
    @AutoLog(value = "字典明细表表-编辑")
    @Operation(summary = "字典明细表表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody DictItem dictItem) {
        Boolean flag = dictItemService.updateById(dictItem);
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
    @AutoLog(value = "字典明细表表-通过id删除")
    @Operation(summary = "字典明细表表-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true)
                                    String id) {
        Boolean flag = dictItemService.removeById(id);
        if (flag) {
            return Result.OK("删除成功！");
        } else {
            return Result.error("删除失败！");
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "字典明细表表-批量删除")
    @Operation(summary = "字典明细表表-批量删除")
    @RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true)
                                 @ApiParameter(name = "ids", description = "IDs", required = true)
                                         String ids) {
        Boolean flag = this.dictItemService.removeByIds(Arrays.asList(ids.split(",")));
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
    @AutoLog(value = "字典明细表表-通过id查询")
    @Operation(summary = "字典明细表表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true)
                                       String id) {
        DictItem dictItem = dictItemService.getById(id);
        return Result.OK(dictItem);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param dictItem
     * @param fileName
     */
    @AutoLog(value = "字典明细表表-文件流导出")
    @Operation(summary = "字典明细表表-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, DictItem dictItem, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("字典明细表表"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            dictItemService.exportData(response.getOutputStream(), request.getParameterMap(), dictItem);
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
     * @param dictItem
     */
    @AutoLog(value = "字典明细表表-base64方式导出")
    @Operation(summary = "字典明细表表-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, DictItem dictItem) {
        try {
            String data = dictItemService.exportDataToBase64(request.getParameterMap(), dictItem);
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
    @AutoLog(value = "字典明细表表-excel文件导入")
    @Operation(summary = "字典明细表表-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam MultipartFile file) {
        return dictItemService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "字典明细表表-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = dictItemService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

}
