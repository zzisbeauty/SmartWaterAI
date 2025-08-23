package com.hanwei.system.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.system.entity.CategoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 分类信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface CategoryInfoMapper extends MPJBaseMapper<CategoryInfo> {

}
