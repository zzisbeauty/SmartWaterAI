package com.hanwei.rag.service.impl;


import java.util.ArrayList;

import cn.hutool.json.JSONObject;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.application.entity.ApplicationConfig;
import com.hanwei.application.entity.ApplicationInfo;
import com.hanwei.application.mapper.ApplicationInfoMapper;
import com.hanwei.application.service.IApplicationConfigService;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.rag.bo.ChoiceRagBO;
import com.hanwei.rag.bo.RagRecallBO;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.mapper.RagInfoMapper;
import com.hanwei.rag.service.IRagInfoService;
import com.hanwei.rag.vo.RagRecallVO;


//import com.hanwei.util.yanjiuyuan.YanjiuyuanHelper;
import com.hanwei.util.shuiwu.PublicHelperFunc;


import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @Description: 知识库基础信息管理
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Service
@Slf4j
public class RagInfoServiceImpl extends ServiceImpl<RagInfoMapper, RagInfo> implements IRagInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    private PublicHelperFunc yanjiuyuanHelper;
    // private YanjiuyuanHelper yanjiuyuanHelper;

    @Autowired
    private IApplicationConfigService applicationConfigService;

    @Autowired
    private ApplicationInfoMapper applicationInfoMapper;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param ragInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, RagInfo ragInfo) {
        QueryWrapper<RagInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragInfo, paramMap);
        List<RagInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, RagInfo.class).sheet("知识库基础信息管理").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param ragInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, RagInfo ragInfo) {
        QueryWrapper<RagInfo> queryWrapper = QueryGenerator.initQueryWrapper(ragInfo, paramMap);
        List<RagInfo> list = list(queryWrapper);
        //字典值转换
        // List<JSON> listJson = commonApi.translateResultByDict(list);
        // List<RagInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,RagInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, RagInfo.class).sheet("知识库基础信息管理").doWrite(list);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }




    // =================================================================================================================

    /**
     * 保存知识库数据
     * @param ragInfo
     * @return
    */
    @Override
    public Result<?> saveRagInfo(RagInfo ragInfo) {
        //调用研究院知识库保存
        Result result = null;
        try {
            result = yanjiuyuanHelper.addRagInfo(ragInfo.getName());
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院新增知识库失败 "+ result.getMessage());
                return Result.error(200,"调用研究院新增知识库失败 "+ result.getMessage());
            } else {
                log.info("调用研究院新增知识库成功 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院新增知识库失败 "+ e.getMessage());
            return Result.error(200,"调用研究院新增知识库失败 "+ e.getMessage());
        }

        // 添加日志打印，查看研究原始返回结构，确认方便水务和这个结构对齐
        log.info("研究院 API 返回的原始 result 对象: " + result);
        log.info("研究院 API 返回的 result.getResult() 内容: " + result.getResult());
        log.info("研究院 API 返回的 result.getResult() 类型: " + (result.getResult() != null ? result.getResult().getClass().getName() : "null"));

        JSONObject jsonObject = (JSONObject) result.getResult();
        String yjyRagId = jsonObject.getStr("kb_id");
        //保存 对一些参数设置默认值
        ragInfo.setChunkNum(0);
        ragInfo.setId(yjyRagId);
        ragInfo.setEmbdId("BAAI/bge-large-zh-v1.5@BAAI");
        ragInfo.setDocParser("DeepDOC");
        ragInfo.setSlicingMethod("General");
        ragInfo.setTokenNum(0);
        ragInfo.setPagerank(512);
        ragInfo.setIsUseGraph(false);
        ragInfo.setSimilarityThreshold(0.2);
        ragInfo.setVectorSimilarityWeight(0.3);
        ragInfo.setTopN(8);
        save(ragInfo);
        return Result.ok("保存成功");
    }

    /**
     * 更新知识库数据 | 原来的更新
     * @param ragInfo
     * @return
     */
    @Override
    public Result<?> updateRagInfo(RagInfo ragInfo) {
        //调用研究院更新方法
        try {
            Result result = yanjiuyuanHelper.editRagInfo(ragInfo);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院更新知识库失败 "+ result.getMessage());
                return Result.error(200,"调用研究院更新知识库失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院更新知识库失败 "+ e.getMessage());
            return Result.error(200,"调用研究院更新知识库失败 "+ e.getMessage());
        }
        updateById(ragInfo);
        return Result.ok("更新成功");
    }

    /**
     * 删除知识库数据
     * @param id
     * @return
     */
    @Override
    public Result<?> removeRagInfoById(String id) {
        //应用是否使用校验
        List<ApplicationConfig> applicationConfigList = applicationConfigService.list(new LambdaQueryWrapper<ApplicationConfig>()
                .like(ApplicationConfig::getRagIds,id));
        if(Optional.ofNullable(applicationConfigList).isPresent() && applicationConfigList.size() > 0){
            StringBuilder sb = new StringBuilder();
            sb.append("当前配置正在被应用使用,不允许删除，请先更改应用。使用得应用有:");
            for(ApplicationConfig applicationConfig : applicationConfigList){
                ApplicationInfo applicationInfo = applicationInfoMapper.selectById(applicationConfig.getApplicationId());
                sb.append(applicationInfo.getApplicationName()).append(" ");
            }
            return Result.error(200,sb.toString());
        }

        //调用研究院删除知识库接口
        try {
            Result result = yanjiuyuanHelper.deleteRagInfo(id);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院删除知识库失败 "+ result.getMessage());
                return Result.error(200,"调用研究院删除知识库失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院删除知识库失败 "+ e.getMessage());
            return Result.error(200,"调用研究院删除知识库失败 "+ e.getMessage());
        }
        removeById(id);
        return Result.ok("删除成功");
    }

    /**
     * 知识库召回测试
     * @param ragRecallVO
     * @return
     */
    @Override
    public Result<?> ragRecall(RagRecallVO ragRecallVO) {
        //调用研究院召回接口
        Result result = null;
        RagRecallBO ragRecallBO = new RagRecallBO();
        try {
            result = yanjiuyuanHelper.ragRecall(ragRecallVO);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院知识库召回测试失败 "+ result.getMessage());
                return Result.error(200,"调用研究院知识库召回测试失败 "+ result.getMessage());
            }
            JSONObject jsonObject = (JSONObject) result.getResult();
            ragRecallBO.setChunkList(jsonObject.getJSONArray("chunks").toList(String.class));
            ragRecallBO.setDocAggs(jsonObject.getJSONArray("doc_aggs").toList(String.class));
            ragRecallBO.setLabels(jsonObject.getStr("labels"));
            ragRecallBO.setTotal(jsonObject.getInt("total"));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院知识库召回测试失败 "+ e.getMessage());
            return Result.error("调用研究院知识库召回测试失败 "+ e.getMessage());
        }
        //组装返回前端VO
        return Result.ok(ragRecallBO);
    }

    /**
     * 设置知识库
     * @param choiceRagBO
     * @return
     */
    @Override
    public Result<?> choiceRagByYanjiuyuan(ChoiceRagBO choiceRagBO) {
        //调用设置知识库
        Result result = null;
        try {
            result = yanjiuyuanHelper.choiceRag(choiceRagBO);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院设置知识库失败 "+ result.getMessage());
                return Result.error("调用研究院设置知识库失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院设置知识库失败 "+ e.getMessage());
            return Result.error("调用研究院设置知识库失败 "+ e.getMessage());
        }
        //组装数据给前端
        return Result.ok(result.getResult());
    }


    // ============================= 暂缺实现 =================================== \\

    /**
     * 获取知识库知识图谱
     * @param id
     * @return
     */
    @Override
    public Result<?> getRagGraph(String id) {
        //调用研究院获取图谱数据
        Result result = null;
        try {
            result = yanjiuyuanHelper.getRagGraphInfo(id);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院获取图谱数据失败 "+ result.getMessage());
                return Result.error("调用研究院获取图谱数据失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院获取图谱数据失败 "+ e.getMessage());
            return Result.error("调用研究院获取图谱数据失败 "+ e.getMessage());
        }
        //组装数据给前端
        return Result.ok(result.getResult());
    }

}
