package com.hanwei.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.api.entity.ApiQuestionConfig;

import java.util.List;


/**
 * @Description: 接口提问语信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IApiQuestionConfigService extends IService<ApiQuestionConfig> {

    /**
     * 根据apiId获取提问语信息
     * @param id
     * @return
     */
    List<ApiQuestionConfig> getQuestionByApiId(String id);

    /**
     * 保存提问语信息
     * @param apiQuestionConfig
     */
    boolean saveApiQuestionConfig(ApiQuestionConfig apiQuestionConfig) throws Exception;

    /**
     * 根据apiId删除提问语信息
     * @param apiServiceInfoId
     */
    void removeByApiId(String apiServiceInfoId);

    /**
     * 更新提问语信息
     * @param id
     * @param apiQuestionConfigList
     */
    void updateApiQuestionConfig(String id, List<ApiQuestionConfig> apiQuestionConfigList);
}
