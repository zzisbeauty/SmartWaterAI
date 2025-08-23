package com.hanwei.core.autoapi.service.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hanwei.core.autoapi.entity.ApiKind;
import com.hanwei.core.autoapi.mapper.ApiKindMapper;
import com.hanwei.core.autoapi.service.IApiKindService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * @Description: Api分类信息
 * @Author: hanwei
 * @Date:   2025-03-09
 * @Version: V1.0
 */
@Service
public class ApiKindServiceImpl extends ServiceImpl<ApiKindMapper, ApiKind> implements IApiKindService {
    @Autowired
    private ApiKindMapper apiKindMapper;

    @Override
    public String getMax(MPJLambdaWrapper<ApiKind> queryWrapper) {
        return apiKindMapper.selectJoinOne(String.class, queryWrapper);
    }
}
