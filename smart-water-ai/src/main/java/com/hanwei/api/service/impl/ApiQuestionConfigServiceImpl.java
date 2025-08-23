package com.hanwei.api.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiQuestionConfig;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.mapper.ApiQuestionConfigMapper;
import com.hanwei.api.service.IApiQuestionConfigService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @Description: 接口提问语信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApiQuestionConfigServiceImpl extends ServiceImpl<ApiQuestionConfigMapper, ApiQuestionConfig> implements IApiQuestionConfigService {

    /**
     * 根据接口id获取提问语
     * @param id
     * @return
     */
    @Override
    public List<ApiQuestionConfig> getQuestionByApiId(String id) {
        return list(new LambdaQueryWrapper<ApiQuestionConfig>().eq(ApiQuestionConfig::getApiId, id));
    }

    /**
     * 保存提问语信息
     * @param apiQuestionConfig
     * @return
     */
    @Override
    public boolean saveApiQuestionConfig(@Valid ApiQuestionConfig apiQuestionConfig) throws Exception{
            return save(apiQuestionConfig);
    }

    /**
     * 根据接口id删除提问语信息
     * @param apiServiceInfoId
     */
    @Override
    public void removeByApiId(String apiServiceInfoId) {
        remove(new LambdaQueryWrapper<ApiQuestionConfig>().eq(ApiQuestionConfig::getApiId, apiServiceInfoId));
    }

    /**
     * 更新提问语信息
     * @param apiId
     * @param apiQuestionConfigList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApiQuestionConfig(String apiId, List<ApiQuestionConfig> apiQuestionConfigList) {
        try {
            removeByApiId(apiId);
            for (ApiQuestionConfig apiQuestionConfig : apiQuestionConfigList) {
                apiQuestionConfig.setApiId(apiId);
                save(apiQuestionConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新提问语信息失败{}",e.getMessage());
        }
    }
}
