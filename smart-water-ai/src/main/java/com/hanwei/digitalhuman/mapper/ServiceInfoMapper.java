package com.hanwei.digitalhuman.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.digitalhuman.entity.ServiceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 数字人服务信息
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Mapper
public interface ServiceInfoMapper extends MPJBaseMapper<ServiceInfo> {

}
