package com.hanwei.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.core.common.api.vo.Result;


/**
 * @Description: 应用配置信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
public interface IApplicationConfigService extends IService<ApplicationConfig> {

    /**
     * 根据应用id获取配置信息
     * @param applicationId
     * @return
     */
    ApplicationConfig getByApplicationId(String applicationId);

    /**
     * 根据应用id删除配置信息
     * @param applicationId
     */
    void removeByApplicationId(String applicationId);

    /**
     * 编辑数字人配置信息
     * @param applicationId
     * @param digitalHumanServiceConfigId
     * @return
     */
    Result<?> editAppDigitalHumanConfig(String applicationId, String digitalHumanServiceConfigId);

    /**
     * 编辑Asr配置信息
     * @param applicationId
     * @param asrConfigId
     * @return
     */
    Result<?> editAppAsrConfig(String applicationId, String asrConfigId);

    /**
     * 编辑模型配置信息
     * @param applicationId
     * @param modelId
     * @return
     */
    Result<?> editAppModel(String applicationId, String modelId);

    /**
     * 编辑知识库配置信息
     * @param applicationId
     * @param ragIds
     * @return
     */
    Result<?> editAppRag(String applicationId, String ragIds);
}
