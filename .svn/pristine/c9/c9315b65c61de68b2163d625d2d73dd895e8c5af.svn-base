package com.hanwei.process.business.gis.service.impl;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.gis.service.IGisService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.vo.ChartResult;
import com.hanwei.process.vo.ResultForFrontVo;
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
public class GisServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IGisService {

    /**
     * 查询管网整体概况
     * 当前各类管网长度情况统计
     * 目前各类管网有多长？
     * 管网长度是多少？
     * 各类型管网铺设情况？
     * @param queryType
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatistics(String channelCode, String queryType) {

        queryType = "1";
        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"阜康市区全部管网长度为563.28Km，其中：\",\"title\": \"管网管线统计\",\"ulDataList\": [\"排水管网长度:174.84km,占比31.04%\",\"供水管网长度:235.22km,占比41.72%\",\"绿化管网长度:153.22km,占比27.20%\"],\"speechText\": \"你好，你可以向我提问任何问题\",\"options\": [{\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"管网长度\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"563.28\",\"textAlign\": \"center\",\"font\": \" 34px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( km )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"供水管网\",\"value\": 235.22,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"排水管网\",\"value\": 174.84,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"绿化管网\",\"value\": 153.22,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|km}{line2|{d}%}\\n{hr|}\",\"color\": \"#fffdef\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-12,15,0,15],\"color\": \"#FFF\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 16,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#fff\",\"fontSize\": 16,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"供水管网\",\"value\": 235.22},{\"name\": \"排水管网\",\"value\": 174.84},{\"name\": \"绿化管网\",\"value\": 153.22}]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"阜康区域全部管网长度为563.28Km，其中：\",\"title\": \"管网管线统计\",\"ulDataList\": [\"供水管网:长度<strong>235.22</strong>km, 占比41.7%\",\"供水管网:长度<strong>174.84</strong>km, 占比31.0%\",\"供水管网:长度<strong>153.22</strong>km, 占比27.3%\"],\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": [{\"echartId\": \"echarts-1730182877101\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"center\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 18,\"style\": {\"text\": \"管网长度\",\"font\": \" 12px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"#333333\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 34,\"style\": {\"text\": \"9834\",\"fill\": \"#333333\",\"textAlign\": \"center\",\"font\": \" 24px \\\"Dinpro\\\", sans-serif\"}},{\"type\": \"text\",\"top\": 55,\"style\": {\"text\": \"( km )\",\"font\": \" 12px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333333\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"series\": [{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"45%\",\"65%\"],\"padAngle\": 4,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"供水管网\",\"value\": 235.22,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"排水管网\",\"value\": 174.84,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"绿化管网\",\"value\": 153.22,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}],\"labelLine\": {\"length\": 15,\"length2\": 25},\"label\": {\"formatter\": \"{name|{b}}\\n{line2|{c}}{unit|km}{line2|{d}%}\\n{hr|}\",\"color\": \"#fffdef\",\"rich\": {\"hr\": {\"backgroundColor\": \"auto\",\"borderRadius\": 3,\"width\": 3,\"height\": 3,\"padding\": [3,3,0,-12]},\"name\": {\"padding\": [-12,15,0,15],\"color\": \"#333\",\"fontSize\": 12,\"fontFamily\": \"AlibabaRegular\"},\"line2\": {\"padding\": [0,10,-20,5],\"fontFamily\": \"AlibabaRegular\",\"fontSize\": 10,\"fontWeight\": \"bold\"},\"unit\": {\"color\": \"#333\",\"fontSize\": 10,\"padding\": [0,0,-20,0],\"fontFamily\": \"AlibabaRegular\"}}}},{\"type\": \"pie\",\"center\": [\"50%\",\"50%\"],\"radius\": [\"37%\",\"75%\"],\"label\": {\"show\": false},\"padAngle\": 4,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"供水管网\",\"value\": 235.22,\"label\": {\"color\": \"rgb(251, 132, 88)\"}},{\"name\": \"排水管网\",\"value\": 174.84,\"label\": {\"color\": \"rgb(53, 251, 152)\"}},{\"name\": \"绿化管网\",\"value\": 153.22,\"label\": {\"color\": \"rgb(7, 143, 240)\"}}]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("阜康市管网整体情况");
        chartResult.setText("阜康市区全部管网长度为563.28公里，其中供水管网长度:235.22公里，占比42%。排水管网长度:174.84 公里，占比31%。绿化管网长度:153.22公里，占比27%，");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("阜康市区全部管网长度为563.28公里，其中供水管网长度:235.22公里，占比42%。排水管网长度:174.84 公里，占比31%。绿化管网长度:153.22公里，占比27%，");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }


    /**
     * 查询管网整体概况
     * 当前各类管网长度情况统计
     * 目前各类管网有多长？
     * 管网长度是多少？
     * 各类型管网铺设情况？
     * @param queryType
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatistics(String queryType) {
        return this.pipeStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN,queryType);
    }

    /**
     * 按照管径统计管网情况
     * 按管径统计管网长度
     * 目前管网都有什么口径的？
     * 100的管网有多少？
     * @param diameter
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatisticsByDiameter(String channelCode, String diameter) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"管径统计管网情况\",\"options\": [{\"echartId\": \"echarts-173018044932941\",\"graphic\": [{\"type\": \"image\",\"style\": {\"image\": \"echarts/barbg.png\",\"width\": \"98%\",\"height\": 100,\"left\": \"4%\"},\"bottom\": 0,\"left\": \"center\",\"z\": -1}],\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"15%\",\"containLabel\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"100\",\"150\",\"160\",\"200\",\"250\",\"300\",\"400\",\"500\",\"600\",\"800\",\"1000\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#fff\"},\"axisTick\": {\"show\": false}},\"yAxis\": {\"name\": \"单位:户\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"solid\",\"width\": 0.5,\"color\": \"rgb(255, 255, 255,0.4)\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#FFF\"}},\"series\": [{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": 0,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(10, 223, 239)\"},{\"offset\": 1,\"color\": \"rgb(7, 102, 230)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(10, 223, 239)\"},{\"offset\": 1,\"color\": \"rgb(7, 102, 230)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgb(10, 223, 239)\"}}]},\"data\":[22,2,21,61,3,114,69,73,21,17,10]},{\"type\": \"bar\",\"yAxisIndex\": 0,\"label\": {\"show\": true,\"color\": \"#ffffff\",\"offset\": [0,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"backgroundColor\": {\"image\": \"echarts/bluebg.png\"},\"padding\": [6,6]}},\"formatter\": \"{bg| {c} }\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\":[ 22,2,21,61,3,114,69,73,21,17,10]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"管径统计管网情况\",\"title\": \"管径统计管网情况\",\"speechText\": \"管径统计管网情况如下图所示\",\"id\": \"echarts-17301804493294\",\"options\": [{\"echartId\": \"echarts-1\",\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"10%\",\"containLabel\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"100\",\"200\",\"300\",\"600\",\"800\",\"900\",\"1000\",\"1100\",\"1200\",\"1300\",\"1400\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"#DDDDDD\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#333\"},\"axisTick\": {\"show\": true}},\"yAxis\": [{\"name\": \"单位:km\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": false},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"dashed\",\"width\": 1,\"color\": \"#E8E8E8\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333333\"}},{\"name\": \"单位:%\",\"nameTextStyle\": {\"color\": \"#333333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": false},\"splitLine\": {\"show\": false},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333\"}}],\"series\": [{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": -12,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(194, 225, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(46, 155, 255, 1)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(194, 225, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(46, 155, 255, 1)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 1)\"}}]},\"data\": [10,50,40,60,70,80,90,100,110,120,130]},{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": -12,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}}]},\"data\": [100,100,100,100,100,100,100,100,100,100,100,100]},{\"type\": \"bar\",\"yAxisIndex\": 0,\"label\": {\"show\": true,\"color\": \"#1991FF\",\"offset\": [4,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"padding\": [6,6]}},\"formatter\": \"{bg| {c} }\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\": [10,50,40,60,70,80,90,100,110,120,130]},{\"type\": \"bar\",\"yAxisIndex\": 1,\"label\": {\"show\": true,\"color\": \"#04D61A\",\"offset\": [0,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"padding\": [6,6]}},\"formatter\": \"{bg|{c}%}\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\": [11,15,24,16,17,20,21,22,22,22,22]},{\"name\": \"\",\"type\": \"custom\",\"yAxisIndex\": 1,\"renderItemConfig\": {\"type\": \"group\",\"x\": 18,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(183, 255, 183, 1)\"},{\"offset\": 1,\"color\": \"rgba(34, 195, 71, 1)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(183, 255, 183, 1)\"},{\"offset\": 1,\"color\": \"rgba(34, 195, 71, 1)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(183, 255, 183, 1)\"}}]},\"data\": [11,15,24,16,17,20,21,22,22,22,22]},{\"name\": \"\",\"type\": \"custom\",\"yAxisIndex\": 1,\"renderItemConfig\": {\"type\": \"group\",\"x\": 18,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(13, 152, 44, 0.1)\"}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(13, 152, 44, 0.1)\"}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(13, 152, 44, 0.1)\"}}]},\"data\": [25,25,25,25,25,25,25,25,25,25,25,25]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("阜康市管网按管径统计情况");
        chartResult.setText("阜康市区全部管网长度为563.28公里;管径最小为DN30,最大为DN1000；详情参见图表。");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("阜康市区全部管网长度为563.28公里;管径最小为DN30,最大为DN1000；详情参见图表。");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 按照管径统计管网情况
     * 按管径统计管网长度
     * 目前管网都有什么口径的？
     * 100的管网有多少？
     * @param diameter
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatisticsByDiameter(String diameter) {
        return this.pipeStatisticsByDiameter(CommonConstant.CHANNEL_CODE_BIGSCREEN,diameter);
    }


    /**
     * 按照材质统计管网情况
     * 按照材质统计管网长度
     * 目前管网都有什么材质的？
     * PE的供水/排水/绿化管网有多少？
     * @param material
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatisticsByMaterial(String channelCode, String material) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"材质统计管网情况\",\"options\": [{\"echartId\": \"echarts-17301804493295\",\"graphic\": [{\"type\": \"image\",\"style\": {\"image\": \"echarts/barbg.png\",\"width\": \"98%\",\"height\": 100,\"left\": \"4%\"},\"bottom\": 0,\"left\": \"center\",\"z\": -1}],\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"15%\",\"containLabel\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"PE\",\"砼\",\"球墨铸铁\",\"铸铁\",\"\",\"球磨铸铁\",\"PVC\",\"水泥\",\"砖石\",\"钢\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#fff\"},\"axisTick\": {\"show\": false}},\"yAxis\": [{\"name\": \"单位:km\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"solid\",\"width\": 0.5,\"color\": \"rgb(255, 255, 255,0.4)\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#FFF\"}},{\"name\": \"单位:%\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"type\": \"value\",\"min\": 0,\"max\": 100,\"interval\": 20,\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#FFF\"}}],\"series\": [{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": -12,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(10, 223, 239)\"},{\"offset\": 1,\"color\": \"rgb(7, 102, 230)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(10, 223, 239)\"},{\"offset\": 1,\"color\": \"rgb(7, 102, 230)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(10, 223, 239)\"},{\"offset\": 1,\"color\": \"rgb(7, 102, 230)\"}],\"global\": false}}}]},\"data\":[240.75,126.26,19.29,15.59,6.35,5.60,1.50,0.80,0.22,0.02]},{\"type\": \"bar\",\"yAxisIndex\": 0,\"label\": {\"show\": true,\"color\": \"#ffffff\",\"offset\": [4,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"backgroundColor\": {\"image\": \"echarts/bluebg.png\"},\"padding\": [6,6]}},\"formatter\": \"{bg| {c} }\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\":[240.75,126.26,19.29,15.59,6.35,5.60,1.50,0.80,0.22,0.02]},{\"type\": \"bar\",\"yAxisIndex\": 1,\"label\": {\"show\": true,\"color\": \"#ffffff\",\"offset\": [0,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"backgroundColor\": {\"image\": \"echarts/orangebg.png\"},\"padding\": [6,6]}},\"formatter\": \"{bg|{c}%}\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\": [57.82,30.32,4.63,3.74,1.52,1.34,0.36,0.19,0.05,0.01]},{\"name\": \"\",\"type\": \"custom\",\"yAxisIndex\": 1,\"renderItemConfig\": {\"type\": \"group\",\"x\": 18,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 185, 87)\"},{\"offset\": 1,\"color\": \"rgba(255, 180, 112)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 185, 87)\"},{\"offset\": 1,\"color\": \"rgba(255, 180, 112)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(255, 185, 87)\"}}]},\"data\": [57.82,30.32,4.63,3.74,1.52,1.34,0.36,0.19,0.05,0.01]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"材质统计管网情况\",\"options\": [{\"echartId\": \"echarts-17301804493295\",\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"10%\",\"containLabel\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"PE\",\"铸铁\",\"球墨铸铁\",\"球墨\",\"铸铁\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"#DDDDDD\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#333\"},\"axisTick\": {\"show\": false}},\"yAxis\": [{\"name\": \"单位:km\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": false},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"dashed\",\"width\": 0.5,\"color\": \"#E8E8E8\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333\"}},{\"name\": \"单位:%\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": false},\"splitLine\": {\"show\": false},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333\"}}],\"series\": [{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": -12,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(194, 225, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(46, 155, 255, 1)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(194, 225, 255, 1)\"},{\"offset\": 1,\"color\": \"rgba(46, 155, 255, 1)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 1)\"}}]},\"data\": [10,50,40,60,70]},{\"name\": \"\",\"yAxisIndex\": 0,\"type\": \"custom\",\"renderItemConfig\": {\"type\": \"group\",\"x\": -12,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(194, 225, 255, 0.25)\"}}]},\"data\": [100,100,100,100,100]},{\"type\": \"bar\",\"yAxisIndex\": 0,\"label\": {\"show\": true,\"color\": \"rgba(46, 155, 255, 1)\",\"offset\": [4,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"padding\": [6,6]}},\"formatter\": \"{bg| {c} }\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\": [10,50,40,60,70]},{\"type\": \"bar\",\"yAxisIndex\": 1,\"label\": {\"show\": true,\"color\": \"rgba(255, 119, 0, 1)\",\"offset\": [0,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"padding\": [6,6]}},\"formatter\": \"{bg|{c}%}\"},\"itemStyle\": {\"color\": \"transparent\"},\"tooltip\": {\"show\": false},\"data\": [11,15,24,16,17]},{\"name\": \"\",\"type\": \"custom\",\"yAxisIndex\": 1,\"renderItemConfig\": {\"type\": \"group\",\"x\": 18,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 229, 183, 1)\"},{\"offset\": 1,\"color\": \"rgba(255, 119, 0, 1)\"}],\"global\": false}}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 0,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgba(255, 229, 183, 1)\"},{\"offset\": 1,\"color\": \"rgba(255, 119, 0, 1)\"}],\"global\": false}}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(255, 229, 183, 1)\"}}]},\"data\": [11,15,24,16,17]},{\"name\": \"\",\"type\": \"custom\",\"yAxisIndex\": 1,\"renderItemConfig\": {\"type\": \"group\",\"x\": 18,\"children\": [{\"type\": \"CubeLeft\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(255, 229, 183, 0.4)\"}},{\"type\": \"CubeRight\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(255, 229, 183, 0.4)\"}},{\"type\": \"CubeTop\",\"shape\": {\"x\": 0,\"y\": 1,\"xAxisPoint\": [0,0]},\"style\": {\"fill\": \"rgba(255, 229, 183, 0.4)\"}}]},\"data\": [25,25,25,25,25]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("阜康市管网材质情况");
        chartResult.setText("阜康市区全部管网长度为563.28公里，占比最大的材质为PE管，占比43%。详情参见图表。");
        List<String> textData = new ArrayList<>();
        textData.add("供水管网:长度<strong>235.22</strong>km, 占比41.7%");
        textData.add("排水管网:长度<strong>174.84</strong>km, 占比31.0%");
        textData.add("绿化管网:长度<strong>153.22</strong>km, 占比27.3%");
        chartResult.setTextData(textData);
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("阜康市区全部管网长度为563.28公里，占比最大的材质为PE管，占比43%。详情参见图表。");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 按照材质统计管网情况
     * 按照材质统计管网长度
     * 目前管网都有什么材质的？
     * PE的供水/排水/绿化管网有多少？
     * @param material
     * @return
     */
    @Override
    public ResultForFrontVo pipeStatisticsByMaterial(String material) {
        return this.pipeStatisticsByMaterial(CommonConstant.CHANNEL_CODE_BIGSCREEN,material);
    }

    /**
     * 管网设施统计
     * 按设施类型统计设施数量
     * 目前有多少设施？
     * 都有什么类型的设施？
     * @return
     */
    @Override
    public ResultForFrontVo pipeDeviceStatistics(String channelCode) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"管网设施统计\",\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": [{\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(254, 253, 140)\"},{\"offset\": 1,\"color\": \"rgb(255, 207, 51)\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(224, 149, 216)\"},{\"offset\": 1,\"color\": \"rgb(197, 56, 233)\"}],\"global\": false}, {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(186, 248, 1581)\"},{\"offset\": 1,\"color\": \"rgb(149, 245, 136)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"80\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备数量\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"18181\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"top\": \"10%\",\"right\": \"5%\",\"icon\": \"circle\",\"itemGap\": 20,\"itemWidth\": 10,\"itemHeight\": 10,\"color\": \"#fff\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 10,\"formatter\": \"function (name,data) { let items = data.find((item) => item.name == name); return `{name|${name}}{value| ${items?.value || ''}} {unit|个} {line||}{percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"bottom\",\"rich\": {\"value\": {\"color\": \"#DDF6FD\",\"align\": \"left\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\",\"width\": 80},\"name\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\",\"width\": 100},\"unit\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 12,\"width\":30,\"fontFamily\": \"AlibabaRegular\"},\"line\": {\"fontSize\": 12,\"color\": \"#fff\",\"padding\": [0,10,0,10]},\"percent\": {\"color\": \"#DDF6FD\",\"width\": 50,\"align\": \"right\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\"}}},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.90%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 3590,\"percent\": \"19.75%\"},{\"name\": \"排水篦子\",\"value\": 1006,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.02%\"} ]},\"series\": [{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"50%\",\"70%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.90%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 3590,\"percent\": \"19.75%\"},{\"name\": \"排水篦子\",\"value\": 1006,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.02%\"}],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"42%\",\"80%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.90%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 3590,\"percent\": \"19.75%\"},{\"name\": \"排水篦子\",\"value\": 1006,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.02%\"}]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"管网设施统计\",\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": [{\"echartId\": \"echarts-17301848814672\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"100\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"总计\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"18181\",\"textAlign\": \"center\",\"font\": \" 24px \\\"Dinpro\\\", sans-serif\",\"fill\": \"#333\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"top\": \"10%\",\"right\": \"5%\",\"icon\": \"rect\",\"itemGap\": 10,\"itemWidth\": 13,\"itemHeight\": 5,\"color\": \"#333\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 8,\"formatter\": \"function (name,data) { let items = data.find((item) => item.name == name); return `{name|${name}}{value| ${items?.value || ''}} {unit|个} {line||} {percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"bottom\",\"rich\": {\"value\": {\"color\": \"#333\",\"align\": \"left\",\"fontSize\": 14,\"width\": 60,\"fontFamily\": \"AlibabaRegular\"},\"name\": {\"color\": \"#333\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\",\"width\": 80},\"unit\": {\"color\": \"#333\",\"fontSize\": 12,\"fontFamily\": \"AlibabaRegular\"},\"line\": {\"fontSize\": 10,\"color\": \"#333\",\"padding\": [0,0,0,10],\"width\": 10},\"percent\": {\"color\": \"#333\",\"width\": 50,\"align\": \"right\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\"}}},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.89%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 1903,\"percent\": \"19.74%\"},{\"name\": \"排水篦子\",\"value\": 1903,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.04%\"}]},\"series\": [{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"55%\",\"75%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.89%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 1903,\"percent\": \"19.74%\"},{\"name\": \"排水篦子\",\"value\": 1903,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.04%\"}],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"47%\",\"85%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"井\",\"value\": 6702,\"percent\": \"36.86%\"},{\"name\": \"阀门\",\"value\": 2890,\"percent\": \"15.89%\"},{\"name\": \"消防设施\",\"value\": 353,\"percent\": \"1.94%\"},{\"name\": \"管件\",\"value\": 1903,\"percent\": \"19.74%\"},{\"name\": \"排水篦子\",\"value\": 1903,\"percent\": \"5.53%\"},{\"name\": \"其他设施\",\"value\": 3640,\"percent\": \"20.04%\"}]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("阜康市管网设施统计");
        chartResult.setText("阜康市区共有6类设施类型，占比最多的是井，共6702座，占比37%；详情参见图表。");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("阜康市区共有6类设施类型，占比最多的是井，共6702座，占比37%；详情参见图表。");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 管网设施统计
     * 按设施类型统计设施数量
     * 目前有多少设施？
     * 都有什么类型的设施？
     * @return
     */
    @Override
    public ResultForFrontVo pipeDeviceStatistics() {
        return pipeDeviceStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 整体管网统计
     * @return
     */
    @Override
    public ResultForFrontVo pipeOverAllStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("管网数据总览");
        urlResult.setUrl("http://10.0.15.41:8000/gis/#/GWQuery");
        urlResult.setSpeechText("为您展示管网数据总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 整体管网统计
     * @return
     */
    @Override
    public ResultForFrontVo pipeOverAllStatistics() {
        return pipeOverAllStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
