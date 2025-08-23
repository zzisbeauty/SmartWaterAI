package com.hanwei.application.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.application.mapper.ApplicationConfigMapper;
import com.hanwei.application.service.IApplicationConfigService;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @Description: 应用配置信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Service
public class ApplicationConfigServiceImpl extends ServiceImpl<ApplicationConfigMapper, ApplicationConfig> implements IApplicationConfigService {

    /**
     * 根据应用id获取配置信息
     * @param id
     * @return
     */
    @Override
    public ApplicationConfig getByApplicationId(String id) {
        return getOne(new LambdaQueryWrapper<ApplicationConfig>().eq(ApplicationConfig::getApplicationId,id),false);
    }

    /**
     * 根据应用id删除配置信息
     * @param applicationId
     */
    @Override
    public void removeByApplicationId(String applicationId){
        remove(new LambdaQueryWrapper<ApplicationConfig>().eq(ApplicationConfig::getApplicationId,applicationId));
    }

    /**
     * 编辑数字人配置信息
     * @param applicationId
     * @param digitalHumanServiceConfigId
     * @return
     */
    @Override
    public Result<?> editAppDigitalHumanConfig(String applicationId, String digitalHumanServiceConfigId){
        ApplicationConfig applicationConfig = getByApplicationId(applicationId);
        if(Optional.ofNullable(applicationConfig).isEmpty()){
            applicationConfig = new ApplicationConfig();
            applicationConfig.setApplicationId(applicationId);
            applicationConfig.setDigitalHumanConfigId(digitalHumanServiceConfigId);
        }else{
            applicationConfig.setDigitalHumanConfigId(digitalHumanServiceConfigId);
        }
        save(applicationConfig);
        return Result.ok("保存成功");
    }

    /**
     * 编辑Asr配置信息
     * @param applicationId
     * @param asrConfigId
     * @return
     */
    @Override
    public Result<?> editAppAsrConfig(String applicationId, String asrConfigId){
        ApplicationConfig applicationConfig = getByApplicationId(applicationId);
        if(Optional.ofNullable(applicationConfig).isEmpty()){
            applicationConfig = new ApplicationConfig();
            applicationConfig.setApplicationId(applicationId);
            applicationConfig.setAsrConfigId(asrConfigId);
        }else{
            applicationConfig.setAsrConfigId(asrConfigId);
        }
        save(applicationConfig);
        return Result.ok("保存成功");
    }

    /**
     * 编辑模型配置信息
     * @param applicationId
     * @param modelId
     * @return
     */
    @Override
    public Result<?> editAppModel(String applicationId, String modelId){
        ApplicationConfig applicationConfig = getByApplicationId(applicationId);
        if(Optional.ofNullable(applicationConfig).isEmpty()){
            applicationConfig = new ApplicationConfig();
            applicationConfig.setApplicationId(applicationId);
            applicationConfig.setModelId(modelId);
        }else{
            applicationConfig.setModelId(modelId);
        }
        save(applicationConfig);
        return Result.ok("保存成功");
    }

    /**
     * 编辑知识库配置信息
     * @param applicationId
     * @param ragIds
     * @return
     */
    @Override
    public Result<?> editAppRag(String applicationId, String ragIds){
        ApplicationConfig applicationConfig = getByApplicationId(applicationId);
        if(Optional.ofNullable(applicationConfig).isEmpty()){
            applicationConfig = new ApplicationConfig();
            applicationConfig.setApplicationId(applicationId);
            applicationConfig.setRagIds(ragIds);
        }else{
            applicationConfig.setRagIds(ragIds);
        }
        save(applicationConfig);
        return Result.ok("保存成功");
    }
}
