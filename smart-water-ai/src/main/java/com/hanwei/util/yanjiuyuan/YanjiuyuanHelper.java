package com.hanwei.util.yanjiuyuan;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.rag.bo.ChoiceRagBO;
import com.hanwei.rag.bo.MessageBO;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.vo.RagRecallVO;
import com.hanwei.util.IModelBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @author CX
 * @version : [v1.0]
 * @description : [调用研究院提供接口]
 * @createTime : [2025/5/26 10:00]
 * @updateRemark : [说明本次修改内容]
 */
@Service
@Slf4j
public class YanjiuyuanHelper implements IModelBaseService {

    @Value("${yanjiuyuan.urlPrefix}")
    private String urlPrefix;

    /**
     * 研究院鉴权临时用，后期去掉
     */
    private final String USERNAME = "UserName";

    /**
     * 研究院鉴权临时用，后期去掉
     */
    private final String WORKNO = "H05583";

    //================================公共方法=========================================/

    /**
     * 将其他类型的返回值转换为Result类型
     *
     * @param otherResult
     * @return
     */
    @Override
    public Result changeResult(Object otherResult) {
        if (otherResult == null) {
            return Result.error("调用研究院接口失败");
        }
        YjyResult yjyResult = (YjyResult) otherResult;
        Result result = new Result();
        result.setCode(yjyResult.getCode());
        result.setMessage(yjyResult.getMessage());
        result.setResult(yjyResult.getData());
        return result;
    }

    //================================公共方法=========================================/


    //================================知识库管理=========================================/

    /**
     * 创建知识库
     *
     * @param ragName
     * @return
     */
    @Override
    public Result addRagInfo(String ragName) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", ragName);
        log.info("调用研究院创建知识库接口 发送报文: " + paramMap);
        //调用研究院 / 水务接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/kb/create").header(USERNAME, WORKNO).
                body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院创建知识库接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 更新知识库
     * @param ragInfo 知识库实体类
     * @return
     */
    @Override
    public Result editRagInfo(RagInfo ragInfo) {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("avatar", ragInfo.getAvatar());
        paramMap.put("description", ragInfo.getDescription());
        paramMap.put("embd_id", ragInfo.getEmbdId());
        paramMap.put("kb_id", ragInfo.getId());
        paramMap.put("name", ragInfo.getName());
        paramMap.put("pagerank", ragInfo.getPagerank());
        Map parserConfigParamMap = new HashMap<>();
        parserConfigParamMap.put("layout_recognize", ragInfo.getDocParser());
        parserConfigParamMap.put("auto_keywords", 0);
        parserConfigParamMap.put("auto_questions", 0);
        Map raptorParamMap = new HashMap<>();
        raptorParamMap.put("use_raptor", false);
        parserConfigParamMap.put("raptor", raptorParamMap);

        Map graphParamMap = new HashMap<>();
        graphParamMap.put("use_graphrag", ragInfo.getIsUseGraph());
        graphParamMap.put("entity_types", ragInfo.getEntityTypes());
        graphParamMap.put("resolution", ragInfo.getIsResolution());
        graphParamMap.put("method", ragInfo.getMethodGraph());
        graphParamMap.put("community", ragInfo.getIsGeneralReport());
        parserConfigParamMap.put("graphrag", graphParamMap);
        paramMap.put("parser_config", parserConfigParamMap);

        paramMap.put("parser_id", ragInfo.getSlicingMethod());
        paramMap.put("permission", "team");

        //paramMap.put("manager_list", new ArrayList<String>().add("H05583"));
        //paramMap.put("auth_list", new ArrayList<String>().add("2122"));
        paramMap.put("manager_list", Arrays.asList("H05583"));
        paramMap.put("auth_list", Arrays.asList("2122"));

        log.info("调用研究院更新知识库接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/kb/update").header(USERNAME, WORKNO).
                body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院更新知识库接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 知识库ID
     * @param id
     * @return
     */
    @Override
    public Result deleteRagInfo(String id) {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("kb_id", id);

        log.info("调用研究院删除知识库接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/kb/rm").
                header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院删除知识库接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 获取知识库列表
     * @param pageNo
     * @param pageSize
     * @param ragName
     * @return
     * @throws Exception
     */
    @Override
    public Result getRagInfoList(String pageNo, String pageSize, String ragName) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("page", pageNo);
        paramMap.put("page_size", pageSize);
        if (StrUtil.isNotEmpty(ragName)) {
            paramMap.put("keywords", ragName);
        }

        log.info("调用研究院获取知识库列表接口 发送报文: " + paramMap);
        //调用研究院接口
        HttpRequest request = HttpUtil.createGet(urlPrefix + "/v1/kb/list")
                .header(USERNAME, WORKNO)
                .form("page", pageNo)
                .form("page_size", pageSize);
        if (StrUtil.isNotEmpty(ragName)) {
            request.form("keywords", ragName);
        }

        String resultStr = request.execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院获取知识库列表接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 获取知识库详情
     *
     * @param id
     * @return
     */
    @Override
    public Result getRagInfoDetail(String id) {
        log.info("调用研究院获取知识库详情接口 发送报文: " + id);
        //调用研究院接口
        String resultStr = HttpUtil.createGet(urlPrefix + "/v1/kb/detail")
                .header(USERNAME, WORKNO)
                .form("kb_id", id).execute().body();

        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院获取知识库详情接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 查询知识库知识图谱
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Result getRagGraphInfo(String id) throws Exception {
        log.info("调用研究院获取知识库知识图谱接口 发送报文: " + id);
        //调用研究院接口
        String resultStr = HttpUtil.createGet(urlPrefix + "/v1/kb/" + id + "/knowledge_graph")
                .header(USERNAME, WORKNO).execute().body();

        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院获取知识库知识图谱接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 召回测试
     * @param ragRecallVo
     * @return
     * @throws Exception
     */
    @Override
    public Result ragRecall(RagRecallVO ragRecallVo) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("similarity_threshold", ragRecallVo.getSimilarityThreshold());
        paramMap.put("vector_similarity_weight", ragRecallVo.getVectorSimilarityWeight());
        paramMap.put("use_kg", ragRecallVo.getUseGraphRag());
        paramMap.put("question", ragRecallVo.getQuestion());
        paramMap.put("doc_ids", ragRecallVo.getDoc_ids());
        paramMap.put("kb_id", ragRecallVo.getRagId());
        paramMap.put("page", ragRecallVo.getPageNo());
        paramMap.put("size", ragRecallVo.getPageSize());

        log.info("调用研究院知识库召回接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/chunk/retrieval_test").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库召回接口 返回报文: " + result);
        return changeResult(result);
    }


    //================================知识库管理=========================================/


    //================================知识库文件管理=========================================/

    /**
     * 知识库文件上传
     * @param ragId
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public Result ragFileUpload(String ragId, MultipartFile file) throws Exception {
        File toFile = transferToFile(file);

        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("kb_id", ragId);
        paramMap.put("file", toFile);

        log.info("调用研究院知识库文件上传接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/document/upload")
                .header(USERNAME, WORKNO)
                .form(paramMap)
                .execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文件上传接口 返回报文: " + result);
        return changeResult(result);
    }
    /**
     * 将MultipartFile转换为File对象
     * @param multipartFile 要转换的MultipartFile对象
     * @return 转换后的File对象，如果转换失败则返回null
     */
//    private File transferToFile(MultipartFile multipartFile) {
//        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
//        File file = null;
//        try {
//            String originalFilename = multipartFile.getOriginalFilename();
//            //获取文件后缀
//            String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
//            // file = File.createTempFile("", "");    //创建临时文件 会报错 Prefix string "" too short: length must be at least 3
//            file = File.createTempFile("shuiwuTMP", "");    //创建临时文件
//            multipartFile.transferTo(file);
//            //删除
//            file.deleteOnExit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
    // 使用的是shuiwu重新实现的文件上传方案，是比上述方法更健壮的实现
    private File transferToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                originalFilename = "upload_file";
            }
            // 获取文件扩展名
            String extension = "";
            int lastDotIndex = originalFilename.lastIndexOf(".");
            if (lastDotIndex > 0) {
                extension = originalFilename.substring(lastDotIndex);
            }
            // 创建临时文件，使用原始文件名作为前缀
            String nameWithoutExt = lastDotIndex > 0 ?
                    originalFilename.substring(0, lastDotIndex) : originalFilename;
            file = File.createTempFile(nameWithoutExt + "_", extension);

            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            log.error("MultipartFile转换为File失败", e);
            return null;
        }
        return file;
    }



    /**
     * 知识库文件查询
     * @param pageNo
     * @param pageSize
     * @param fileName
     * @param ragId
     * @return
     * @throws Exception
     */
    @Override
    public Result getRagFileList(String pageNo, String pageSize, String fileName, String ragId) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("page", pageNo);
        paramMap.put("page_size", pageSize);
        paramMap.put("keywords", fileName);
        paramMap.put("kb_id", ragId);

        log.info("调用研究院知识库文件查询接口 发送报文: " + paramMap);
        //调用研究院接口
        //调用研究院接口
        HttpRequest request = HttpUtil.createGet(urlPrefix + "/v1/document/list")
                .header(USERNAME, WORKNO)
                .form("page", pageNo)
                .form("page_size", pageSize)
                .form("kb_id", ragId);
        if (StrUtil.isNotEmpty(fileName)) {
            request.form("keywords", fileName);
        }

        String resultStr = request.execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文件查询接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 知识库文件解析
     *
     * @param docIdList
     * @param deleteFlag 是否清除之前的分块
     * @param runModel   模式 1:文件解析 2:终止解析
     * @return
     * @throws Exception
     */
    @Override
    public Result ragFileParsing(List<String> docIdList, Boolean deleteFlag, Integer runModel) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("delete", deleteFlag);
        paramMap.put("doc_ids", docIdList);
        paramMap.put("run", runModel);

        log.info("调用研究院知识库文件解析接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/document/run").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文件解析接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 知识库文件状态切换
     *
     * @param docId
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public Result ragFileSwitch(String docId, Integer status) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("doc_id", docId);
        paramMap.put("status", status);

        log.info("调用研究院知识库文件状态切换接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/document/change_status").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文件状态切换接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 知识库文件下载
     *
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    public HttpResponse downLoadRagFile(String fileId) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("fileId", fileId);

        log.info("调用研究院知识库文件下载接口 发送报文: " + paramMap);
        //调用研究院接口
        HttpRequest request = HttpUtil.createGet(urlPrefix + "/v1/document/get/" + fileId)
                .header(USERNAME, WORKNO);

        HttpResponse response = request.execute();

        log.info("调用研究院知识库文件下载接口");
        return response;
    }

    /**
     * 知识库文件删除
     *
     * @param docIdList
     * @return
     * @throws Exception
     */
    @Override
    public Result deleteRagFile(List<String> docIdList) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("doc_id", docIdList);

        log.info("调用研究院知识库文件删除接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/document/rm").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文件删除接口 返回报文: " + result);
        return changeResult(result);
    }


    /**
     * 文档切片查询
     *
     * @param pageNo
     * @param pageSize
     * @param docId
     * @param keyWord  关键字
     * @return
     * @throws Exception
     */
    @Override
    public Result getDocumentSlicingList(String pageNo, String pageSize, String docId, String keyWord) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("page", pageNo);
        paramMap.put("size", pageSize);
        paramMap.put("doc_id", docId);
        if (StrUtil.isNotEmpty(keyWord)) {
            paramMap.put("keywords", keyWord);
        }

        log.info("调用研究院知识库文档切片查询接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/chunk/list").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文档切片查询接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 文档切片状态切换
     *
     * @param status        1 启用 0 禁用
     * @param docId
     * @param slicingIdList
     * @return
     * @throws Exception
     */
    @Override
    public Result documentSlicingStatusSwitch(Integer status, String docId, List<String> slicingIdList) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("available_int", status);
        paramMap.put("chunk_ids", slicingIdList);
        paramMap.put("doc_id", docId);

        log.info("调用研究院知识库文档切片状态切换接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/chunk/switch").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院知识库文档切片状态切换接口 返回报文: " + result);
        return changeResult(result);
    }


    //================================知识库文件管理=========================================/


    //================================大模型管理=========================================/

    /**
     * 获取模型列表
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result getLargeModelList() throws Exception {

        //调用研究院接口
        HttpRequest request = HttpUtil.createGet(urlPrefix + "/v1/llm/my_llms")
                .header(USERNAME, WORKNO);

        String resultStr = request.execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院取模型列表接口 返回报文: " + result);
        return changeResult(result);
    }

    /**
     * 设置模型
     *
     * @param largeModelName
     * @return
     * @throws Exception
     */
    @Override
    public Result choiceLargeModel(String largeModelName) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("asr_id", "");
        paramMap.put("embd_id", "BAAI/bge-large-zh-v1.5@BAAI");
        paramMap.put("img2txt_id", "");
        paramMap.put("llm_id", largeModelName);
        paramMap.put("name", "H05583‘s Kingdom");
        paramMap.put("parser_ids", "naive:General,qa:Q&A,resume:Resume,manual:Manual,table:Table,paper:Paper,book:Book,laws:Laws,presentation:Presentation,picture:Picture,one:One,audio:Audio,email:Email,tag:Tag");
        paramMap.put("rerank_id", "");
        paramMap.put("tenant_id", "H05583");
        paramMap.put("tts_id", "");


        log.info("调用研究院设置模型接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/user/set_tenant_info").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院设置模型接口 返回报文: " + result);
        return changeResult(result);
    }

    //设置知识库

    /**
     * 设置知识库
     *
     * @param choiceRagBO
     * @return
     * @throws Exception
     */
    @Override
    public Result choiceRag(ChoiceRagBO choiceRagBO) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("icon", choiceRagBO.getIcon());
        paramMap.put("kb_ids", choiceRagBO.getRagIdList());
        paramMap.put("language", choiceRagBO.getLanguage());
        paramMap.put("llm_id", choiceRagBO.getLargeModelName());
        paramMap.put("name", choiceRagBO.getName());

        Map largeModelSettingParamMap = new HashMap<>();
        largeModelSettingParamMap.put("frequency_penalty", choiceRagBO.getFrequencyPenalty());
        largeModelSettingParamMap.put("presence_penalty", choiceRagBO.getPresencePenalty());
        largeModelSettingParamMap.put("temperature", choiceRagBO.getTemperature());
        largeModelSettingParamMap.put("top_p", choiceRagBO.getTopN());
        paramMap.put("llm_setting", largeModelSettingParamMap);

        Map promptConfigParamMap = new HashMap<>();
        promptConfigParamMap.put("empty_response", choiceRagBO.getEmptyResponse());
        promptConfigParamMap.put("keyword", choiceRagBO.getKeyWord());
        promptConfigParamMap.put("parameters", choiceRagBO.getParameters());
        promptConfigParamMap.put("prologue", choiceRagBO.getPrologue());
        promptConfigParamMap.put("quote", choiceRagBO.getQuote());
        promptConfigParamMap.put("reasoning", choiceRagBO.getReasoning());
        promptConfigParamMap.put("refine_multiturn", choiceRagBO.getRefineMultiTurn());
        promptConfigParamMap.put("system", choiceRagBO.getSystem());
        promptConfigParamMap.put("tavily_api_key", choiceRagBO.getTavilyApiKey());
        promptConfigParamMap.put("tts", choiceRagBO.getTts());
        promptConfigParamMap.put("use_kg", choiceRagBO.getUseKg());

        paramMap.put("prompt_config", promptConfigParamMap);
        paramMap.put("similarity_threshold", choiceRagBO.getSimilarityThreshold());
        paramMap.put("top_n", choiceRagBO.getTopN());
        paramMap.put("vector_similarity_weight", choiceRagBO.getVectorSimilarityWeight());

        log.info("调用研究院设置知识库接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/dialog/set").
                header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院设置知识库接口 返回报文: " + result);
        return changeResult(result);
    }

    //================================大模型管理=========================================/

    //================================大模型知识库调用=========================================/

    /**
     * 调用模型对话
     *
     * @param conversationId
     * @param messageBOList
     * @return
     * @throws Exception
     */
    @Override
    public Result modelDialogue(String conversationId, List<MessageBO> messageBOList) throws Exception {
        Map paramMap = new HashMap<>();
        //组装数据
        paramMap.put("conversation_id", conversationId);
        paramMap.put("messages", messageBOList);

        log.info("调用研究院模型对话接口 发送报文: " + paramMap);
        //调用研究院接口
        String resultStr = HttpUtil.createPost(urlPrefix + "/v1/conversation/completion").header(USERNAME, WORKNO).body(JSONUtil.toJsonStr(paramMap)).execute().body();
        YjyResult result = JSONUtil.toBean(resultStr, YjyResult.class);
        log.info("调用研究院模型对话接口 返回报文: " + result);
        return changeResult(result);
    }

    //================================大模型知识库调用=========================================/


    public static void main(String[] args) throws Exception {
        YanjiuyuanHelper yanjiuyuanHelper = new YanjiuyuanHelper();
        System.out.println(yanjiuyuanHelper.getRagInfoList("1", "10", ""));
    }
}
