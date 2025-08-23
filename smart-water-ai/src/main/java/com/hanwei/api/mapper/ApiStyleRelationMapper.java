package com.hanwei.api.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.api.entity.ApiStyleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 接口结果展示样式关联信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface ApiStyleRelationMapper extends MPJBaseMapper<ApiStyleRelation> {

}
