package com.hanwei.application.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.service.IApiServiceInfoService;
import com.hanwei.application.entity.ApplicationApiRelation;
import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.application.mapper.ApplicationInfoMapper;
import com.hanwei.application.service.IApplicationApiRelationService;
import com.hanwei.application.service.IApplicationConfigService;
import com.hanwei.application.service.IApplicationInfoService;
import com.hanwei.asr.entity.AsrServiceConfig;
import com.hanwei.asr.service.IAsrServiceConfigService;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.dto.FileDTO;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.digitalhuman.entity.ServiceConfig;
import com.hanwei.digitalhuman.service.IServiceConfigService;
import com.hanwei.model.entity.LargeModelInfo;
import com.hanwei.model.service.ILargeModelInfoService;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.service.IRagInfoService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;


/**
 * @Description: 应用基础信息表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApplicationInfoServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements IApplicationInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    private IApplicationConfigService applicationConfigService;

    @Autowired
    private IApplicationApiRelationService applicationApiRelationService;

    @Autowired
    private IServiceConfigService digitalHumanServiceConfigService;

    @Autowired
    private IAsrServiceConfigService asrServiceConfigService;

    @Autowired
    private ILargeModelInfoService largeModelInfoService;

    @Autowired
    private IRagInfoService ragInfoService;

    @Autowired
    private IApiServiceInfoService apiServiceInfoService;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param applicationInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, ApplicationInfo applicationInfo) {
        QueryWrapper<ApplicationInfo> queryWrapper = QueryGenerator.initQueryWrapper(applicationInfo, paramMap);
        List<ApplicationInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, ApplicationInfo.class).sheet("应用基础信息表").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param applicationInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, ApplicationInfo applicationInfo) {
        QueryWrapper<ApplicationInfo> queryWrapper = QueryGenerator.initQueryWrapper(applicationInfo, paramMap);
        List<ApplicationInfo> list = list(queryWrapper);
        //字典值转换
//        List<JSON> listJson = commonApi.translateResultByDict(list);
//        List<ApplicationInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,ApplicationInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ApplicationInfo.class).sheet("应用基础信息表").doWrite(list);
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
            EasyExcel.read(file.getInputStream(), ApplicationInfo.class, new ReadListener<ApplicationInfo>() {

                private List<ApplicationInfo> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(ApplicationInfo data, AnalysisContext context) {
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
            ClassPathResource classPathResource = new ClassPathResource("template/ApplicationInfo.xlsx");
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
     * 通过id查询应用全部信息
     * @param id
     * @return
     */
    @Override
    public Result getApplicationAllInfo(String id) {
        //获取应用基础信息
        ApplicationInfo applicationInfo = getById(id);
        if(Optional.ofNullable(applicationInfo).isEmpty()){
            log.error("未找到相关应用 应用id:" +id);
            return Result.error(200,"未找到相关应用 应用id:" +id);
        }

        ApplicationConfig applicationConfig = applicationConfigService.getByApplicationId(id);

        if(Optional.ofNullable(applicationConfig).isEmpty()){
            log.error("未找到相关应用配置 应用id:" +id);
            return Result.error(200,"未找到相关应用配置 应用id:" +id);
        }

        //获取应用数字人相关配置信息
        String digitalHumanId = applicationConfig.getDigitalHumanConfigId();
        if(StrUtil.isNotEmpty(digitalHumanId)){
            ServiceConfig serviceConfig = digitalHumanServiceConfigService.getById(digitalHumanId);
            List<ServiceConfig> serviceConfigs =new ArrayList<>();
            serviceConfigs.add(serviceConfig);
            applicationInfo.setDigitalHumanServiceConfig(serviceConfigs);
        }

        //获取应用ASR相关配置信息
        String asrId = applicationConfig.getAsrConfigId();
        if(StrUtil.isNotEmpty(asrId)){
            AsrServiceConfig asrServiceConfig = asrServiceConfigService.getById(asrId);
            List<AsrServiceConfig> asrServiceConfigs =new ArrayList<>();
            asrServiceConfigs.add(asrServiceConfig);
            applicationInfo.setAsrServiceConfig(asrServiceConfigs);
        }

        //获取应用模型相关配置信息
        String modelId = applicationConfig.getModelId();
        if(StrUtil.isNotEmpty(modelId)){
            LargeModelInfo largeModelInfo = largeModelInfoService.getById(modelId);
            List<LargeModelInfo> largeModelInfos =new ArrayList<>();
            largeModelInfos.add(largeModelInfo);
            applicationInfo.setLargeModelInfo(largeModelInfos);
        }

        //获取应用知识库相关配置信息
        String ragIds = applicationConfig.getRagIds();
        if(StrUtil.isNotEmpty(ragIds)){
            List<RagInfo> ragInfoList = new ArrayList<>();
            String[] ragIdArr = ragIds.split(",");
            for(String ragId : ragIdArr){
                RagInfo ragInfo = ragInfoService.getById(ragId);
                ragInfoList.add(ragInfo);
            }
            applicationInfo.setRagInfoList(ragInfoList);
        }
        //获取应用接口相关配置信息
        List<ApplicationApiRelation> applicationApiRelationList = applicationApiRelationService.listByApplicationId(id);
        if(Optional.ofNullable(applicationApiRelationList).isPresent() && applicationApiRelationList.size() > 0){
            List<ApiServiceInfo> apiInfoList = new ArrayList<>();
            for(ApplicationApiRelation applicationApiRelation : applicationApiRelationList){
                ApiServiceInfo apiServiceInfo = apiServiceInfoService.getById(applicationApiRelation.getApiId());
                apiInfoList.add(apiServiceInfo);
            }
            applicationInfo.setApiInfoList(apiInfoList);
        }


        //字典翻译
        List transList = new ArrayList<>();
        transList.add(applicationInfo.getAsrServiceConfig().get(0));


        List result = commonApi.translateResultByDict(transList);
        applicationInfo.setAsrServiceConfig(result);

        transList = new ArrayList<>();
        transList.add(applicationInfo.getDigitalHumanServiceConfig().get(0));
        result = commonApi.translateResultByDict(transList);
        applicationInfo.setDigitalHumanServiceConfig(result);

        transList = new ArrayList<>();
        transList.addAll(applicationInfo.getApiInfoList());
        result = commonApi.translateResultByDict(transList);
        applicationInfo.setApiInfoList(result);
        return Result.ok(applicationInfo);
    }

    /**
     * 保存应用信息及新增配置信息
     * @param applicationInfo
     * @return
     */
    @Override
    @Transactional
    public Result<?> saveApplicationInfo(ApplicationInfo applicationInfo){
        try {
            commonApi.updateFileRelation(applicationInfo.getId(),applicationInfo.getFileId());
            List<FileDTO> fileDTOS = commonApi.findFileList(applicationInfo.getId());
            if(Optional.ofNullable(fileDTOS).isPresent() && fileDTOS.size() > 0){
                applicationInfo.setIconUrl(fileDTOS.get(0).getFilePath());
            }
            save(applicationInfo);
            ApplicationConfig applicationConfig = applicationConfigService.getByApplicationId(applicationInfo.getId());
            if(Optional.ofNullable(applicationConfig).isEmpty()){
                applicationConfig = new ApplicationConfig();
                applicationConfig.setApplicationId(applicationInfo.getId());
            }

            if(Optional.ofNullable(applicationInfo.getDigitalHumanServiceConfig()).isPresent() || applicationInfo.getDigitalHumanServiceConfig().size() > 0){
                applicationConfig.setDigitalHumanConfigId(applicationInfo.getDigitalHumanServiceConfig().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getAsrServiceConfig()).isPresent() || applicationInfo.getAsrServiceConfig().size() > 0){
                applicationConfig.setAsrConfigId(applicationInfo.getAsrServiceConfig().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getLargeModelInfo()).isPresent() || applicationInfo.getLargeModelInfo().size() > 0){
                applicationConfig.setModelId(applicationInfo.getLargeModelInfo().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getRagInfoList()).isPresent() || applicationInfo.getRagInfoList().size() > 0){
                StringBuilder ragIds = new StringBuilder();
                applicationInfo.getRagInfoList().forEach(ragInfo -> {
                    ragIds.append(ragInfo.getId()).append(",");
                });
                ragIds.delete(ragIds.length() - 1, ragIds.length());
                applicationConfig.setRagIds(ragIds.toString());
            }

            if(Optional.ofNullable(applicationInfo.getApiInfoList()).isPresent() || applicationInfo.getApiInfoList().size() > 0){
                applicationApiRelationService.removeByApplicationId(applicationInfo.getId());
                List<ApplicationApiRelation> applicationApiRelationList = new ArrayList<>();
                applicationInfo.getApiInfoList().forEach(apiInfo -> {
                    ApplicationApiRelation applicationApiRelation = new ApplicationApiRelation();
                    applicationApiRelation.setApplicationId(applicationInfo.getId());
                    applicationApiRelation.setApiId(apiInfo.getId());
                    applicationApiRelationList.add(applicationApiRelation);
                });
                applicationApiRelationService.saveBatch(applicationApiRelationList);
            }

            if(StrUtil.isEmpty(applicationConfig.getId())){
                applicationConfigService.save(applicationConfig);
            }else{
                applicationConfigService.updateById(applicationConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("应用信息保存失败{}",e.getMessage());
            return Result.error("应用信息保存失败{}",e.getMessage());
        }
        return Result.ok("应用信息保存成功");
    }

    /**
     * 删除应用信息及相关配置信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result<?> removeByApplicationId(String id){
        //先删除配置信息后删除应用信息
        try {
            applicationConfigService.removeByApplicationId(id);
            applicationApiRelationService.removeByApplicationId(id);
            removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("应用信息删除失败{}",e.getMessage());
            return Result.error("应用信息删除失败{}",e.getMessage());
        }
        return Result.ok("应用信息删除成功");
    }

    /**
     * 更新应用信息及配置信息
     * @param applicationInfo
     * @return
     */
    @Override
    @Transactional
    public Boolean updateApplicationInfo(ApplicationInfo applicationInfo) {
        try {
            commonApi.updateFileRelation(applicationInfo.getId(),applicationInfo.getFileId());
            List<FileDTO> fileDTOS = commonApi.findFileList(applicationInfo.getId());
            if(Optional.ofNullable(fileDTOS).isPresent() && fileDTOS.size() > 0){
                applicationInfo.setIconUrl(fileDTOS.get(0).getFilePath());
            }
            updateById(applicationInfo);
            ApplicationConfig applicationConfig = applicationConfigService.getByApplicationId(applicationInfo.getId());
            if(Optional.ofNullable(applicationConfig).isEmpty()){
                applicationConfig = new ApplicationConfig();
                applicationConfig.setApplicationId(applicationInfo.getId());
            }
            if(Optional.ofNullable(applicationInfo.getDigitalHumanServiceConfig()).isPresent() || applicationInfo.getDigitalHumanServiceConfig().size() > 0){
                applicationConfig.setDigitalHumanConfigId(applicationInfo.getDigitalHumanServiceConfig().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getAsrServiceConfig()).isPresent() || applicationInfo.getAsrServiceConfig().size() > 0){
                applicationConfig.setAsrConfigId(applicationInfo.getAsrServiceConfig().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getLargeModelInfo()).isPresent() || applicationInfo.getLargeModelInfo().size() > 0){
                applicationConfig.setModelId(applicationInfo.getLargeModelInfo().get(0).getId());
            }

            if(Optional.ofNullable(applicationInfo.getRagInfoList()).isPresent() || applicationInfo.getRagInfoList().size() > 0){
                StringBuilder ragIds = new StringBuilder();
                applicationInfo.getRagInfoList().forEach(ragInfo -> {
                    ragIds.append(ragInfo.getId()).append(",");
                });
                ragIds.delete(ragIds.length() - 1, ragIds.length());
                applicationConfig.setRagIds(ragIds.toString());
            }

            if(Optional.ofNullable(applicationInfo.getApiInfoList()).isPresent() || applicationInfo.getApiInfoList().size() > 0){
                List<ApplicationApiRelation> applicationApiRelationList = new ArrayList<>();
                applicationInfo.getApiInfoList().forEach(apiInfo -> {
                    //判断是否已经关联
                    Boolean isExist = applicationApiRelationService.isExist(applicationInfo.getId(),apiInfo.getId());
                    if(!isExist){
                        ApplicationApiRelation applicationApiRelation = new ApplicationApiRelation();
                        applicationApiRelation.setApplicationId(applicationInfo.getId());
                        applicationApiRelation.setApiId(apiInfo.getId());
                        applicationApiRelationList.add(applicationApiRelation);
                    }
                });
                applicationApiRelationService.saveBatch(applicationApiRelationList);
            }

            if(StrUtil.isEmpty(applicationConfig.getId())){
                applicationConfigService.save(applicationConfig);
            }else{
                applicationConfigService.updateById(applicationConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("应用信息保存失败{}",e.getMessage());
            return false;
        }
        return true;
    }
}
