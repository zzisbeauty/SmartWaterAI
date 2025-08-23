package com.hanwei.process.business.pipepolling.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.pipepolling.service.IPipePollingService;
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
public class PipePollingServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IPipePollingService {

    /**
     * 管网巡检数据分析汇总
     * 管网巡检数据总览
     * 管网巡检用户画像
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网巡检数据分析汇总");
        urlResult.setUrl("http://10.0.15.41:8000/xj/#overview");
        urlResult.setSpeechText("为您展示管网巡检数据分析汇总");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 管网巡检数据分析汇总
     * 管网巡检数据总览
     * 管网巡检用户画像
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics() {
        return dataStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 巡检监控一张图
     * @return
     */
    @Override
    public ResultForFrontVo pollingMonitorStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网巡检数据分析汇总");
        urlResult.setUrl("http://10.0.15.41:8000/xj/#/Monitoring");
        urlResult.setSpeechText("为您展示管网巡检数据分析汇总");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 巡检监控一张图
     * @return
     */
    @Override
    public ResultForFrontVo pollingMonitorStatistics() {
        return pollingMonitorStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
