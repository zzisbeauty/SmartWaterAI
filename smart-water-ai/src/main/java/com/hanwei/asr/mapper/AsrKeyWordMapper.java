package com.hanwei.asr.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.asr.entity.AsrKeyWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: ASR关键词信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Mapper
public interface AsrKeyWordMapper extends MPJBaseMapper<AsrKeyWord> {

}
