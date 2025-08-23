package com.hanwei.process.business.pipepolling.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.pipepolling.service.IPipePollingService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.service.IModelInvokingService;
import com.hanwei.process.vo.ResultForFrontVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 模型调用信息控制器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/modelinvoking/pipepolling")
public class PipePollingController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IPipePollingService pipePollingService;


	/**
	 * 管网巡检数据分析汇总
	 * 管网巡检数据总览
	 * 管网巡检用户画像
	 * @return
	 */
	@AutoLog(value = "模型调用-管网巡检数据分析汇总")
	@GetMapping(value = "/dataStatistics")
	public Result<?> dataStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-管网巡检数据分析汇总");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = pipePollingService.dataStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage(logBuffer.append("操作成功").toString());
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(logBuffer.append("失败").toString(),e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 巡检监控一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-巡检监控一张图")
	@GetMapping(value = "/pollingMonitorStatistics")
	public Result<?> pollingMonitorStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-巡检监控一张图");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = pipePollingService.pollingMonitorStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage(logBuffer.append("操作成功").toString());
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(logBuffer.append("失败").toString(),e.getMessage());
			return Result.error("操作失败");
		}
	}
}
