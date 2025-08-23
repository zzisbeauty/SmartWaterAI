package com.hanwei.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.api.entity.ApiRelationConfig;

import java.util.List;


/**
 * @Description: 接口关联问题信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IApiRelationConfigService extends IService<ApiRelationConfig> {

    /**
     * 根据apiId获取关联问题信息
     * @param id
     * @return
     */
    List<ApiRelationConfig> getRelationByApiId(String id);

    /**
     * 保存接口关联问题信息
     * @param apiRelationConfig
     */
    boolean saveApiRelationConfig(ApiRelationConfig apiRelationConfig) throws Exception;

    /**
     * 根据apiId删除接口关联问题信息
     * @param apiServiceInfoId
     */
    void removeByApiId(String apiServiceInfoId);

    /**
     * 更新接口关联问题信息
     * @param apiId
     * @param apiRelationConfigList
     */
    void updateApiRelationConfig(String apiId ,List<ApiRelationConfig> apiRelationConfigList);
}
