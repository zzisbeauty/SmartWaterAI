package com.hanwei.api.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiStyleRelation;
import com.hanwei.api.mapper.ApiStyleRelationMapper;
import com.hanwei.api.service.IApiStyleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @Description: 接口结果展示样式关联信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Service
public class ApiStyleRelationServiceImpl extends ServiceImpl<ApiStyleRelationMapper, ApiStyleRelation> implements IApiStyleRelationService {

    /**
     * 根据接口id获取样式id
     * @param id
     * @return
     */
    @Override
    public String getStyleIdByApiId(String id) {
        ApiStyleRelation apiStyleRelation = getOne(new QueryWrapper<ApiStyleRelation>().lambda().eq(ApiStyleRelation::getApiId, id), false);
        if (Optional.ofNullable(apiStyleRelation).isPresent()){
            return apiStyleRelation.getStyleId();
        }
        return null;
    }

    /**
     * 根据接口id获取样式id
     * @param id
     * @return
     */
    @Override
    public List<ApiStyleRelation> getByApiId(String id) {
        List<ApiStyleRelation> apiStyleRelationList = list(new QueryWrapper<ApiStyleRelation>().lambda().eq(ApiStyleRelation::getApiId, id));
        return apiStyleRelationList;
    }

    /**
     * 根据接口id删除样式id
     * @param apiServiceInfoId
     */
    @Override
    public void removeByApiId(String apiServiceInfoId) {
        remove(new QueryWrapper<ApiStyleRelation>().lambda().eq(ApiStyleRelation::getApiId, apiServiceInfoId));
    }

    /**
     * 更新样式
     * @param apiStyleRelationList
     */
    @Override
    @Transactional
    public void updateApiStyle(String apiId,List<ApiStyleRelation> apiStyleRelationList) {
        apiStyleRelationList.forEach(
                apiStyleRelation -> {
                    removeByApiId(apiId);
                    apiStyleRelation.setApiId(apiId);
                    save(apiStyleRelation);
                }
        );

    }
}
