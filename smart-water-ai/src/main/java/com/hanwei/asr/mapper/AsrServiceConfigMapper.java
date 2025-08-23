package com.hanwei.asr.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: ASR实例配置信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface AsrServiceConfigMapper extends MPJBaseMapper<AsrServiceConfig> {

}
