package com.hanwei.process.business.pipemonitoring.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.pipemonitoring.service.IPipeMonitoringService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.vo.ChartResult;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.TextResult;
import com.hanwei.process.vo.UrlResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 模型调用信息ServiceImpl
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class PipeMonitoringServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IPipeMonitoringService {

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
    @Override
    public ResultForFrontVo getTopMonitor(String channelCode, String queryType) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"当前监测点最高点是<strong>博峰西路德天利路口监测点</strong>,具体数据如下\",\"title\": \"\",\"speechText\": \"目前阜康市区共有48个压力监测点，当前最高的压力的点为准东西苑小区东侧，压力值：0.51Mpa\",\"ulDataList\":[\"监测点地址:<strong>博峰西路德天利路口</strong>\",\"点位实时压力值:<strong>0.58</strong>Mpa\",\"采集时间:<strong>2024-10-29 18:41</strong>Mpa\",\"状态:在线\"]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"当前最高压力点<strong>博峰西路德天利路口监测点</strong> 最低点是<strong>平升压力-南华路与乌奇路路口压力点</strong>,具体数据如下\",\"title\": \"当前监测点最低点\",\"speechText\": \"目前阜康市区共有48个压力监测点，当前最高压力点博峰西路德天利路口监测点最低的压力的点为南华路与乌奇路路口，压力值：0.08Mpa。\",\"ulDataList\": [\"监测点地址:<strong>博峰西路德天利路口</strong>\",\"点位实时压力值:<strong>0.58</strong>Mpa\",\"采集时间:<strong>2024-10-29 18:41</strong>Mpa\",\"状态:在线\",\"监测点地址: <strong>平升压力-南华路与乌奇路路口压力点</strong>\",\"点位实时压力值: <strong>0.08</strong>Mpa\",\"采集时间: <strong>2024-10-29 20:40</strong>Mpa\",\"状态: 在线\"]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);

        TextResult textResult = new TextResult();
        textResult.setText("目前阜康市区共有48个压力监测点，当前最高的压力的点为准东西苑小区东侧，压力值：0.58Mpa。当前最低的压力的点为平升压力-南华路与乌奇路路口压力点，压力值：0.08Mpa。");
        List<String> textData = new ArrayList<>();
        textData.add("监测点地址:<strong>博峰西路德天利路口</strong>");
        textData.add("点位实时压力值:<strong>0.58</strong>Mpa");
        textData.add("采集时间:<strong>2024年10月29日18点41分</strong>Mpa");
        textData.add("状态:在线");

        textData.add("监测点地址: <strong>平升压力-南华路与乌奇路路口压力点</strong>");
        textData.add("点位实时压力值: <strong>0.08</strong>Mpa");
        textData.add("采集时间: <strong>2024年10月29日20点40分</strong>Mpa");
        textData.add("状态:在线");
        textResult.setTextData(textData);
        textResult.setSpeechText("目前阜康市区共有48个压力监测点，当前最高的压力的点为准东西苑小区东侧，压力值：0.58兆帕。当前最低的压力的点为平升压力-南华路与乌奇路路口压力点，压力值：0.08兆帕。");
        resultForFrontVo.setTextResult(textResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 当前管网压力最高的点？
     * 当前管网的压力情况？
     * 当前管网压力最低的点？
     * 管网压力现在是什么情况？
     * 当前管网压力最高和最低的点是哪个
     * @param queryType
     * @return
     */
    @Override
    public ResultForFrontVo getTopMonitor(String queryType) {
        return getTopMonitor(CommonConstant.CHANNEL_CODE_BIGSCREEN,queryType);
    }


    /**
     * {名称}本月压力情况？总减压井
     * 我想看下{名称}2024年10月1日至2024年10月29日的压力
     * @param channelCode
     * @param monitorName
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultForFrontVo getHistoryDataByMonitor(String channelCode, String monitorName, String startTime, String endTime) {
        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"减压阀-高速公路南侧总减压井，实时压力值为0.37Mpa，今日历史监测数据如下:\",\"title\": \"减压阀-高速公路南侧总减压井今日历史监测数据\",\"speechText\": \"2024年10月1日至2024年10月29日，减压阀-高速公路南侧总减压井压力最高值为0.37Mpa，发生在2024-10-15 23:00:00;详见下方图表\",\"options\": [{\"echartId\": \"echarts-17301873809321\",\"tooltip\": {\"trigger\": \"axis\"},\"xAxis\": {\"data\": [\"2024-10-21 00:00:27\",\"2024-10-21 00:15:27\",\"2024-10-21 00:30:27\",\"2024-10-21 00:45:27\",\"2024-10-21 01:00:27\",\"2024-10-21 01:15:27\",\"2024-10-21 01:30:27\",\"2024-10-21 01:45:27\",\"2024-10-21 02:00:27\",\"2024-10-21 02:15:27\",\"2024-10-21 02:15:27\",\"2024-10-21 02:30:27\",\"2024-10-21 02:30:27\",\"2024-10-21 02:45:27\",\"2024-10-21 02:45:27\",\"2024-10-21 03:00:27\",\"2024-10-21 03:00:27\",\"2024-10-21 03:15:27\",\"2024-10-21 03:15:27\",\"2024-10-21 03:30:27\",\"2024-10-21 03:30:27\",\"2024-10-21 03:45:27\",\"2024-10-21 03:45:27\",\"2024-10-21 04:00:27\",\"2024-10-21 04:00:27\",\"2024-10-21 04:15:27\",\"2024-10-21 04:30:27\",\"2024-10-21 04:45:27\",\"2024-10-21 05:00:27\",\"2024-10-21 05:15:27\",\"2024-10-21 05:30:27\",\"2024-10-21 05:45:27\",\"2024-10-21 06:00:27\",\"2024-10-21 06:15:27\"],\"type\": \"category\",\"boundaryGap\": true,\"axisTick\": {\"show\": false},\"axisLabel\": {\"textStyle\": {\"show\": true,\"color\": \"#fff\",\"fontSize\": \"14\"}},\"axisLine\": {\"lineStyle\": {\"width\": 1,\"color\": \"rgba(255,255,255,0.2)\"}}},\"yAxis\": {\"name\": \"单位\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"type\": \"value\",\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"solid\",\"width\": 1,\"color\": \"rgba(255,255,255,0.3)\"}},\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgba(255,255,255,0.3)\"}},\"axisLabel\": {\"textStyle\": {\"show\": true,\"color\": \"#fff\",\"fontSize\": \"14\"}}},\"graphic\": [{\"type\": \"image\",\"style\": {\"image\": \"echarts/barbg.png\",\"width\": \"98%\",\"height\": 100,\"left\": \"4%\"},\"bottom\": 0,\"left\": \"center\",\"z\": -1}],\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"15%\",\"containLabel\": true},\"series\": [{\"data\": [0.36, 0.36, 0.36, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.35, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37, 0.37],\"name\": \"\",\"type\": \"line\",\"symbol\": \"circle\",\"smooth\": false,\"symbolSize\": 0,\"lineStyle\": {\"width\": 4,\"type\": \"solid\"},\"itemStyle\": {\"borderWidth\": 8,\"borderColor\": \"rgb(41, 169, 255)\",\"color\": \"rgb(41, 169, 255)\"},\"areaStyle\": {\"normal\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(41, 169, 255)\"},{\"offset\": 1,\"color\": \"rgb(41, 169, 255,0)\"}],\"global\": false}}},\"markLine\": {\"symbol\": \"none\",\"data\": [{\"type\": \"average\",\"name\": \"平均值\",\"label\": {\"normal\": {\"position\": \"end\",\"color\": \"rgb(0, 255, 42)\",\"distance\": [-100,0],\"formatter\": \"平均值{c}\"}}}]},\"markPoint\": {\"symbolSize\": [80,30],\"symbolOffset\": [0,-18],\"itemStyle\": {\"color\": {\"type\": \"radial\",\"x\": 0.5,\"y\": 0.5,\"r\": 0.9,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(0, 0, 0, 1)\"},{\"offset\": 1,\"color\": \"rgb(23, 104, 163)\"}],\"global\": false},\"borderColor\": \"rgb(41, 169, 255)\",\"borderWidth\": [2]},\"symbol\": \"path://M1233.329493 195.75466633h-1112.064c-34.128213 0-61.781333 27.661653-61.781333 61.781334v473.658026c0 34.117973 27.65312 61.781333 61.781333 61.781334h472.80128l83.23072 144.155306 83.23072-144.155306h472.80128c34.128213 0 61.781333-27.66336 61.781334-61.781334V257.53600033c0-34.117973-27.65312-61.781333-61.781334-61.781334z\",\"data\": [{\"type\": \"max\",\"name\": \"最大值\",\"label\": {\"normal\": {\"formatter\": \"最大值{c}\"}}},{\"type\": \"min\",\"name\": \"最小值\",\"label\": {\"show\": true,\"normal\": {\"formatter\": \"最小值{c}\"}}}]}}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"减压阀-高速公路南侧总减压井，实时压力值为0.37Mpa，今日历史监测数据如下:\",\"title\": \"减压阀-高速公路南侧总减压井今日历史监测数据\",\"speechText\": \"2024年10月1日至2024年10月29日，减压阀-高速公路南侧总减压井压力最高值为0.37Mpa，发生在2024-10-15 23:00:00;详见下方图表\",\"options\": [{\"echartId\": \"echarts-17301873809321\",\"tooltip\": {\"trigger\": \"axis\"},\"xAxis\": {\"data\": [\"2024-10-21 00:00:27\",\"2024-10-21 00:15:27\",\"2024-10-21 00:30:27\",\"2024-10-21 00:45:27\",\"2024-10-21 01:00:27\",\"2024-10-21 01:15:27\",\"2024-10-21 01:30:27\",\"2024-10-21 01:45:27\",\"2024-10-21 02:00:27\",\"2024-10-21 02:15:27\",\"2024-10-21 02:15:27\",\"2024-10-21 02:30:27\",\"2024-10-21 02:30:27\",\"2024-10-21 02:45:27\",\"2024-10-21 02:45:27\",\"2024-10-21 03:00:27\",\"2024-10-21 03:00:27\",\"2024-10-21 03:15:27\",\"2024-10-21 03:15:27\",\"2024-10-21 03:30:27\",\"2024-10-21 03:30:27\",\"2024-10-21 03:45:27\",\"2024-10-21 03:45:27\",\"2024-10-21 04:00:27\",\"2024-10-21 04:00:27\",\"2024-10-21 04:15:27\",\"2024-10-21 04:30:27\",\"2024-10-21 04:45:27\",\"2024-10-21 05:00:27\",\"2024-10-21 05:15:27\",\"2024-10-21 05:30:27\",\"2024-10-21 05:45:27\",\"2024-10-21 06:00:27\",\"2024-10-21 06:15:27\"],\"type\": \"category\",\"boundaryGap\": false,\"axisTick\": {\"show\": false},\"axisLabel\": {\"textStyle\": {\"show\": true,\"color\": \"#333\",\"fontSize\": \"14\"},\"margin\": 20},\"axisLine\": {\"lineStyle\": {\"width\": 1,\"color\": \"rgba(255,255,255,0.2)\"}}},\"yAxis\": {\"name\": \"单位\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"dashed\",\"width\": 1,\"color\": \"#E8E8E8\"}},\"axisLine\": {\"show\": false},\"axisLabel\": {\"textStyle\": {\"show\": true,\"color\": \"#333\",\"fontSize\": \"14\"}}},\"graphic\": [{\"type\": \"image\",\"style\": {\"width\": \"98%\",\"height\": 100,\"left\": \"4%\"},\"bottom\": 0,\"left\": \"center\",\"z\": -1}],\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"10%\",\"containLabel\": true},\"series\": [{\"data\": [0.36,0.36,0.36,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.35,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37,0.37],\"name\": \"\",\"type\": \"line\",\"symbol\": \"circle\",\"smooth\": false,\"symbolSize\": 0,\"lineStyle\": {\"width\": 4,\"type\": \"solid\"},\"itemStyle\": {\"borderWidth\": 6,\"borderColor\": \"rgb(41, 169, 255)\",\"color\": \"rgb(41, 169, 255)\"},\"areaStyle\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(38, 131, 255, 0.2)\"},{\"offset\": 1,\"color\": \"rgba(38, 131, 255, 0)\"}],\"global\": false}},\"markLine\": {\"symbol\": \"none\",\"data\": [{\"type\": \"average\",\"name\": \"平均值\",\"label\": {\"normal\": {\"position\": \"end\",\"color\": \"rgb(0, 255, 42)\",\"fontSize\": \"16px\",\"fontWeight\": \"bold\",\"distance\": [-100,0],\"formatter\": \"平均值{c}\"}}}]},\"markPoint\": {\"symbolSize\": [90,50],\"symbolOffset\": [50,0],\"textAlign\": \"left\",\"itemStyle\": {\"color\": \"rgb(255, 255, 255)\",\"borderWidth\": [2],\"shadowBlur\": 10,\"shadowColor\": \"rgba(153, 196, 255, 0.8)\",\"shadowOffsetX\": 3,\"shadowOffsetY\": 3},\"symbol\": \"path://M17,39L17,51Q17,51.1473,17.00723,51.2944Q17.01445,51.4415,17.02889,51.5881Q17.043329999999997,51.7347,17.06494,51.8804Q17.086550000000003,52.0261,17.11529,52.1705Q17.144019999999998,52.315,17.17981,52.4579Q17.215600000000002,52.6008,17.25836,52.7417Q17.30111,52.8827,17.35073,53.0213Q17.40036,53.16,17.45672,53.2961Q17.51309,53.4322,17.57606,53.5653Q17.63904,53.6985,17.70847,53.8284Q17.777900000000002,53.9583,17.853630000000003,54.0846Q17.92935,54.2109,18.01118,54.3334Q18.09301,54.4559,18.18075,54.5742Q18.26849,54.6925,18.36194,54.8064Q18.455379999999998,54.9202,18.55429,55.0294Q18.65321,55.1385,18.75736,55.2426Q18.861510000000003,55.3468,18.97064,55.4457Q19.0798,55.5446,19.1936,55.6381Q19.307499999999997,55.7315,19.425800000000002,55.8192Q19.5441,55.907,19.666600000000003,55.9888Q19.789,56.0706,19.915399999999998,56.1464Q20.0417,56.2221,20.171599999999998,56.2915Q20.3015,56.361,20.4347,56.4239Q20.5678,56.4869,20.7039,56.5433Q20.84,56.5996,20.9787,56.6493Q21.1173,56.6989,21.2583,56.7416Q21.3992,56.7844,21.542099999999998,56.8202Q21.685000000000002,56.856,21.8295,56.8847Q21.9739,56.9134,22.1196,56.9351Q22.2653,56.9567,22.4119,56.9711Q22.558500000000002,56.9855,22.7056,56.9928Q22.8527,57,23,57L100,57Q100.1473,57,100.2944,56.9928Q100.4415,56.9855,100.5881,56.9711Q100.7347,56.9567,100.8804,56.9351Q101.0261,56.9134,101.1705,56.8847Q101.315,56.856,101.4579,56.8202Q101.6008,56.7844,101.7417,56.7416Q101.8826,56.6989,102.0213,56.6493Q102.16,56.5996,102.2961,56.5433Q102.4322,56.4869,102.5653,56.4239Q102.6985,56.361,102.8284,56.2915Q102.9583,56.2221,103.0846,56.1464Q103.2109,56.0706,103.3334,55.9888Q103.4559,55.907,103.5742,55.8192Q103.6925,55.7315,103.8063,55.6381Q103.9202,55.5446,104.0293,55.4457Q104.1385,55.3468,104.2426,55.2426Q104.3468,55.1385,104.4457,55.0294Q104.5446,54.9202,104.6381,54.8064Q104.7315,54.6925,104.8192,54.5742Q104.907,54.4559,104.9888,54.3334Q105.0706,54.2109,105.1464,54.0846Q105.2221,53.9583,105.2915,53.8284Q105.361,53.6985,105.4239,53.5653Q105.4869,53.4322,105.5433,53.2961Q105.5996,53.16,105.6493,53.0213Q105.6989,52.8827,105.7416,52.7417Q105.7844,52.6008,105.8202,52.4579Q105.856,52.315,105.8847,52.1705Q105.9134,52.0261,105.9351,51.8804Q105.9567,51.7347,105.9711,51.5881Q105.9855,51.4415,105.9928,51.2944Q106,51.1473,106,51L106,12Q106,11.85271,105.9928,11.70559Q105.9855,11.55848,105.9711,11.4119Q105.9567,11.26531,105.9351,11.119620000000001Q105.9134,10.97392,105.8847,10.829460000000001Q105.856,10.684999999999999,105.8202,10.54212Q105.7844,10.399239999999999,105.7416,10.258289999999999Q105.6989,10.11734,105.6493,9.97866Q105.5996,9.83998,105.5433,9.7039Q105.4869,9.567820000000001,105.4239,9.43467Q105.3609,9.30152,105.2915,9.17162Q105.2221,9.04172,105.1464,8.915379999999999Q105.0706,8.78905,104.9888,8.66658Q104.907,8.54411,104.8192,8.4258Q104.7315,8.307500000000001,104.6381,8.19364Q104.5446,8.07978,104.4457,7.97065Q104.3468,7.86151,104.2426,7.75736Q104.1385,7.65321,104.0293,7.55429Q103.9202,7.45538,103.8064,7.36194Q103.6925,7.2684999999999995,103.5742,7.18075Q103.4559,7.09301,103.3334,7.0111799999999995Q103.2109,6.9293510000000005,103.0846,6.8536280000000005Q102.9583,6.777905,102.8284,6.708472Q102.6985,6.63904,102.5653,6.576064Q102.4322,6.513089,102.2961,6.456723Q102.16,6.400357,102.0213,6.350736Q101.8827,6.301114,101.7417,6.258358Q101.6008,6.215601,101.4579,6.179812Q101.315,6.144023,101.1705,6.115288Q101.0261,6.0865531,100.8804,6.0649409Q100.7347,6.0433288,100.5881,6.0288916Q100.4415,6.0144545,100.2944,6.00722726Q100.1473,6,100,6L23,6Q22.8527,6,22.7056,6.00722726Q22.558500000000002,6.0144545,22.4119,6.0288916Q22.2653,6.0433288,22.1196,6.0649409Q21.9739,6.0865531,21.8295,6.115288Q21.685000000000002,6.144023,21.542099999999998,6.179812Q21.3992,6.215601,21.2583,6.258358Q21.1173,6.301114,20.9787,6.350736Q20.84,6.400357,20.7039,6.456723Q20.5678,6.513089,20.4347,6.576064Q20.3015,6.63904,20.171599999999998,6.708472Q20.0417,6.777905,19.915399999999998,6.8536280000000005Q19.789,6.9293510000000005,19.666600000000003,7.0111799999999995Q19.5441,7.09301,19.425800000000002,7.18075Q19.307499999999997,7.2684999999999995,19.1936,7.36194Q19.0798,7.45538,18.97064,7.55429Q18.861510000000003,7.65321,18.75736,7.75736Q18.65321,7.86151,18.55429,7.97065Q18.455379999999998,8.07978,18.36194,8.19364Q18.26849,8.307500000000001,18.18075,8.4258Q18.09301,8.54411,18.01118,8.66658Q17.92935,8.78905,17.853630000000003,8.915379999999999Q17.777900000000002,9.04172,17.70847,9.17162Q17.63904,9.30152,17.57606,9.43467Q17.51309,9.567820000000001,17.45672,9.7039Q17.40036,9.83998,17.35073,9.97866Q17.30111,10.11734,17.25836,10.258289999999999Q17.215600000000002,10.399239999999999,17.17981,10.54212Q17.144019999999998,10.684999999999999,17.11529,10.829460000000001Q17.086550000000003,10.97392,17.06494,11.119620000000001Q17.043329999999997,11.26532,17.02889,11.4119Q17.01445,11.55848,17.00723,11.70559Q17,11.85271,17,12L17,24L9,31.5L17,39Z\",\"data\": [{\"type\": \"max\",\"name\": \"最大值\",\"label\": {\"position\": \"start\",\"padding\": [5,0,5,20],\"formatter\": \"最大值\\n{blue|{c}} Mpa\",\"rich\": {\"blue\": {\"color\": \"rgba(38, 131, 255, 1)\",\"fontWeight\": \"bold\",\"fontSize\": 18,\"lineHeight\": 30}}}},{\"type\": \"min\",\"name\": \"最小值\",\"label\": {\"show\": true,\"position\": \"start\",\"padding\": [5,0,5,20],\"formatter\": \"最小值\\n{blue|{c}} Mpa\",\"rich\": {\"blue\": {\"color\": \"rgba(38, 131, 255, 1)\",\"fontWeight\": \"bold\",\"fontSize\": 18,\"lineHeight\": 30}}}}]}}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("点位压力情况");
        chartResult.setText("2024年10月21日至2024年10月30日，减压阀-高速公路南侧总减压井压力最高值为0.37Mpa，发生在2024-10-30 03:30:27;最低值为0.21Mpa，发生在2024-10-15 23:00:00;详情参见图表。");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("2024年10月1日至2024年10月30日，减压阀-高速公路南侧总减压井压力最高值为0.37兆帕，发生在2024年10月30日 3点30分27秒;最低值为0.21兆帕，发生在2024年10月15日23点;详情参见图表。");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * {名称}本月压力情况？
     * 我想看下{名称}2024年10月1日至2024年10月29日的压力
     * @param monitorName
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultForFrontVo getHistoryDataByMonitor(String monitorName, String startTime, String endTime) {
        return getHistoryDataByMonitor(CommonConstant.CHANNEL_CODE_BIGSCREEN,monitorName,startTime,endTime);
    }


    /**
     * 现在已安装多少监测设备？
     * 目前监测设备建设情况？
     * 监测设备运行情况？
     * @return
     */
    @Override
    public ResultForFrontVo pipeMonitorDeviceStatistics(String channelCode) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"title\": \"管网物联监测设备\",\"text\": \"\",\"type\": \"echarts\",\"speechText\": \"阜康市区目前建设有305个监测设备，其中压力监测48个；流量监测252个；水质监测5个，具体情况见下方图表\",\"options\": [{\"echartTitle\": \"设备状态统计\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(3, 177, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(55, 224, 255, 1)\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(198, 198, 198, 1)\"},{\"offset\": 1,\"color\": \"rgba(171, 171, 171, 1)\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 41, 41, 1)\"},{\"offset\": 1,\"color\": \"rgba(255, 128, 128, 1)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备总数\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"305\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"在线\",\"value\": 244,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"离线\",\"value\": 61,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"报警\",\"value\": 12,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|个}{line2|{d}%}\\n{hr|}\",\"color\": \"#fffdef\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-14,15,0,15],\"color\": \"#FFF\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 16,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#fff\",\"fontSize\": 14,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"在线\",\"value\": 244},{\"name\": \"离线\",\"value\": 61},{\"name\": \"报警\",\"value\": 12}]}]},{\"echartTitle\": \"设备类别统计\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备总数\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"305\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"水质监测点\",\"value\": 5,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"流量\",\"value\": 252,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"压力\",\"value\": 48,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|个}{line2|{d}%}\\n{hr|}\",\"color\": \"#fffdef\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-12,15,0,15],\"color\": \"#FFF\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 16,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#fff\",\"fontSize\": 14,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"水质监测点\",\"value\": 5},{\"name\": \"流量\",\"value\": 252},{\"name\": \"压力\",\"value\": 48}]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"title\": \"管网物联监测设备\",\"text\": \"\",\"type\": \"echarts\",\"speechText\": \"阜康市区目前建设有305个监测设备，其中压力监测48个；流量监测252个；水质监测5个，具体情况见下方图表\",\"options\": [{\"echartId\": \"echarts-17301828771011\",\"echartTitle\": \"设备状态统计\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(3, 177, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(55, 224, 255, 1)\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(198, 198, 198, 1)\"},{\"offset\": 1,\"color\": \"rgba(171, 171, 171, 1)\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 41, 41, 1)\"},{\"offset\": 1,\"color\": \"rgba(255, 128, 128, 1)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备总数\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"305\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient-echarts-17301828771011)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"在线\",\"value\": 244,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"离线\",\"value\": 61,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"报警\",\"value\": 12,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|个}{line2|{d}%}\\n{hr|}\",\"color\": \"#333\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-14,15,0,15],\"color\": \"#333\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 16,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#333\",\"fontSize\": 14,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"在线\",\"value\": 244},{\"name\": \"离线\",\"value\": 61},{\"name\": \"报警\",\"value\": 12}]}]},{\"echartId\": \"echarts-173018287710112\",\"echartTitle\": \"设备类别统计\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备总数\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"305\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient-echarts-173018287710112)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"水质监测点\",\"value\": 5,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"流量\",\"value\": 252,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"压力\",\"value\": 48,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|个}{line2|{d}%}\\n{hr|}\",\"color\": \"#fffdef\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-12,15,0,15],\"color\": \"#333\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 16,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#333\",\"fontSize\": 14,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"水质监测点\",\"value\": 5},{\"name\": \"流量\",\"value\": 252},{\"name\": \"压力\",\"value\": 48}]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("监测设备建设情况");
        chartResult.setText("阜康市区目前建设有305个监测设备，其中压力监测48个；流量监测252个；水质监测5个，详情参见图表。");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("阜康市区目前建设有305个监测设备，其中压力监测48个；流量监测252个；水质监测5个，详情参见图表。");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 现在已安装多少监测设备？
     * 目前监测设备建设情况？
     * 监测设备运行情况？
     * @return
     */
    @Override
    public ResultForFrontVo pipeMonitorDeviceStatistics() {
        return pipeMonitorDeviceStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 目前有多少压力监测设备？
     * 压力监测设备情况？
     */
    @Override
    public ResultForFrontVo getPressureMonitorCount(String channelCode) {


        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"阜康市区共有<strong>48</strong>个压力监测设备。其中:\",\"title\": \"\",\"speechText\": \"阜康市区目前建设有48个压力监测设备，其中在线37个，离线11个；目前有7个点位正在报警，当前压力最高的点位为准东西苑小区东侧，压力值：0.51Mpa\",\"ulDataList\":[\"在线设备:<strong>37</strong>,占比77.08%\",\"离线设备:<strong>11</strong>,占比22.92%\",\"报警设备:<strong>7</strong>,占比14.58%\"]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"阜康市区共有<strong>48</strong>个压力监测设备。其中:\",\"title\": \"压力监测点统计\",\"speechText\": \"阜康市区目前建设有48个压力监测设备，其中在线37个，离线11个；目前有7个点位正在报警，当前压力最高的点位为准东西苑小区东侧，压力值：0.51Mpa\",\"ulDataList\": [\"在线设备: <strong>37</strong>, 占比77.08%\",\"离线设备: <strong>11</strong>, 占比22.92%\",\"报警设备: <strong>7</strong>, 占比14.58%\"]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
        TextResult textResult = new TextResult();
        textResult.setText("阜康市区目前建设有48个压力监测设备，其中在线37个，离线11个；目前有7个点位正在报警。");
        List<String> textData = new ArrayList<>();
        textData.add("在线设备: <strong>37</strong>, 占比77.08%");
        textData.add("离线设备: <strong>11</strong>, 占比22.92%");
        textData.add("报警设备: <strong>7</strong>, 占比14.58%");
        textResult.setTextData(textData);
        textResult.setSpeechText("阜康市区目前建设有48个压力监测设备，其中在线37个，离线11个；目前有7个点位正在报警。");
        resultForFrontVo.setTextResult(textResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 目前有多少压力监测设备？
     * 压力监测设备情况？
     */
    @Override
    public ResultForFrontVo getPressureMonitorCount() {
        return getPressureMonitorCount(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 目前有多少流量监测设备？
     * 流量监测设备情况？
     */
    @Override
    public ResultForFrontVo getFlowMonitorCount(String channelCode) {


        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"阜康市区共有<strong>252</strong>个流量监测设备。其中:\",\"title\": \"\",\"speechText\": \"阜康市区目前建设有252个流量监测设备，其中在线203个，离线49个；目前有5个点位正在报警\",\"ulDataList\":[\"在线设备:<strong>203</strong>,占比80.56%\",\"离线设备:<strong>49</strong>,占比19.44%\",\"报警设备:<strong>5</strong>,占比1.98%\"]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"text\",\"text\": \"阜康市区共有<strong>252</strong>个流量监测设备。其中:\",\"title\": \"流量监测点统计\",\"speechText\": \"阜康市区目前建设有252个流量监测设备，其中在线203个，离线49个；目前有5个点位正在报警\",\"ulDataList\": [\"在线设备: <strong>203</strong>, 占比80.56%\",\"离线设备: <strong>49</strong>, 占比19.44%\",\"报警设备: <strong>5</strong>, 占比1.98%\"]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
        TextResult textResult = new TextResult();
        List<String> textData = new ArrayList<>();
        textData.add("在线设备: <strong>203</strong>, 占比80.56%");
        textData.add("离线设备: <strong>49</strong>, 占比19.44%");
        textData.add("报警设备: <strong>5</strong>, 占比1.98%");
        textResult.setTextData(textData);
        textResult.setText("阜康市区目前建设有252个流量监测设备，其中在线203个，离线49个；目前有5个点位正在报警");
        textResult.setSpeechText("阜康市区目前建设有252个流量监测设备，其中在线203个，离线49个；目前有5个点位正在报警");
        resultForFrontVo.setTextResult(textResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 目前有多少流量监测设备？
     * 流量监测设备情况？
     */
    @Override
    public ResultForFrontVo getFlowMonitorCount() {
        return getFlowMonitorCount(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 目前有多少报警点？
     * 目前管网运行报警情况？
     * 目前管网运行有什么问题？
     */
    @Override
    public ResultForFrontVo getAlarmMonitor(String channelCode) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"2024年8月23日监测点报警情况\",\"speechText\": \"阜康市区目前建设有305个监测设备,目前共有12个点位报警，其中压力监测7个；流量监测5个；具体情况见下表。\",\"tableConfig\": {\"propList\": [{\"prop\": \"name\",\"label\": \"监测点名称\"},{\"prop\": \"value\",\"label\": \"当前检测值\"},{\"prop\": \"\",\"label\": \"正常阈值范围\"}],\"total\": 0,\"tableList\": []}}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"2024年8月23日监测点报警情况\",\"speechText\": \"阜康市区目前建设有305个监测设备,目前共有12个点位报警，其中压力监测7个；流量监测5个；具体情况见下表。\",\"options\": {\"propList\": [{\"prop\": \"name\",\"label\": \"报警地点\"},{\"prop\": \"value\",\"label\": \"当前检测值\"},{\"prop\": \"range\",\"label\": \"正常值范围\"}],\"total\": 15,\"tableList\": [{\"name\": \"减压阀-阜新路阜兴花园南大门\",\"value\": 0.24,\"range\": \"4.000~12.000\"},{\"name\": \"减压阀-天池街万邦医院北侧\",\"value\": 0.429,\"range\": \"0.150~0.300\"},{\"name\": \"减压阀-天池街与新运路口西北角\",\"value\": 0,\"range\": \"0.100~0.300\"},{\"name\": \"减压阀-迎宾路与龙潭路口\",\"value\": 0.473,\"range\": \"0.250~0.450\"},{\"name\": \"减压阀-迎宾路与桃源路口\",\"value\": 0.468,\"range\": \"0.470~0.670\"},{\"name\": \"减压阀-迎宾路与新运路口\",\"value\": 0.414,\"range\": \"0.150~0.350\"},{\"name\": \"减压阀-淮喝尔市民主路\",\"value\": 0,\"range\": \"0.210~0.410\"},{\"name\": \"康宁西苑\",\"value\": 10.088,\"range\": \"20.000~20.000\"},{\"name\": \"蓝琦小区\",\"value\": 17,\"range\": \"30.000~50.000\"},{\"name\": \"南华路碧琳城门口减压阀后端压力\",\"value\": 0.248,\"range\": \"0.250~0.450\"},{\"name\": \"南华路与康宁路十字路口\",\"value\": 0.352,\"range\": \"0.270~0.350\"},{\"name\": \"天池街万邦医院北侧减压阀后端压力\",\"value\": 0.359,\"range\": \"0.150~0.300\"},{\"name\": \"天池街与新运路口西北角减压阀后端压力\",\"value\": 0.405,\"range\": \"0.150~0.300\"},{\"name\": \"文昌路与龙潭路减压阀后端压力\",\"value\": 0.024,\"range\": \"0.250~0.450\"},{\"name\": \"迎宾路与桃源路口减压阀后端压力\",\"value\": 0.319,\"range\": \"0.470~0.670\"},{\"name\": \"淮东创业路监测站丁字口\",\"value\": 0.325,\"range\": \"0.400~0.620\"}]}}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
        TextResult textResult = new TextResult();
        textResult.setText("阜康市区目前建设有305个监测设备,目前共有12个点位报警，其中压力监测7个；流量监测5个；");
        textResult.setSpeechText("阜康市区目前建设有305个监测设备,目前共有12个点位报警，其中压力监测7个；流量监测5个；");
        resultForFrontVo.setTextResult(textResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 目前有多少报警点？
     * 目前管网运行报警情况？
     * 目前管网运行有什么问题？
     */
    @Override
    public ResultForFrontVo getAlarmMonitor() {
        return getAlarmMonitor(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 目前管网运行情况？
     * 管网现在是什么情况？
     */
    @Override
    public ResultForFrontVo pipeStatistics(String channelCode) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{'type': 'echarts','text': '','title': '2024年度新增用户统计','options': [{'graphic': [{'type': 'image','style': {'image': 'echarts/barbg.png','width': '98%','height': 100,'left': '4%'},'bottom': 0,'left': 'center','z': -1}],'grid': {'top': '15%','left': '5%','right': '5%','bottom': '15%','containLabel': true},'xAxis': {'type': 'category','data': ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],'axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(255, 255, 255,0.08)'}},'splitLine': {'show': false},'axisLabel': {'color': '#fff'},'axisTick': {'show': false}},'yAxis': {'name': '单位:户','nameTextStyle': {'color': '#fff','nameLocation': 'start'},'type': 'value','axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(255, 255, 255,0.08)'}},'splitLine': {'show': true,'lineStyle': {'type': 'solid','width': 0.5,'color': 'rgb(255, 255, 255,0.4)'}},'axisTick': {'show': false},'axisLabel': {'fontSize': 14,'color': '#FFF'}},'series': [{'name': '','yAxisIndex': 0,'type': 'custom','renderItemConfig': {'type': 'group','x': 0,'children': [{'type': 'CubeLeft','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeRight','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeTop','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': 'rgb(10, 223, 239)'}}]},'data': [525,183,801,693,149,146,160,115,124,137,0,0]},{'type': 'bar','yAxisIndex': 0,'label': {'show': true,'color': '#ffffff','offset': [0,-4],'position': 'top','fontSize': '14','rich': {'bg': {'backgroundColor': {'image': 'echarts/bluebg.png'},'padding': [6,6]}},'formatter': '{bg| {c} }'},'itemStyle': {'color': 'transparent'},'tooltip': {'show': false},'data': [525,183,801,693,149,146,160,115,124,137,0,0]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{'type': 'echarts','text': '','title': '新增用户统计','options': [{'echartId': 'echarts-173018044932941','graphic': [{'type': 'image','style': {'width': '98%','height': 100,'left': '4%'},'bottom': 0,'left': 'center','z': -1}],'grid': {'top': '15%','left': '5%','right': '5%','bottom': '15%','containLabel': true},'xAxis': {'type': 'category','data': ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],'axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(200,200,200,.5)'}},'splitLine': {'show': false},'axisLabel': {'color': '#333'},'axisTick': {'show': false}},'yAxis': {'name': '单位:户','nameTextStyle': {'color': '#333','nameLocation': 'start'},'type': 'value','axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(200,200,200,.5)'}},'splitLine': {'show': true,'lineStyle': {'width': 0.5,'color': 'rgb(200,200,200,.5)','type': 'dashed'}},'axisTick': {'show': false},'axisLabel': {'fontSize': 14,'color': '#333'}},'series': [{'name': '','yAxisIndex': 0,'type': 'custom','renderItemConfig': {'type': 'group','x': 0,'children': [{'type': 'CubeLeft','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeRight','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeTop','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': 'rgb(10, 223, 239)'}}]},'data': [525,183,801,693,149,146,160,115,124,137,0,0]},{'type': 'bar','yAxisIndex': 0,'label': {'show': true,'color': '#333','offset': [0,-10],'position': 'top','fontSize': '14'},'itemStyle': {'color': 'transparent'},'tooltip': {'show': false},'data': [525,183,801,693,149,146,160,115,124,137,0,0]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网监测总览");
        urlResult.setUrl("http://123.57.11.138:8087/SmartWater/DevelopmentCase/PLDS/RunningOverView/GisOverview/tianMap.html");
//        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/PLDS/RunningOverView/GisOverview/tianMap.html");
        urlResult.setSpeechText("为您展示管网监测总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 目前管网运行情况？
     * 管网现在是什么情况？
     */
    @Override
    public ResultForFrontVo pipeStatistics() {
        return this.pipeStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 压力监测-GIS总览
     */
    @Override
    public ResultForFrontVo gisStatisticsByPressure(String channelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("压力监测GIS总览");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/PLDS/PressureMonitor/PressureGis/gaoMap.html");
        urlResult.setSpeechText("为您展示压力监测GIS总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 压力监测-GIS总览
     */
    @Override
    public ResultForFrontVo gisStatisticsByPressure() {
        return gisStatisticsByPressure(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 管网流量监测一张图
     * 管网流量GIS一张图
     */
    @Override
    public ResultForFrontVo gisStatisticsByFlow(String channelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网流量监测一张图");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/PLDS/FlowQuality/FlowGis/tianMap.html");
        urlResult.setSpeechText("为您展示管网流量监测一张图");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 管网流量监测一张图
     * 管网流量GIS一张图
     */
    @Override
    public ResultForFrontVo gisStatisticsByFlow() {
        return gisStatisticsByFlow(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 管网水质监测一张图
     * 管网水质GIS一张图
     */
    @Override
    public ResultForFrontVo gisStatisticsByQuality(String channelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网水质监测一张图");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/PLDS/WaterQuality/WaterGis/gaoMap.html");
        urlResult.setSpeechText("为您展示管网水质监测一张图");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 管网水质监测一张图
     * 管网水质GIS一张图
     */
    @Override
    public ResultForFrontVo gisStatisticsByQuality() {
        return gisStatisticsByQuality(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }









}
