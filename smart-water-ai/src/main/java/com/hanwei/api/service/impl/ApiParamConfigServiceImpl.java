package com.hanwei.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiParamConfig;
import com.hanwei.api.mapper.ApiParamConfigMapper;
import com.hanwei.api.service.IApiParamConfigService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Description: 接口参数信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApiParamConfigServiceImpl extends ServiceImpl<ApiParamConfigMapper, ApiParamConfig> implements IApiParamConfigService {

    /**
     * 根据接口ID获取参数信息
     * @param id
     * @return
     */
    @Override
    public List<ApiParamConfig> getParamByApiId(String id) {
        return list(new LambdaQueryWrapper<ApiParamConfig>().eq(ApiParamConfig::getApiId, id).orderByAsc(ApiParamConfig::getParamOrder));
    }

    /**
     * 保存接口参数信息
     * @param apiParamConfig
     */
    @Override
    public boolean saveApiParamConfig(@Valid ApiParamConfig apiParamConfig) throws Exception {
            return save(apiParamConfig);
    }

    /**
     * 根据接口ID删除参数信息
     * @param apiServiceInfoId
     */
    @Override
    public void removeByApiId(String apiServiceInfoId) {
        remove(new LambdaQueryWrapper<ApiParamConfig>().eq(ApiParamConfig::getApiId, apiServiceInfoId));
    }

    /**
     * 更新接口参数信息 先删后加
     * @param apiId
     * @param apiParamConfigList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApiParamConfig(String apiId, List<ApiParamConfig> apiParamConfigList) {
        try {
            removeByApiId(apiId);
            for (ApiParamConfig apiParamConfig : apiParamConfigList) {
                apiParamConfig.setApiId(apiId);
                save(apiParamConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新接口参数信息失败{}",e.getMessage());
        }
    }
}
