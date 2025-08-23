package com.hanwei.process.modelrule.service.impl;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.util.DateUtils;
import com.hanwei.process.business.businesscharge.service.IBusinessChargeService;
import com.hanwei.process.business.dma.service.IDmaService;
import com.hanwei.process.business.gis.service.IGisService;
import com.hanwei.process.business.hotline.service.IHotLineService;
import com.hanwei.process.business.pipemonitoring.service.IPipeMonitoringService;
import com.hanwei.process.business.pipepolling.service.IPipePollingService;
import com.hanwei.process.business.secondsupply.service.ISecondSupplyService;
import com.hanwei.process.business.smartwaterplant.service.ISmartWaterPlantService;
import com.hanwei.process.business.watersource.service.IWaterSourceService;
import com.hanwei.process.business.wspss.service.IWspssService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.modelrule.service.INoModelHandleService;
import com.hanwei.process.util.CommonUtils;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.TextResult;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



/**
 * @Description: 无模型分配器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class NoModelHandleServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements INoModelHandleService {

    @Resource
    private IBusinessChargeService businessChargeService;
    @Resource
    private IGisService gisService;
    @Resource
    private IPipeMonitoringService pipeMonitoringService;
    @Resource
    private IWaterSourceService waterSourceService;
    @Resource
    private ISmartWaterPlantService smartWaterPlantService;
    @Resource
    private IWspssService wspssService;
    @Resource
    private ISecondSupplyService secondSupplyService;
    @Resource
    private IDmaService dmaService;
    @Resource
    private IHotLineService hotLineService;
    @Resource
    private IPipePollingService pipePollingService;

    /**
     * 无模型规则匹配器
     * @param message
     * @return
     */
    @Override
    public ResultForFrontVo noModelHandle(String channelCode, String message) {
        if(StrUtil.isEmpty(message)){
            return null;
        }

        /***********************营收系统 begin*************************************/
        /**
         * 支付途径统计(营收各个支付途径的统计情况、收入占比) 支付渠道 收费情况
         */
        if(message.contains("支付途径") || message.contains("收入占比") || message.contains("支付渠道")){
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            return businessChargeService.paymentChannelStatistics(channelCode,startTime,endTime);
        }

        /**
         * 客户档案情况汇总统计 用户档案 现在有多少用水用户
         */
        if(message.contains("客户档案") || message.contains("用水用户") || message.contains("用户档案")|| message.contains("用户数量")
                || message.contains("用水户数量")
                || message.contains("用水户统计")|| message.contains("哪种表")){
            /**
             * 1-按片区
             * 2-按用水性质
             * 3-按表计类型
             */
            String queryType = "1";
            return businessChargeService.customerInfoStatistic(channelCode,queryType);
        }

        /**
         * 年度新增用户统计 报装情况 用户增长 报装了多少用户
         */
        if(message.contains("新增了多少用水用户") || message.contains("新增用户")|| message.contains("报装情况")|| message.contains("用户增长")|| message.contains("报装了多少用户")){
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            return businessChargeService.getNewUserNumber(channelCode,startTime,endTime);
        }

        /**
         * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
         */
        if(message.contains("应收") || message.contains("实收")|| message.contains("回收")|| message.contains("营收情况")
                || message.contains("收入情况")|| message.contains("收费情况")){
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            return businessChargeService.getPaymentInfoByCustomerCode(channelCode,startTime,endTime);
        }

        /**
         * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
         * 查询用户当前账户情况
         */
        if(message.contains("缴费情况") || message.contains("是否有欠费")|| message.contains("账户情况")|| message.contains("用水情况")){
            String customerCode = "029-009020601";
            return businessChargeService.chargeStatistics(channelCode,customerCode);
        }

        /**
         * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
         */
        if(message.contains("月份用水情况") || message.contains("本月份账单情况")){
            String customerCode = "029-009020601";
            String queryDate = "2024-05-01";
            return businessChargeService.getPaymentInfoByMonth(channelCode,customerCode,queryDate);
        }

        /**
         * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
         */
        if(message.contains("历史用水") || message.contains("历史用水情况")|| message.contains("账单情况")){
            String customerCode = "029-009020601";
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            return businessChargeService.getWaterUseHistoryByDate(channelCode,customerCode,startTime,endTime);
        }

        /**
         * 查询某户历史缴费情况 缴费情况  历史缴费
         */
        if(message.contains("历史缴费") || message.contains("历史缴费情况")|| message.contains("是否缴费")|| message.contains("是否有缴费")){
            String customerCode = "029-009020601";
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            return businessChargeService.getPaymentHistoryByDate(channelCode,customerCode,startTime,endTime);
        }

        /**
         * 查询欠费大户 欠费用户  欠费排名
         */
        if(message.contains("欠费大户") || message.contains("欠费用户")|| message.contains("欠费排名")|| message.contains("欠费用户")|| message.contains("欠费的用户")
                || message.contains("欠费")|| message.contains("账单未收")){
            return businessChargeService.getDefaultingUser(channelCode);
        }

        /**
         * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
         */
        if(message.contains("用水大户") || message.contains("用水最多")|| message.contains("用水量排行")|| message.contains("用水最高")|| message.contains("用水量排名")){
            return businessChargeService.getBigWaterUserByDate(channelCode);
        }

        /**
         * 营收数据分析汇总
         * 营收用户画像
         * 营收数据总览
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("营收数据分析") || message.contains("营收用户画像")|| message.contains("营收数据总览")|| message.contains("营收一张图")){
            return businessChargeService.dataStatistics(channelCode);
        }

        /***********************营收系统 end*************************************/


        /***********************GIS系统 begin*************************************/
        /**
         * 查询管网整体概况
         * 当前各类管网长度情况统计
         * 目前各类管网有多长？
         * 管网长度是多少？
         * 各类型管网铺设情况？
         */
        if(message.contains("管网长度") || message.contains("管网概况")|| message.contains("管网铺设情况")|| message.contains("管网整体概况")){
            String queryType = "1";
            return gisService.pipeStatistics(channelCode,queryType);
        }

        /**
         * 按照管径统计管网情况
         * 按管径统计管网长度
         * 目前管网都有什么口径的？
         * 100的管网有多少？
         */
        if(message.contains("100的管网") || message.contains("管网口径")|| message.contains("按管径统计")|| message.contains("各类管网")|| message.contains("目前管网都有")){
            String diameter = "100";
            return gisService.pipeStatisticsByDiameter(channelCode,diameter);
        }

        /**
         * 按照材质统计管网情况
         * 按照材质统计管网长度
         * 目前管网都有什么材质的？
         * PE的供水/排水/绿化管网有多少？
         */
        if(message.contains("PE") || message.contains("管网材质")|| message.contains("材质统计")|| message.contains("什么材质")){
            String material = "PE";
            return gisService.pipeStatisticsByMaterial(channelCode,material);
        }

        /**
         * 管网设施统计
         * 按设施类型统计设施数量
         * 目前有多少设施？
         * 都有什么类型的设施？
         */
        if(message.contains("管网设施") || message.contains("按设施类型统计")|| message.contains("多少管网设施")|| message.contains("什么类型的设施")){
            return gisService.pipeDeviceStatistics(channelCode);
        }

        /**
         * 整体管网统计
         * @return
         */
        if(message.contains("整体管网") || message.contains("数据总览")){
            return gisService.pipeOverAllStatistics(channelCode);
        }



        /***********************GIS系统 end*************************************/

        /***********************管网监测系统 begin*************************************/
        /**
         * 当前管网压力最高的点？
         * 当前管网的压力情况？
         * 当前管网压力最低的点？
         * 管网压力现在是什么情况？
         * 当前管网压力最高和最低的点是哪个
         */
        if(message.contains("最高") || message.contains("最低")|| message.contains("管网压力")|| message.contains("管网的压力")){
            return pipeMonitoringService.getTopMonitor(channelCode);
        }
        /**
         * {名称}本月压力情况？
         * 我想看下{名称}2024年10月1日至2024年10月29日的压力
         */
        if(message.contains("总减压井") || message.contains("本月压力")){
            String startTime = "2024-01-01 00:00:00";
            String endTime = DateUtils.now();
            String monitorName = "总减压井";
            return pipeMonitoringService.getHistoryDataByMonitor(channelCode, monitorName, startTime,  endTime);
        }
        /**
         * 现在已安装多少监测设备？
         * 目前监测设备建设情况？
         * 监测设备运行情况？
         */
        if(message.contains("监测设备运行情况") || message.contains("建设情况")|| message.contains("已安装多少")|| message.contains("管网物联监测设备")){
            return pipeMonitoringService.pipeMonitorDeviceStatistics(channelCode);
        }
        /**
         * 目前有多少压力监测设备？
         * 压力监测设备情况？
         */
        if(message.contains("压力监测设备") || message.contains("压力监测点")){
            return pipeMonitoringService.getPressureMonitorCount(channelCode);
        }
        /**
         * 目前有多少流量监测设备？
         * 流量监测设备情况？
         */
        if(message.contains("流量监测设备") || message.contains("多少流量监测点")){
            return pipeMonitoringService.getFlowMonitorCount(channelCode);
        }
        /**
         * 目前有多少报警点？
         * 目前管网运行报警情况？
         * 目前管网运行有什么问题？
         */
        if(message.contains("报警情况") || message.contains("管网运行有什么问题")|| message.contains("报警点")|| message.contains("报警监测点")){
            return pipeMonitoringService.getAlarmMonitor(channelCode);
        }
        /**
         * 目前管网运行情况？
         * 管网现在是什么情况？
         */
        if(message.contains("管网监测总览") || message.contains("管网现在是什么情况")|| message.contains("管网总览")|| message.contains("管网一张图")|| message.contains("管网整体情况")
                || message.contains("管网监测信息")|| message.contains("管网监测分布")){
            return pipeMonitoringService.pipeStatistics(channelCode);
        }



        /**
         * 压力监测-GIS总览
         */
        if(message.contains("压力监测GIS总览") || message.contains("压力点位分布图")|| message.contains("压力监测一张图")|| message.contains("管网压力监测")){
            return pipeMonitoringService.gisStatisticsByPressure(channelCode);
        }



        /**
         * 管网流量监测一张图
         * 管网流量GIS一张图
         */
        if(message.contains("流量监测一张图") || message.contains("流量监测点位分布图")|| message.contains("管网流量GIS一张图")|| message.contains("管网流量监测")){
            return pipeMonitoringService.gisStatisticsByFlow(channelCode);
        }



        /**
         * 管网水质监测一张图
         * 管网水质GIS一张图
         */
        if(message.contains("水质监测一张图") || message.contains("水质一张图")|| message.contains("水质")){
            return pipeMonitoringService.gisStatisticsByQuality(channelCode);
        }

        /***********************管网监测系统 end*************************************/

        /***********************水源井监测系统 start*************************************/
        /**
         * 水源井数据分析统计
         * 水源井用户画像
         */
        if(message.contains("水源井数据分析统计") || message.contains("水源井用户画像")|| message.contains("水源井系统")
                || message.contains("水源井运行")|| message.contains("水源井数据驾驶")){
            return waterSourceService.dataStatistics(channelCode);
        }
        /**
         * 水源井一张图
         * 水源井GIS一张图
         */
        if(message.contains("水源井一张图") || message.contains("水源井GIS一张图")|| message.contains("水源井信息")|| message.contains("水源井分布")){
            return waterSourceService.gisStatistics(channelCode);
        }
        /***********************水源井监测系统 end*************************************/

        /***********************智慧水厂系统 start*************************************/

        /**
         * 一水厂整体工艺
         * 一水厂厂区总览
         * 一水厂监测一张图
         * @return
         */
        if(message.contains("一水厂整体工艺") || message.contains("一水厂厂区总览")|| message.contains("一水厂监测一张图")|| message.contains("一水厂一张图")
                || message.contains("一水厂工艺")|| message.contains("一水厂全景图")|| message.contains("一水厂运行态势")|| message.contains("一水厂实时运行")){
            return smartWaterPlantService.plantStatisticsByFirstWorks(channelCode);
        }


        /**
         * 一水厂综合投加间
         * 一水厂加药间
         * @return
         */
        if(message.contains("一水厂综合投加") || message.contains("一水厂加药")){
            return smartWaterPlantService.dosingRoomByFirstWorks(channelCode);
        }


        /**
         * 一水厂净水车间
         * 一水厂净水车间一张图
         * @return
         */
        if(message.contains("一水厂净水")){
            return smartWaterPlantService.waterPurificationPlantByFirstWorks(channelCode);
        }


        /**
         * 一水厂调节池
         * 一水厂排泥排水调节池
         * @return
         */
        if(message.contains("一水厂调节") || message.contains("一水厂排泥排水")){
            return smartWaterPlantService.regulatingPondByFirstWorks(channelCode);
        }


        /**
         * 一水厂配水间
         * 一水厂栅格配水间
         * @return
         */
        if(message.contains("一水厂配水") || message.contains("一水厂栅格配水")){
            return smartWaterPlantService.distributingRoomByFirstWorks(channelCode);
        }

        /**
         * 一水厂清水池
         * @return
         */
        if(message.contains("一水厂清水池") ){
            return smartWaterPlantService.cleanWaterBasinByFirstWorks(channelCode);
        }


        /**
         * 一水厂脱水机房
         * @return
         */
        if(message.contains("一水厂脱水机房")){
            return smartWaterPlantService.dehydrationRoomByFirstWorks(channelCode);
        }


        /**
         * 一水厂-自用水泵房
         * @return
         */
        if(message.contains("一水厂自用水泵") ){
            return smartWaterPlantService.waterPumpHouseByFirstWorks(channelCode);
        }


        /**
         * 二水厂整体工艺
         * 二水厂厂区总览
         * 二水厂监测一张图
         * @return
         */
        if(message.contains("二水厂整体工艺") || message.contains("二水厂厂区总览")|| message.contains("二水厂厂区总览")|| message.contains("二水厂监测")
                || message.contains("二水厂工艺")|| message.contains("二水厂全景图")|| message.contains("二水厂运行态势")|| message.contains("二水厂实时运行")){
            return smartWaterPlantService.plantStatisticsBySecondWorks(channelCode);
        }


        /**
         * 二水厂加药间
         * @return
         */
        if(message.contains("二水厂加药") ){
            return smartWaterPlantService.dosingRoomBySecondWorks(channelCode);
        }


        /**
         * 二水厂净水车间
         * 二水厂净水车间一张图
         * @return
         */
        if(message.contains("二水厂净水")){
            return smartWaterPlantService.waterPurificationPlantBySecondWorks(channelCode);
        }


        /**
         * 二水厂排污池
         * @return
         */
        if(message.contains("二水厂排污")){
            return smartWaterPlantService.sewagePitBySecondWorks(channelCode);
        }


        /**
         * 二水厂清水池
         * @return
         */
        if(message.contains("二水厂清水")){
            return smartWaterPlantService.cleanWaterBasinBySecondWorks(channelCode);
        }


        /**
         * 二水厂提升泵房
         * @return
         */
        if(message.contains("二水厂提升")){
            return smartWaterPlantService.liftPumpHouseBySecondWorks(channelCode);
        }

        /**
         * 二水厂调蓄水池
         * @return
         */
        if(message.contains("水厂调蓄水")){
            return smartWaterPlantService.regulatingReservoirBySecondWorks(channelCode);
        }


        /**
         * 二水厂-增压泵房
         * @return
         */
        if(message.contains("二水厂增压")){
            return smartWaterPlantService.boosterPumpRoomBySecondWorks(channelCode);
        }


        /**
         * 二水厂锅炉房
         * @return
         */
        if(message.contains("二水厂锅炉")){
            return smartWaterPlantService.boilerRoomBySecondWorks(channelCode);
        }


        /**
         * 二水厂反冲洗泵房
         * @return
         */
        if(message.contains("二水厂反冲洗泵")){
            return smartWaterPlantService.backwashPumpHouseBySecondWorks(channelCode);
        }


        /**
         * 二水厂加氯间
         * @return
         */
        if(message.contains("二水厂加氯")){
            return smartWaterPlantService.chlorineDosingRoomBySecondWorks(channelCode);
        }


        /**
         * 二水厂配电柜
         * @return
         */
        if(message.contains("二水厂配电")){
            return smartWaterPlantService.electricClosetBySecondWorks(channelCode);
        }

        /**
         * 二水厂预沉池
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("二水厂预沉")){
            return smartWaterPlantService.preliminarySedimentationTankBySecondWorks(channelCode);
        }
        /***********************智慧水厂系统 end*************************************/

        /***********************供水稳压系统 start*************************************/

        /**
         * 稳压系统用户画像
         * 调流调压数据分析汇总
         * 调流调压数据画像
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("调流调压分析")||message.contains("调流调压数据")||message.contains("调流调压一张图")||message.contains("稳压系统用户画像")
                ||message.contains("稳压系统运行态势")||message.contains("稳压系统数据驾驶")||message.contains("稳压系统数据分析")){
            return wspssService.monitoringStatistics(channelCode);
        }

        /**
         * 调流调压GIS总览
         * 调流调压GIS一张图
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("调流调压GIS")||message.contains("稳压一张图")||message.contains("稳压系统信息")||message.contains("稳压系统分布")){
            return wspssService.gisStatistics(channelCode);
        }

        /**
         * 稳压系统工艺
         * 调流调压工艺
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("稳压系统工艺")||message.contains("调流调压工艺")){
            return wspssService.scadaStatistics(channelCode);
        }
        /***********************供水稳压系统 end*************************************/


        /***********************二次供水系统 start*************************************/

        /**
         * 二供数据总览
         * 二供用户画像
         * 二供数据分析汇总
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("二供数据总览")||message.contains("二供用户画像")||message.contains("二供数据分析")||message.contains("二次供水")){
            return secondSupplyService.dataStatistics(channelCode);
        }

        /**
         * 二供监测总览
         * 二供GIS一张图
         * 二供GIS总览
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("二供监测")||message.contains("二供GIS一张图")||message.contains("二供一张图")||message.contains("二供点位分布")){
            return secondSupplyService.gisStatistics(channelCode);
        }
        /***********************二次供水系统 end*************************************/


        /***********************DMA分区系统 start*************************************/

        /**
         * 漏损一张图
         * 漏损GIS总览
         * 漏损总览
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("漏损一张图")||message.contains("漏损总览")||message.contains("漏损分析总览")){
            return dmaService.gisStatistics(channelCode);
        }


        /**
         * 漏损分析汇总
         * 漏损分析报表
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("漏损分析")){
            return dmaService.leakageAnalysisStatistics(channelCode);
        }
        /***********************DMA分区系统 end*************************************/


        /***********************热线系统 start*************************************/

        /**
         * 热线数据总览
         * 热线用户画像
         * 热线数据分析汇总
         * @return
         */
        if(message.contains("热线数据总览")||message.contains("热线用户画像")||message.contains("热线数据")||message.contains("热线一张图")){
            return hotLineService.dataStatistics(channelCode);
        }

        /***********************热线系统 end*************************************/


        /***********************管网巡检系统 start*************************************/

        /**
         * 管网巡检数据分析汇总
         * 管网巡检数据总览
         * 管网巡检用户画像
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("巡检数据")||message.contains("巡检用户画像")){
            return pipePollingService.dataStatistics(channelCode);
        }


        /**
         * 巡检监控一张图
         * @param ChannelCode 渠道号
         * @return
         */
        if(message.contains("巡检一张图")||message.contains("巡检监控一张图") ||message.contains("巡检工作情况")){
            return pipePollingService.pollingMonitorStatistics(channelCode);
        }

        /***********************管网巡检系统 end*************************************/


        // 无匹配结果处理
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_TEXT);
        TextResult textResult = new TextResult();
        String reply = CommonUtils.getNoAnswerReply();
        textResult.setText(reply);
        textResult.setSpeechText(reply);
        resultForFrontVo.setTextResult(textResult);

        return resultForFrontVo;
    }

}
