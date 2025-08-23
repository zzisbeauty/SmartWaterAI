package com.hanwei.process.modelinvoking.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.service.IModelInvokingService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @Description: 模型调用信息控制器
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/modelinvoking")
public class ModelInvokingController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Autowired
	private IModelInvokingService modelInvokingService;

	/**
	 * 数字人鉴权接口 获取token
	 * @return
	 */
	@AutoLog(value = "数字人调用-数字人鉴权")
	@GetMapping(value = "/getDigitalHumanToken")
	public Result<?> getDigitalHumanToken() {

		try {
			log.info("数字人调用-数字人鉴权");
			String token = this.modelInvokingService.getDigitalHumanToken();
			return Result.ok(token);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数字人调用-数字人鉴权失败",e.getMessage());
			return Result.error("操作失败");
		}
	}
}
