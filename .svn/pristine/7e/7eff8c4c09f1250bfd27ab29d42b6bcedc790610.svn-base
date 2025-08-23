package com.hanwei.process.business.wspss.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.wspss.service.IWspssService;
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
public class WspssServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IWspssService {


    /**
     * 稳压系统用户画像
     * 调流调压数据分析汇总
     * 调流调压数据画像
     * @param ChannelCode 渠道号
     * @return
     */
    @Override
    public ResultForFrontVo monitoringStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("稳压系统用户画像");
        urlResult.setUrl("http://10.0.15.41:8000/wspss/?#/overviewMonitoring");
        urlResult.setSpeechText("为您展示稳压系统用户画像");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 稳压系统用户画像
     * 调流调压数据分析汇总
     * 调流调压数据画像
     * @return
     */
    @Override
    public ResultForFrontVo monitoringStatistics() {
        return monitoringStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 调流调压GIS总览
     * 调流调压GIS一张图
     * @param ChannelCode 渠道号
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("调流调压GIS总览");
        urlResult.setUrl("http://10.0.15.41:8000/wspss/?#/gisMonitoring");
        urlResult.setSpeechText("为您展示调流调压GIS总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 调流调压GIS总览
     * 调流调压GIS一张图
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics() {
        return gisStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 稳压系统工艺
     * 调流调压工艺
     * @return
     */
    @Override
    public ResultForFrontVo scadaStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("稳压系统工艺");
        urlResult.setUrl("http://10.0.15.41:8000/wspss/?#/dataScada");
        urlResult.setSpeechText("为您展示稳压系统工艺");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 稳压系统工艺
     * 调流调压工艺
     * @return
     */
    @Override
    public ResultForFrontVo scadaStatistics() {
        return scadaStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
