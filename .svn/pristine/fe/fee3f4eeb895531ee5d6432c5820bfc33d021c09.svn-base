package com.hanwei.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.system.entity.ExternalServiceConfig;
import com.hanwei.system.mapper.ExternalServiceConfigMapper;
import com.hanwei.system.service.IExternalServiceConfigService;
import com.hanwei.system.vo.ServiceConfigItemVO;
import com.hanwei.system.vo.ServiceConfigVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @Description: 外部服务配置表(配置基本不变并且难以获取的外部服务数据)
 * @Author: hanwei
 * @Date:   2025-07-16
 * @Version: V1.0
 */
@Service
public class ExternalServiceConfigServiceImpl extends ServiceImpl<ExternalServiceConfigMapper, ExternalServiceConfig> implements IExternalServiceConfigService {

    /**
     * 根据服务id获取相关服务配置
     * @param serviceId
     * @return
     */
    @Override
    public List<ServiceConfigVO> getByServiceId(String serviceId) {
        List<ExternalServiceConfig> externalServiceConfigList = list(new LambdaQueryWrapper<ExternalServiceConfig>().eq(ExternalServiceConfig::getServiceId, serviceId).orderByAsc(ExternalServiceConfig::getTarget));
        List<ServiceConfigVO> serviceConfigVOList = new ArrayList<>();
        if(Optional.ofNullable(externalServiceConfigList).isPresent()){
            String currTarget = "";
            //组装返回数据
            ServiceConfigVO serviceConfigVO = null;
            List<ServiceConfigItemVO> list;

            for(ExternalServiceConfig item: externalServiceConfigList){
                if("".equals(currTarget) || !currTarget.equals(item.getTarget())){

                    serviceConfigVO = new ServiceConfigVO();
                    serviceConfigVO.setTargetName(item.getTarget());
                    serviceConfigVOList.add(serviceConfigVO);
                }
                ServiceConfigItemVO serviceConfigItemVO = new ServiceConfigItemVO();
                serviceConfigItemVO.setKey(item.getKey());
                serviceConfigItemVO.setText(item.getText());
                serviceConfigItemVO.setRemark(item.getRemark());
                if(Optional.ofNullable(serviceConfigVO.getItemList()).isPresent()){
                    list = serviceConfigVO.getItemList();
                }else{
                    list = new ArrayList<>();
                    serviceConfigVO.setItemList(list);
                }

                list.add(serviceConfigItemVO);
                currTarget = item.getTarget();
            }
        }

        return serviceConfigVOList;
    }
}
