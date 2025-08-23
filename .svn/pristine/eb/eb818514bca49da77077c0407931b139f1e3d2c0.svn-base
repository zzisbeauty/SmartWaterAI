package com.hanwei.process.business.businesscharge.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 营收Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IBusinessChargeService extends IService<ModelInvokingInfo> {
    /**
     * 年度新增用户统计
     * @param ChannelCode 渠道号
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo getNewUserNumber(String ChannelCode, String startTime, String endTime);

    /**
     * 年度新增用户统计
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo getNewUserNumber(String startTime, String endTime);

    /**
     * 支付途径统计
     * @param ChannelCode 渠道号
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo paymentChannelStatistics(String ChannelCode,String startTime, String endTime);

    /**
     * 支付途径统计
     * @param startTime
     * @param endTime
     * @return
     */
    ResultForFrontVo paymentChannelStatistics(String startTime, String endTime);

    /**
     * 客户档案情况汇总统计 用户档案 现在有多少用水用户
     */
    ResultForFrontVo customerInfoStatistic(String channelCode, String queryType);

    /**
     * 客户档案情况汇总统计 用户档案 现在有多少用水用户
     */
    ResultForFrontVo customerInfoStatistic(String queryType);


    /**
     * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
     */
    ResultForFrontVo getPaymentInfoByCustomerCode(String channelCode, String startTime, String endTime);

    /**
     * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
     */
    ResultForFrontVo getPaymentInfoByCustomerCode(String startTime, String endTime);

    /**
     * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
     * 查询用户当前账户情况
     */
    ResultForFrontVo chargeStatistics(String channelCode, String customerCode);

    /**
     * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
     * 查询用户当前账户情况
     */
    ResultForFrontVo chargeStatistics(String customerCode);

    /**
     * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
     */
    ResultForFrontVo getPaymentInfoByMonth(String channelCode, String customerCode, String queryDate);

    /**
     * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
     */
    ResultForFrontVo getPaymentInfoByMonth(String customerCode, String queryDate);

    /**
     * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
     */
    ResultForFrontVo getWaterUseHistoryByDate(String channelCode, String customerCode, String startTime, String endTime);

    /**
     * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
     */
    ResultForFrontVo getWaterUseHistoryByDate(String customerCode, String startTime, String endTime);


    /**
     * 查询某户历史缴费情况 缴费情况  历史缴费
     */
    ResultForFrontVo getPaymentHistoryByDate(String channelCode, String customerCode, String startTime, String endTime);

    /**
     * 查询某户历史缴费情况 缴费情况  历史缴费
     */
    ResultForFrontVo getPaymentHistoryByDate(String customerCode, String startTime, String endTime);

    /**
     * 查询欠费大户 欠费用户  欠费排名
     */
    ResultForFrontVo getDefaultingUser(String channelCode);

    /**
     * 查询欠费大户 欠费用户  欠费排名
     */
    ResultForFrontVo getDefaultingUser();

    /**
     * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
     */
    ResultForFrontVo getBigWaterUserByDate(String channelCode);

    /**
     * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
     */
    ResultForFrontVo getBigWaterUserByDate();

    /**
     * 营收数据分析汇总
     * 营收用户画像
     * 营收数据总览
     * @param ChannelCode 渠道号
     * @return
     */
    ResultForFrontVo dataStatistics(String ChannelCode);

    /**
     * 营收数据分析汇总
     * 营收用户画像
     * 营收数据总览
     * @return
     */
    ResultForFrontVo dataStatistics();
}
