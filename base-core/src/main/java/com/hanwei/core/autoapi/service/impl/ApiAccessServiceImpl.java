package com.hanwei.core.autoapi.service.impl;


import com.hanwei.core.autoapi.entity.ApiAccess;
import com.hanwei.core.autoapi.mapper.ApiAccessMapper;
import com.hanwei.core.autoapi.service.IApiAccessService;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


/**
 * @Description: api授权信息
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
@Service
public class ApiAccessServiceImpl extends ServiceImpl<ApiAccessMapper, ApiAccess> implements IApiAccessService {
}
