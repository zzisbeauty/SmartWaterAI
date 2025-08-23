package com.hanwei.asr.controller;

import com.hanwei.asr.entity.AsrKeyWord;
import com.hanwei.asr.service.IAsrKeyWordService;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: ASR关键词信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "ASR关键词信息")
@RestController
@RequestMapping("/asr/asrKeyWord")
@AutoRegister
@ApiKind(value = "AI数字助手-ASR管理-关键词管理")
public class AsrKeyWordController extends BaseController<AsrKeyWord, IAsrKeyWordService> {
    @Autowired
    private IAsrKeyWordService asrKeyWordService;


    /**
     * 添加
     *
     * @param asrKeyWord
     * @return
     */
    @AutoLog(value = "ASR关键词信息-添加")
    @Operation(summary = "ASR关键词信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody AsrKeyWord asrKeyWord) {
        try {
            Boolean flag = asrKeyWordService.save(asrKeyWord);
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
     * @param asrKeyWord
     * @return
     */
    @AutoLog(value = "ASR关键词信息-编辑")
    @Operation(summary = "ASR关键词信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody AsrKeyWord asrKeyWord) {
        Boolean flag = asrKeyWordService.updateById(asrKeyWord);
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
    @AutoLog(value = "ASR关键词信息-通过id删除")
    @Operation(summary = "ASR关键词信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "关键词ID", required = true, demovalue = "1",
                                    location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        Boolean flag = asrKeyWordService.removeById(id);
        if (flag) {
            return Result.OK("删除成功！");
        } else {
            return Result.error(200,"删除失败！");
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "ASR关键词信息-通过id查询")
    @Operation(summary = "ASR关键词信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "关键词ID", required = true, demovalue = "1",
                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String id) {
        AsrKeyWord asrKeyWord = asrKeyWordService.getById(id);
        return Result.OK(asrKeyWord);
    }

}
