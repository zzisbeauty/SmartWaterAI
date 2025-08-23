package com.hanwei.system.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHT
 * @version : [v1.0]
 * @description : [服务配置展示对象]
 * @createTime : [2025/7/16 11:23]
 */
@Data
public class ServiceConfigVO {
    /**
     * 指标名称
     */
    private String targetName;

    /**
     * 指标对象
     */
    private List<ServiceConfigItemVO> itemList;
}
