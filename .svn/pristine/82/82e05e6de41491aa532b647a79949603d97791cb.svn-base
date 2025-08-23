package com.hanwei.digitalhuman.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import com.hanwei.digitalhuman.entity.ServiceInfo;
import com.hanwei.digitalhuman.mapper.ServiceInfoMapper;
import com.hanwei.digitalhuman.service.IServiceConfigService;
import com.hanwei.digitalhuman.service.IServiceInfoService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Description: 数字人服务信息
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Service
@Slf4j
public class ServiceInfoServiceImpl extends ServiceImpl<ServiceInfoMapper, ServiceInfo> implements IServiceInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    IServiceConfigService serviceConfigService;


    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param serviceInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, ServiceInfo serviceInfo) {
        QueryWrapper<ServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(serviceInfo, paramMap);
        List<ServiceInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, ServiceInfo.class).sheet("数字人服务信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param serviceInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, ServiceInfo serviceInfo) {
        QueryWrapper<ServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(serviceInfo, paramMap);
        List<ServiceInfo> list = list(queryWrapper);
        //字典值转换
        List<JSON> listJson = commonApi.translateResultByDict(list);
        List<ServiceInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,ServiceInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ServiceInfo.class).sheet("数字人服务信息").doWrite(result);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param file
     */
    @Override
    public Result<?> importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), ServiceInfo.class, new ReadListener<ServiceInfo>() {

                private List<ServiceInfo> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(ServiceInfo data, AnalysisContext context) {
                    //---------------处理字典转义
//                    String text = commonApi.translateDictTextToKey("risk_place_type",data.getRiskType());
//                    if(StringUtils.isEmpty(text)){
//                        throw new ImportException("数据第" + context.readRowHolder().getRowIndex()+ "行存在未配置得字典类型，数据类型:" + data.getRiskType());
//                    }
//                    data.setRiskType(text);
                    //---------------处理字典转义
                    updateStatus(data);
                    cachedDataList.add(data);
                    if (cachedDataList.size() >= BATCH_SAVE_COUNT) {
                        saveData();
                        // 存储完成清理 list
                        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_SAVE_COUNT);
                    }
                }

                /**
                 * 所有数据解析完成后才会调用
                 */
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    saveData();
                }

                /**
                 * 加上存储数据库
                 */
                private void saveData() {
                    saveBatch(cachedDataList);
                }

            }).sheet().doRead();
        } catch (Exception e) {
            return Result.error("导入失败", e.getMessage());
        }
        return Result.OK("导入成功");
    }

    /**
     * 下载导入模板
     * @return
     */
    @Override
    public String getImportTemplate() {
        String excelContent = null;
        try {
            // 模版文件
            ClassPathResource classPathResource = new ClassPathResource("template/ServiceInfo.xlsx");
            // 方式一：路径
            String templateFileName = classPathResource.getFile().getPath();

            if (StringUtils.isNotBlank(templateFileName)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(templateFileName).excelType(ExcelTypeEnum.XLSX).autoCloseStream(Boolean.FALSE).build();
                excelWriter.finish();
                excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            }
            return excelContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelContent;
    }

    /**
     * 定时处理状态变更 一天一次 距离过期7天内改为临期状态 过期后改为已过期状态
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void untilStatusHandle() {
        //获取所有服务信息
        try {
            List<ServiceInfo> serviceInfos = list();
            for (ServiceInfo serviceInfo : serviceInfos) {
                updateStatus(serviceInfo);
                updateById(serviceInfo);
            }
            log.info("数字人定时处理状态变更成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("数字人定时处理状态变更失败",e);
        }
    }

    /**
     * 距离过期7天内改为临期状态 过期后改为已过期状态
     */
    private void updateStatus(ServiceInfo serviceInfo) {
        //判断服务信息是否过期
        if (serviceInfo.getExpirationTime().getTime() < System.currentTimeMillis()) {
            //如果已过期，则改为已过期状态
            serviceInfo.setStatus(CommonConstant.UNTILS_STATUS_EXPIRED);
        } else if (serviceInfo.getExpirationTime().getTime() - 7 * 24 * 60 * 60 * 1000 < System.currentTimeMillis()) {
            //如果距离过期时间小于7天，则改为临期状态
            serviceInfo.setStatus(CommonConstant.UNTILS_STATUS_ADVENT);
        }else{
            //如果未过期，则改为正常状态
            serviceInfo.setStatus(CommonConstant.UNTILS_STATUS_NORMAL);
        }
    }


    /**
     * 保存服务信息
     * @param entity
     * @return
     */
    @Override
    public boolean save(ServiceInfo entity) {
        updateStatus(entity);
        return super.save(entity);
    }

    /**
     * 删除服务信息,如果还有关联实例则不允许删除
     * @param id
     * @return
     */
    @Override
    public Result removeServiceInfoById(String id) {
        List<ServiceConfig> serviceConfigList = serviceConfigService.getByServiceInfoId(id);
        if (null!=serviceConfigList && serviceConfigList.size() > 0) {
            return Result.error(200,"该服务信息还有关联实例，不允许删除");
        }
        removeById(id);
        return Result.ok("删除成功");
    }

    /**
     * 更新服务信息
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(ServiceInfo entity) {

        if(null == entity.getExpirationTime()){
            ServiceInfo oldServiceInfo = getById(entity.getId());
            entity.setExpirationTime(oldServiceInfo.getExpirationTime());
        }
        updateStatus(entity);
        return super.updateById(entity);
    }
}
