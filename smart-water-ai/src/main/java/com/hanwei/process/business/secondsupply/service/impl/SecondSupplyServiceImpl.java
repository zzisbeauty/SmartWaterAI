package com.hanwei.process.business.secondsupply.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.secondsupply.service.ISecondSupplyService;
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
public class SecondSupplyServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements ISecondSupplyService {

    /**
     * 二供数据总览
     * 二供用户画像
     * 二供数据分析汇总
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二供数据总览");
        urlResult.setUrl("http://10.0.15.41:8000/TwoSupply/Overview/newGrid.html");
        urlResult.setSpeechText("为您展示二供数据总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二供数据总览
     * 二供用户画像
     * 二供数据分析汇总
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics() {
        return dataStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二供监测总览
     * 二供GIS一张图
     * 二供GIS总览
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二供GIS一张图");
        urlResult.setUrl("http://10.0.15.41:8000/TwoSupply/Map/GisMap.html");
        urlResult.setSpeechText("为您展示二供GIS一张图");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二供监测总览
     * 二供GIS一张图
     * 二供GIS总览
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics() {
        return gisStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
