package com.hanwei.api.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.api.entity.ApiRelationConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 接口关联问题信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface ApiRelationConfigMapper extends MPJBaseMapper<ApiRelationConfig> {

}
