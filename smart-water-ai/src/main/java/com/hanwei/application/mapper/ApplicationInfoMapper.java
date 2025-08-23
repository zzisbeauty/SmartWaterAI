package com.hanwei.application.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.application.entity.ApplicationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 应用基础信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Mapper
public interface ApplicationInfoMapper extends MPJBaseMapper<ApplicationInfo> {

}
