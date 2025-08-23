package com.hanwei.process.business.businesscharge.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.businesscharge.service.IBusinessChargeService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.vo.*;
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
public class BusinessChargeServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IBusinessChargeService {


    /**
     * 年度新增用户统计
     * @param channelCode
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultForFrontVo getNewUserNumber(String channelCode, String startTime, String endTime) {
        System.out.println("getNewUserNumber-年度新增用户统计调用成功 参数为" + startTime + ","+endTime);
        log.info("getNewUserNumber-年度新增用户统计调用成功 参数为" + startTime + ","+endTime);
        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{'type': 'echarts','text': '','title': '2024年度新增用户统计','options': [{'graphic': [{'type': 'image','style': {'image': 'echarts/barbg.png','width': '98%','height': 100,'left': '4%'},'bottom': 0,'left': 'center','z': -1}],'grid': {'top': '15%','left': '5%','right': '5%','bottom': '15%','containLabel': true},'xAxis': {'type': 'category','data': ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],'axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(255, 255, 255,0.08)'}},'splitLine': {'show': false},'axisLabel': {'color': '#fff'},'axisTick': {'show': false}},'yAxis': {'name': '单位:户','nameTextStyle': {'color': '#fff','nameLocation': 'start'},'type': 'value','axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(255, 255, 255,0.08)'}},'splitLine': {'show': true,'lineStyle': {'type': 'solid','width': 0.5,'color': 'rgb(255, 255, 255,0.4)'}},'axisTick': {'show': false},'axisLabel': {'fontSize': 14,'color': '#FFF'}},'series': [{'name': '','yAxisIndex': 0,'type': 'custom','renderItemConfig': {'type': 'group','x': 0,'children': [{'type': 'CubeLeft','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeRight','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeTop','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': 'rgb(10, 223, 239)'}}]},'data': [525,183,801,693,149,146,160,115,124,137,0,0]},{'type': 'bar','yAxisIndex': 0,'label': {'show': true,'color': '#ffffff','offset': [0,-4],'position': 'top','fontSize': '14','rich': {'bg': {'backgroundColor': {'image': 'echarts/bluebg.png'},'padding': [6,6]}},'formatter': '{bg| {c} }'},'itemStyle': {'color': 'transparent'},'tooltip': {'show': false},'data': [525,183,801,693,149,146,160,115,124,137,0,0]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{'type': 'echarts','text': '','title': '新增用户统计','options': [{'echartId': 'echarts-173018044932941','graphic': [{'type': 'image','style': {'width': '98%','height': 100,'left': '4%'},'bottom': 0,'left': 'center','z': -1}],'grid': {'top': '15%','left': '5%','right': '5%','bottom': '15%','containLabel': true},'xAxis': {'type': 'category','data': ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],'axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(200,200,200,.5)'}},'splitLine': {'show': false},'axisLabel': {'color': '#333'},'axisTick': {'show': false}},'yAxis': {'name': '单位:户','nameTextStyle': {'color': '#333','nameLocation': 'start'},'type': 'value','axisLine': {'show': true,'lineStyle': {'width': 1,'color': 'rgb(200,200,200,.5)'}},'splitLine': {'show': true,'lineStyle': {'width': 0.5,'color': 'rgb(200,200,200,.5)','type': 'dashed'}},'axisTick': {'show': false},'axisLabel': {'fontSize': 14,'color': '#333'}},'series': [{'name': '','yAxisIndex': 0,'type': 'custom','renderItemConfig': {'type': 'group','x': 0,'children': [{'type': 'CubeLeft','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeRight','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': {'type': 'linear','x': 0,'y': 0,'x2': 0,'y2': 1,'colorStops': [{'offset': 0,'color': 'rgb(10, 223, 239)'},{'offset': 1,'color': 'rgb(7, 102, 230)'}],'global': false}}},{'type': 'CubeTop','shape': {'x': 0,'y': 1,'xAxisPoint': [0,0]},'style': {'fill': 'rgb(10, 223, 239)'}}]},'data': [525,183,801,693,149,146,160,115,124,137,0,0]},{'type': 'bar','yAxisIndex': 0,'label': {'show': true,'color': '#333','offset': [0,-10],'position': 'top','fontSize': '14'},'itemStyle': {'color': 'transparent'},'tooltip': {'show': false},'data': [525,183,801,693,149,146,160,115,124,137,0,0]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("2024年度新增用户统计");
        chartResult.setText("今年共增长用户3032户，其中3月份增长最多，共增长801户，具体详情参见图表");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("今年共增长用户3032户，其中3月份增长最多，共增长801户，具体详情参见图表");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    @Override
    public ResultForFrontVo getNewUserNumber(String startTime, String endTime) {
        //TODO 模型确认渠道问题
        return this.getNewUserNumber(CommonConstant.CHANNEL_CODE_BIGSCREEN,startTime,endTime);
    }

    /**
     * 支付途径统计
     * @param channelCode
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ResultForFrontVo paymentChannelStatistics(String channelCode,String startTime, String endTime) {
        System.out.println("paymentChannelStatistics-支付途径统计调用成功 参数为" + startTime + ","+endTime);
        log.info("paymentChannelStatistics-支付途径统计调用成功 参数为" + startTime + ","+endTime);

        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"支付途径统计\",\"speechText\": \"你好，你可以向我提问任何问题\",\"options\": [{\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"80\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"支付途径\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"249319\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 笔 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\",\"formatter\": \"{b} <br/> 金额: {c} 万元 ({d}%)\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"top\": \"center\",\"right\": \"5%\",\"icon\": \"circle\",\"itemGap\": 20,\"itemWidth\": 10,\"itemHeight\": 10,\"color\": \"#fff\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 10,\"formatter\": \"function (name, data) { let items = data.find((item) => item.name == name); return `{name|${name}}{count| ${(items?.count/10000).toFixed(2) || ''}} {unit|万笔}{line||} {value| ${items?.value || ''}} {unit|万元} {line||}{percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"bottom\",\"rich\": {\"value\": {\"color\": \"#DDF6FD\",\"align\": \"left\",\"fontSize\": 16,\"width\": 60,\"fontFamily\": \"AlibabaRegular\"},\"count\": {\"color\": \"#fff\",\"align\": \"left\",\"fontSize\": 14,\"fontFamily\": \"AlibabaRegular\",\"width\": 40},\"name\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\",\"width\": 110},\"unit\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 12,\"fontFamily\": \"AlibabaRegular\"},\"line\": {\"fontSize\": 12,\"color\": \"#fff\",\"padding\": [0,0,0,10]},\"percent\": {\"color\": \"#DDF6FD\",\"width\": 50,\"align\": \"right\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\"}}},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}]},\"series\": [{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"55%\",\"75%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"47%\",\"85%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"支付途径统计\",\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": [{\"echartId\": \"echarts-17301848814672\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"90\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"设备数量\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"9834\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"#333\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\",\"formatter\": \"{b} <br/> 金额: {c} 万元 ({d}%)\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"right\": \"3%\",\"icon\": \"circle\",\"itemGap\": 10,\"itemWidth\": 10,\"itemHeight\": 10,\"color\": \"#333\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 10,\"formatter\": \"function (name, data) { let items = data.find((item) => item.name == name); return `{name|${name}}{count| ${(items?.count/10000).toFixed(2) || ''}} {unit|万笔}{line||} {value| ${items?.value || ''}} {unit|万元} {line||}{percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"middle\",\"rich\": {\"value\": {\"color\": \"#333\",\"align\": \"left\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 65},\"count\": {\"color\": \"#333\",\"align\": \"left\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 40},\"name\": {\"color\": \"rgba(0,0,0,0.8)\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 90},\"unit\": {\"color\": \"rgba(0,0,0,0.8)\",\"fontSize\": 12,\"fontFamily\": \"Microsoft YaHei\"},\"line\": {\"fontSize\": 12,\"color\": \"#333\",\"padding\": [0,0,0,3]},\"percent\": {\"color\": \"#333\",\"width\": 50,\"align\": \"right\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\"}}},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}]},\"series\": [{\"name\": \"支付金额\",\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"55%\",\"75%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"name\": \"支付金额\",\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"47%\",\"85%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"柜台\",\"value\": 510.77,\"count\": 20824,\"percent\": \"11.6%\"},{\"name\": \"银行对公转账\",\"value\": 2634.62,\"count\": 5802,\"percent\": \"60.1%\"},{\"name\": \"微信公众号\",\"value\": 1242.36,\"count\": 222693,\"percent\": \"28.3%\"}]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("2024年1月至10月各支付渠道收费统计");
        chartResult.setText("2024年1月至10月总计缴费249319笔，收入4387.76万元，总共有6种收费渠道；其中缴费笔数最高的途径为微信公众号，总计缴费222693笔，占比89%；具体详情参见图表");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("2024年1月至10月总计缴费249319笔，收入4387.76万元，总共有6种收费渠道；其中缴费笔数最高的途径为微信公众号，总计缴费222693笔，占比89%；具体详情参见图表");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    @Override
    public ResultForFrontVo paymentChannelStatistics(String startTime, String endTime) {
        return this.paymentChannelStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN,startTime,endTime);
    }



    /**
     * 客户档案情况汇总统计 用户档案 现在有多少用水用户
     */
    @Override
    public ResultForFrontVo customerInfoStatistic(String channelCode, String queryType) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"客户档案情况汇总统计\",\"title\": \"客户档案情况汇总统计\",\"speechText\": \"你好，你可以向我提问任何问题\",\"options\": [{\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"80\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"用户数\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"80163\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\",\"fill\": \"url(#textGradient)\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 户 )\",\"font\": \" 14px \\\"AlibabaRegular\\\", sans-serif\",\"fill\": \"rgb(255,255,255,0.8)\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"top\": \"10%\",\"right\": \"5%\",\"icon\": \"circle\",\"itemGap\": 20,\"itemWidth\": 10,\"itemHeight\": 10,\"color\": \"#fff\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 10,\"formatter\": \"function (name,data) { let items = data.find((item) => item.name == name); return `{name|${name}}{value| ${items?.value || ''}} {unit|户} {line||}{percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"bottom\",\"rich\": {\"value\": {\"color\": \"#DDF6FD\",\"align\": \"left\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\",\"width\": 80},\"name\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\",\"width\": 140},\"unit\": {\"color\": \"rgba(255,255,255,0.8)\",\"fontSize\": 12,\"fontFamily\": \"AlibabaRegular\"},\"line\": {\"fontSize\": 12,\"color\": \"#fff\",\"padding\": [0,0,0,10]},\"percent\": {\"color\": \"#DDF6FD\",\"width\": 50,\"align\": \"right\",\"fontSize\": 16,\"fontFamily\": \"AlibabaRegular\"}}},\"data\": [{ \"name\": \"居民生活用水\", \"value\": 71823, \"percent\": \"89.7%\" },{ \"name\": \"行政事业用水\", \"value\": 1552, \"percent\": \"2.0%\" },{ \"name\": \"工业用水\", \"value\": 6196, \"percent\": \"7.7%\" },{ \"name\": \"经营服务用水\", \"value\": 402, \"percent\": \"0.5%\" },{ \"name\": \"热衷行业用水\", \"value\": 190, \"percent\": \"0.2%\" }]},\"series\": [{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"55%\",\"75%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{ \"name\": \"居民生活用水\", \"value\": 71823, \"percent\": \"89.7%\" },{ \"name\": \"行政事业用水\", \"value\": 1552, \"percent\": \"2.0%\" },{ \"name\": \"工业用水\", \"value\": 6196, \"percent\": \"7.7%\" },{ \"name\": \"经营服务用水\", \"value\": 402, \"percent\": \"0.5%\" },{ \"name\": \"热衷行业用水\", \"value\": 190, \"percent\": \"0.2%\" }],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"47%\",\"85%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{ \"name\": \"居民生活用水\", \"value\": 71823, \"percent\": \"89.7%\" },{ \"name\": \"行政事业用水\", \"value\": 1552, \"percent\": \"2.0%\" },{ \"name\": \"工业用水\", \"value\": 6196, \"percent\": \"7.7%\" },{ \"name\": \"经营服务用水\", \"value\": 402, \"percent\": \"0.5%\" },{ \"name\": \"热衷行业用水\", \"value\": 190, \"percent\": \"0.2%\" }]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"客户档案情况汇总统计\",\"speechText\": \"共有80163户\",\"options\": [{\"echartId\": \"echarts-17301848814672\",\"color\": [{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(251, 132, 88)\"},{\"offset\": 1,\"color\": \"#F2CAA4\"}],\"global\": false},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(53, 251, 152)\"},{\"offset\": 1,\"color\": \"rgb(127, 235, 186 )\"}]},{\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}],\"graphic\": [{\"type\": \"group\",\"top\": \"middle\",\"left\": \"80\",\"id\": \"data\",\"children\": [{\"type\": \"text\",\"top\": 20,\"style\": {\"text\": \"总用户数\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}},{\"type\": \"text\",\"top\": 40,\"style\": {\"text\": \"80163\",\"fill\": \"#333\",\"textAlign\": \"center\",\"font\": \" 36px \\\"Dinpro\\\", sans-serif\"}},{\"type\": \"text\",\"top\": 80,\"style\": {\"text\": \"( 个 )\",\"font\": \" 14px \\\"Microsoft YaHei\\\", sans-serif\",\"fill\": \"#333\",\"textAlign\": \"center\",\"zIndex\": 9999}}]}],\"tooltip\": {\"trigger\": \"item\",\"formatter\": \"{a} : {b} ({d}%)\"},\"legend\": {\"show\": true,\"orient\": \"vertical\",\"right\": \"3%\",\"icon\": \"circle\",\"itemGap\": 10,\"itemWidth\": 10,\"itemHeight\": 10,\"color\": \"#333\",\"type\": \"scroll\",\"y\": \"center\",\"scrollDataIndex\": 0,\"pageIconColor\": \"#337ab7\",\"pageTextStyle\": {\"color\": \"#999\"},\"pageIconSize\": 10,\"formatter\": \"function (name, data) { let items = data.find((item) => item.name == name); return `{name|${name}}{value| ${items?.value || ''}}{unit|户}{line||}{percent|${items?.percent + '' || ''}}`; }\",\"itemStyle\": {\"borderWidth\": 1},\"textStyle\": {\"overflow\": \"break\",\"verticalAlign\": \"middle\",\"rich\": {\"value\": {\"color\": \"#333\",\"align\": \"left\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 65},\"count\": {\"color\": \"#333\",\"align\": \"left\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 40},\"name\": {\"color\": \"rgba(0,0,0,0.8)\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\",\"width\": 90},\"unit\": {\"color\": \"rgba(0,0,0,0.8)\",\"fontSize\": 12,\"fontFamily\": \"Microsoft YaHei\"},\"line\": {\"fontSize\": 12,\"color\": \"#333\",\"padding\": [0,0,0,3]},\"percent\": {\"color\": \"#333\",\"width\": 50,\"align\": \"right\",\"fontSize\": 14,\"fontFamily\": \"Microsoft YaHei\"}}},\"data\": [{\"name\": \"居民生活用水\",\"value\": 71823,\"percent\": \"89.7%\"},{\"name\": \"行政事业用水\",\"value\": 1552,\"percent\": \"2.0%\"},{\"name\": \"工业用水\",\"value\": 6196,\"percent\": \"7.7%\"},{\"name\": \"经营服务用水\",\"value\": 402,\"percent\": \"0.5%\"},{\"name\": \"热衷行业用水\",\"value\": 190,\"percent\": \"0.2%\"}]},\"series\": [{\"name\": \"用户数量\",\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"55%\",\"75%\"],\"padAngle\": 5,\"itemStyle\": {\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"居民生活用水\",\"value\": 71823,\"percent\": \"89.7%\"},{\"name\": \"行政事业用水\",\"value\": 1552,\"percent\": \"2.0%\"},{\"name\": \"工业用水\",\"value\": 6196,\"percent\": \"7.7%\"},{\"name\": \"经营服务用水\",\"value\": 402,\"percent\": \"0.5%\"},{\"name\": \"热衷行业用水\",\"value\": 190,\"percent\": \"0.2%\"}],\"labelLine\": {\"show\": false},\"label\": {\"show\": false}},{\"name\": \"用户数量\",\"type\": \"pie\",\"center\": [130,\"50%\"],\"radius\": [\"47%\",\"85%\"],\"label\": {\"show\": false},\"padAngle\": 5,\"itemStyle\": {\"opacity\": 0.1,\"borderRadius\": 0,\"borderWidth\": 10},\"data\": [{\"name\": \"居民生活用水\",\"value\": 71823,\"percent\": \"89.7%\"},{\"name\": \"行政事业用水\",\"value\": 1552,\"percent\": \"2.0%\"},{\"name\": \"工业用水\",\"value\": 6196,\"percent\": \"7.7%\"},{\"name\": \"经营服务用水\",\"value\": 402,\"percent\": \"0.5%\"},{\"name\": \"热衷行业用水\",\"value\": 190,\"percent\": \"0.2%\"}]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("客户档案情况汇总统计");
        chartResult.setText("当前阜康市用户共80163户，共有5种用户类型，具体详情参见图表");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setSpeechText("当前阜康市用户共80163户，共有5种用户类型，具体详情参见图表");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 客户档案情况汇总统计 用户档案 现在有多少用水用户
     */
    @Override
    public ResultForFrontVo customerInfoStatistic(String queryType) {
        return this.customerInfoStatistic(CommonConstant.CHANNEL_CODE_BIGSCREEN,queryType);
    }

    /**
     * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
     */
    @Override
    public ResultForFrontVo getPaymentInfoByCustomerCode(String channelCode, String startTime, String endTime) {
        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"本年度应收、实收、回收率统计\",\"options\": [{\"graphic\": [{\"type\": \"image\",\"style\": {\"image\": \"echarts/barbg.png\",\"width\": \"98%\",\"height\": 100,\"left\": \"4%\"},\"bottom\": 0,\"left\": \"center\",\"z\": -1}],\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"15%\",\"containLabel\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"1月\",\"2月\",\"3月\",\"4月\",\"5月\",\"6月\",\"7月\",\"8月\",\"9月\",\"10月\",\"11月\",\"12月\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#fff\"},\"axisTick\": {\"show\": false}},\"yAxis\": [{\"name\": \"水费:万元\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"solid\",\"width\": 0.5,\"color\": \"rgb(255, 255, 255,0.4)\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#FFF\"}},{\"name\": \"单位:%\",\"nameTextStyle\": {\"color\": \"#fff\",\"nameLocation\": \"start\"},\"min\": 0,\"max\": 100,\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#FFF\"}}],\"legend\": {\"itemWidth\": 17,\"itemHeight\": 10,\"textStyle\": {\"color\": \"#fff\"}},\"series\": [{\"name\": \"应收\",\"yAxisIndex\": 0,\"type\": \"bar\",\"barWidth\": 12,\"itemStyle\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(255, 202, 0)\"},{\"offset\": 1,\"color\": \"rgb(255, 110, 0)\"}],\"global\": false}},\"data\": [189.43,102.54,469.37,270.94,385.46,469.47,717.45,639.09,1123.45,820.22,0,0]},{\"type\": \"pictorialBar\",\"symbol\": \"rect\",\"symbolSize\": [12,3],\"symbolOffset\": [-7,0],\"symbolPosition\": \"end\",\"yAxisIndex\": 0,\"data\": [189.43,102.54,469.37,270.94,385.46,469.47,717.45,639.09,1123.45,820.22,0,0],\"itemStyle\": {\"color\": \"#FFFFFF\"},\"z\": 99},{\"name\": \"实收\",\"yAxisIndex\": 0,\"type\": \"bar\",\"barWidth\": 12,\"itemStyle\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"rgb(18, 217, 254)\"},{\"offset\": 1,\"color\": \"rgb(7, 143, 240)\"}],\"global\": false}},\"data\": [152.59,81.66,420.74,245.21,347.54,466.02,637.73,579.18,1101.84,355.24,0,0]},{\"type\": \"pictorialBar\",\"symbol\": \"rect\",\"symbolSize\": [12,3],\"symbolOffset\": [7,0],\"symbolPosition\": \"end\",\"yAxisIndex\": 0,\"data\": [152.59,81.66,420.74,245.21,347.54,466.02,637.73,579.18,1101.84,355.24,0,0],\"itemStyle\": {\"color\": \"#FFFFFF\"},\"z\": 99},{\"type\": \"line\",\"name\": \"回收率\",\"yAxisIndex\": 1,\"symbolSize\": 10,\"label\": {\"show\": true,\"color\": \"#ffffff\",\"offset\": [0,-4],\"position\": \"top\",\"fontSize\": \"14\",\"rich\": {\"bg\": {\"color\": \"rgb(0, 224, 26)\",\"padding\": [6,6]}},\"formatter\": \"{bg| {c}% }\"},\"lineStyle\": {\"width\": 4,\"type\": \"solid\"},\"itemStyle\": {\"borderWidth\": 8,\"borderColor\": \"rgb(0, 224, 26)\",\"color\": \"rgb(0, 224, 26)\"},\"tooltip\": {\"show\": false},\"data\": [80.55,79.64,89.64,90.50,90.16,99.27,88.89,90.63,98.08,43.31,0,0]}]}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"echarts\",\"text\": \"\",\"title\": \"本年度应收、实收、回收率统计\",\"speechText\": \"\",\"options\": [{\"echartId\": \"echarts-17301804493294111\",\"grid\": {\"top\": \"15%\",\"left\": \"5%\",\"right\": \"5%\",\"bottom\": \"15%\",\"containLabel\": true},\"tooltip\": {\"show\": true},\"xAxis\": {\"type\": \"category\",\"data\": [\"1月\",\"2月\",\"3月\",\"4月\",\"5月\",\"6月\",\"7月\",\"8月\",\"9月\",\"10月\",\"11月\",\"12月\"],\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"#DDDDDD\"}},\"splitLine\": {\"show\": false},\"axisLabel\": {\"color\": \"#333\"},\"axisTick\": {\"show\": true}},\"yAxis\": [{\"name\": \"水费:万元\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": true,\"lineStyle\": {\"type\": \"dashed\",\"width\": 1,\"color\": \"#E8E8E8\"}},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333\"}},{\"name\": \"单位:%\",\"nameTextStyle\": {\"color\": \"#333\",\"nameLocation\": \"start\"},\"type\": \"value\",\"axisLine\": {\"show\": true,\"lineStyle\": {\"width\": 1,\"color\": \"rgb(255, 255, 255,0.08)\"}},\"splitLine\": {\"show\": false},\"axisTick\": {\"show\": false},\"axisLabel\": {\"fontSize\": 14,\"color\": \"#333\"}}],\"legend\": {\"itemWidth\": 17,\"itemHeight\": 10,\"top\": \"5%\",\"textStyle\": {\"color\": \"#333\"}},\"series\": [{\"name\": \"应收\",\"yAxisIndex\": 0,\"type\": \"bar\",\"barWidth\": 12,\"itemStyle\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"#FFDAA6\"},{\"offset\": 1,\"color\": \"#FF8110\"}],\"global\": false}},\"data\": [8,30,35,55,65,70,85,95,95,100,110]},{\"type\": \"pictorialBar\",\"symbol\": \"rect\",\"symbolSize\": [12,3],\"symbolOffset\": [-7,0],\"symbolPosition\": \"end\",\"yAxisIndex\": 0,\"data\": [8,30,35,55,65,70,85,95,95,100,110],\"itemStyle\": {\"color\": \"#FF8211\"},\"z\": 99},{\"name\": \"实收\",\"yAxisIndex\": 0,\"type\": \"bar\",\"barWidth\": 12,\"itemStyle\": {\"color\": {\"type\": \"linear\",\"x\": 0,\"y\": 0,\"x2\": 1,\"y2\": 1,\"colorStops\": [{\"offset\": 0,\"color\": \"#BDDFFF\"},{\"offset\": 1,\"color\": \"#0085FF\"}],\"global\": false}},\"data\": [10,50,40,60,70,80,90,100,110,120,130]},{\"type\": \"pictorialBar\",\"symbol\": \"rect\",\"symbolSize\": [12,3],\"symbolOffset\": [7,0],\"symbolPosition\": \"end\",\"yAxisIndex\": 0,\"data\": [10,50,40,60,70,80,90,100,110,120,130],\"itemStyle\": {\"color\": \"#0286FF\"},\"z\": 99},{\"type\": \"line\",\"name\": \"回收率\",\"yAxisIndex\": 1,\"symbolSize\": 10,\"label\": {\"show\": true,\"color\": \"#0FCE3B\",\"position\": \"top\",\"fontSize\": \"14\",\"formatter\": \"{c}%\"},\"lineStyle\": {\"width\": 2,\"shadowBlur\": 10,\"shadowColor\": \"rgba(32, 209, 74, 0.5)\",\"shadowOffsetX\": 5,\"shadowOffsetY\": 5,\"type\": \"solid\"},\"itemStyle\": {\"borderWidth\": 4,\"borderColor\": \"#0FCE3B\",\"color\": \"#0FCE3B\"},\"tooltip\": {\"show\": false},\"data\": [10,50,40,60,70,80,90,50,60,40,30]}]}]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_CHART);
        ChartResult chartResult = new ChartResult();
        chartResult.setTitle("本年度应收、实收、回收率统计");
        chartResult.setOptions(JSON.parseObject(option));
        chartResult.setText("2024年1月至10月共收入4387.76 万元，应收水费5187.42万元，回收率为85%，具体详情参见图表");
        chartResult.setSpeechText("2024年1月至10月共收入4387.76 万元，应收水费5187.42万元，回收率为85%，具体详情参见图表");
        resultForFrontVo.setChartResult(chartResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
     */
    @Override
    public ResultForFrontVo getPaymentInfoByCustomerCode(String startTime, String endTime) {
        return getPaymentInfoByCustomerCode(CommonConstant.CHANNEL_CODE_BIGSCREEN,startTime,endTime);
    }

    /**
     * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
     * 查询用户当前账户情况
     */
    @Override
    public ResultForFrontVo chargeStatistics(String channelCode, String customerCode) {


        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"是否有欠费\",\"speechText\": \"你好，你可以向我提问任何问题\",\"options\": {\"propList\": [{\"label\": \"账单年月\",\"prop\": \"date\"},{\"label\": \"用水量\",\"prop\": \"usage\"},{\"label\": \"用水金额\",\"prop\": \"waterCost\"},{\"label\": \"缴费情况\",\"prop\": \"state\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"usage\": 36,\"waterCost\": 122.4,\"state\": \"已缴费\"},{\"date\": \"2024-4\",\"usage\": 13,\"waterCost\": 100,\"state\": \"已缴费\"},{\"date\": \"2024-5\",\"usage\": 11,\"waterCost\": 37.4,\"state\": \"已缴费\"},{\"date\": \"2024-6\",\"usage\": 12,\"waterCost\": 40.8,\"state\": \"已缴费\"},{\"date\": \"2024-7\",\"usage\": 13,\"waterCost\": 44.2,\"state\": \"已缴费\"},{\"date\": \"2024-8\",\"usage\": 7,\"waterCost\": 23.8,\"state\": \"已缴费\"},{\"date\": \"2024-9\",\"usage\": 13,\"waterCost\": 44.2,\"state\": \"已缴费\"},{\"date\": \"2024-10\",\"usage\": 14,\"waterCost\": 47.6,\"state\": \"已缴费\"}]},\"ulDataList\": [{\"name\": \"用户号\",\"value\": \" 029-009020601\"},{\"name\": \"用户名\",\"value\": \" 杜凤琴\"},{\"name\": \"用户地址\",\"value\": \"阳光花园小区9-2-601\"},{\"name\": \"用户余额\",\"value\": \"34.40元\"},{\"name\": \"总计欠费金额\",\"value\": \"0.00元\"}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"是否有欠费\",\"ulDataList\": [\"用户号:<strong>029-009020601</strong>\",\"用户名:<strong>杜凤琴</strong>\",\"用户地址:<strong>阳光花园小区9-2-601</strong>\",\"用户余额:<strong>34.40</strong>\",\"总计欠费金额:<strong>0.00</strong>元\"],\"speechText\": \"\",\"options\": {\"propList\": [{\"label\": \"账单年月\",\"prop\": \"date\"},{\"label\": \"用水量\",\"prop\": \"usage\"},{\"label\": \"用水金额\",\"prop\": \"waterCost\"},{\"label\": \"缴费情况\",\"prop\": \"state\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"usage\": 36,\"waterCost\": 122.4,\"state\": \"已缴费\"},{\"date\": \"2024-4\",\"usage\": 13,\"waterCost\": 100,\"state\": \"已缴费\"},{\"date\": \"2024-5\",\"usage\": 11,\"waterCost\": 37.4,\"state\": \"已缴费\"},{\"date\": \"2024-6\",\"usage\": 12,\"waterCost\": 40.8,\"state\": \"已缴费\"},{\"date\": \"2024-7\",\"usage\": 13,\"waterCost\": 44.2,\"state\": \"已缴费\"},{\"date\": \"2024-8\",\"usage\": 7,\"waterCost\": 23.8,\"state\": \"已缴费\"},{\"date\": \"2024-9\",\"usage\": 13,\"waterCost\": 44.2,\"state\": \"已缴费\"},{\"date\": \"2024-10\",\"usage\": 14,\"waterCost\": 47.6,\"state\": \"已缴费\"}]}}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TABLE);

        TableResult tableResult = new TableResult();

        tableResult.setTitle("用户当前账户情况");
        tableResult.setText("用户029-009020601，姓名：杜凤琴，当前账户余额34.4元，欠费金额0元，用水明细参见图表。");
        List<String> textData = new ArrayList<>();
        textData.add("用户号:<strong>029-009020601</strong>");
        textData.add("用户名:<strong>杜凤琴</strong>");
        textData.add("用户地址:<strong>阳光花园小区9-2-601</strong>");
        textData.add("用户余额:<strong>34.40</strong>");
        textData.add("总计欠费金额:<strong>0.00</strong>元");
        tableResult.setTextData(textData);
        tableResult.setOptions(JSON.parseObject(option));
        tableResult.setSpeechText("用户029-009020601，姓名：杜凤琴，当前账户余额34.4元，欠费金额0元，用水明细参见图表。");
        resultForFrontVo.setTableResult(tableResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
     * 查询用户当前账户情况
     */
    @Override
    public ResultForFrontVo chargeStatistics(String customerCode) {
        return chargeStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN,customerCode);
    }

    /**
     * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
     */
    @Override
    public ResultForFrontVo getPaymentInfoByMonth(String channelCode, String customerCode, String queryDate) {

        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);

        TextResult textResult = new TextResult();
        textResult.setText("用户029-009020601，姓名：杜凤琴，2024年10月累计用水量14方，共计47.6元，本月抄码131，上月抄码117");
        textResult.setSpeechText("用户029-009020601，姓名：杜凤琴，2024年10月累计用水量14方，共计47.6元，本月抄码131，上月抄码117");
        resultForFrontVo.setTextResult(textResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
     */
    @Override
    public ResultForFrontVo getPaymentInfoByMonth(String customerCode, String queryDate) {
        return getPaymentInfoByMonth(CommonConstant.CHANNEL_CODE_BIGSCREEN,customerCode,queryDate);
    }

    /**
     * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
     */
    @Override
    public ResultForFrontVo getWaterUseHistoryByDate(String channelCode, String customerCode, String startTime, String endTime) {
        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"029-009020601用户于2024年3月--2024年10月共计用水60吨，用水金额为264元，详情如下:\",\"title\": \"用户用水情况\",\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": {\"propList\": [{\"label\": \"账单年月\",\"prop\": \"date\"},{\"label\": \"用水量\",\"prop\": \"usage\"},{\"label\": \"用水金额\",\"prop\": \"waterCost\"},{\"label\": \"缴费金额\",\"prop\": \"payment\"},{\"label\": \"账户余额\",\"prop\": \"balance\"},{\"label\": \"上月抄码\",\"prop\": \"lastMonthCode\"},{\"label\": \"本月抄码\",\"prop\": \"thisMonthCode\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"usage\": 36,\"waterCost\": 122.4,\"payment\": 66.6,\"balance\": 0,\"lastMonthCode\": 12,\"thisMonthCode\": 48},{\"date\": \"2024-4\",\"usage\": 13,\"waterCost\": 100,\"payment\": 55.8,\"balance\": 48,\"lastMonthCode\": 61,\"thisMonthCode\": 72},{\"date\": \"2024-5\",\"usage\": 11,\"waterCost\": 37.4,\"payment\": 37.4,\"balance\": 18.4,\"lastMonthCode\": 61,\"thisMonthCode\": 72},{\"date\": \"2024-6\",\"usage\": 12,\"waterCost\": 40.8,\"payment\": 22.4,\"balance\": 0,\"lastMonthCode\": 72,\"thisMonthCode\": 84},{\"date\": \"2024-7\",\"usage\": 13,\"waterCost\": 44.2,\"payment\": 44.2,\"balance\": 0,\"lastMonthCode\": 84,\"thisMonthCode\": 97},{\"date\": \"2024-8\",\"usage\": 7,\"waterCost\": 23.8,\"payment\": 50,\"balance\": 26.2,\"lastMonthCode\": 97,\"thisMonthCode\": 104},{\"date\": \"2024-9\",\"usage\": 13,\"waterCost\": 44.2,\"payment\": 50,\"balance\": 32,\"lastMonthCode\": 104,\"thisMonthCode\": 117},{\"date\": \"2024-10\",\"usage\": 14,\"waterCost\": 47.6,\"payment\": 50,\"balance\": 34.4,\"lastMonthCode\": 117,\"thisMonthCode\": 131}]},\"ulDataList\": [{\"name\": \"用户号\",\"value\": \" 029-009020601\"},{\"name\": \"用户名\",\"value\": \" 杜凤琴\"},{\"name\": \"用户地址\",\"value\": \"阳光花园小区9-2-601\"},{\"name\": \"用户余额\",\"value\": \"34.40元\"},{\"name\": \"总计欠费金额\",\"value\": \"0.00元\"}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"029-009020601用户于2024年3月--2024年10月共计用水60吨，用水金额为264元，详情如下:\",\"title\": \"用户用水情况\",\"speechText\": \"我的历史用水情况\",\"options\": {\"propList\": [{\"label\": \"账单年月\",\"prop\": \"date\"},{\"label\": \"用水量\",\"prop\": \"usage\"},{\"label\": \"用水金额\",\"prop\": \"waterCost\"},{\"label\": \"缴费金额\",\"prop\": \"payment\"},{\"label\": \"账户余额\",\"prop\": \"balance\"},{\"label\": \"上月抄码\",\"prop\": \"lastMonthCode\"},{\"label\": \"本月抄码\",\"prop\": \"thisMonthCode\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"usage\": 36,\"waterCost\": 122.4,\"payment\": 66.6,\"balance\": 0,\"lastMonthCode\": 12,\"thisMonthCode\": 48},{\"date\": \"2024-4\",\"usage\": 13,\"waterCost\": 100,\"payment\": 55.8,\"balance\": 48,\"lastMonthCode\": 61,\"thisMonthCode\": 72},{\"date\": \"2024-5\",\"usage\": 11,\"waterCost\": 37.4,\"payment\": 37.4,\"balance\": 18.4,\"lastMonthCode\": 61,\"thisMonthCode\": 72},{\"date\": \"2024-6\",\"usage\": 12,\"waterCost\": 40.8,\"payment\": 22.4,\"balance\": 0,\"lastMonthCode\": 72,\"thisMonthCode\": 84},{\"date\": \"2024-7\",\"usage\": 13,\"waterCost\": 44.2,\"payment\": 44.2,\"balance\": 0,\"lastMonthCode\": 84,\"thisMonthCode\": 97},{\"date\": \"2024-8\",\"usage\": 7,\"waterCost\": 23.8,\"payment\": 50,\"balance\": 26.2,\"lastMonthCode\": 97,\"thisMonthCode\": 104},{\"date\": \"2024-9\",\"usage\": 13,\"waterCost\": 44.2,\"payment\": 50,\"balance\": 32,\"lastMonthCode\": 104,\"thisMonthCode\": 117},{\"date\": \"2024-10\",\"usage\": 14,\"waterCost\": 47.6,\"payment\": 50,\"balance\": 34.4,\"lastMonthCode\": 117,\"thisMonthCode\": 131}]},\"ulDataList\": [\"用户号:<strong>029-009020601</strong>\",\"用户名:<strong>杜凤琴</strong>\",\"用户地址:<strong>阳光花园小区9-2-601</strong>\",\"用户余额:<strong>34.40</strong>\",\"总计欠费金额:<strong>0.00</strong>元\"]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TABLE);

        TableResult tableResult = new TableResult();
        tableResult.setTitle("2024年1月至6月累计用水情况统计");
        tableResult.setText("用户029-009020601，姓名：杜凤琴，2024年1月至2024年6月，累计用水量72方，共计244.8元");
        List<String> textData = new ArrayList<>();
        textData.add("用户号:<strong>029-009020601</strong>");
        textData.add("用户名:<strong>杜凤琴</strong>");
        textData.add("用户地址:<strong>阳光花园小区9-2-601</strong>");
        textData.add("用户余额:<strong>34.40</strong>");
        textData.add("总计欠费金额:<strong>0.00</strong>元");
        tableResult.setTextData(textData);
        tableResult.setOptions(JSON.parseObject(option));
        tableResult.setSpeechText("用户029-009020601，姓名：杜凤琴，2024年1月至2024年6月，累计用水量72方，共计244.8元");
        resultForFrontVo.setTableResult(tableResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
     */
    @Override
    public ResultForFrontVo getWaterUseHistoryByDate(String customerCode, String startTime, String endTime) {
        return getWaterUseHistoryByDate(CommonConstant.CHANNEL_CODE_BIGSCREEN,customerCode,startTime,endTime);
    }

    /**
     * 查询某户历史缴费情况 缴费情况  历史缴费
     */
    @Override
    public ResultForFrontVo getPaymentHistoryByDate(String channelCode, String customerCode, String startTime, String endTime) {
        if(StrUtil.isEmpty(startTime)) {
            startTime = "2024-01-01 00:00:00";
        }
        if(StrUtil.isEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"009020601用户缴费情况\",\"speechText\": \"你好我是数字人，你可以向我提问任何问题\",\"options\": {\"propList\": [{\"label\": \"缴费时间\",\"prop\": \"date\"},{\"label\": \"缴费渠道\",\"prop\": \"channel\"},{\"label\": \"缴费金额\",\"prop\": \"payment\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"payment\": 66.6,\"channel\": \"微信公众号\"},{\"date\": \"2024-4\",\"payment\": 55.8,\"channel\": \"微信公众号\"},{\"date\": \"2024-5\",\"payment\": 37.4,\"channel\": \"微信公众号\"},{\"date\": \"2024-6\",\"payment\": 22.4,\"channel\": \"微信公众号\"},{\"date\": \"2024-7\",\"payment\": 44.2,\"channel\": \"微信公众号\"},{\"date\": \"2024-8\",\"payment\": 50,\"channel\": \"微信公众号\"},{\"date\": \"2024-9\",\"payment\": 50,\"channel\": \"微信公众号\"},{\"date\": \"2024-10\",\"payment\": 50,\"channel\": \"微信公众号\"}]},\"ulDataList\": [{\"name\": \"用户号\",\"value\": \" 029-009020601\"},{\"name\": \"用户名\",\"value\": \" 杜凤琴\"},{\"name\": \"用户地址\",\"value\": \"阳光花园小区9-2-601\"}]}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"table\",\"text\": \"\",\"title\": \"009020601用户缴费情况\",\"speechText\": \"029-009020601用户缴费情况\",\"options\": {\"propList\": [{\"label\": \"缴费时间\",\"prop\": \"date\"},{\"label\": \"缴费渠道\",\"prop\": \"channel\"},{\"label\": \"缴费金额\",\"prop\": \"payment\"}],\"total\": 8,\"tableList\": [{\"date\": \"2024-3\",\"payment\": 66.6,\"channel\": \"微信公众号\"},{\"date\": \"2024-4\",\"payment\": 55.8,\"channel\": \"微信公众号\"},{\"date\": \"2024-5\",\"payment\": 37.4,\"channel\": \"微信公众号\"},{\"date\": \"2024-6\",\"payment\": 22.4,\"channel\": \"微信公众号\"},{\"date\": \"2024-7\",\"payment\": 44.2,\"channel\": \"微信公众号\"},{\"date\": \"2024-8\",\"payment\": 50,\"channel\": \"微信公众号\"},{\"date\": \"2024-9\",\"payment\": 50,\"channel\": \"微信公众号\"},{\"date\": \"2024-10\",\"payment\": 50,\"channel\": \"微信公众号\"}]},\"ulDataList\": [\"用户号:<strong>029-009020601</strong>\",\"用户名:<strong>杜凤琴</strong>\",\"用户地址:<strong>阳光花园小区9-2-601</strong>\"]}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TABLE);

        TableResult tableResult = new TableResult();
        tableResult.setTitle("2024年8月至10月用户缴费统计");
        tableResult.setText("用户029-009020601，姓名：杜凤琴，2024年8月至2024年10月，共缴费7笔，累计缴费金额247.35元，缴费明细见图表。");
        List<String> textData = new ArrayList<>();
        textData.add("用户号:<strong>029-009020601</strong>");
        textData.add("用户名:<strong>杜凤琴</strong>");
        textData.add("用户地址:<strong>阳光花园小区9-2-601</strong>");
        tableResult.setTextData(textData);
        tableResult.setOptions(JSON.parseObject(option));
        tableResult.setSpeechText("用户029-009020601，姓名：杜凤琴，2024年8月至2024年10月，共缴费7笔，累计缴费金额247.35元，缴费明细见图表。");
        resultForFrontVo.setTableResult(tableResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 查询某户历史缴费情况 缴费情况  历史缴费
     */
    @Override
    public ResultForFrontVo getPaymentHistoryByDate(String customerCode, String startTime, String endTime) {
        return getPaymentHistoryByDate(CommonConstant.CHANNEL_CODE_BIGSCREEN,customerCode,startTime,endTime);
    }

    /**
     * 查询欠费大户 欠费用户  欠费排名
     */
    @Override
    public ResultForFrontVo getDefaultingUser(String channelCode) {

        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"rank\",\"text\": \"2024年4月-2024年10月欠费大户查询结果如下：\",\"title\": \"查询欠费大户\",\"speechText\": \"查询欠费大户\",\"options\": {\"propList\": [\"排名\",\"用户名称\",\"欠费金额\"],\"total\": 10,\"rankData\": [{\"name\": \"新疆博格达物业管理有限公司\",\"value\": 4057638.6,\"unit\": \"元\"},{\"name\": \"中国人民解放军9243部队\",\"value\": 452730.33,\"unit\": \"元\"},{\"name\": \"阜康有色发展有限责任公司\",\"value\": 446047.2,\"unit\": \"元\"},{\"name\": \"新疆五江天山投资有限公司\",\"value\": 352037.25,\"unit\": \"元\"},{\"name\": \"新疆神火炭素制品有限公司\",\"value\": 282184.5,\"unit\": \"元\"},{\"name\": \"阜康市城市管理局\",\"value\": 179878.7,\"unit\": \"元\"},{\"name\": \"宝石花物业管理有限公司阜康准东分公司\",\"value\": 154525.75,\"unit\": \"元\"},{\"name\": \"阜康市天池热力\",\"value\": 147718.6,\"unit\": \"元\"},{\"name\": \"克拉玛依准东第一小学\",\"value\": 130692.6,\"unit\": \"元\"},{\"name\": \"城关镇鱼儿沟中心村\",\"value\": 91713.6,\"unit\": \"元\"}]}}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"rank\",\"text\": \"2024年4月-2024年10月欠费大户查询结果如下：\",\"title\": \"查询欠费大户\",\"speechText\": \"查询欠费大户\",\"options\": {\"propList\": [\"排名\",\"用户名称\",\"欠费金额\"],\"total\": 10,\"rankData\": [{\"name\": \"新疆博格达物业管理有限公司\",\"value\": 4057638.6,\"unit\": \"元\"},{\"name\": \"中国人民解放军9243部队\",\"value\": 452730.33,\"unit\": \"元\"},{\"name\": \"阜康有色发展有限责任公司\",\"value\": 446047.2,\"unit\": \"元\"},{\"name\": \"新疆五江天山投资有限公司\",\"value\": 352037.25,\"unit\": \"元\"},{\"name\": \"新疆神火炭素制品有限公司\",\"value\": 282184.5,\"unit\": \"元\"},{\"name\": \"阜康市城市管理局\",\"value\": 179878.7,\"unit\": \"元\"},{\"name\": \"宝石花物业管理有限公司阜康准东分公司\",\"value\": 154525.75,\"unit\": \"元\"},{\"name\": \"阜康市天池热力\",\"value\": 147718.6,\"unit\": \"元\"},{\"name\": \"克拉玛依准东第一小学\",\"value\": 130692.6,\"unit\": \"元\"},{\"name\": \"城关镇鱼儿沟中心村\",\"value\": 91713.6,\"unit\": \"元\"}]}}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_RANK);
        RankResult rankResult = new RankResult();
        rankResult.setTitle("欠费用户排名");
        rankResult.setText("截止本日，欠费最多的用户为新疆博格达物业管理有限公司，共计欠费405.76万元，前10名欠费用户排名参见图表。");
        rankResult.setOptions(JSON.parseObject(option));
        rankResult.setSpeechText("截止本日，欠费最多的用户为新疆博格达物业管理有限公司，共计欠费405.76万元，前10名欠费用户排名参见图表。");
        resultForFrontVo.setRankResult(rankResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 查询欠费大户 欠费用户  欠费排名
     */
    @Override
    public ResultForFrontVo getDefaultingUser() {
        return getDefaultingUser(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
     */
    @Override
    public ResultForFrontVo getBigWaterUserByDate(String channelCode) {


        String option ="";
        if(CommonConstant.CHANNEL_CODE_BIGSCREEN.equals(channelCode)) {
            option = "{\"type\": \"rank\",\"text\": \"2024年4月-2024年10月用水大户查询结果如下：\",\"title\": \"查询历史用水大户\",\"speechText\": \"查询历史用水大户\",\"options\": {\"propList\": [\"排名\",\"用户名称\",\"用水量\"],\"total\": 10,\"rankData\": [{\"name\": \"热依木\",\"value\": 80.19,\"unit\": \"立方米\"},{\"name\": \"李金禄\",\"value\": 75.13,\"unit\": \"立方米\"},{\"name\": \"努尔巴拉提·赛散巴依\",\"value\": 70.4,\"unit\": \"立方米\"},{\"name\": \"张君成\",\"value\": 60.06,\"unit\": \"立方米\"},{\"name\": \"马海军\",\"value\": 59.51,\"unit\": \"立方米\"},{\"name\": \"马德\",\"value\": 55.99,\"unit\": \"立方米\"},{\"name\": \"马建新\",\"value\": 54.01,\"unit\": \"立方米\"},{\"name\": \"朱晓玲\",\"value\": 52.25,\"unit\": \"立方米\"},{\"name\": \"司马呼·居马乎\",\"value\": 46.64,\"unit\": \"立方米\"},{\"name\": \"冉孟捷\",\"value\": 19.36,\"unit\": \"立方米\"}]}}";
        } else if(CommonConstant.CHANNEL_CODE_BACKGROUND.equals(channelCode)) {
            option = "{\"type\": \"rank\",\"text\": \"2024年4月-2024年10月用水大户查询结果如下：\",\"title\": \"查询历史用水大户\",\"speechText\": \"查询历史用水大户\",\"options\": {\"propList\": [\"排名\",\"用户名称\",\"用水量\"],\"total\": 10,\"rankData\": [{\"name\": \"热依木\",\"value\": 80.19,\"unit\": \"立方米\"},{\"name\": \"李金禄\",\"value\": 75.13,\"unit\": \"立方米\"},{\"name\": \"努尔巴拉提·赛散巴依\",\"value\": 70.4,\"unit\": \"立方米\"},{\"name\": \"张君成\",\"value\": 60.06,\"unit\": \"立方米\"},{\"name\": \"马海军\",\"value\": 59.51,\"unit\": \"立方米\"},{\"name\": \"马德\",\"value\": 55.99,\"unit\": \"立方米\"},{\"name\": \"马建新\",\"value\": 54.01,\"unit\": \"立方米\"},{\"name\": \"朱晓玲\",\"value\": 52.25,\"unit\": \"立方米\"},{\"name\": \"司马呼·居马乎\",\"value\": 46.64,\"unit\": \"立方米\"},{\"name\": \"冉孟捷\",\"value\": 19.36,\"unit\": \"立方米\"}]}}";
        }


        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_RANK);
        RankResult rankResult = new RankResult();
        rankResult.setTitle("2024年用户量排名");
        rankResult.setText("2024年3月至2024年10月，累计用水最多的用户为热依木，共用水729方，前10名用水用户排名参见图表。");
        rankResult.setOptions(JSON.parseObject(option));
        rankResult.setSpeechText("2024年3月至2024年10月，累计用水最多的用户为热依木，共用水729方，前10名用水用户排名参见图表。");
        resultForFrontVo.setRankResult(rankResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
     */
    @Override
    public ResultForFrontVo getBigWaterUserByDate() {
        return getBigWaterUserByDate(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 营收数据分析汇总
     * 营收用户画像
     * 营收数据总览
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("营收数据分析汇总");
        urlResult.setUrl("http://123.57.11.138:8087/IBCS/Portal/portal.html");
//        urlResult.setUrl("http://10.0.15.41:8000/IBCS/Portal/portal.html");
        urlResult.setSpeechText("为您展示营收数据分析汇总");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 营收数据分析汇总
     * 营收用户画像
     * 营收数据总览
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics() {
        return dataStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

}
