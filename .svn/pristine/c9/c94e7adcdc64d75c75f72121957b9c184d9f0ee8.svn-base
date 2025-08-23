package com.hanwei.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.api.entity.ApiRelationConfig;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.service.IApiRelationConfigService;
import com.hanwei.api.service.IApiServiceInfoService;
import com.hanwei.api.vo.ApiRelationConfigVO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 接口关联问题信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "接口关联问题信息")
@RestController
@RequestMapping("/api/apiRelationConfig")
@AutoRegister
@ApiKind(value = "AI数字助手-接口管理-关联问题管理")
public class ApiRelationConfigController extends BaseController<ApiRelationConfig, IApiRelationConfigService> {
    @Autowired
    private IApiRelationConfigService apiRelationConfigService;

    @Autowired
    private IApiServiceInfoService apiServiceInfoService;


    /**
     * 添加
     *
     * @param apiRelationConfig
     * @return
     */
    @AutoLog(value = "接口关联问题信息-添加")
    @Operation(summary = "接口关联问题信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ApiRelationConfig apiRelationConfig) {
        Boolean flag = apiRelationConfigService.save(apiRelationConfig);
        if (flag) {
            return Result.OK("添加成功！");
        } else {
            return Result.error(200, "添加失败！");
        }

    }

    /**
     * 编辑
     *
     * @param apiRelationConfig
     * @return
     */
    @AutoLog(value = "接口关联问题信息-编辑")
    @Operation(summary = "接口关联问题信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody ApiRelationConfig apiRelationConfig) {
        Boolean flag = apiRelationConfigService.updateById(apiRelationConfig);
        if (flag) {
            return Result.OK("编辑成功！");
        } else {
            return Result.error(200, "编辑失败！");
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "接口关联问题信息-通过id删除")
    @Operation(summary = "接口关联问题信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "接口ID", required = true) String id) {
        Boolean flag = apiRelationConfigService.removeById(id);
        if (flag) {
            return Result.OK("删除成功！");
        } else {
            return Result.error(200, "删除失败！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "接口关联问题信息-通过id查询")
    @Operation(summary = "接口关联问题信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "接口ID", required = true) String id) {
        ApiRelationConfig apiRelationConfig = apiRelationConfigService.getById(id);
        return Result.OK(apiRelationConfig);
    }

}
