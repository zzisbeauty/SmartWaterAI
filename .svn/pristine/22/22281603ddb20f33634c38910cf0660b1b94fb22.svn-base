package com.hanwei.application.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiRelationConfig;
import com.hanwei.application.entity.ApplicationApiRelation;
import com.hanwei.application.mapper.ApplicationApiRelationMapper;
import com.hanwei.application.service.IApplicationApiRelationService;
import com.hanwei.core.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @Description: 应用接口关系表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApplicationApiRelationServiceImpl extends ServiceImpl<ApplicationApiRelationMapper, ApplicationApiRelation> implements IApplicationApiRelationService {

    /**
     * 根据应用id获取关系数据
     * @param id
     * @return
     */
    @Override
    public List<ApplicationApiRelation> listByApplicationId(String id) {
        return list(new LambdaQueryWrapper<ApplicationApiRelation>().eq(ApplicationApiRelation::getApplicationId,id));
    }

    /**
     * 根据应用id删除接收关联信息
     * @param applicationId
     */
    @Override
    public void removeByApplicationId(String applicationId){
        remove(new LambdaQueryWrapper<ApplicationApiRelation>().eq(ApplicationApiRelation::getApplicationId,applicationId));
    }

    /**
     * 编辑应用接口关联管理
     * @param applicationId
     * @param apiIdList
     * @return
     */
    @Override
    @Transactional
    public Result<?> updateByList(String applicationId,List<String> apiIdList){
        //先删除后添加
        try {
            removeByApplicationId(applicationId);

            List<ApplicationApiRelation> applicationApiRelationList = new ArrayList<>();
            for(String apiId : apiIdList){
                ApplicationApiRelation applicationApiRelation = new ApplicationApiRelation();
                applicationApiRelation.setApplicationId(applicationId);
                applicationApiRelation.setApiId(apiId);
                applicationApiRelationList.add(applicationApiRelation);
            }
            saveBatch(applicationApiRelationList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新应用接口关系失败 {}"+e.getMessage());
            return Result.error(200,"更新应用接口关系失败 {}"+e.getMessage());
        }
        return Result.OK("更新应用接口关系成功");
    }

    /**
     * 判断是否存在
     * @param applicationId
     * @param ApiId
     * @return
     */
    @Override
    public Boolean isExist(String applicationId, String ApiId) {
        List<ApplicationApiRelation> list = list(new LambdaQueryWrapper<ApplicationApiRelation>()
                .eq(ApplicationApiRelation::getApplicationId,applicationId)
                .eq(ApplicationApiRelation::getApiId,ApiId));
        if(Optional.ofNullable(list).isPresent() && list.size()>0){
            return true;
        }
        return false;
    }
}
