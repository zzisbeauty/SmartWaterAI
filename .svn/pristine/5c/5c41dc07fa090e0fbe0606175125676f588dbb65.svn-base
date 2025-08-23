package com.hanwei.process.business.watersource.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IWaterSourceService extends IService<ModelInvokingInfo> {
    /**
     * 水源井数据分析统计
     * 水源井用户画像
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dataStatistics(String ChannelCode);

    /**
     * 水源井数据分析统计
     * 水源井用户画像
     * @return
     */
    ResultForFrontVo dataStatistics();

    /**
     * 水源井一张图
     * 水源井GIS一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo gisStatistics(String ChannelCode);

    /**
     * 水源井一张图
     * 水源井GIS一张图
     * @return
     */
    ResultForFrontVo gisStatistics();

}
