package com.hanwei.process.business.wspss.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IWspssService extends IService<ModelInvokingInfo> {

    /**
     * 稳压系统用户画像
     * 调流调压数据分析汇总
     * 调流调压数据画像
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo monitoringStatistics(String ChannelCode);

    /**
     * 稳压系统用户画像
     * 调流调压数据分析汇总
     * 调流调压数据画像
     * @return
     */
    ResultForFrontVo monitoringStatistics();

    /**
     * 调流调压GIS总览
     * 调流调压GIS一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo gisStatistics(String ChannelCode);

    /**
     * 调流调压GIS总览
     * 调流调压GIS一张图
     * @return
     */
    ResultForFrontVo gisStatistics();

    /**
     * 稳压系统工艺
     * 调流调压工艺
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo scadaStatistics(String ChannelCode);

    /**
     * 稳压系统工艺
     * 调流调压工艺
     * @return
     */
    ResultForFrontVo scadaStatistics();
}
