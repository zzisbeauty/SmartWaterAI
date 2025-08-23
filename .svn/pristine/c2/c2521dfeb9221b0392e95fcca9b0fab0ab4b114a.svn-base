package com.hanwei.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.system.entity.ExternalServiceConfig;
import com.hanwei.system.vo.ServiceConfigVO;

import java.util.List;


/**
 * @Description: 外部服务配置表(配置基本不变并且难以获取的外部服务数据)
 * @Author: hanwei
 * @Date:   2025-07-16
 * @Version: V1.0
 */
public interface IExternalServiceConfigService extends IService<ExternalServiceConfig> {

    /**
     * 根据服务id获取相关服务配置
     * @param serviceId
     * @return
     */
    List<ServiceConfigVO> getByServiceId(String serviceId);
}
