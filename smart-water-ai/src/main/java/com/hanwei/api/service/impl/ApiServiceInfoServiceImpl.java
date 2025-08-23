package com.hanwei.api.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hanwei.api.entity.*;
import com.hanwei.api.mapper.ApiQuestionConfigMapper;
import com.hanwei.api.mapper.ApiServiceInfoMapper;
import com.hanwei.api.service.*;
import com.hanwei.api.vo.ApiRelationConfigVO;
import com.hanwei.application.entity.ApplicationApiRelation;
import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.application.mapper.ApplicationInfoMapper;
import com.hanwei.application.service.IApplicationApiRelationService;
import com.hanwei.application.service.IApplicationInfoService;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.style.entity.StyleInfo;
import com.hanwei.style.service.IStyleInfoService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description: api基本信息
 * @Author: hanwei
 * @Date:   2025-05-15
 * @Version: V1.0
 */
@Service
@Slf4j
public class ApiServiceInfoServiceImpl extends ServiceImpl<ApiServiceInfoMapper, ApiServiceInfo> implements IApiServiceInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    private IStyleInfoService styleInfoService;

    @Autowired
    private IApiStyleRelationService apiStyleRelationService;


    @Autowired
    private IApiParamConfigService apiParamConfigService;

    @Autowired
    private IApiQuestionConfigService apiQuestionConfigService;

    @Autowired
    private IApiRelationConfigService apiRelationConfigService;

    @Autowired
    private IApplicationApiRelationService applicationApiRelationService;

    @Autowired
    private ApiQuestionConfigMapper apiQuestionConfigMapper;

    @Autowired
    private ApplicationInfoMapper applicationInfoMapper;


    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param apiServiceInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, ApiServiceInfo apiServiceInfo) {
        QueryWrapper<ApiServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(apiServiceInfo, paramMap);
        List<ApiServiceInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, ApiServiceInfo.class).sheet("api基本信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param apiServiceInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, ApiServiceInfo apiServiceInfo) {
        QueryWrapper<ApiServiceInfo> queryWrapper = QueryGenerator.initQueryWrapper(apiServiceInfo, paramMap);
        List<ApiServiceInfo> list = list(queryWrapper);
        //字典值转换
//        List<JSON> listJson = commonApi.translateResultByDict(list);
//        List<ApiServiceInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,ApiServiceInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ApiServiceInfo.class).sheet("api基本信息").doWrite(list);
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
            EasyExcel.read(file.getInputStream(), ApiServiceInfo.class, new ReadListener<ApiServiceInfo>() {

                private List<ApiServiceInfo> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(ApiServiceInfo data, AnalysisContext context) {
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
            ClassPathResource classPathResource = new ClassPathResource("template/ApiServiceInfo.xlsx");
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
     * 根据apiId获取样式信息
     * @param id
     * @return
     */
    @Override
    public StyleInfo getStyleInfoByApiId(String id) {
        String styleId = apiStyleRelationService.getStyleIdByApiId(id);
        if (StringUtils.isNotBlank(styleId)) {
            return styleInfoService.getById(styleId);
        }
        return null;
    }

    /**
     * 保存api基本信息以及样式问题等各类信息
     * @param apiServiceInfo
     * @return
     */
    @Override
    @Transactional
    public Result<?> saveApiServiceAllInfo(ApiServiceInfo apiServiceInfo) {
        try {
            //保存基本信息
            if(Optional.ofNullable(apiServiceInfo).isPresent()){
                save(apiServiceInfo);
            }
            String apiId = apiServiceInfo.getId();

            //保存参数信息
            if(Optional.ofNullable(apiServiceInfo.getApiParamConfigList()).isPresent()){
                for (ApiParamConfig apiParamConfig : apiServiceInfo.getApiParamConfigList()) {
                    //设置apiId
                    apiParamConfig.setApiId(apiId);
                    apiParamConfigService.saveApiParamConfig(apiParamConfig);
                }
            }

            //保存提问词信息
            if(Optional.ofNullable(apiServiceInfo.getApiQuestionConfigList()).isPresent()){
                for (ApiQuestionConfig apiQuestionConfig : apiServiceInfo.getApiQuestionConfigList()) {
                    //设置apiId
                    apiQuestionConfig.setApiId(apiId);
                    apiQuestionConfigService.saveApiQuestionConfig(apiQuestionConfig);
                }
            }

            //保存关联问题信息
            if(Optional.ofNullable(apiServiceInfo.getApiRelationConfigList()).isPresent()){
                for (ApiRelationConfig apiRelationConfig : apiServiceInfo.getApiRelationConfigList()) {
                    //设置apiId
                    apiRelationConfig.setApiId(apiId);
                    apiRelationConfigService.saveApiRelationConfig(apiRelationConfig);
                }
            }

            //保存样式信息
            if(Optional.ofNullable(apiServiceInfo.getApiStyleRelationList()).isPresent()){
                List<ApiStyleRelation> apiStyleRelationList = apiServiceInfo.getApiStyleRelationList();
                apiStyleRelationList.forEach(apiStyleRelation->{
                    apiStyleRelation.setApiId(apiId);
                    apiStyleRelationService.save(apiStyleRelation);
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存api基本信息以及样式问题等各类信息失败 {}",e.getMessage());
            return Result.error("保存失败 " +e.getMessage());
        }

        return Result.OK("保存成功");
    }

    /**
     * 补充api基本信息
     * @param records
     * @return
     */
//    @Override
//    public List<ApiServiceInfo> completeApiServiceInfo(List<ApiServiceInfo> records) {
//
//        records.forEach(item->{
//            //填充参数信息
//            List<ApiParamConfig> apiParamConfigList = apiParamConfigService.getParamByApiId(item.getId());
//            if(Optional.ofNullable(apiParamConfigList).isPresent()){
//                apiParamConfigList = new ArrayList<>();
//            }
//            item.setApiParamConfigList(apiParamConfigList);
//
//            //填充提问语信息
//            List<ApiQuestionConfig> apiQuestionConfigList = apiQuestionConfigService.getQuestionByApiId(item.getId());
//            if(Optional.ofNullable(apiQuestionConfigList).isPresent()){
//                apiQuestionConfigList = new ArrayList<>();
//            }
//            item.setApiQuestionConfigList(apiQuestionConfigList);
//
//
//            //填充关联问题信息
//            List<ApiRelationConfig> apiRelationConfigList = apiRelationConfigService.getRelationByApiId(item.getId());
//            if(Optional.ofNullable(apiRelationConfigList).isPresent()){
//                apiRelationConfigList = new ArrayList<>();
//            }
//            item.setApiRelationConfigList(apiRelationConfigList);
//
//            //填充结果样式信息
//            List<ApiStyleRelation> list = new ArrayList<>();
//            List<ApiStyleRelation> apiStyleRelationList = item.getApiStyleRelationList();
//            for (ApiStyleRelation apiStyleRelation : apiStyleRelationList) {
//                ApiStyleRelation apiStyleRelationItem = apiStyleRelationService.getByApiId(item.getId());
//                list.add(apiStyleRelationItem);
//            }
//
//
//            item.setApiStyleRelationList(list);
//        });
//
//        return records;
//    }

    /**
     * 删除api基本信息
     * @param apiServiceInfoId
     * @return
     */
    @Override
    @Transactional
    public Result<?> removeApiServiceInfo(String apiServiceInfoId) {
        if(StrUtil.isEmpty(apiServiceInfoId)){
            return Result.error(200,"删除失败，不存在此ID");
        }

        //应用是否使用校验
        List<ApplicationApiRelation> applicationApiRelationList =
                applicationApiRelationService.list(new LambdaQueryWrapper<ApplicationApiRelation>()
                .eq(ApplicationApiRelation::getApiId,apiServiceInfoId));
        if(Optional.ofNullable(applicationApiRelationList).isPresent() && applicationApiRelationList.size()>0){
            StringBuilder sb = new StringBuilder();
            sb.append("当前配置正在被应用使用,不允许删除，请先更改应用。使用得应用有:");
            for(ApplicationApiRelation applicationApiRelation : applicationApiRelationList){
                ApplicationInfo applicationInfo = applicationInfoMapper.selectById(applicationApiRelation.getApplicationId());
                sb.append(applicationInfo.getApplicationName()).append(" ");
            }
            return Result.error(200,sb.toString());
        }




        try {
            //删除结果样式信息
            apiStyleRelationService.removeByApiId(apiServiceInfoId);

            //删除关联问题信息
            apiRelationConfigService.removeByApiId(apiServiceInfoId);

            //删除提问语信息
            apiQuestionConfigService.removeByApiId(apiServiceInfoId);

            //删除参数信息
            apiParamConfigService.removeByApiId(apiServiceInfoId);

            //删除API基本信息
            removeById(apiServiceInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除api信息失败 {}",e.getMessage());
            return Result.error("删除api信息失败 {}",e.getMessage());
        }


        return Result.OK("删除成功");
    }

    /**
     * 批量删除api基本信息 假批量，没有太大性能要求，直接循环删除即可
     * @param asList
     * @return
     */
    @Override
    public Boolean removeApiServiceInfoIdList(List<String> asList) {
        try {
            for (String apiServiceInfoId : asList) {
                removeApiServiceInfo(apiServiceInfoId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("批量删除api信息失败 {}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 更新api基本信息 基本信息，样式进行更新操作，其余信息进行删除重建操作
     * @param apiServiceInfo
     * @return
     */
    @Override
    @Transactional
    public Result<?> updateApiServiceInfo(ApiServiceInfo apiServiceInfo) {

        if(StrUtil.isEmpty(apiServiceInfo.getId())){
            return Result.error(200,"更新api信息失败");
        }

        try {
            //更新结果样式信息
            apiStyleRelationService.updateApiStyle(apiServiceInfo.getId(),apiServiceInfo.getApiStyleRelationList());

            //更新关联问题信息
            apiRelationConfigService.updateApiRelationConfig(apiServiceInfo.getId(),apiServiceInfo.getApiRelationConfigList());

            //更新提问语信息
            apiQuestionConfigService.updateApiQuestionConfig(apiServiceInfo.getId(),apiServiceInfo.getApiQuestionConfigList());

            //更新参数信息
            apiParamConfigService.updateApiParamConfig(apiServiceInfo.getId(),apiServiceInfo.getApiParamConfigList());

            //更新API基本信息
            updateById(apiServiceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新api信息失败 {}",e.getMessage());
            return Result.error(200,"更新api信息失败");
        }


        return Result.OK("更新成功");
    }

    /**
     * 通过id查询api全部信息
     * @param id
     * @return
     */
    @Override
    public Object queryAllApiInfoById(String id){
        ApiServiceInfo item = getById(id);

        //填充参数信息
        List<ApiParamConfig> apiParamConfigList = apiParamConfigService.getParamByApiId(item.getId());
        if(Optional.ofNullable(apiParamConfigList).isEmpty()){
            apiParamConfigList = new ArrayList<>();
        }
        item.setApiParamConfigList(apiParamConfigList);

        //填充提问语信息
        List<ApiQuestionConfig> apiQuestionConfigList = apiQuestionConfigService.getQuestionByApiId(item.getId());
        if(Optional.ofNullable(apiQuestionConfigList).isEmpty()){
            apiQuestionConfigList = new ArrayList<>();
        }
        item.setApiQuestionConfigList(apiQuestionConfigList);


        //填充关联问题信息
        List<ApiRelationConfig> apiRelationConfigList = apiRelationConfigService.getRelationByApiId(item.getId());
        if(Optional.ofNullable(apiRelationConfigList).isEmpty()){
            apiRelationConfigList = new ArrayList<>();
        }
        for(ApiRelationConfig apiRelationConfig : apiRelationConfigList){
            //填充前端字段
            ApiServiceInfo apiServiceInfo = getById(apiRelationConfig.getApiId());
            apiRelationConfig.setApiName(apiServiceInfo.getApiName());
            apiRelationConfig.setCategoryId(apiServiceInfo.getCategoryId());
            apiRelationConfig.setBusinessSystemCode(apiServiceInfo.getBusinessSystemCode());
        }
        item.setApiRelationConfigList(apiRelationConfigList);

        //填充结果样式信息
        List<ApiStyleRelation> apiStyleRelationList =  apiStyleRelationService.getByApiId(item.getId());
        List<ApiStyleRelation> list = new ArrayList<>();
        if(Optional.ofNullable(apiStyleRelationList).isPresent()&&apiStyleRelationList.size() > 0){
            for(ApiStyleRelation apiStyleRelation : apiStyleRelationList){
                StyleInfo styleInfo = styleInfoService.getById(apiStyleRelation.getStyleId());
                if(Optional.ofNullable(styleInfo).isPresent()){
                    //填充对应信息
                    apiStyleRelation.setStyleName(styleInfo.getStyleName());
                    apiStyleRelation.setStyleType(styleInfo.getStyleType());
                    apiStyleRelation.setStyleScheme(styleInfo.getStyleScheme());
                    apiStyleRelation.setSampleData(styleInfo.getSampleData());
                    apiStyleRelation.setIconUrl(styleInfo.getIconUrl());
                    apiStyleRelation.setResultType(styleInfo.getResultType());
                    list.add(apiStyleRelation);
                }
            }
        }


        item.setApiStyleRelationList(list);


        //字典翻译


        List transList = new ArrayList<>();
        transList.addAll(item.getApiQuestionConfigList());

        List result = commonApi.translateResultByDict(transList);
        item.setApiQuestionConfigList(result);

        transList = new ArrayList<>();
        transList.addAll(item.getApiStyleRelationList());
        result = commonApi.translateResultByDict(transList);
        item.setApiStyleRelationList(result);

        transList = new ArrayList<>();
        transList.addAll(item.getApiRelationConfigList());
        result = commonApi.translateResultByDict(transList);
        item.setApiRelationConfigList(result);

        // 翻译
        List tempList = new ArrayList();
        tempList.add(item);
        List resList = commonApi.translateResultByDict(tempList);

        return resList.get(0);
    }

    /**
     * 获取提问语分页
     * @param apiId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public IPage<ApiRelationConfigVO> getQuestionPage(String apiId, Integer pageNo, Integer pageSize) {
        //如果apiId不为空，则查询已选关联问题
        List<ApiRelationConfig> apiRelationConfigList;
        Set<String> questionContentSet = new HashSet<>();
        if(!StrUtil.isEmpty(apiId)){
            apiRelationConfigList = apiRelationConfigService.getRelationByApiId(apiId);
            if(Optional.ofNullable(apiRelationConfigList).isPresent()){
                questionContentSet = apiRelationConfigList.stream().map(ApiRelationConfig::getQuestionContent).collect(Collectors.toSet());
            }

        }
        Page<ApiRelationConfigVO> page = new Page(pageNo, pageSize);
        MPJLambdaWrapper queryWrapper = JoinWrappers.lambda(ApiQuestionConfig.class)
                .selectAs(ApiQuestionConfig::getId,ApiRelationConfigVO::getId)
                .selectAs(ApiQuestionConfig::getQuestionContent,ApiRelationConfigVO::getQuestionContent)
                .selectAs(ApiServiceInfo::getApiName,ApiRelationConfigVO::getApiName)
                .selectAs(ApiServiceInfo::getApiCode,ApiRelationConfigVO::getApiCode)
                .selectAs(ApiServiceInfo::getCategoryId,ApiRelationConfigVO::getCategoryId)
                .selectAs(ApiServiceInfo::getBusinessSystemCode,ApiRelationConfigVO::getBusinessSystemCode)
                .leftJoin(ApiServiceInfo.class, ApiServiceInfo::getId, ApiQuestionConfig::getApiId);


        IPage<ApiRelationConfigVO> pageList = apiQuestionConfigMapper.selectJoinPage(page, ApiRelationConfigVO.class, queryWrapper);

        for(ApiRelationConfigVO item: pageList.getRecords()){
            if(questionContentSet.contains(item.getQuestionContent())){
                item.setIsChoose(true);
            }else{
                item.setIsChoose(false);
            }
        }

        return pageList;
    }
}
