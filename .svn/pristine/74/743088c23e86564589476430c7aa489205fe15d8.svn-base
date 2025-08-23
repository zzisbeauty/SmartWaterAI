package com.hanwei.process.business.watersource.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.watersource.service.IWaterSourceService;
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
@RequestMapping("/modelinvoking/watersource")
public class WaterSourceController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IWaterSourceService waterSourceService;

	/**
	 * 水源井数据分析统计
	 * 水源井用户画像
	 * @return
	 */
	@AutoLog(value = "模型调用-水源井数据分析统计")
	@GetMapping(value = "/dataStatistics")
	public Result<?> dataStatistics() {

		try {
			log.info("模型调用-水源井数据分析统计成功");
			ResultForFrontVo resultForFrontVo = waterSourceService.dataStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-水源井数据分析统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-水源井数据分析统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 水源井一张图
	 * 水源井GIS一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-水源井一张图")
	@GetMapping(value = "/gisStatistics")
	public Result<?> gisStatistics() {

		try {
			log.info("模型调用-水源井一张图成功");
			ResultForFrontVo resultForFrontVo = waterSourceService.gisStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-水源井一张图操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-水源井一张图失败",e.getMessage());
			return Result.error("操作失败");
		}
	}
}
