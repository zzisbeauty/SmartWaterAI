package com.hanwei.digitalhuman.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 数字人配置实例
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Mapper
public interface ServiceConfigMapper extends MPJBaseMapper<ServiceConfig> {

}
