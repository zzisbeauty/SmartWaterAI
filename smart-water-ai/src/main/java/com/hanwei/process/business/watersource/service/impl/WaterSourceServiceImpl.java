package com.hanwei.process.business.watersource.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.watersource.service.IWaterSourceService;
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
public class WaterSourceServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IWaterSourceService {


    /**
     * 水源井数据分析统计
     * 水源井用户画像
     * @param channelCode 渠道号
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics(String channelCode) {
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
        urlResult.setTitle("数据总览");
        urlResult.setUrl("http://10.0.15.41:8000/smartwater/DevelopmentCase/WaterSourceWell/Portal/index.html");
        urlResult.setSpeechText("请看水源井数据总览情况");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }
    /**
     * 水源井数据分析统计
     * 水源井用户画像
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics() {
        return dataStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 水源井一张图
     * 水源井GIS一张图
     * @param channelCode 渠道号
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics(String channelCode) {
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
        urlResult.setTitle("水源井GIS一张图");
        urlResult.setUrl("http://10.0.15.41:8000/smartwater/DevelopmentCase/WaterSourceWell/GisData/Map.html");
        urlResult.setSpeechText("请看水源井GIS一张图情况");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 水源井一张图
     * 水源井GIS一张图
     * @return
     */
    @Override
    public ResultForFrontVo gisStatistics() {
        return gisStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
