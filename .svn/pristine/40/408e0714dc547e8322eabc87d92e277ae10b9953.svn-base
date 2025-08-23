package com.hanwei.style.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.style.entity.StyleInfo;
import com.hanwei.style.service.IStyleInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 展示样式管理
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "展示样式管理")
@RestController
@RequestMapping("/style/styleInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-样式管理-样式信息")
public class StyleInfoController extends BaseController<StyleInfo, IStyleInfoService> {
    @Autowired
    private IStyleInfoService styleInfoService;

    /**
     * 分页列表查询
     *
     * @param styleInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "展示样式管理-分页列表查询")
    @Operation(summary = "展示样式管理-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(StyleInfo styleInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<StyleInfo> queryWrapper = QueryGenerator.initQueryWrapper(styleInfo, req.getParameterMap());
        Page<StyleInfo> page = new Page<StyleInfo>(pageNo, pageSize);
        IPage<StyleInfo> pageList = styleInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param styleInfo
     * @return
     */
    @AutoLog(value = "展示样式管理-添加")
    @Operation(summary = "展示样式管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody StyleInfo styleInfo) {
        try {
            Boolean flag = styleInfoService.saveStyleInfo(styleInfo);
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
     * @param styleInfo
     * @return
     */
    @AutoLog(value = "展示样式管理-编辑")
    @Operation(summary = "展示样式管理-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody StyleInfo styleInfo) {
        Boolean flag = styleInfoService.updateStyleInfoById(styleInfo);
        if (flag) {
            return Result.OK("编辑成功！");
        } else {
            return Result.error(200,"编辑失败！");
        }
    }

    /**
     * 保存为示例数据
     *
     * @param styleInfo
     * @return
     */
    @AutoLog(value = "展示样式管理-保存为示例数据")
    @Operation(summary = "展示样式管理-保存为示例数据")
    @RequestMapping(value = "/addSample", method = {RequestMethod.POST})
    public Result<?> addSample(@RequestBody StyleInfo styleInfo) {
        StyleInfo sample = new StyleInfo();
        sample.setId(styleInfo.getId());
        sample.setSampleData(styleInfo.getSampleData());
        Boolean flag = styleInfoService.updateById(sample);
        if (flag) {
            return Result.OK("保存示例数据成功！");
        } else {
            return Result.error(200,"保存示例数据失败！");
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "展示样式管理-通过id删除")
    @Operation(summary = "展示样式管理-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true)
                                    String id) {
        return styleInfoService.removeStyleById(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "展示样式管理-通过id查询")
    @Operation(summary = "展示样式管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true)
                                       String id) {
        StyleInfo styleInfo = styleInfoService.getById(id);
        return Result.OK(styleInfo);
    }

}
