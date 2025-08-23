package com.hanwei.process.business.businesscharge.controller;

import com.alibaba.fastjson.JSON;

import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.businesscharge.service.IBusinessChargeService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.service.IModelInvokingService;
import com.hanwei.process.vo.ResultForFrontVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 模型调用信息控制器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/modelinvoking/businesscharge")
public class BusinessChargeController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IBusinessChargeService businessChargeService;

	/**
	 * 年度新增用户统计
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	/**
	 * 年度新增用户统计
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@AutoLog(value = "模型调用-年度新增用户统计")
	@GetMapping(value = "/getNewUserNumber")
	public Result<?> getNewUserNumber(@RequestParam(name="startTime" ,required = false) String startTime,
									  @RequestParam(name="endTime" ,required = false) String endTime) {

		try {
			log.info("模型调用-年度新增用户统计成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getNewUserNumber(startTime, endTime);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-年度新增用户统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-年度新增用户统计失败",e.getMessage());
			return Result.error(200,"操作失败");
		}
	}


	/**
	 * 支付途径统计
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@AutoLog(value = "模型调用-支付途径统计")
	@GetMapping(value = "/paymentChannelStatistics")
	public Result<?> paymentChannelStatistics(@RequestParam(name="startTime",required = false) String startTime,
											  @RequestParam(name="endTime",required = false) String endTime) {

		try {
			log.info("模型调用-支付途径统计成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.paymentChannelStatistics(startTime, endTime);

			Result result = new Result();
			result.setResult(JSON.toJSONString(resultForFrontVo));
			result.setMessage("模型调用-支付途径统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-支付途径统计失败",e.getMessage());
			return Result.error(200,"操作失败");
		}
	}


	/**
	 * 客户档案情况汇总统计 用户档案 现在有多少用水用户
	 */
	@AutoLog(value = "模型调用-客户档案情况汇总统计")
	@GetMapping(value = "/customerInfoStatistic")
	public Result<?> customerInfoStatistic(@RequestParam(name="queryType" ,required = false) String queryType) {

		try {
			log.info("模型调用-客户档案情况汇总统计");
			ResultForFrontVo resultForFrontVo = businessChargeService.customerInfoStatistic(queryType);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-客户档案情况汇总统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-客户档案情况汇总统计统计失败",e.getMessage());
			return Result.error(200,"操作失败");
		}
	}


	/**
	 * 本年度应收、实收、回收率统计 应收、实收、回收率统计 营收情况
	 */
	@AutoLog(value = "模型调用-本年度应收、实收、回收率统计 应收、实收、回收率统计")
	@GetMapping(value = "/getPaymentInfoByCustomerCode")
	public Result<?> getPaymentInfoByCustomerCode(@RequestParam(name="startTime" ,required = false) String startTime,
									  @RequestParam(name="endTime" ,required = false) String endTime) {

		try {
			log.info("模型调用-本年度应收、实收、回收率统计 应收、实收、回收率统计成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getPaymentInfoByCustomerCode(startTime, endTime);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-本年度应收、实收、回收率统计 应收、实收、回收率统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-本年度应收、实收、回收率统计 应收、实收、回收率统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 查询某一用户用水情况  缴费情况 用水情况 是否有欠费
	 * 查询用户当前账户情况
	 */
	@AutoLog(value = "模型调用-查询某一用户用水情况")
	@GetMapping(value = "/chargeStatistics")
	public Result<?> chargeStatistics(@RequestParam(name="customerCode" ,required = false) String customerCode) {

		try {
			log.info("模型调用-查询某一用户用水情况成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.chargeStatistics(customerCode);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-查询某一用户用水情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-查询某一用户用水情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 按年月查询用户用水情况 本月份用水情况  本月份账单情况
	 */
	@AutoLog(value = "模型调用-按年月查询用户用水情况")
	@GetMapping(value = "/getPaymentInfoByMonth")
	public Result<?> getPaymentInfoByMonth(@RequestParam(name="customerCode" ,required = false) String customerCode,
									  @RequestParam(name="queryDate" ,required = false) String queryDate) {

		try {
			log.info("模型调用-按年月查询用户用水情况成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getPaymentInfoByMonth(customerCode, queryDate);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-按年月查询用户用水情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-按年月查询用户用水情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 我的历史用水情况/某月到某月用水情况？(历史用水、账单情况) 历史用水情况
	 */
	@AutoLog(value = "模型调用-我的历史用水情况")
	@GetMapping(value = "/getWaterUseHistoryByDate")
	public Result<?> getWaterUseHistoryByDate(@RequestParam(name="customerCode" ,required = false) String customerCode,
											  @RequestParam(name="startTime" ,required = false) String startTime,
									  @RequestParam(name="endTime" ,required = false) String endTime) {

		try {
			log.info("模型调用-我的历史用水情况成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getWaterUseHistoryByDate(customerCode,startTime, endTime);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-我的历史用水情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-我的历史用水情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 查询某户历史缴费情况 缴费情况  历史缴费
	 */
	@AutoLog(value = "模型调用-查询某户历史缴费情况")
	@GetMapping(value = "/getPaymentHistoryByDate")
	public Result<?> getPaymentHistoryByDate(@RequestParam(name="customerCode" ,required = false) String customerCode,
											 @RequestParam(name="startTime" ,required = false) String startTime,
									  @RequestParam(name="endTime" ,required = false) String endTime) {

		try {
			log.info("模型调用-查询某户历史缴费情况成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getPaymentHistoryByDate(customerCode,startTime, endTime);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-查询某户历史缴费情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-查询某户历史缴费情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}



	/**
	 * 查询欠费大户 欠费用户  欠费排名
	 */
	@AutoLog(value = "模型调用-查询欠费大户")
	@GetMapping(value = "/getDefaultingUser")
	public Result<?> getDefaultingUser() {

		try {
			log.info("模型调用-查询欠费大户成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getDefaultingUser();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-查询欠费大户操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-查询欠费大户失败",e.getMessage());
			return Result.error("操作失败");
		}
	}



	/**
	 * 查询历史用水大户 用水大户 用水最多 用水最高 用水量排名
	 */
	@AutoLog(value = "模型调用-查询历史用水大户")
	@GetMapping(value = "/getBigWaterUserByDate")
	public Result<?> getBigWaterUserByDate() {

		try {
			log.info("模型调用-查询历史用水大户成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.getBigWaterUserByDate();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-查询历史用水大户操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-查询历史用水大户失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 营收数据分析汇总
	 * 营收用户画像
	 * 营收数据总览
	 * @return
	 */
	@AutoLog(value = "模型调用-营收数据分析汇总")
	@GetMapping(value = "/dataStatistics")
	public Result<?> dataStatistics() {

		try {
			log.info("模型调用-营收数据分析汇总成功");
			ResultForFrontVo resultForFrontVo = businessChargeService.dataStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-营收数据分析汇总操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-营收数据分析汇总失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

}
