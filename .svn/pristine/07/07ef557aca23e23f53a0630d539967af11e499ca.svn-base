package com.hanwei.process.business.gis.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: GISService接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IGisService extends IService<ModelInvokingInfo> {
    /**
     * 查询管网整体概况
     * 当前各类管网长度情况统计
     * 目前各类管网有多长？
     * 管网长度是多少？
     * 各类型管网铺设情况？
     * @param queryType
     * @return
     */
    ResultForFrontVo pipeStatistics(String channelCode, String queryType);

    /**
     * 查询管网整体概况
     * 当前各类管网长度情况统计
     * 目前各类管网有多长？
     * 管网长度是多少？
     * 各类型管网铺设情况？
     * @param queryType
     * @return
     */
    ResultForFrontVo pipeStatistics(String queryType);


    /**
     * 按照管径统计管网情况
     * 按管径统计管网长度
     * 目前管网都有什么口径的？
     * 100的管网有多少？
     * @param diameter
     * @return
     */
    ResultForFrontVo pipeStatisticsByDiameter(String channelCode, String diameter);

    /**
     * 按照管径统计管网情况
     * 按管径统计管网长度
     * 目前管网都有什么口径的？
     * 100的管网有多少？
     * @param diameter
     * @return
     */
    ResultForFrontVo pipeStatisticsByDiameter(String diameter);

    /**
     * 按照材质统计管网情况
     * 按照材质统计管网长度
     * 目前管网都有什么材质的？
     * PE的供水/排水/绿化管网有多少？
     * @param material
     * @return
     */
    ResultForFrontVo pipeStatisticsByMaterial(String channelCode,String material);

    /**
     * 按照材质统计管网情况
     * 按照材质统计管网长度
     * 目前管网都有什么材质的？
     * PE的供水/排水/绿化管网有多少？
     * @param material
     * @return
     */
    ResultForFrontVo pipeStatisticsByMaterial(String material);

    /**
     * 管网设施统计
     * 按设施类型统计设施数量
     * 目前有多少设施？
     * 都有什么类型的设施？
     * @return
     */
    ResultForFrontVo pipeDeviceStatistics(String channelCode);

    /**
     * 管网设施统计
     * 按设施类型统计设施数量
     * 目前有多少设施？
     * 都有什么类型的设施？
     * @return
     */
    ResultForFrontVo pipeDeviceStatistics();

    /**
     * 整体管网统计
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo pipeOverAllStatistics(String ChannelCode);

    /**
     * 整体管网统计
     * @return
     */
    ResultForFrontVo pipeOverAllStatistics();

}
