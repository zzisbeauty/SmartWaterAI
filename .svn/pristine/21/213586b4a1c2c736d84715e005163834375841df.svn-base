package com.hanwei.process.business.dma.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.dma.service.IDmaService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.UrlResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: 模型调用信息ServiceImpl
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class DmaServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IDmaService {

    /**
     * 漏损一张图
     * 漏损GIS总览
     * 漏损总览
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("漏损一张图");
        urlResult.setUrl("http://10.0.15.41:8000/smartwater/DevelopmentCase/DMA/Overview/Map/GisMap.html");
        urlResult.setSpeechText("为您展示漏损一张图");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 漏损一张图
     * 漏损GIS总览
     * 漏损总览
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics() {
        return gisStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 漏损分析汇总
     * 漏损分析报表
     * @return
     */
    @Override
    public ResultForFrontVo leakageAnalysisStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("漏损分析汇总");
        urlResult.setUrl("http://10.0.15.41:8000/smartwater/DevelopmentCase/DMA/Overview/Report/Grid.html");
        urlResult.setSpeechText("为您展示漏损分析汇总");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 漏损分析汇总
     * 漏损分析报表
     * @return
     */
    @Override
    public ResultForFrontVo leakageAnalysisStatistics() {
        return leakageAnalysisStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
