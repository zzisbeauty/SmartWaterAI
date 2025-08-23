package com.hanwei.process.business.dma.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.dma.service.IDmaService;
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
@RequestMapping("/modelinvoking/dma")
public class DmaController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IDmaService dmaService;


	/**
	 * 漏损一张图
	 * 漏损GIS总览
	 * 漏损总览
	 * @return
	 */
	@AutoLog(value = "模型调用-漏损一张图")
	@GetMapping(value = "/gisStatistics")
	public Result<?> gisStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-漏损一张图");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = dmaService.gisStatistics();

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
	 * 漏损分析汇总
	 * 漏损分析报表
	 * @return
	 */
	@AutoLog(value = "模型调用-漏损分析汇总")
	@GetMapping(value = "/leakageAnalysisStatistics")
	public Result<?> leakageAnalysisStatistics() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-漏损分析汇总");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = dmaService.leakageAnalysisStatistics();

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
