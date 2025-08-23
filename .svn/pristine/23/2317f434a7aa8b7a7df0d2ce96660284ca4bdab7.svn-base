package com.hanwei.application.controller;

import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.application.service.IApplicationConfigService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 应用配置信息表
 * @Author: hanwei
 * @Date: 2025-05-28
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "应用配置信息表")
@RestController
@RequestMapping("/application/applicationConfig")
@AutoRegister
@ApiKind(value = "AI数字助手-应用管理-应用配置管理")
public class ApplicationConfigController extends BaseController<ApplicationConfig, IApplicationConfigService> {
    @Autowired
    private IApplicationConfigService applicationConfigService;

    /**
     * 编辑数字人配置信息
     *
     * @param digitalHumanServiceConfigId
     * @return
     */
    @AutoLog(value = "应用配置信息表-编辑数字人配置信息")
    @Operation(summary = "应用配置信息表-编辑数字人配置信息")
    @RequestMapping(value = "/editAppDigitalHumanConfig", method = {RequestMethod.POST})
    public Result<?> editAppDigitalHumanConfig(@RequestParam
                                               @ApiParameter(name = "applicationId", description = "应用ID", required = true, demovalue = "1",
                                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String applicationId,
                                               @RequestParam
                                               @ApiParameter(name = "digitalHumanServiceConfigId", description = "数字人配置ID", required = true, demovalue = "1",
                                                       location = ApiEnum.PARAMETER_LOCATION_QUERY) String digitalHumanServiceConfigId) {
        return applicationConfigService.editAppDigitalHumanConfig(applicationId, digitalHumanServiceConfigId);
    }

    /**
     * 编辑Asr配置信息
     *
     * @param asrConfigId
     * @return
     */
    @AutoLog(value = "应用配置信息表-编辑Asr配置信息")
    @Operation(summary = "应用配置信息表-编辑Asr配置信息")
    @RequestMapping(value = "/editAppAsrConfig", method = {RequestMethod.POST})
    public Result<?> editAppAsrConfig(@RequestParam
                                      @ApiParameter(name = "applicationId", description = "应用ID", required = true, demovalue = "1",
                                              location = ApiEnum.PARAMETER_LOCATION_QUERY) String applicationId,
                                      @RequestParam
                                      @ApiParameter(name = "asrConfigId", description = "ASR配置ID", required = true, demovalue = "1",
                                              location = ApiEnum.PARAMETER_LOCATION_QUERY) String asrConfigId) {
        return applicationConfigService.editAppAsrConfig(applicationId, asrConfigId);
    }

    /**
     * 编辑模型配置信息
     *
     * @param modelId
     * @return
     */
    @AutoLog(value = "应用配置信息表-编辑模型配置信息")
    @Operation(summary = "应用配置信息表-编辑模型配置信息")
    @RequestMapping(value = "/editAppModel", method = {RequestMethod.POST})
    public Result<?> editAppModel(@RequestParam
                                  @ApiParameter(name = "applicationId", description = "应用ID", required = true, demovalue = "1",
                                          location = ApiEnum.PARAMETER_LOCATION_QUERY) String applicationId,
                                  @RequestParam
                                  @ApiParameter(name = "modelId", description = "模型ID", required = true, demovalue = "1",
                                          location = ApiEnum.PARAMETER_LOCATION_QUERY) String modelId) {
        return applicationConfigService.editAppModel(applicationId, modelId);
    }

    /**
     * 编辑知识库配置信息
     *
     * @param ragIds
     * @return
     */
    @AutoLog(value = "应用配置信息表-编辑知识库配置信息")
    @Operation(summary = "应用配置信息表-编辑知识库配置信息")
    @RequestMapping(value = "/editAppRag", method = {RequestMethod.POST})
    public Result<?> editAppRag(@RequestParam
                                @ApiParameter(name = "applicationId", description = "应用ID", required = true, demovalue = "1",
                                        location = ApiEnum.PARAMETER_LOCATION_QUERY) String applicationId,
                                @RequestParam
                                @ApiParameter(name = "ragIds", description = "知识库IDS", required = true, demovalue = "1",
                                        location = ApiEnum.PARAMETER_LOCATION_QUERY) String ragIds) {
        return applicationConfigService.editAppRag(applicationId, ragIds);
    }


}
