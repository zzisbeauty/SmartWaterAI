package com.hanwei.style.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.style.entity.StyleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 展示样式管理
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface StyleInfoMapper extends MPJBaseMapper<StyleInfo> {

}
