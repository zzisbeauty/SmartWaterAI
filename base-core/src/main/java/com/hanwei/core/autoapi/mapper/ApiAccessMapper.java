package com.hanwei.core.autoapi.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.core.autoapi.entity.ApiAccess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: api授权信息
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
@Mapper
public interface ApiAccessMapper extends MPJBaseMapper<ApiAccess> {

}
