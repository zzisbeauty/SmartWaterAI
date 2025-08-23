package com.hanwei.process.business.smartwaterplant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.smartwaterplant.service.ISmartWaterPlantService;
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
public class SmartWaterPlantServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements ISmartWaterPlantService {

    /**
     * 一水厂整体工艺
     * 一水厂厂区总览
     * 一水厂监测一张图
     * @return
     */
    @Override
    public ResultForFrontVo plantStatisticsByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-厂区总览");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/cqzl/Grid.html");
        urlResult.setSpeechText("为您展示一水厂厂区总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂整体工艺
     * 一水厂厂区总览
     * 一水厂监测一张图
     * @return
     */
    @Override
    public ResultForFrontVo plantStatisticsByFirstWorks() {
        return plantStatisticsByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂综合投加间
     * 一水厂加药间
     * @return
     */
    @Override
    public ResultForFrontVo dosingRoomByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-综合投加间");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/jyj/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-综合投加间");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂综合投加间
     * 一水厂加药间
     * @return
     */
    @Override
    public ResultForFrontVo dosingRoomByFirstWorks() {
        return dosingRoomByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂净水车间
     * 一水厂净水车间一张图
     * @return
     */
    @Override
    //TODO 修改为取水泵房
    public ResultForFrontVo waterPurificationPlantByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("取水泵站工艺总览");
        urlResult.setUrl("http://123.57.11.138:8087/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/qsbf/Grid.html");
        urlResult.setSpeechText("为您展示取水泵站工艺总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂净水车间
     * 一水厂净水车间一张图
     * @return
     */
    @Override
    public ResultForFrontVo waterPurificationPlantByFirstWorks() {
        return waterPurificationPlantByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂调节池
     * 一水厂排泥排水调节池
     * @return
     */
    @Override
    public ResultForFrontVo regulatingPondByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-排泥排水调节池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/pnpstjc/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-排泥排水调节池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂调节池
     * 一水厂排泥排水调节池
     * @return
     */
    @Override
    public ResultForFrontVo regulatingPondByFirstWorks() {
        return regulatingPondByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂配水间
     * 一水厂栅格配水间
     * @return
     */
    @Override
    public ResultForFrontVo distributingRoomByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-栅格配水间");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/psj/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-栅格配水间");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂配水间
     * 一水厂栅格配水间
     * @return
     */
    @Override
    public ResultForFrontVo distributingRoomByFirstWorks() {
        return distributingRoomByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂清水池
     * @return
     */
    @Override
    public ResultForFrontVo cleanWaterBasinByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-清水池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/qsc/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-清水池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂清水池
     * @return
     */
    @Override
    public ResultForFrontVo cleanWaterBasinByFirstWorks() {
        return cleanWaterBasinByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂脱水机房
     * @return
     */
    @Override
    public ResultForFrontVo dehydrationRoomByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-脱水机房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/wntsj/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-脱水机房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂脱水机房
     * @return
     */
    @Override
    public ResultForFrontVo dehydrationRoomByFirstWorks() {
        return dehydrationRoomByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 一水厂-自用水泵房
     * @return
     */
    @Override
    public ResultForFrontVo waterPumpHouseByFirstWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("一水厂-自用水泵房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/FirstWaterWorks/zysbf/Grid.html");
        urlResult.setSpeechText("为您展示一水厂-自用水泵房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 一水厂-自用水泵房
     * @return
     */
    @Override
    public ResultForFrontVo waterPumpHouseByFirstWorks() {
        return waterPumpHouseByFirstWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂整体工艺
     * 二水厂厂区总览
     * 二水厂监测一张图
     * @return
     */
    @Override
    public ResultForFrontVo plantStatisticsBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-厂区总览");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/cqzl/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-厂区总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂整体工艺
     * 二水厂厂区总览
     * 二水厂监测一张图
     * @return
     */
    @Override
    public ResultForFrontVo plantStatisticsBySecondWorks() {
        return plantStatisticsBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂加药间
     * @return
     */
    @Override
    public ResultForFrontVo dosingRoomBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-加药间");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/jyj/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-加药间");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂加药间
     * @return
     */
    @Override
    public ResultForFrontVo dosingRoomBySecondWorks() {
        return dosingRoomBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂净水车间
     * 二水厂净水车间一张图
     * @return
     */
    @Override
    public ResultForFrontVo waterPurificationPlantBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-净水车间");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/jscj/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-净水车间");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂净水车间
     * 二水厂净水车间一张图
     * @return
     */
    @Override
    public ResultForFrontVo waterPurificationPlantBySecondWorks() {
        return waterPurificationPlantBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂排污池
     * @return
     */
    @Override
    public ResultForFrontVo sewagePitBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-排污池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/pwc/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-排污池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂排污池
     * @return
     */
    @Override
    public ResultForFrontVo sewagePitBySecondWorks() {
        return sewagePitBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂清水池
     * @return
     */
    @Override
    public ResultForFrontVo cleanWaterBasinBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-清水池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/qsc/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-清水池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂清水池
     * @return
     */
    @Override
    public ResultForFrontVo cleanWaterBasinBySecondWorks() {
        return cleanWaterBasinBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂提升泵房
     * @return
     */
    @Override
    public ResultForFrontVo liftPumpHouseBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-提升泵房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/tsbf/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-提升泵房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂提升泵房
     * @return
     */
    @Override
    public ResultForFrontVo liftPumpHouseBySecondWorks() {
        return liftPumpHouseBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂调蓄水池
     * @return
     */
    @Override
    public ResultForFrontVo regulatingReservoirBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("水厂调蓄水池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/txsc/Grid.html");
        urlResult.setSpeechText("为您展示水厂调蓄水池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂调蓄水池
     * @return
     */
    @Override
    public ResultForFrontVo regulatingReservoirBySecondWorks() {
        return regulatingReservoirBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂-增压泵房
     * @return
     */
    @Override
    public ResultForFrontVo boosterPumpRoomBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂-增压泵房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/zybf/Grid.html");
        urlResult.setSpeechText("为您展示二水厂-增压泵房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂-增压泵房
     * @return
     */
    @Override
    public ResultForFrontVo boosterPumpRoomBySecondWorks() {
        return boosterPumpRoomBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂锅炉房
     * @return
     */
    @Override
    public ResultForFrontVo boilerRoomBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂锅炉房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/glf/Grid.html");
        urlResult.setSpeechText("为您展示二水厂锅炉房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂锅炉房
     * @return
     */
    @Override
    public ResultForFrontVo boilerRoomBySecondWorks() {
        return boilerRoomBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂反冲洗泵房
     * @return
     */
    @Override
    public ResultForFrontVo backwashPumpHouseBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("水厂反冲洗泵房");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/fcxbf/Grid.html");
        urlResult.setSpeechText("为您展示水厂反冲洗泵房");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂反冲洗泵房
     * @return
     */
    @Override
    public ResultForFrontVo backwashPumpHouseBySecondWorks() {
        return backwashPumpHouseBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂加氯间
     * @return
     */
    @Override
    public ResultForFrontVo chlorineDosingRoomBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂加氯间");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/jlj/Grid.html");
        urlResult.setSpeechText("为您展示二水厂加氯间");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂加氯间
     * @return
     */
    @Override
    public ResultForFrontVo chlorineDosingRoomBySecondWorks() {
        return chlorineDosingRoomBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂配电柜
     * @return
     */
    @Override
    public ResultForFrontVo electricClosetBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂配电柜");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/pdg/Grid.html");
        urlResult.setSpeechText("为您展示二水厂配电柜");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂配电柜
     * @return
     */
    @Override
    public ResultForFrontVo electricClosetBySecondWorks() {
        return electricClosetBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }

    /**
     * 二水厂预沉池
     * @return
     */
    @Override
    public ResultForFrontVo preliminarySedimentationTankBySecondWorks(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("二水厂预沉池");
        urlResult.setUrl("http://10.0.15.41:8000/SmartWater/DevelopmentCase/SmartWaterFactory/SecondWaterWorks/ycc/Grid.html");
        urlResult.setSpeechText("为您展示二水厂预沉池");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 二水厂预沉池
     * @return
     */
    @Override
    public ResultForFrontVo preliminarySedimentationTankBySecondWorks() {
        return preliminarySedimentationTankBySecondWorks(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
