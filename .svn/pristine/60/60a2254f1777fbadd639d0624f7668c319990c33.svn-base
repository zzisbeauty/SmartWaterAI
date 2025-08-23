package com.hanwei.process.business.smartwaterplant.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.smartwaterplant.service.ISmartWaterPlantService;
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
@RequestMapping("/modelinvoking/smartwaterplant")
public class SmartWaterPlantController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private ISmartWaterPlantService smartWaterPlantService;

	/**
	 * 一水厂整体工艺
	 * 一水厂厂区总览
	 * 一水厂监测一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂整体工艺")
	@GetMapping(value = "/plantStatisticsByFirstWorks")
	public Result<?> plantStatisticsByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂整体工艺");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.plantStatisticsByFirstWorks();

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
	 * 一水厂综合投加间
	 * 一水厂加药间
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂综合投加间")
	@GetMapping(value = "/dosingRoomByFirstWorks")
	public Result<?> dosingRoomByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂综合投加间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.dosingRoomByFirstWorks();

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
	 * 一水厂净水车间
	 * 一水厂净水车间一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂净水车间")
	@GetMapping(value = "/waterPurificationPlantByFirstWorks")
	public Result<?> waterPurificationPlantByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂净水车间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.waterPurificationPlantByFirstWorks();

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
	 * 一水厂调节池
	 * 一水厂排泥排水调节池
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂调节池")
	@GetMapping(value = "/regulatingPondByFirstWorks")
	public Result<?> regulatingPondByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂调节池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.regulatingPondByFirstWorks();

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
	 * 一水厂配水间
	 * 一水厂栅格配水间
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂配水间")
	@GetMapping(value = "/distributingRoomByFirstWorks")
	public Result<?> distributingRoomByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂配水间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.distributingRoomByFirstWorks();

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
	 * 一水厂清水池
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂清水池")
	@GetMapping(value = "/cleanWaterBasinByFirstWorks")
	public Result<?> cleanWaterBasinByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂清水池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.cleanWaterBasinByFirstWorks();

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
	 * 一水厂脱水机房
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂脱水机房")
	@GetMapping(value = "/dehydrationRoomByFirstWorks")
	public Result<?> dehydrationRoomByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂脱水机房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.dehydrationRoomByFirstWorks();

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
	 * 一水厂-自用水泵房
	 * @return
	 */
	@AutoLog(value = "模型调用-一水厂-自用水泵房")
	@GetMapping(value = "/waterPumpHouseByFirstWorks")
	public Result<?> waterPumpHouseByFirstWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-一水厂-自用水泵房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.waterPumpHouseByFirstWorks();

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
	 * 二水厂整体工艺
	 * 二水厂厂区总览
	 * 二水厂监测一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂整体工艺")
	@GetMapping(value = "/plantStatisticsBySecondWorks")
	public Result<?> plantStatisticsBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂整体工艺");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.plantStatisticsBySecondWorks();

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
	 * 二水厂加药间
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂加药间")
	@GetMapping(value = "/dosingRoomBySecondWorks")
	public Result<?> dosingRoomBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂加药间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.dosingRoomBySecondWorks();

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
	 * 二水厂净水车间
	 * 二水厂净水车间一张图
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂净水车间")
	@GetMapping(value = "/waterPurificationPlantBySecondWorks")
	public Result<?> waterPurificationPlantBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂净水车间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.waterPurificationPlantBySecondWorks();

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
	 * 二水厂排污池
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂排污池")
	@GetMapping(value = "/sewagePitBySecondWorks")
	public Result<?> sewagePitBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂排污池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.sewagePitBySecondWorks();

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
	 * 二水厂清水池
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂清水池")
	@GetMapping(value = "/cleanWaterBasinBySecondWorks")
	public Result<?> cleanWaterBasinBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂清水池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.cleanWaterBasinBySecondWorks();

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
	 * 二水厂提升泵房
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂提升泵房")
	@GetMapping(value = "/liftPumpHouseBySecondWorks")
	public Result<?> liftPumpHouseBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂提升泵房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.liftPumpHouseBySecondWorks();

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
	 * 二水厂调蓄水池
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂调蓄水池")
	@GetMapping(value = "/regulatingReservoirBySecondWorks")
	public Result<?> regulatingReservoirBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂调蓄水池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.regulatingReservoirBySecondWorks();

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
	 * 二水厂-增压泵房
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂-增压泵房")
	@GetMapping(value = "/boosterPumpRoomBySecondWorks")
	public Result<?> boosterPumpRoomBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂-增压泵房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.boosterPumpRoomBySecondWorks();

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
	 * 二水厂锅炉房
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂锅炉房")
	@GetMapping(value = "/boilerRoomBySecondWorks")
	public Result<?> boilerRoomBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂锅炉房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.boilerRoomBySecondWorks();

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
	 * 二水厂反冲洗泵房
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂反冲洗泵房")
	@GetMapping(value = "/backwashPumpHouseBySecondWorks")
	public Result<?> backwashPumpHouseBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂反冲洗泵房");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.backwashPumpHouseBySecondWorks();

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
	 * 二水厂加氯间
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂加氯间")
	@GetMapping(value = "/chlorineDosingRoomBySecondWorks")
	public Result<?> chlorineDosingRoomBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂加氯间");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.chlorineDosingRoomBySecondWorks();

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
	 * 二水厂配电柜
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂配电柜")
	@GetMapping(value = "/electricClosetBySecondWorks")
	public Result<?> electricClosetBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂配电柜");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.electricClosetBySecondWorks();

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
	 * 二水厂预沉池
	 * @return
	 */
	@AutoLog(value = "模型调用-二水厂预沉池")
	@GetMapping(value = "/preliminarySedimentationTankBySecondWorks")
	public Result<?> preliminarySedimentationTankBySecondWorks() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("模型调用-二水厂预沉池");
		try {
			log.info(logBuffer.append("成功").toString());
			ResultForFrontVo resultForFrontVo = smartWaterPlantService.preliminarySedimentationTankBySecondWorks();

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
