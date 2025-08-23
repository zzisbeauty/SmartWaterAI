package com.hanwei.process.business.pipemonitoring.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 管网监测Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IPipeMonitoringService extends IService<ModelInvokingInfo> {
    /**
     * 当前管网压力最高的点？
     * 当前管网的压力情况？
     * 当前管网压力最低的点？
     * 管网压力现在是什么情况？
     * 当前管网压力最高和最低的点是哪个
     * @param channelCode 渠道号
     * @param queryType
     * @return
     */
    ResultForFrontVo getTopMonitor(String channelCode, String queryType);

    /**
     * 当前管网压力最高的点？
     * 当前管网的压力情况？
     * 当前管网压力最低的点？
     * 管网压力现在是什么情况？
     * 当前管网压力最高和最低的点是哪个
     * @param queryType
     * @return
     */
    ResultForFrontVo getTopMonitor(String queryType);

    /**
     * {名称}本月压力情况？
     * 我想看下{名称}2024年10月1日至2024年10月29日的压力
     * @param channelCode
     * @param monitorName
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo getHistoryDataByMonitor(String channelCode,String monitorName,String startTime, String endTime);

    /**
     * {名称}本月压力情况？
     * 我想看下{名称}2024年10月1日至2024年10月29日的压力
     * @param monitorName
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo getHistoryDataByMonitor(String monitorName,String startTime, String endTime);



    /**
     * 现在已安装多少监测设备？
     * 目前监测设备建设情况？
     * 监测设备运行情况？
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo pipeMonitorDeviceStatistics(String ChannelCode);

    /**
     * 现在已安装多少监测设备？
     * 目前监测设备建设情况？
     * 监测设备运行情况？
     * @return
     */
    ResultForFrontVo pipeMonitorDeviceStatistics();



    /**
     * 目前有多少压力监测设备？
     * 压力监测设备情况？
     */
    ResultForFrontVo getPressureMonitorCount(String channelCode);

    /**
     * 目前有多少压力监测设备？
     * 压力监测设备情况？
     */
    ResultForFrontVo getPressureMonitorCount();

    /**
     * 目前有多少流量监测设备？
     * 流量监测设备情况？
     */
    ResultForFrontVo getFlowMonitorCount(String channelCode);

    /**
     * 目前有多少流量监测设备？
     * 流量监测设备情况？
     */
    ResultForFrontVo getFlowMonitorCount();

    /**
     * 目前有多少报警点？
     * 目前管网运行报警情况？
     * 目前管网运行有什么问题？
     */
    ResultForFrontVo getAlarmMonitor(String channelCode);

    /**
     * 目前有多少报警点？
     * 目前管网运行报警情况？
     * 目前管网运行有什么问题？
     */
    ResultForFrontVo getAlarmMonitor();

    /**
     * 目前管网运行情况？
     * 管网现在是什么情况？
     */
    ResultForFrontVo pipeStatistics(String channelCode);

    /**
     * 目前管网运行情况？
     * 管网现在是什么情况？
     */
    ResultForFrontVo pipeStatistics();

    /**
     * 压力监测-GIS总览
     */
    ResultForFrontVo gisStatisticsByPressure(String channelCode);

    /**
     * 压力监测-GIS总览
     */
    ResultForFrontVo gisStatisticsByPressure();

    /**
     * 管网流量监测一张图
     * 管网流量GIS一张图
     */
    ResultForFrontVo gisStatisticsByFlow(String channelCode);

    /**
     * 管网流量监测一张图
     * 管网流量GIS一张图
     */
    ResultForFrontVo gisStatisticsByFlow();

    /**
     * 管网水质监测一张图
     * 管网水质GIS一张图
     */
    ResultForFrontVo gisStatisticsByQuality(String channelCode);

    /**
     * 管网水质监测一张图
     * 管网水质GIS一张图
     */
    ResultForFrontVo gisStatisticsByQuality();
}
