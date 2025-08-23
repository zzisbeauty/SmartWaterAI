package com.hanwei.process.business.smartwaterplant.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface ISmartWaterPlantService extends IService<ModelInvokingInfo> {
    /**
     * 一水厂整体工艺
     * 一水厂厂区总览
     * 一水厂监测一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo plantStatisticsByFirstWorks(String ChannelCode);

    /**
     * 一水厂整体工艺
     * 一水厂厂区总览
     * 一水厂监测一张图
     * @return
     */
    ResultForFrontVo plantStatisticsByFirstWorks();

    /**
     * 一水厂综合投加间
     * 一水厂加药间
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dosingRoomByFirstWorks(String ChannelCode);

    /**
     * 一水厂综合投加间
     * 一水厂加药间
     * @return
     */
    ResultForFrontVo dosingRoomByFirstWorks();

    /**
     * 一水厂净水车间
     * 一水厂净水车间一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo waterPurificationPlantByFirstWorks(String ChannelCode);

    /**
     * 一水厂净水车间
     * 一水厂净水车间一张图
     * @return
     */
    ResultForFrontVo waterPurificationPlantByFirstWorks();

    /**
     * 一水厂调节池
     * 一水厂排泥排水调节池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo regulatingPondByFirstWorks(String ChannelCode);

    /**
     * 一水厂调节池
     * 一水厂排泥排水调节池
     * @return
     */
    ResultForFrontVo regulatingPondByFirstWorks();

    /**
     * 一水厂配水间
     * 一水厂栅格配水间
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo distributingRoomByFirstWorks(String ChannelCode);

    /**
     * 一水厂配水间
     * 一水厂栅格配水间
     * @return
     */
    ResultForFrontVo distributingRoomByFirstWorks();

    /**
     * 一水厂清水池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo cleanWaterBasinByFirstWorks(String ChannelCode);

    /**
     * 一水厂清水池
     * @return
     */
    ResultForFrontVo cleanWaterBasinByFirstWorks();

    /**
     * 一水厂脱水机房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dehydrationRoomByFirstWorks(String ChannelCode);

    /**
     * 一水厂脱水机房
     * @return
     */
    ResultForFrontVo dehydrationRoomByFirstWorks();

    /**
     * 一水厂-自用水泵房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo waterPumpHouseByFirstWorks(String ChannelCode);

    /**
     * 一水厂-自用水泵房
     * @return
     */
    ResultForFrontVo waterPumpHouseByFirstWorks();

    /**
     * 二水厂整体工艺
     * 二水厂厂区总览
     * 二水厂监测一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo plantStatisticsBySecondWorks(String ChannelCode);

    /**
     * 二水厂整体工艺
     * 二水厂厂区总览
     * 二水厂监测一张图
     * @return
     */
    ResultForFrontVo plantStatisticsBySecondWorks();

    /**
     * 二水厂加药间
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dosingRoomBySecondWorks(String ChannelCode);

    /**
     * 二水厂加药间
     * @return
     */
    ResultForFrontVo dosingRoomBySecondWorks();

    /**
     * 二水厂净水车间
     * 二水厂净水车间一张图
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo waterPurificationPlantBySecondWorks(String ChannelCode);

    /**
     * 二水厂净水车间
     * 二水厂净水车间一张图
     * @return
     */
    ResultForFrontVo waterPurificationPlantBySecondWorks();

    /**
     * 二水厂排污池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo sewagePitBySecondWorks(String ChannelCode);

    /**
     * 二水厂排污池
     * @return
     */
    ResultForFrontVo sewagePitBySecondWorks();

    /**
     * 二水厂清水池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo cleanWaterBasinBySecondWorks(String ChannelCode);

    /**
     * 二水厂清水池
     * @return
     */
    ResultForFrontVo cleanWaterBasinBySecondWorks();

    /**
     * 二水厂提升泵房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo liftPumpHouseBySecondWorks(String ChannelCode);

    /**
     * 二水厂提升泵房
     * @return
     */
    ResultForFrontVo liftPumpHouseBySecondWorks();

    /**
     * 二水厂调蓄水池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo regulatingReservoirBySecondWorks(String ChannelCode);

    /**
     * 二水厂调蓄水池
     * @return
     */
    ResultForFrontVo regulatingReservoirBySecondWorks();

    /**
     * 二水厂-增压泵房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo boosterPumpRoomBySecondWorks(String ChannelCode);

    /**
     * 二水厂-增压泵房
     * @return
     */
    ResultForFrontVo boosterPumpRoomBySecondWorks();

    /**
     * 二水厂锅炉房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo boilerRoomBySecondWorks(String ChannelCode);

    /**
     * 二水厂锅炉房
     * @return
     */
    ResultForFrontVo boilerRoomBySecondWorks();

    /**
     * 二水厂反冲洗泵房
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo backwashPumpHouseBySecondWorks(String ChannelCode);

    /**
     * 二水厂反冲洗泵房
     * @return
     */
    ResultForFrontVo backwashPumpHouseBySecondWorks();

    /**
     * 二水厂加氯间
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo chlorineDosingRoomBySecondWorks(String ChannelCode);

    /**
     * 二水厂加氯间
     * @return
     */
    ResultForFrontVo chlorineDosingRoomBySecondWorks();

    /**
     * 二水厂配电柜
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo electricClosetBySecondWorks(String ChannelCode);

    /**
     * 二水厂配电柜
     * @return
     */
    ResultForFrontVo electricClosetBySecondWorks();

    /**
     * 二水厂预沉池
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo preliminarySedimentationTankBySecondWorks(String ChannelCode);

    /**
     * 二水厂预沉池
     * @return
     */
    ResultForFrontVo preliminarySedimentationTankBySecondWorks();


}
