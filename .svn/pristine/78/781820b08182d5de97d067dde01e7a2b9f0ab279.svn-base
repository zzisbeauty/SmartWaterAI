package com.hanwei.process.business.secondsupply.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface ISecondSupplyService extends IService<ModelInvokingInfo> {
    /**
     * 二供数据总览
     * 二供用户画像
     * 二供数据分析汇总
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dataStatistics(String ChannelCode);

    /**
     * 二供数据总览
     * 二供用户画像
     * 二供数据分析汇总
     * @return
     */
    ResultForFrontVo dataStatistics();

    /**
     * 二供监测总览
     * 二供GIS一张图
     * 二供GIS总览
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo gisStatistics(String ChannelCode);

    /**
     * 二供监测总览
     * 二供GIS一张图
     * 二供GIS总览
     * @return
     */
    ResultForFrontVo gisStatistics();
}
