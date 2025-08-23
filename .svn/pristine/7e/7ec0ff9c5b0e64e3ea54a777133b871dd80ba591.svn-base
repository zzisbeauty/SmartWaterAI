package com.hanwei.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiRelationConfig;
import com.hanwei.api.mapper.ApiRelationConfigMapper;
import com.hanwei.api.service.IApiRelationConfigService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Description: 接口关联问题信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApiRelationConfigServiceImpl extends ServiceImpl<ApiRelationConfigMapper, ApiRelationConfig> implements IApiRelationConfigService {

    /**
     * 根据接口id查询关联问题信息
     * @param id
     * @return
     */
    @Override
    public List<ApiRelationConfig> getRelationByApiId(String id) {
        return list(new LambdaQueryWrapper<ApiRelationConfig>().eq(ApiRelationConfig::getApiId, id));
    }

    /**
     * 保存接口关联问题信息
     * @param apiRelationConfig
     * @return
     */
    @Override
    public boolean saveApiRelationConfig(@Valid ApiRelationConfig apiRelationConfig) throws Exception{
            return save(apiRelationConfig);
    }

    /**
     * 根据接口id删除关联问题信息
     * @param apiServiceInfoId
     */
    @Override
    public void removeByApiId(String apiServiceInfoId) {
        remove(new LambdaQueryWrapper<ApiRelationConfig>().eq(ApiRelationConfig::getApiId, apiServiceInfoId));
    }


    /**
     * 更新接口关联问题信息 先删后加
     * @param apiRelationConfigList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApiRelationConfig(String apiId,List<ApiRelationConfig> apiRelationConfigList) {
        try {
            removeByApiId(apiId);

            for (ApiRelationConfig apiRelationConfig : apiRelationConfigList) {
                apiRelationConfig.setApiId(apiId);
                saveApiRelationConfig(apiRelationConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新接口关联问题信息失败{}",e.getMessage());
        }
    }
}
