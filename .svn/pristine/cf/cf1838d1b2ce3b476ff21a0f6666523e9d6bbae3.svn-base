package com.hanwei.api.controller;

import com.hanwei.api.entity.ApiQuestionConfig;
import com.hanwei.api.service.IApiQuestionConfigService;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 接口提问语信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "接口提问语信息")
@RestController
@RequestMapping("/api/apiQuestionConfig")
@AutoRegister
@ApiKind(value = "AI数字助手-接口管理-提问语管理")
public class ApiQuestionConfigController extends BaseController<ApiQuestionConfig, IApiQuestionConfigService> {
    @Autowired
    private IApiQuestionConfigService apiQuestionConfigService;



    /**
     * 添加
     *
     * @param apiQuestionConfig
     * @return
     */
    @AutoLog(value = "接口提问语信息-添加")
    @Operation(summary = "接口提问语信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ApiQuestionConfig apiQuestionConfig) {
        Boolean flag = apiQuestionConfigService.save(apiQuestionConfig);
        if (flag) {
            return Result.OK("添加成功！");
        } else {
            return Result.error(200,"添加失败！");
        }

    }

    /**
     * 编辑
     *
     * @param apiQuestionConfig
     * @return
     */
    @AutoLog(value = "接口提问语信息-编辑")
    @Operation(summary = "接口提问语信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody ApiQuestionConfig apiQuestionConfig) {
        Boolean flag = apiQuestionConfigService.updateById(apiQuestionConfig);
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
    @AutoLog(value = "接口提问语信息-通过id删除")
    @Operation(summary = "接口提问语信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        Boolean flag = apiQuestionConfigService.removeById(id);
        if (flag) {
            return Result.OK("删除成功！");
        } else {
            return Result.error("删除失败！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "接口提问语信息-通过id查询")
    @Operation(summary = "接口提问语信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "接口ID", required = true, demovalue = "1",
                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        ApiQuestionConfig apiQuestionConfig = apiQuestionConfigService.getById(id);
        return Result.OK(apiQuestionConfig);
    }

}
