package com.hanwei.rag.controller;

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
import com.hanwei.rag.bo.ChoiceRagBO;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.service.IRagInfoService;
import com.hanwei.rag.vo.RagRecallVO;
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
import java.util.Optional;


/**
 * @Description: 知识库基础信息管理
 * @Author: hanwei
 * @Date: 2025-05-26
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "知识库基础信息管理")
@RestController
@RequestMapping("/tag/ragInfo")
@AutoRegister
@ApiKind(value = "AI数字助手-知识库管理-知识库信息")
public class RagInfoController extends BaseController<RagInfo, IRagInfoService> {
    @Autowired
    private IRagInfoService ragInfoService;

    /**
     * 分页列表查询
     *
     * @param ragInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-分页列表查询")
    @Operation(summary = "知识库基础信息管理-分页列表查询")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<?> queryPageList(RagInfo ragInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1")
                                   @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1", defaultvalue = "1")
                                           Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10")
                                   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1", defaultvalue = "10")
                                           Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RagInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragInfo, req.getParameterMap());
        Page<RagInfo> page = new Page<RagInfo>(pageNo, pageSize);
        IPage<RagInfo> pageList = ragInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }


    /**
     * 测试用：直接调用创建知识库方法
     * @param ragName 知识库名称
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-测试创建")
    @Operation(summary = "知识库基础信息管理-测试创建")
    @PostMapping(value = "/testAdd")
    public Result<?> testAdd(@RequestParam String ragName) {
        try {
            log.info("开始测试创建知识库，名称: " + ragName);
            // 创建 RagInfo 实体
            RagInfo ragInfo = new RagInfo();
            ragInfo.setName(ragName);
            ragInfo.setDescription("测试创建的知识库");
            // 调用 saveRagInfo 方法
            Result<?> result = ragInfoService.saveRagInfo(ragInfo);
            log.info("测试创建知识库结果: " + result);
            return result;
        } catch (Exception e) {
            log.error("测试创建知识库失败: " + e.getMessage(), e);
            return Result.error(200, "测试创建失败: " + e.getMessage());
        }
    }

    /**
     * 添加
     * @param ragInfo
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-添加")
    @Operation(summary = "知识库基础信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@Valid @RequestBody RagInfo ragInfo) {
        try {
            return ragInfoService.saveRagInfo(ragInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error(200,e.getMessage());
        }
    }






    /**
     * 编辑
     * @param ragInfo
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-编辑")
    @Operation(summary = "知识库基础信息管理-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.POST})
    public Result<?> edit(@RequestBody RagInfo ragInfo) {
        return ragInfoService.updateRagInfo(ragInfo);
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-通过id删除")
    @Operation(summary = "知识库基础信息管理-通过id删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result<?> delete(
            @RequestParam(name = "id", required = true)
            @ApiParameter(name = "id", description = "ID", required = true)
            String id) {
        return ragInfoService.removeRagInfoById(id);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-通过id查询")
    @Operation(summary = "知识库基础信息管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true)
                               @ApiParameter(name = "id", description = "ID", required = true) String id) {
        RagInfo ragInfo = ragInfoService.getById(id);
        return Result.OK(ragInfo);
    }


    /**
     * 支持文件流的情况下直接使用该方式
     * 文件流
     * @param request
     * @param response
     * @param ragInfo
     * @param fileName
     */
    @AutoLog(value = "知识库基础信息管理-文件流导出")
    @Operation(summary = "知识库基础信息管理-文件流导出")
    @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
    public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, RagInfo ragInfo, String fileName) {
        try {
            // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // URLEncoder.encode可以防止中文乱码
            fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("知识库基础信息管理"), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ragInfoService.exportData(response.getOutputStream(), request.getParameterMap(), ragInfo);
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
     * @param ragInfo
     */
    @AutoLog(value = "知识库基础信息管理-base64方式导出")
    @Operation(summary = "知识库基础信息管理-base64方式导出")
    @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
    public Result<?> exportXlsToBase64(HttpServletRequest request, RagInfo ragInfo) {
        try {
            String data = ragInfoService.exportDataToBase64(request.getParameterMap(), ragInfo);
            return Result.OK("导出成功", data);
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            return Result.error("导出失败", e.getMessage());
        }
    }


    /**
     * 知识库召回测试
     *
     * @param ragRecallVO
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-知识库召回测试")
    @Operation(summary = "知识库基础信息管理-知识库召回测试")
    @RequestMapping(value = "/ragRecall", method = {RequestMethod.POST})
    public Result<?> ragRecall(@RequestBody RagRecallVO ragRecallVO) {
        return ragInfoService.ragRecall(ragRecallVO);
    }

    /**
     * 获取知识库知识图谱
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识库基础信息管理-获取知识库知识图谱")
    @Operation(summary = "知识库基础信息管理-获取知识库知识图谱")
    @RequestMapping(value = "/getRagGraph", method = {RequestMethod.GET})
    public Result<?> getRagGraph(@RequestParam
                                 @ApiParameter(name = "id", description = "ID", required = true) String id) {
        return ragInfoService.getRagGraph(id);
    }

    /**
     * 研究院:设置知识库
     *
     * @param choiceRagBO
     * @return
     */
    @AutoLog(value = "大模型基础信息-研究院:设置知识库")
    @Operation(summary = "大模型基础信息-研究院:设置知识库")
    @RequestMapping(value = "/choiceRagByYanjiuyuan", method = {RequestMethod.POST})
    public Result<?> choiceRagByYanjiuyuan(@RequestBody ChoiceRagBO choiceRagBO) {
        return ragInfoService.choiceRagByYanjiuyuan(choiceRagBO);
    }
}
