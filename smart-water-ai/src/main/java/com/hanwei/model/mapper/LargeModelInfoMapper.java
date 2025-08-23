package com.hanwei.model.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.model.entity.LargeModelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 大模型基础信息
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Mapper
public interface LargeModelInfoMapper extends MPJBaseMapper<LargeModelInfo> {

}
