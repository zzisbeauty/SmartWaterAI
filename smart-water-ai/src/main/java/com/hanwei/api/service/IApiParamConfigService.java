package com.hanwei.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.api.entity.ApiParamConfig;

import java.util.List;


/**
 * @Description: 接口参数信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IApiParamConfigService extends IService<ApiParamConfig> {

    /**
     * 根据接口ID获取参数信息
     * @param id
     * @return
     */
    List<ApiParamConfig> getParamByApiId(String id);

    /**
     * 保存接口参数信息
     * @param apiParamConfig
     */
    boolean saveApiParamConfig(ApiParamConfig apiParamConfig) throws Exception;

    /**
     * 根据接口ID删除参数信息
     * @param apiServiceInfoId
     */
    void removeByApiId(String apiServiceInfoId);

    /**
     * 更新接口参数信息
     * @param id
     * @param apiParamConfigList
     */
    void updateApiParamConfig(String id, List<ApiParamConfig> apiParamConfigList);
}
