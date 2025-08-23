package com.hanwei.system.controller;

import java.util.Arrays;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.system.entity.Dict;
import com.hanwei.system.entity.ExternalServiceConfig;
import com.hanwei.system.service.IExternalServiceConfigService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.system.vo.ServiceConfigVO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



 /**
 * @Description: 外部服务配置表(配置基本不变并且难以获取的外部服务数据)
 * @Author: hanwei
 * @Date:   2025-07-16
 * @Version: V1.0
 */
@Slf4j
@Tag(name="外部服务配置表(配置基本不变并且难以获取的外部服务数据)")
@RestController
@RequestMapping("/system/externalServiceConfig")
public class ExternalServiceConfigController extends BaseController<ExternalServiceConfig, IExternalServiceConfigService> {
	@Autowired
	private IExternalServiceConfigService externalServiceConfigService;

	 /**
	  * 根据服务id获取相关服务配置
	  *
	  * @param serviceId
	  * @return
	  */
	 @Operation(summary="外部服务配置表-根据服务id获取相关服务配置")
	 @RequestMapping(value = "/getByServiceId", method = {RequestMethod.GET})
	 public Result<?> getByServiceId(@RequestParam(name="serviceId", defaultValue="1")String serviceId) {
		 List<ServiceConfigVO> pageList = externalServiceConfigService.getByServiceId(serviceId);
		 return Result.OK(pageList);
	 }



	/**
	 * 分页列表查询
	 *
	 * @param externalServiceConfig
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-分页列表查询")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-分页列表查询")
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public Result<?> queryPageList(ExternalServiceConfig externalServiceConfig,
								   @RequestParam(name="pageNo", defaultValue="1")
								    @ApiParameter(name = "pageNo", description = "页码", required = true, demovalue = "1",
                                                                              location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "1")
								   Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10")
								   @ApiParameter(name = "pageSize", description = "每页数量", required = true, demovalue = "1",
                                                                              location = ApiEnum.PARAMETER_LOCATION_QUERY, defaultvalue = "10")
								   Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ExternalServiceConfig> queryWrapper = QueryGenerator.initQueryWrapper(externalServiceConfig, req.getParameterMap());
		Page<ExternalServiceConfig> page = new Page<ExternalServiceConfig>(pageNo, pageSize);
		IPage<ExternalServiceConfig> pageList = externalServiceConfigService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param externalServiceConfig
	 * @return
	 */
	@AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-添加")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@Valid @RequestBody ExternalServiceConfig externalServiceConfig) {
	try{
            Boolean flag = externalServiceConfigService.save(externalServiceConfig);
            if(flag){
                return Result.OK("添加成功！");
            }else{
                return Result.error(200,"添加失败！");
            }
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return Result.error(200,e.getMessage());
		}

	}

	/**
	 * 编辑
	 *
	 * @param externalServiceConfig
	 * @return
	 */
	@AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-编辑")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody ExternalServiceConfig externalServiceConfig) {
		Boolean flag = externalServiceConfigService.updateById(externalServiceConfig);
		if(flag){
            return Result.OK("编辑成功！");
        }else{
            return Result.error(200,"编辑失败！");
        }
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
    @AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-通过id删除")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true)
	                        @ApiParameter(name = "id", description = "接口id", required = true,
                                location = ApiEnum.PARAMETER_LOCATION_QUERY)
                            String id) {
		Boolean flag = externalServiceConfigService.removeById(id);
		if(flag){
            return Result.OK("删除成功！");
        }else{
            return Result.error(200,"删除失败！");
        }
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-批量删除")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true)
                                 @ApiParameter(name = "ids", description = "接口ids", required = true,
                                                                location = ApiEnum.PARAMETER_LOCATION_QUERY)
                                 String ids) {
		Boolean flag = this.externalServiceConfigService.removeByIds(Arrays.asList(ids.split(",")));
		if(flag){
            return Result.OK("批量删除成功！");
        }else{
            return Result.error(200,"批量删除失败！");
        }
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "外部服务配置表(配置基本不变并且难以获取的外部服务数据)-通过id查询")
	@Operation(summary="外部服务配置表(配置基本不变并且难以获取的外部服务数据)-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true)
                                @ApiParameter(name = "id", description = "接口id", required = true,
                                                                location = ApiEnum.PARAMETER_LOCATION_QUERY)
                                String id) {
		ExternalServiceConfig externalServiceConfig = externalServiceConfigService.getById(id);
		return Result.OK(externalServiceConfig);
	}
}
