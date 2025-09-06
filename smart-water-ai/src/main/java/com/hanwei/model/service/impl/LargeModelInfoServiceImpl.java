package com.hanwei.model.service.impl;


import cn.hutool.json.JSONArray;
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
import com.hanwei.model.bo.YjyLargeModelBO;
import com.hanwei.model.entity.LargeModelInfo;
import com.hanwei.model.mapper.LargeModelInfoMapper;
import com.hanwei.model.service.ILargeModelInfoService;
import com.hanwei.rag.bo.MessageBO;


import com.hanwei.util.shuiwu.PublicHelperFunc;


import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description: 大模型基础信息
 * @Author: hanwei
 * @Date:   2025-05-26
 * @Version: V1.0
 */
@Service
@Slf4j
public class LargeModelInfoServiceImpl extends ServiceImpl<LargeModelInfoMapper, LargeModelInfo> implements ILargeModelInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    PublicHelperFunc yanjiuyuanHelper;
    // YanjiuyuanHelper yanjiuyuanHelper;

    @Autowired
    private IApplicationConfigService applicationConfigService;

    @Autowired
    private ApplicationInfoMapper applicationInfoMapper;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     * @param outputStream
     * @param paramMap
     * @param largeModelInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, LargeModelInfo largeModelInfo) {
        QueryWrapper<LargeModelInfo> queryWrapper = QueryGenerator.initQueryWrapper(largeModelInfo, paramMap);
        List<LargeModelInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, LargeModelInfo.class).sheet("大模型基础信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     * @param paramMap
     * @param largeModelInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, LargeModelInfo largeModelInfo) {
        QueryWrapper<LargeModelInfo> queryWrapper = QueryGenerator.initQueryWrapper(largeModelInfo, paramMap);
        List<LargeModelInfo> list = list(queryWrapper);
        //字典值转换
        // List<JSON> listJson = commonApi.translateResultByDict(list);
        // List<LargeModelInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,LargeModelInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, LargeModelInfo.class).sheet("大模型基础信息").doWrite(list);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }

    /**
     * 同步研究院模型数据，按名称同步 不存在新增
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void synYjyLLM(){

        //研究院现有模型分类，因目前接口限制，需要手动维护
        String[] modelCategoryArray = new String[]
                {"Azure-OpenAI","BaiduYiyan","Ollama","OpenAI-API-Compatible","Tongyi-Qianwen","VolcEngine"};
        Map<String,YjyLargeModelBO> yjyLargeModelBOMap = new HashMap<>();
        //获取研究院数据
        try {
            Result result = yanjiuyuanHelper.getLargeModelList();
            if(0 != result.getCode()){
                log.error("获取研究院模型列表失败" + result.getMessage());
                return;
            }



            //组装研究院返回BO
            JSONObject jsonObject = (JSONObject) result.getResult();
            for(String modelCategory : modelCategoryArray){
                JSONObject jsonBO = jsonObject.getJSONObject(modelCategory);
                if(Optional.ofNullable(jsonBO).isEmpty()){
                    continue;
                }
                JSONArray jsonArray =  jsonBO.getJSONArray("llm");
                for(Object o : jsonArray){
                    JSONObject jo = (JSONObject) o;
                    YjyLargeModelBO yjyLargeModelBO = new YjyLargeModelBO();
                    yjyLargeModelBO.setCategory(modelCategory);
                    yjyLargeModelBO.setTags(jsonBO.getStr("tags"));
                    yjyLargeModelBO.setName(jo.getStr("name"));
                    yjyLargeModelBO.setType(jo.getStr("type"));
                    yjyLargeModelBO.setUsedToken(jo.getInt("used_token"));
                    yjyLargeModelBOMap.put(jo.getStr("name"),yjyLargeModelBO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用获取研究院模型列表接收失败 " + e.getMessage());
            return;
        }

        //获取全部本地存储得研究院模型数据
        List<LargeModelInfo> largeModelInfoList = list(new LambdaQueryWrapper<LargeModelInfo>().eq(LargeModelInfo::getSource,"yanjiuyuan"));

        //比对，不存在则新增
        for(String name : yjyLargeModelBOMap.keySet()){
            List<LargeModelInfo> result = largeModelInfoList.stream().filter(item->name.equals(item.getName())).collect(Collectors.toList());
            if(null == result || result.size() == 0){
                //新增
                LargeModelInfo largeModelInfo = new LargeModelInfo();
                YjyLargeModelBO yjyLargeModelBO = yjyLargeModelBOMap.get(name);
                largeModelInfo.setModelCategoryId(yjyLargeModelBO.getCategory());
                largeModelInfo.setName(yjyLargeModelBO.getName());
                largeModelInfo.setUsedToken(yjyLargeModelBO.getUsedToken());
                largeModelInfo.setType(yjyLargeModelBO.getType());
                largeModelInfo.setFrequencyPenalty(0.7);
                largeModelInfo.setPresencePenalty(0.4);
                largeModelInfo.setTemperature(0.1);
                largeModelInfo.setTopP(0.3);
                String promptStr = "\"prompt_config\":{\"empty_response\":\"\",\"keyword\":false,\"parameters\":[{\"key\":\"knowledge\",\"optional\":false}],\"prologue\":\"你好！ 我是你的助理，有什么可以帮到你的吗？\",\"quote\":true,\"reasoning\":false,\"refine_multiturn\":false,\"system\":\"你是一个智能助手，请总结知识库的内容来回答问题，请列举知识库中的数据详细回答。当所有知识库内容都与问题无关时，你的回答必须包括\\\"知识库中未找到您要的答案！\\\"这句话。回答需要考虑聊天历史。\\n        以下是知识库：\\n        {knowledge}\\n        以上是知识库。\",\"tavily_api_key\":\"\",\"tts\":false,\"use_kg\":false}";
                largeModelInfo.setPromptConfigStr(promptStr);
                largeModelInfo.setSource("yanjiuyuan");

                save(largeModelInfo);
            }else{
                //更新
                LargeModelInfo largeModelInfo = result.get(0);
                largeModelInfo.setUsedToken(yjyLargeModelBOMap.get(name).getUsedToken());
                updateById(largeModelInfo);
            }
        }
    }


    /**
     * 更新大模型信息
     * @param largeModelInfo
     * @return
     */
    @Override
    public Result<?> updateLargeModelInfo(LargeModelInfo largeModelInfo) {
        //只能更新本地模型
        if(!"local".equals(largeModelInfo.getSource())){
            return Result.error(200,"仅可修改本地模型，三方模型无法修改！");
        }

        updateById(largeModelInfo);

        //后续追加模型参数设置功能TODO
        return Result.ok("更新成功");
    }

    /**
     * 根据ID删除大模型信息
     * @param id
     * @return
     */
    @Override
    public Result<?> deleteLargeModelInfo(String id) {
        //应用是否使用校验
        List<ApplicationConfig> applicationConfigList = applicationConfigService.list(new LambdaQueryWrapper<ApplicationConfig>()
                .eq(ApplicationConfig::getModelId,id));
        if(Optional.ofNullable(applicationConfigList).isPresent() && applicationConfigList.size()>0){
            StringBuilder sb = new StringBuilder();
            sb.append("当前配置正在被应用使用,不允许删除，请先更改应用。使用得应用有:");
            for(ApplicationConfig applicationConfig : applicationConfigList){
                ApplicationInfo applicationInfo = applicationInfoMapper.selectById(applicationConfig.getApplicationId());
                sb.append(applicationInfo.getApplicationName()).append(" ");
            }
            return Result.error(sb.toString());
        }

        LargeModelInfo largeModelInfo = getById(id);
        //只能删除本地模型
        if(null == largeModelInfo || !"local".equals(largeModelInfo.getSource())){
            return Result.error(200,"仅可删除本地模型，三方模型无法修改！");
        }

        removeById(id);
        return Result.ok("删除成功");
    }

    /**
     * 设置模型
     * @param largeModelName
     * @return
     */
    @Override
    public Result<?> choiceLargeModelByYanjiuyuan(String largeModelName) {
        //调用设置模型
        Result result = null;
        try {
            result = yanjiuyuanHelper.choiceLargeModel(largeModelName);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院设置模型失败 "+ result.getMessage());
                return Result.error(200,"调用研究院设置模型失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院设置模型失败 "+ e.getMessage());
            return Result.error(200,"调用研究院设置模型失败 "+ e.getMessage());
        }
        //组装数据给前端
        return Result.ok("设置成功");
    }

    /**
     * 调用模型对话
     * @param conversationId
     * @param messageBOList
     * @return
     */
    @Override
    public Result<?> modelDialogueByYanjiuyuan(String conversationId, List<MessageBO> messageBOList) {
        //调用模型对话
        Result result = null;
        try {
            result = yanjiuyuanHelper.modelDialogue(conversationId, messageBOList);
            if(null == result || 0!=result.getCode()){
                log.error("调用研究院调用模型对话失败 "+ result.getMessage());
                return Result.error("调用研究院调用模型对话失败 "+ result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用研究院调用模型对话失败 "+ e.getMessage());
            return Result.error("调用研究院调用模型对话失败 "+ e.getMessage());
        }
        //组装数据给前端
        return Result.ok(result.getResult());
    }


}
