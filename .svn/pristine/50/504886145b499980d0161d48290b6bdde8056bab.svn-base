package com.hanwei.process.business.dma.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IDmaService extends IService<ModelInvokingInfo> {

    /**
     * 漏损一张图
     * 漏损GIS总览
     * 漏损总览
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo gisStatistics(String ChannelCode);

    /**
     * 漏损一张图
     * 漏损GIS总览
     * 漏损总览
     * @return
     */
    ResultForFrontVo gisStatistics();

    /**
     * 漏损分析汇总
     * 漏损分析报表
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo leakageAnalysisStatistics(String ChannelCode);

    /**
     * 漏损分析汇总
     * 漏损分析报表
     * @return
     */
    ResultForFrontVo leakageAnalysisStatistics();
}
