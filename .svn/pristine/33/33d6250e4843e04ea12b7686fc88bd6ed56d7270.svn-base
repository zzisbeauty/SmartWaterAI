package com.hanwei.process.business.gis.controller;


import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.process.business.gis.service.IGisService;
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
@RequestMapping("/modelinvoking/gis")
public class GisController extends BaseController<ModelInvokingInfo, IModelInvokingService> {

	@Resource
	private IGisService gisService;

	/**
	 * 查询管网整体概况
	 * 当前各类管网长度情况统计
	 * 目前各类管网有多长？
	 * 管网长度是多少？
	 * 各类型管网铺设情况？
	 * @param queryType
	 * @return
	 */
	@AutoLog(value = "模型调用-查询管网整体概况")
	@GetMapping(value = "/pipeStatistics")
	public Result<?> pipeStatistics(@RequestParam(name="queryType" ,required = false) String queryType) {

		try {
			log.info("模型调用-查询管网整体概况成功");
			ResultForFrontVo resultForFrontVo = gisService.pipeStatistics(queryType);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-查询管网整体概况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-查询管网整体概况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 按照管径统计管网情况
	 * 按管径统计管网长度
	 * 目前管网都有什么口径的？
	 * 100的管网有多少？
	 * @param diameter
	 * @return
	 */
	@AutoLog(value = "模型调用-按照管径统计管网情况")
	@GetMapping(value = "/pipeStatisticsByDiameter")
	public Result<?> pipeStatisticsByDiameter(@RequestParam(name="diameter" ,required = false) String diameter) {

		try {
			log.info("模型调用-按照管径统计管网情况成功");
			ResultForFrontVo resultForFrontVo = gisService.pipeStatisticsByDiameter(diameter);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-按照管径统计管网情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-按照管径统计管网情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 按照材质统计管网情况
	 * 按照材质统计管网长度
	 * 目前管网都有什么材质的？
	 * PE的供水/排水/绿化管网有多少？
	 * @param material
	 * @return
	 */
	@AutoLog(value = "模型调用-按照材质统计管网情况")
	@GetMapping(value = "/pipeStatisticsByMaterial")
	public Result<?> pipeStatisticsByMaterial(@RequestParam(name="material" ,required = false) String material) {

		try {
			log.info("模型调用-按照材质统计管网情况成功");
			ResultForFrontVo resultForFrontVo = gisService.pipeStatisticsByMaterial(material);

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-按照材质统计管网情况操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-按照材质统计管网情况失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 管网设施统计
	 * 按设施类型统计设施数量
	 * 目前有多少设施？
	 * 都有什么类型的设施？
	 * @return
	 */
	@AutoLog(value = "模型调用-管网设施统计")
	@GetMapping(value = "/pipeDeviceStatistics")
	public Result<?> pipeDeviceStatistics() {

		try {
			log.info("模型调用-管网设施统计成功");
			ResultForFrontVo resultForFrontVo = gisService.pipeDeviceStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-管网设施统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-管网设施统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}

	/**
	 * 整体管网统计
	 * @return
	 */
	@AutoLog(value = "模型调用-整体管网统计")
	@GetMapping(value = "/pipeOverAllStatistics")
	public Result<?> pipeOverAllStatistics() {

		try {
			log.info("模型调用-整体管网统计成功");
			ResultForFrontVo resultForFrontVo = gisService.pipeOverAllStatistics();

			//测试
			Result result = new Result();
			result.setResult(resultForFrontVo);
			result.setMessage("模型调用-整体管网统计操作成功");
			result.setCode(CommonConstant.SC_OK_200);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("模型调用-整体管网统计失败",e.getMessage());
			return Result.error("操作失败");
		}
	}
}
