package com.hanwei.model.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.model.entity.LargeModelInfo;
import com.hanwei.model.service.ILargeModelInfoService;
import com.hanwei.rag.bo.MessageBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;


/**
 * @Description: 大模型基础信息
 * @Author: hanwei
 * @Date: 2025-05-26
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "大模型基础信息")
@RestController
@RequestMapping("/model/largeModelInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-模型管理-模型管理")
public class LargeModelInfoController extends BaseController<LargeModelInfo, ILargeModelInfoService> {
    @Autowired
    private ILargeModelInfoService largeModelInfoService;

    /**
     * 分页列表查询
     *
     * @param largeModelInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "大模型基础信息-分页列表查询")
    @Operation(summary = "大模型基础信息-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(LargeModelInfo largeModelInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<LargeModelInfo> queryWrapper = QueryGenerator.initQueryWrapper(largeModelInfo, req.getParameterMap());
        Page<LargeModelInfo> page = new Page<LargeModelInfo>(pageNo, pageSize);
        IPage<LargeModelInfo> pageList = largeModelInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    @AutoLog(value = "大模型基础信息-同步研究院模型信息接口")
    @Operation(summary = "大模型基础信息-同步研究院模型信息接口")
    @RequestMapping(value = "/synYjyModelInfo", method = {RequestMethod.GET})
    public Result<?> synYjyModelInfo() {
        try {
            largeModelInfoService.synYjyLLM();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("同步研究院模型信息失败" + e.getMessage());
        }
        return Result.OK("同步模型信息成功");
    }

    /**
     * 添加
     *
     * @param largeModelInfo
     * @return
     */
    @AutoLog(value = "大模型基础信息-添加")
    @Operation(summary = "大模型基础信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody LargeModelInfo largeModelInfo) {
        //来源为本地添加
        largeModelInfo.setSource(CommonConstant.MODEL_SOURCE_LOCAL);
        try {
            Boolean flag = largeModelInfoService.save(largeModelInfo);
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
     * @param largeModelInfo
     * @return
     */
    @AutoLog(value = "大模型基础信息-编辑")
    @Operation(summary = "大模型基础信息-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody LargeModelInfo largeModelInfo) {
        return largeModelInfoService.updateLargeModelInfo(largeModelInfo);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "大模型基础信息-通过id删除")
    @Operation(summary = "大模型基础信息-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(@RequestParam(name = "id", required = true)
                            @ApiParameter(name = "id", description = "ID", required = true) String id) {
        return largeModelInfoService.deleteLargeModelInfo(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "大模型基础信息-通过id查询")
    @Operation(summary = "大模型基础信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true) String id) {
        LargeModelInfo largeModelInfo = largeModelInfoService.getById(id);
        return Result.OK(largeModelInfo);
    }

    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     *
     * @param request
     * @param response
     * @param largeModelInfo
     * @param fileName
     */
    @AutoLog(value = "大模型基础信息-文件流导出")
    @Operation(summary = "大模型基础信息-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, LargeModelInfo largeModelInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("大模型基础信息"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            largeModelInfoService.exportData(response.getOutputStream(), request.getParameterMap(), largeModelInfo);
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
     * @param largeModelInfo
     */
    @AutoLog(value = "大模型基础信息-base64方式导出")
    @Operation(summary = "大模型基础信息-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, LargeModelInfo largeModelInfo) {
        try {
            String data = largeModelInfoService.exportDataToBase64(request.getParameterMap(), largeModelInfo);
            return Result.OK("导出成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }

    /**
     * 研究院:设置模型
     *
     * @param largeModelName
     * @return
     */
    @AutoLog(value = "大模型基础信息-研究院:设置模型")
    @Operation(summary = "大模型基础信息-研究院:设置模型")
    @RequestMapping(value = "/choiceLargeModelByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> choiceLargeModelByYanjiuyuan(@RequestBody
                                                  @ApiParameter(name = "largeModelName", description = "模型名称", required = true, location = ApiEnum.PARAMETER_LOCATION_BODY) String largeModelName) {
        return largeModelInfoService.choiceLargeModelByYanjiuyuan(largeModelName);
    }

    /**
     * 研究院:调用会话
     *
     * @param conversationId
     * @param messageBOList
     * @return
     */
    @AutoLog(value = "大模型基础信息-研究院:调用会话")
    @Operation(summary = "大模型基础信息-研究院:调用会话")
    @RequestMapping(value = "/modelDialogueByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> modelDialogueByYanjiuyuan(@RequestBody
                                               @ApiParameter(name = "conversationId", description = "会话ID", required = true, location = ApiEnum.PARAMETER_LOCATION_BODY) String conversationId,
                                               @RequestBody
                                               @ApiParameter(name = "messageBOList", description = "对话信息", required = true, location = ApiEnum.PARAMETER_LOCATION_BODY) List<MessageBO> messageBOList) {
        return largeModelInfoService.modelDialogueByYanjiuyuan(conversationId, messageBOList);
    }
}
