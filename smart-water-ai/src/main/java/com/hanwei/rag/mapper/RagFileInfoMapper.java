package com.hanwei.rag.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.rag.entity.RagFileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 知识库文档管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Mapper
public interface RagFileInfoMapper extends MPJBaseMapper<RagFileInfo> {

}
