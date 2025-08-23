package com.hanwei.process.business.pipepolling.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IPipePollingService extends IService<ModelInvokingInfo> {
    /**
     * 管网巡检数据分析汇总
     * 管网巡检数据总览
     * 管网巡检用户画像
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dataStatistics(String ChannelCode);

    /**
     * 管网巡检数据分析汇总
     * 管网巡检数据总览
     * 管网巡检用户画像
     * @return
     */
    ResultForFrontVo dataStatistics();

    /**
     * 巡检监控一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo pollingMonitorStatistics(String ChannelCode);

    /**
     * 巡检监控一张图
     * @return
     */
    ResultForFrontVo pollingMonitorStatistics();
}
