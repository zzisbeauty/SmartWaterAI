package com.hanwei.digitalhuman.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.application.mapper.ApplicationInfoMapper;
import com.hanwei.application.service.IApplicationConfigService;
import com.hanwei.application.service.IApplicationInfoService;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import com.hanwei.digitalhuman.mapper.ServiceConfigMapper;
import com.hanwei.digitalhuman.service.IServiceConfigService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description: 数字人配置实例
 * @Author: hanwei
 * @Date:   2025-05-09
 * @Version: V1.0
 */
@Service
public class ServiceConfigServiceImpl extends ServiceImpl<ServiceConfigMapper, ServiceConfig> implements IServiceConfigService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    private IApplicationConfigService applicationConfigService;

    @Autowired
    private ApplicationInfoMapper applicationInfoMapper;

    /**
     * 根据服务信息ID获取数字人配置实例
     * @param id
     * @return
     */
    @Override
    public List<ServiceConfig> getByServiceInfoId(String id) {
        List<ServiceConfig> serviceConfigs = this.list(new LambdaQueryWrapper<ServiceConfig>().eq(ServiceConfig::getServiceId, id));
        return serviceConfigs;
    }

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param serviceConfig
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, ServiceConfig serviceConfig) {
        QueryWrapper<ServiceConfig> queryWrapper = QueryGenerator.initQueryWrapper(serviceConfig, paramMap);
        List<ServiceConfig> list = list(queryWrapper);
        EasyExcel.write(outputStream, ServiceConfig.class).sheet("数字人实例配置信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param serviceConfig
     */
    @Override
    public String exportDataToBase64(Map paramMap, ServiceConfig serviceConfig) {
        QueryWrapper<ServiceConfig> queryWrapper = QueryGenerator.initQueryWrapper(serviceConfig, paramMap);
        List<ServiceConfig> list = list(queryWrapper);
        //字典值转换
        List<JSON> listJson = commonApi.translateResultByDict(list);
        List<ServiceConfig> result = listJson.stream().map(e -> JSON.toJavaObject(e,ServiceConfig.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ServiceConfig.class).sheet("数字人实例配置信息").doWrite(result);
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
            EasyExcel.read(file.getInputStream(), ServiceConfig.class, new ReadListener<ServiceConfig>() {

                private List<ServiceConfig> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(ServiceConfig data, AnalysisContext context) {
                    //---------------处理字典转义
//                    String text = commonApi.translateDictTextToKey("risk_place_type",data.getRiskType());
//                    if(StringUtils.isEmpty(text)){
//                        throw new ImportException("数据第" + context.readRowHolder().getRowIndex()+ "行存在未配置得字典类型，数据类型:" + data.getRiskType());
//                    }
//                    data.setRiskType(text);
                    //---------------处理字典转义
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
            ClassPathResource classPathResource = new ClassPathResource("template/ServiceConfig.xlsx");
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
     * 删除数字人配置信息
     * @param id
     * @return
     */
    @Override
    public Result<?> removeConfigById(String id){
        //应用是否使用校验
        List<ApplicationConfig> applicationConfigList = applicationConfigService.list(new LambdaQueryWrapper<ApplicationConfig>()
                .eq(ApplicationConfig::getDigitalHumanConfigId,id));
        if(Optional.ofNullable(applicationConfigList).isPresent() && applicationConfigList.size() > 0){
            StringBuilder sb = new StringBuilder();
            sb.append("当前配置正在被应用使用,不允许删除，请先更改应用。使用得应用有:");
            for(ApplicationConfig applicationConfig : applicationConfigList){
                ApplicationInfo applicationInfo = applicationInfoMapper.selectById(applicationConfig.getApplicationId());
                sb.append(applicationInfo.getApplicationName()).append(" ");
            }
            return Result.error(200,sb.toString());
        }

        removeById(id);
        return Result.OK("删除成功");
    }

    /**
     * 启停状态切换
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean switchStatus(String id,Integer status) {
        ServiceConfig serviceConfig = getById(id);
        if(null == serviceConfig){
            return false;
        }
        serviceConfig.setStatus(status);
        updateById(serviceConfig);
        return true;
    }

    /**
     * 保存数字人配置信息
     * @param serviceConfig
     * @return
     */
    @Override
    public Boolean saveDigitalHumanConfig(ServiceConfig serviceConfig) {
        //更新文件服务器
        try {
            save(serviceConfig);
//            commonApi.updateFileRelation(serviceConfig.getId(),serviceConfig.getImageId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 更新数字人配置信息
     * @param serviceConfig
     * @return
     */
    @Override
    public Boolean updateDigitalHumanConfig(ServiceConfig serviceConfig) {
        try {
            updateById(serviceConfig);
//            commonApi.updateFileRelation(serviceConfig.getId(),serviceConfig.getImageId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
