package com.hanwei.core.autoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.core.autoapi.bo.ApiKindBo;
import com.hanwei.core.autoapi.bo.RegisterBo;
import com.hanwei.core.autoapi.entity.ApiAccess;
import com.hanwei.core.common.api.vo.Result;

import java.util.List;


/**
 * @Description: 调取新网关相关服务接口
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
public interface IGateWayService  {

    /**
     * 根据查询全部API分类
     * @return
     */
    List<ApiKindBo> getApiKindTree();

    /**
     * 保存API类型信息
     * @param apiKind
     * @return
     */
    String saveApiKind(ApiKindBo apiKind);

    /**
     * 保存API服务信息
     * @param registerBo
     * @return
     */
    Boolean saveApiInfo(RegisterBo registerBo);

    /**
     * 删除API服务信息
     * @param ApiSecret
     * @return
     */
    Boolean deleteApiInfo(String ApiSecret);


}
