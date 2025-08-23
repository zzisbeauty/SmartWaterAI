package com.hanwei.core.base.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.hanwei.core.common.api.dto.LogDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: BaseCommonMapper
 */
public interface BaseCommonMapper {

    /**
     * 保存日志
     * @param dto
     */
    @InterceptorIgnore(illegalSql = "true", tenantLine = "true")
    void saveLog(@Param("dto") LogDTO dto);

}
