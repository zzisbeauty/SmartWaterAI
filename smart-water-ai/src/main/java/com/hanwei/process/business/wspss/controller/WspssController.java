package com.hanwei.process.business.wspss.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.wspss.service.IWspssService;
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
@RequestMapping("/modelinvoking/wspss")
public class WspssController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IWspssService wspssService;


	/**
	 * 稳压系统用户画像
	 * 调流调压数据分析汇总
	 * 调流调压数据画像
	 * @return
	 */
	@AutoLog(value = "模型调用-稳压系统用户画像")
	@GetMapping(value = "/monitoringStatistics")
	public Result<?> monitoringStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-稳压系统用户画像");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = wspssService.monitoringStatistics();

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
	 * 调流调压GIS总览
	 * 调流调压GIS一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-调流调压GIS总览")
	@GetMapping(value = "/gisStatistics")
	public Result<?> gisStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-调流调压GIS总览");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = wspssService.gisStatistics();

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
	 * 稳压系统工艺
	 * 调流调压工艺
	 * @return
	 */
	@AutoLog(value = "模型调用-稳压系统工艺")
	@GetMapping(value = "/scadaStatistics")
	public Result<?> scadaStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-稳压系统工艺");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = wspssService.scadaStatistics();

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
