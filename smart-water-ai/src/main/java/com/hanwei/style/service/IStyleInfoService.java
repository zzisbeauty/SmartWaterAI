package com.hanwei.style.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.style.entity.StyleInfo;


/**
 * @Description: 展示样式管理
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
public interface IStyleInfoService extends IService<StyleInfo> {

    /**
     * 保存展示样式信息
     * @param styleInfo
     */
    boolean saveStyleInfo(StyleInfo styleInfo);

    /**
     * 根据apiId删除展示样式信息
     * @param apiServiceInfoId
     */
    void removeByApiId(String apiServiceInfoId);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    Result<?> removeStyleById(String id);

    /**
     * 修改展示样式信息
     * @param styleInfo
     * @return
     */
    Boolean updateStyleInfoById(StyleInfo styleInfo);
}
