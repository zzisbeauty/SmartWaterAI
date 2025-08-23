package com.hanwei.process.business.pipemonitoring.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.pipemonitoring.service.IPipeMonitoringService;
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
@RequestMapping("/modelinvoking/pipemonitoring")
public class PipeMonitoringController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IPipeMonitoringService pipeMonitoringService;

	/**
	 * 当前管网压力最高的点？
	 * 当前管网的压力情况？
	 * 当前管网压力最低的点？
	 * 管网压力现在是什么情况？
	 * 当前管网压力最高和最低的点是哪个
	 * @param queryType
	 * @return
	 */
	@AutoLog(value = "模型调用-管网压力情况")
	@GetMapping(value = "/getTopMonitor")
	public Result<?> getTopMonitor(@RequestParam(name="queryType" ,required = false) String queryType) {

		try {
			log.info("模型调用-管网压力情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.getTopMonitor(queryType);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网压力情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网压力情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * {名称}本月压力情况？
	 * 我想看下{名称}2024年10月1日至2024年10月29日的压力
	 * @param monitorName
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@AutoLog(value = "模型调用-本月压力情况")
	@GetMapping(value = "/getHistoryDataByMonitor")
	public Result<?> getHistoryDataByMonitor(@RequestParam(name="monitorName" ,required = false) String monitorName,
											 @RequestParam(name="startTime" ,required = false) String startTime,
									  @RequestParam(name="endTime" ,required = false) String endTime) {

		try {
			log.info("模型调用-本月压力情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.getHistoryDataByMonitor(monitorName,startTime, endTime);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-本月压力情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-本月压力情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 现在已安装多少监测设备？
	 * 目前监测设备建设情况？
	 * 监测设备运行情况？
	 * @return
	 */
	@AutoLog(value = "模型调用-监测设备运行情况")
	@GetMapping(value = "/pipeMonitorDeviceStatistics")
	public Result<?> pipeMonitorDeviceStatistics() {

		try {
			log.info("模型调用-监测设备运行情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.pipeMonitorDeviceStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-监测设备运行情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-监测设备运行情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


	/**
	 * 目前有多少压力监测设备？
	 * 压力监测设备情况？
	 */
	@AutoLog(value = "模型调用-压力监测设备情况")
	@GetMapping(value = "/getPressureMonitorCount")
	public Result<?> getPressureMonitorCount() {

		try {
			log.info("模型调用-压力监测设备情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.getPressureMonitorCount();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-压力监测设备情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-压力监测设备情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 目前有多少流量监测设备？
	 * 流量监测设备情况？
	 */
	@AutoLog(value = "模型调用-流量监测设备情况")
	@GetMapping(value = "/getFlowMonitorCount")
	public Result<?> getFlowMonitorCount() {

		try {
			log.info("模型调用-流量监测设备情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.getFlowMonitorCount();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-流量监测设备情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-流量监测设备情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 目前有多少报警点？
	 * 目前管网运行报警情况？
	 * 目前管网运行有什么问题？
	 */
	@AutoLog(value = "模型调用-管网运行报警情况")
	@GetMapping(value = "/getAlarmMonitor")
	public Result<?> getAlarmMonitor() {

		try {
			log.info("模型调用-管网运行报警情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.getAlarmMonitor();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网运行报警情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网运行报警情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 目前管网运行情况？
	 * 管网现在是什么情况？
	 */
	@AutoLog(value = "模型调用-管网运行情况")
	@GetMapping(value = "/pipeStatistics")
	public Result<?> pipeStatistics() {

		try {
			log.info("模型调用-管网运行情况统计成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.pipeStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网运行情况统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网运行情况统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 压力监测-GIS总览
	 */
	@AutoLog(value = "模型调用-压力监测-GIS总览")
	@GetMapping(value = "/gisStatisticsByPressure")
	public Result<?> gisStatisticsByPressure() {

		try {
			log.info("模型调用-压力监测-GIS总览成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.gisStatisticsByPressure();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-压力监测-GIS总览操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-压力监测-GIS总览失败",e.getMessage());
			return Result.error("操作失败");
		}
	}
	/**
	 * 管网流量监测一张图
	 * 管网流量GIS一张图
	 */
	@AutoLog(value = "模型调用-管网流量监测一张图")
	@GetMapping(value = "/gisStatisticsByFlow")
	public Result<?> gisStatisticsByFlow() {

		try {
			log.info("模型调用-管网流量监测一张图成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.gisStatisticsByFlow();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网流量监测一张图操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网流量监测一张图失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 管网水质监测一张图
	 * 管网水质GIS一张图
	 */
	@AutoLog(value = "模型调用-管网水质监测一张图")
	@GetMapping(value = "/gisStatisticsByQuality")
	public Result<?> gisStatisticsByQuality() {

		try {
			log.info("模型调用-管网水质监测一张图成功");
			ResultForFrontVo resultForFrontVo = pipeMonitoringService.gisStatisticsByQuality();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网水质监测一张图操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网水质监测一张图失败",e.getMessage());
			return Result.error("操作失败");
		}
	}


}
