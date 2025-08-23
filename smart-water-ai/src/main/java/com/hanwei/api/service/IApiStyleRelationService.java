package com.hanwei.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.api.entity.ApiStyleRelation;

import java.util.List;


/**
 * @Description: 接口结果展示样式关联信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IApiStyleRelationService extends IService<ApiStyleRelation> {

    /**
     * 根据接口id获取样式id
     * @param id
     * @return
     */
    String getStyleIdByApiId(String id);

    /**
     * 根据接口id获取样式关联信息
     * @param id
     * @return
     */
    List<ApiStyleRelation> getByApiId(String id);

    /**
     * 根据接口id删除样式关联信息
     * @param apiServiceInfoId
     */
    void removeByApiId(String apiServiceInfoId);

    /**
     * 更新样式关联信息
     * @param apiStyleRelationList
     */
    void updateApiStyle(String apiId,List<ApiStyleRelation> apiStyleRelationList);
}
