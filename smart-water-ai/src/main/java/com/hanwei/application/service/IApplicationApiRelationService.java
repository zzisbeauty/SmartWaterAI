package com.hanwei.application.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.application.entity.ApplicationApiRelation;
import com.hanwei.core.common.api.vo.Result;

import java.util.List;


/**
 * @Description: 应用接口关系表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
public interface IApplicationApiRelationService extends IService<ApplicationApiRelation> {

    /**
     * 根据应用id获取关系数据
     * @param id
     * @return
     */
    List<ApplicationApiRelation> listByApplicationId(String id);

    /**
     * 根据应用id删除接收关联信息
     * @param applicationId
     */
    void removeByApplicationId(String applicationId);

    /**
     * 编辑应用接口关联管理
     * @param applicationId
     * @param apiIdList
     * @return
     */
    Result<?> updateByList(String applicationId,List<String> apiIdList);

    /**
     * 判断应用接口关联管理是否存在
     * @param applicationId
     * @param ApiId
     * @return
     */
    Boolean isExist(String applicationId, String ApiId);
}
