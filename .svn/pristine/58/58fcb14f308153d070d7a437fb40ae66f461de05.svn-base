package com.hanwei.application.controller;

import com.hanwei.application.entity.ApplicationApiRelation;
import com.hanwei.application.service.IApplicationApiRelationService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @Description: 应用接口关系表
 * @Author: hanwei
 * @Date: 2025-05-28
 * @Version: V1.0
 */
@Slf4j
@Tag(name = "应用接口关系表")
@RestController
@RequestMapping("/application/applicationApiRelation")
@AutoRegister
@ApiKind(value = "AI数字助手-应用管理-API关系管理")
public class ApplicationApiRelationController extends BaseController<ApplicationApiRelation, IApplicationApiRelationService> {
    @Autowired
    private IApplicationApiRelationService applicationApiRelationService;


    /**
     * 编辑应用接口关联管理
     *
     * @param applicationId
     * @param apiIdList
     * @return
     */
    @AutoLog(value = "应用接口关系表-编辑应用接口关联管理")
    @Operation(summary = "应用接口关系表-编辑应用接口关联管理")
    @RequestMapping(value = "/editApplicationApiRelation", method = {RequestMethod.POST})
    public Result<?> editApplicationApiRelation(@RequestBody
                                                @ApiParameter(name = "applicationId", description = "应用ID", required = true,
                                                        location = ApiEnum.PARAMETER_LOCATION_BODY) String applicationId, @RequestBody
                                                @ApiParameter(name = "apiIdList", description = "接口ID数组", required = true,
                                                        location = ApiEnum.PARAMETER_LOCATION_BODY) List<String> apiIdList) {
        return applicationApiRelationService.updateByList(applicationId, apiIdList);
    }

}
