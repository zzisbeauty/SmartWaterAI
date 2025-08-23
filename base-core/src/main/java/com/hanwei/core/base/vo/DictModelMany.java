package com.hanwei.core.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @Description 类/方法描述
 * @author zhuht
 * @Date 2024/1/30 14:50
 * @Since version-1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictModelMany extends DictModel {

    /**
     * 字典code，根据多个字段code查询时才用到，用于区分不同的字典选项
     */
    private String dictCode;

}
