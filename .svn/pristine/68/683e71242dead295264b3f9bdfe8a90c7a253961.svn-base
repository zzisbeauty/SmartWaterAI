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
import com.hanwei.core.base.vo.DictModel;
import com.hanwei.core.common.api.impl.SysBaseApiImpl;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.system.entity.Dict;
import com.hanwei.system.service.IDictService;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @Description: 字典表
 * @Author: hanwei
 * @Date: 2025-05-13
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "字典表")
@RestController
@RequestMapping("/system/dict")
@AutoRegister
@ApiKind(value = "AI数字助手-系统管理-字典管理")
public class DictController extends BaseController<Dict, IDictService> {
    @Autowired
    private IDictService dictService;

    @Autowired
    private SysBaseApiImpl sysBaseApi;

    /**
     * 分页列表查询
     *
     * @param dict
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "字典表-分页列表查询")
    @Operation(summary = "字典表-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(Dict dict,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dict> queryWrapper = QueryGenerator.initQueryWrapper(dict, req.getParameterMap());
        Page<Dict> page = new Page<Dict>(pageNo, pageSize);
        IPage<Dict> pageList = dictService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param dict
     * @return
     */
    @AutoLog(value = "字典表-添加")
    @Operation(summary = "字典表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody Dict dict) {
        try {
            Boolean flag = dictService.save(dict);
            if (flag) {
                return Result.OK("添加成功！");
            } else {
                return Result.error("添加失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }

    }

    /**
     * 编辑
     *
     * @param dict
     * @return
     */
    @AutoLog(value = "字典表-编辑")
    @Operation(summary = "字典表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody Dict dict) {
        Boolean flag = dictService.updateById(dict);
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
    @AutoLog(value = "字典表-通过id删除")
    @Operation(summary = "字典表-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true)
                                    String id) {
        return dictService.removeDictById(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "字典表-通过id查询")
    @Operation(summary = "字典表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true)
                                       String id) {
        Dict dict = dictService.getById(id);
        return Result.OK(dict);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param dict
     * @param fileName
     */
    @AutoLog(value = "字典表-文件流导出")
    @Operation(summary = "字典表-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, Dict dict, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("字典表"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            dictService.exportData(response.getOutputStream(), request.getParameterMap(), dict);
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
     * @param dict
     */
    @AutoLog(value = "字典表-base64方式导出")
    @Operation(summary = "字典表-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, Dict dict) {
        try {
            String data = dictService.exportDataToBase64(request.getParameterMap(), dict);
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
    @AutoLog(value = "字典表-excel文件导入")
    @Operation(summary = "字典表-excel文件导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestParam MultipartFile file) {
        return dictService.importData(file);
    }

    /**
     * 下载导入模板
     *
     * @return
     */
    @Operation(summary = "字典表-下载导入模板")
    @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
    public Result<?> getImportTemplate() {
        try {
            String data = dictService.getImportTemplate();
            return Result.OK("导出模板成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出模板失败", e.getMessage());
        }
    }

    /**
     * 获取数据字典
     *
     * @param code
     * @return
     */
    @GetMapping("/queryDictItemsByCode")
    @Operation(summary = "字典表-获取数据字典")
    Result queryDictItemsByCode(@RequestParam("code")
                                @ApiParameter(name = "code", description = "源编码", required = true)
                                        String code,
                                @RequestParam(value ="table",required = false)
                                @ApiParameter(name = "table", description = "目标表")
                                        String table,
                                @RequestParam(value = "text",required = false)
                                @ApiParameter(name = "text", description = "目标字段")
                                        String text,
                                @RequestParam(value ="keys",required = false)
                                @ApiParameter(name = "keys", description = "限制id")
                                        String keys) {
        List<DictModel> list = sysBaseApi.queryDictItemsByCode(code, table, text, keys);
        return Result.OK(list);
    }

    /**
     * 获取有效的数据字典
     *
     * @param code
     * @return
     */
    @GetMapping("/queryEnableDictItemsByCode")
    @Operation(summary = "字典表-获取有效的数据字典")
    List<DictModel> queryEnableDictItemsByCode(@RequestParam("code")
                                               @ApiParameter(name = "code", description = "字典编码", required = true)
                                                       String code) {
        return sysBaseApi.queryEnableDictItemsByCode(code);
    }

}
