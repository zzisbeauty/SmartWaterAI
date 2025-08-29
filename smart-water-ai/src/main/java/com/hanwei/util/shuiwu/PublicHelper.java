package com.hanwei.util.shuiwu;

import cn.hutool.core.util.StrUtil;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;


import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import cn.hutool.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.hutool.json.JSONArray;
import java.util.ArrayList;
import cn.hutool.http.Method;
import cn.hutool.http.HttpRequest;

import java.io.File;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [调用水务dify服务接口]
 * @createTime : [2025/8/23]
 */
@Service
@Slf4j
public class PublicHelper implements IModelBaseService {

    @Value("${shuiwu.urlPrefix}")
    private String urlPrefix;

    @Value("${shuiwu.bearerToken}")
    private String bearerToken;

    @Value("${shuiwu.fileUploadUrlPrefix}")
    private String fileUploadUrlPrefix;

    // 测试使用
    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }
    public void setFileUploadUrlPrefix(String fileUploadUrlPrefix) {
        this.fileUploadUrlPrefix = fileUploadUrlPrefix;
    }


    //================================公共方法=========================================/

    @Override
    public Result changeResult(Object otherResult) {
        return Result.ok("此 PublicHelper 中的方法直接完成 Result 的封装返回，因此水务无需具体实现此接口");
    }

    //================================知识库管理=========================================/

    @Override
    public Result addRagInfo(String ragName) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();

        // 基础参数
        paramMap.put("name", ragName);
        paramMap.put("description", ""); // 可选参数，设为空让服务端使用默认值
        paramMap.put("indexing_technique", "high_quality");
        paramMap.put("search_method", "semantic_search");
        paramMap.put("provider", "vendor");

        // embedding 相关参数 - 使用默认值
        paramMap.put("embedding_model", ""); // 让服务端使用 embedding_model_config
        paramMap.put("embedding_provider_name", ""); // 让服务端使用 embedding_model_provider_config

        // reranking 参数
        paramMap.put("reranking_enable", false);
        paramMap.put("weights", 0.8);

        // 检索参数
        paramMap.put("score_threshold_enabled", true);
        paramMap.put("score_threshold", 0.3);
        paramMap.put("top_k", 3);

        log.info("调用 dify 创建知识库接口 发送报文: " + paramMap);

        try {
            // 调用 dify 接口
            String resultStr = HttpUtil.createPost(urlPrefix + "/kb/create")
                    // .header("Authorization", "Bearer " + bearerToken)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute().body();

            // 解析响应
            JSONObject response = JSONUtil.parseObj(resultStr);
            log.info("调用 dify 创建知识库接口 返回报文: " + response);

            // 转换为标准 Result 格式
            Result result = new Result();
            result.setCode(response.getInt("code", 0));
            result.setMessage(response.getStr("message", ""));
            result.setResult(response.get("data"));

            return result;

        } catch (Exception e) {
            log.error("调用 dify 创建知识库接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result editRagInfo(RagInfo ragInfo) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();

        // 必需参数：知识库ID (RagInfo.id → kb_id)
        paramMap.put("kb_id", ragInfo.getId());

        // 基础参数映射 (RagInfo 参数名 → dify API 参数名)
        if (ragInfo.getName() != null && !ragInfo.getName().trim().isEmpty()) {
            paramMap.put("name", ragInfo.getName());
        }
        if (ragInfo.getDescription() != null && !ragInfo.getDescription().trim().isEmpty()) {
            paramMap.put("description", ragInfo.getDescription());
        }

        // 直接使用 dify API 参数名的新增参数
        paramMap.put("indexing_technique", ragInfo.getIndexing_technique() != null ?
                ragInfo.getIndexing_technique() : "high_quality");

        // embedding 参数映射
        paramMap.put("embedding_model_name", ragInfo.getEmbdId() != null ? ragInfo.getEmbdId() : "");
        paramMap.put("embedding_provider_name", ragInfo.getEmbedding_provider_name() != null ?
                ragInfo.getEmbedding_provider_name() : "");

        // 检索参数映射
        paramMap.put("search_method", ragInfo.getSearch_method() != null ?
                ragInfo.getSearch_method() : "hybrid_search");

        // weights 参数映射 (RagInfo.vectorSimilarityWeight → weights)
        Double weights = ragInfo.getVectorSimilarityWeight() != null ?
                ragInfo.getVectorSimilarityWeight() : 0.8;
        if (weights > 1 || weights < 0) {
            weights = 0.8;
        }
        weights = Math.round(weights * 10.0) / 10.0;
        paramMap.put("weights", weights);

        // rerank 参数
        paramMap.put("reranking_enable", ragInfo.getReranking_enable() != null ?
                ragInfo.getReranking_enable() : false);
        paramMap.put("reranking_model_name", ragInfo.getReranking_model_name() != null ?
                ragInfo.getReranking_model_name() : "");
        paramMap.put("reranking_provider_name", ragInfo.getReranking_provider_name() != null ?
                ragInfo.getReranking_provider_name() : "");

        // recall/retrieval 参数映射
        paramMap.put("top_k", ragInfo.getTopN() != null ? ragInfo.getTopN() : 10);
        paramMap.put("score_threshold_enabled", ragInfo.getScore_threshold_enabled() != null ?
                ragInfo.getScore_threshold_enabled() : false);
        paramMap.put("score_threshold", ragInfo.getSimilarityThreshold() != null ?
                ragInfo.getSimilarityThreshold() : 0.25);

        log.info("调用 dify 编辑知识库接口 发送报文: " + JSONUtil.toJsonStr(paramMap));

        try {
            // 使用 Apache HttpClient 进行 PATCH 请求
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPatch httpPatch = new HttpPatch(urlPrefix + "/kb/update");
            // 设置请求头 - Server API 不需要认证头
            httpPatch.setHeader("Content-Type", "application/json");
            // 设置请求体
            StringEntity entity = new StringEntity(JSONUtil.toJsonStr(paramMap), StandardCharsets.UTF_8);
            httpPatch.setEntity(entity);
            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpPatch);
            String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("调用 dify 编辑知识库接口 返回报文: " + resultStr);

            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));

            // 关闭资源
            response.close();
            httpClient.close();
            return result;
        } catch (Exception e) {
            log.error("调用 dify 编辑知识库接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result deleteRagInfo(String id) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        // 参数映射：IModelBaseService.deleteRagInfo(String id) → dify API kb_id
        paramMap.put("kb_id", id);
        log.info("调用 dify 删除知识库接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        try {
            // 使用 HttpUtil.createRequest 进行 DELETE 请求，通过请求体传递参数
            String resultStr = HttpUtil.createRequest(Method.DELETE, urlPrefix + "/kb/rm")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute().body();

            log.info("调用 dify 删除知识库接口 返回报文: " + resultStr);

            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 删除知识库接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result getRagInfoList(String pageNo, String pageSize, String ragName) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        // 参数映射：IModelBaseService 参数 → dify API 参数
        paramMap.put("page", pageNo != null ? pageNo : "1");
        paramMap.put("page_size", pageSize != null ? pageSize : "100");
        if (ragName != null && !ragName.trim().isEmpty()) {
            paramMap.put("keywords", ragName);
        }
        // tag_ids 参数暂时不使用，因为接口中没有定义
        log.info("调用 dify 获取知识库列表接口 发送报文: " + paramMap);
        try {
            HttpRequest request = HttpUtil.createGet(urlPrefix + "/kb/list")
                    .header("Content-Type", "application/json");
            // 添加查询参数
            request.form("page", pageNo != null ? pageNo : "1");
            request.form("page_size", pageSize != null ? pageSize : "100");
            if (ragName != null && !ragName.trim().isEmpty()) {
                request.form("keywords", ragName);
            }
            String resultStr = request.execute().body();
            log.info("调用 dify 获取知识库列表接口 返回报文: " + resultStr);
            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 获取知识库列表接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result getRagInfoDetail(String id) throws Exception {
        log.info("调用 dify 获取知识库详情接口，知识库ID: " + id);
        try {
            HttpRequest request = HttpUtil.createGet(urlPrefix + "/kb/detail")
                    .header("Content-Type", "application/json").form("kb_id", id);
            String resultStr = request.execute().body();
            log.info("调用 dify 获取知识库详情接口 返回报文: " + resultStr);
            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 获取知识库详情接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result getRagGraphInfo(String id) throws Exception {
        // TODO: 实现dify知识库图谱查询接口调用
        return Result.ok("待实现");
    }

    @Override
    public Result ragRecall(RagRecallVO ragRecallVO) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        // 必需参数映射
        paramMap.put("kb_id", ragRecallVO.getRagId());
        paramMap.put("question", ragRecallVO.getQuestion());
        // 检索参数映射
        paramMap.put("search_method", ragRecallVO.getSearch_method() != null ?
                ragRecallVO.getSearch_method() : "semantic_search");
        paramMap.put("vector_similarity_weight", ragRecallVO.getVectorSimilarityWeight() != null ?
                ragRecallVO.getVectorSimilarityWeight() : 0.6);
        paramMap.put("similarity_threshold", ragRecallVO.getSimilarityThreshold() != null ?
                ragRecallVO.getSimilarityThreshold() : 0.2);
        paramMap.put("top_k", ragRecallVO.getTopN() != null ? ragRecallVO.getTopN() : 3);
        // 重排序参数
        paramMap.put("reranking_enable", ragRecallVO.getReranking_enable() != null ?
                ragRecallVO.getReranking_enable() : false);
        paramMap.put("score_threshold_enabled", ragRecallVO.getScore_threshold_enabled() != null ?
                ragRecallVO.getScore_threshold_enabled() : false);
        log.info("调用 dify 知识库召回接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        try {
            String resultStr = HttpUtil.createPost(urlPrefix + "/chunk/retrieval_test")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap)).execute().body();

            log.info("调用 dify 知识库召回接口 原始返回报文: " + resultStr);
            if (resultStr != null && resultStr.contains("\\u")) {
                try {
                    resultStr = resultStr.replace("\\u", "\\u");
                    log.info("检测到 Unicode 编码，原始内容: " + resultStr);
                } catch (Exception decodeException) {
                    log.warn("Unicode 解码失败，使用原始内容: " + decodeException.getMessage());
                }
            }
            log.info("调用 dify 知识库召回接口 处理后报文: " + resultStr);
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            String trimmedResult = resultStr.trim();
            if (trimmedResult.startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            try {
                JSONObject responseJson = JSONUtil.parseObj(trimmedResult);
                Result result = new Result();
                result.setCode(responseJson.getInt("code", 0));
                result.setMessage(responseJson.getStr("message", ""));
                result.setResult(responseJson.get("data"));
                return result;
            } catch (Exception jsonException) {
                log.error("JSON解析失败: " + jsonException.getMessage());
                log.error("尝试解析的内容前200字符: " + trimmedResult.substring(0, Math.min(200, trimmedResult.length())));
                return Result.error("JSON解析失败: " + jsonException.getMessage());
            }
        } catch (Exception e) {
            log.error("调用 dify 知识库召回接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    /**
     * Unicode解码方法
     * @param unicode Unicode编码的字符串
     * @return 解码后的中文字符串
     */
    private String unicodeDecode(String unicode) {
        if (unicode == null || unicode.trim().isEmpty()) {
            return unicode;
        }
        StringBuilder sb = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 0; i < hex.length; i++) {
            if (i == 0 && hex[i].length() == 0) {
                continue;
            }
            if (hex[i].length() >= 4) {
                try {
                    // 提取Unicode编码部分
                    String unicodeHex = hex[i].substring(0, 4);
                    String remaining = hex[i].substring(4);
                    // 转换为字符
                    int charCode = Integer.parseInt(unicodeHex, 16);
                    sb.append((char) charCode);
                    sb.append(remaining);
                } catch (NumberFormatException e) {
                    // 如果不是有效的Unicode编码，直接添加原字符串
                    sb.append("\\u").append(hex[i]);
                }
            } else {
                if (i > 0) {
                    sb.append("\\u");
                }
                sb.append(hex[i]);
            }
        }
        return sb.toString();
    }

    //================================知识库文件管理=========================================/

    @Override
    public Result ragFileUpload(String ragId, MultipartFile file) throws Exception {
        try {
            // 参数验证
            if (file == null || file.isEmpty()) {
                return Result.error("上传文件不能为空");
            }
            // 转换 MultipartFile 为 File
            File toFile = transferToFile(file);
            if (toFile == null) {
                return Result.error("文件转换失败");
            }
            String originalFileName = file.getOriginalFilename();
            // 构造 data_json，使用默认参数
            Map<String, Object> dataJson = buildDataJson(
                    "high_quality",    // indexingTechnique
                    "text_model",      // docForm
                    "Chinese",         // language
                    "\n",              // separator
                    1000,              // maxTokens
                    true,              // removeUrlsEmails
                    "custom"           // mode
            );
            String dataJsonStr = JSONUtil.toJsonStr(dataJson);
            // 构建上传URL
            String uploadUrl = fileUploadUrlPrefix + "/" + ragId + "/document/create_by_file";
            // 准备multipart/form-data参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("file", toFile);
            paramMap.put("data", dataJsonStr);
            paramMap.put("name", originalFileName);
            log.info("调用 dify 文件上传接口，URL: " + uploadUrl);
            log.info("原始文件名: " + originalFileName);
            log.info("data_json参数: " + dataJsonStr);
            // 调用 dify 文件上传接口 - 使用 Bearer Token 认证
            String resultStr = HttpUtil.createPost(uploadUrl)
                    .header("Authorization", "Bearer " + bearerToken).form(paramMap).execute().body();
            log.info("调用 dify 文件上传接口 返回报文: " + resultStr);
            JSONObject response = JSONUtil.parseObj(resultStr);
            if (response == null) {
                return Result.error("接口返回为空");
            }
            // dify 接口的成功判断逻辑
            if (response.containsKey("document_id") ||
                    response.containsKey("id") ||
                    response.containsKey("batch") ||
                    (response.containsKey("created_at") && response.get("created_at") != null)) {

                log.info("dify 文件上传成功");
                return Result.OK("文件上传成功", response);

            } else if (response.containsKey("code")) {
                Integer code = response.getInt("code");
                if (code != null && code == 200) {
                    return Result.OK("文件上传成功", response);
                } else {
                    String message = response.getStr("message", response.getStr("detail", "上传失败"));
                    log.error("dify 文件上传失败，错误码: {}, 消息: {}", code, message);
                    return Result.error("文件上传失败: " + message);
                }
            } else if (response.containsKey("error")) {
                String errorMsg = response.getStr("error");
                log.error("dify 文件上传失败: " + errorMsg);
                return Result.error("文件上传失败: " + errorMsg);
            } else {
                // 兜底判断：如果没有明确的错误信息，且有返回内容，认为成功
                log.info("dify 文件上传完成，返回内容: " + resultStr);
                return Result.OK("文件上传成功", response);
            }
        } catch (Exception e) {
            log.error("调用 dify 文件上传接口异常", e);
            return Result.error("文件上传异常: " + e.getMessage());
        }
    }

    /**
     * 将MultipartFile转换为File对象，保持原始文件名
     */
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
     * 构建data_json参数
     */
    private Map<String, Object> buildDataJson(
            String indexingTechnique, String docForm,
            String language, String separator, Integer maxTokens, Boolean removeUrlsEmails, String mode) {
        Map<String, Object> dataJson = new HashMap<>();
        dataJson.put("indexing_technique", indexingTechnique);
        dataJson.put("doc_form", docForm);
        dataJson.put("doc_language", language);
        dataJson.put("process_rule", Map.of(
                "rules", Map.of(
                        "pre_processing_rules", List.of(
                                Map.of("id", "remove_extra_spaces", "enabled", true),
                                Map.of("id", "remove_urls_emails", "enabled", removeUrlsEmails)
                        ),
                        "segmentation", Map.of(
                                "separator", separator,
                                "max_tokens", maxTokens
                        )
                ),
                "mode", mode
        ));
        return dataJson;
    }

    @Override
    public Result getRagFileList(String pageNo, String pageSize, String fileName, String ragId) throws Exception {
        log.info("调用 dify 获取知识库文件列表接口，知识库ID: " + ragId);
        try {
            // 使用 GET 方法，参数通过 URL 查询字符串传递
            HttpRequest request = HttpUtil.createGet(urlPrefix + "/document/list")
                    .header("Content-Type", "application/json")
                    .form("kb_id", ragId)
                    .form("pageNo", pageNo != null ? pageNo : "1")
                    .form("pageSize", pageSize != null ? pageSize : "20");
            // 可选参数：文件名关键词
            if (fileName != null && !fileName.trim().isEmpty()) {
                request.form("keywords", fileName);
            }
            String resultStr = request.execute().body();
            log.info("调用 dify 获取知识库文件列表接口 返回报文: " + resultStr);
            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 获取知识库文件列表接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }


    // ==========================================================================================================

    /**
     * 通过文档 ID 获取知识库 ID
     * @param documentId 文档ID
     * {
     *   "code": 0,
     *   "data": "e0b33974-287c-42cf-bf85-9aa09fa9ae59",
     *   "elapsed_s": 0.4579,
     *   "message": "\u901a\u8fc7docid\u627e\u5230\u4e86kbid"
     * }
     * @return 知识库ID
     * @throws Exception
     */
    public String getKbIdByDocumentId(String documentId) throws Exception {
        log.info("调用 dify 通过文档ID获取知识库ID接口，文档ID: " + documentId);
        try {
            String resultStr = HttpUtil.createGet(urlPrefix + "/getkbidwithdocid")
                    .header("Content-Type", "application/json")
                    .form("target_docid", documentId).execute().body();
            log.info("调用 dify 通过文档ID获取知识库ID接口 返回报文: " + resultStr);
            if (resultStr == null || resultStr.trim().isEmpty()) {
                log.warn("接口返回空内容，文档ID可能不存在: " + documentId);
                return null;
            }
            return resultStr.trim();
        } catch (Exception e) {
            log.error("调用 dify 通过文档ID获取知识库ID接口失败: " + e.getMessage(), e);
            throw new Exception("获取知识库ID失败: " + e.getMessage());
        }
    }

    // 使用 Jackson 解析
    private String extractDataOrMessage(String jsonStr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonStr);
            String data = root.get("data").asText();
            String message = root.get("message").asText();
            return data.isEmpty() ? message : data;
        } catch (Exception e) {
            return "解析失败: " + e.getMessage();
        }
    }

    // ==========================================================================================================

    @Override
    public Result ragFileParsing(List<String> docIdList, Boolean deleteFlag, Integer runModel) throws Exception {
        return null;
    }

    @Override
    public Result ragFileSwitch(String docId, Integer status) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        // 参数映射：IModelBaseService 参数 → dify API 参数
        paramMap.put("doc_id", docId);
        // 状态转换：Integer status → String action
        String action;
        if (status != null && status == 1) {
            action = "enable";
        } else {
            action = "disable";
        }
        paramMap.put("action", action);
        String findKbIdRes = getKbIdByDocumentId(docId);
        String kbId = extractDataOrMessage(findKbIdRes);
        paramMap.put("kb_id", kbId);
        // paramMap.put("kb_id", "7cfa0b54-452e-4ce0-8d5c-a71fff0bc027");  // 手动指定 db id 进行测试

        log.info("调用 dify 文档状态切换接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        try {
            String resultStr = HttpUtil.createPost(urlPrefix + "/document/change_status")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap)).execute().body();
            log.info("调用 dify 文档状态切换接口 返回报文: " + resultStr);
            // 标准的响应处理逻辑
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 文档状态切换接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }


    /**
     * 获取用户桌面路径（跨平台）
     * @return 桌面路径
     */
    private String getUserDesktopPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        if (os.contains("win")) {
            // Windows系统
            return userHome + File.separator + "Desktop";
        } else {
            // Linux/Unix/Mac系统
            return userHome + File.separator + "Desktop";
        }
    }



    @Override
    public HttpResponse downLoadRagFile(String fileId) throws Exception {
        try {
            // 第一步：通过文档ID获取知识库ID
            String findKbIdRes = getKbIdByDocumentId(fileId);
            String kbId = extractDataOrMessage(findKbIdRes);
            if (StrUtil.isEmpty(kbId)) {
                throw new Exception("无法获取文档对应的知识库ID，文档ID: " + fileId);
            }
            log.info("获取到知识库ID: " + kbId + "，文档ID: " + fileId);

            // 获取用户桌面作为默认下载目录
            String defaultDownloadDir = getUserDesktopPath();
            File downloadDirFile = new File(defaultDownloadDir);
            if (!downloadDirFile.exists()) {
                downloadDirFile.mkdirs();
                log.info("创建下载目录: " + defaultDownloadDir);
            }

            // 第二步：调用获取文件信息的接口
            String fileInfoUrl = fileUploadUrlPrefix + "/" + kbId + "/documents/" + fileId + "/upload-file";
            log.info("调用获取文件信息接口，URL: " + fileInfoUrl);

            HttpResponse infoResponse = HttpUtil.createGet(fileInfoUrl)
                    .header("Authorization", "Bearer " + bearerToken)
                    .execute();

            if (infoResponse.getStatus() != 200) {
                log.error("获取文件信息失败，HTTP状态码: " + infoResponse.getStatus());
                throw new Exception("获取文件信息失败，HTTP状态码: " + infoResponse.getStatus());
            }

            // 解析文件信息
            String infoResponseBody = infoResponse.body();
            log.info("文件信息响应: " + infoResponseBody);
            JSONObject fileInfo = JSONUtil.parseObj(infoResponseBody);

            // 获取下载信息
            String downloadUrl = fileInfo.getStr("download_url");
            String fileName = fileInfo.getStr("name");
            if (StrUtil.isEmpty(fileName)) {
                fileName = "document_" + fileId;
            }
            if (StrUtil.isEmpty(downloadUrl)) {
                throw new Exception("未找到下载链接");
            }

            // 处理相对路径URL
            if (downloadUrl.startsWith("/")) {
                downloadUrl = "http://10.30.30.97:8080" + downloadUrl;
            }

            log.info("实际下载URL: " + downloadUrl);
            log.info("文件名: " + fileName);

            // 第三步：下载实际文件
            HttpResponse downloadResponse = HttpUtil.createGet(downloadUrl).execute();
            if (downloadResponse.getStatus() != 200) {
                throw new Exception("下载文件失败，状态码: " + downloadResponse.getStatus());
            }

            // 保存文件到用户桌面
            String filePath = defaultDownloadDir + File.separator + fileName;
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                byte[] fileBytes = downloadResponse.bodyBytes();
                fos.write(fileBytes);
                fos.flush();
            }

            log.info("文件下载成功，保存路径: " + filePath);

            // 返回下载响应（保持与接口定义一致）
            return downloadResponse;

        } catch (Exception e) {
            log.error("下载文件异常", e);
            throw e;
        }
    }

    /** 下载单个文件到客户端本地的 Java 接口调用方案（旧版，做参考使用），测试命令以及接口方法如下：
    # 测试命令 test.testDownloadFileFromServer("02e54617-65ef-435f-815a-85ed6579e634", "60f9f674-2b7b-49b8-bfde-f4ac208c7872", null);
    public Result<?> downloadFileFromServer(String kbId, String documentId, String downloadDir) {
        try {
            // 获取项目根路径并创建downloads目录
            String projectRoot = System.getProperty("user.dir");
            String defaultDownloadDir = projectRoot + File.separator + "downloads";
            String actualDownloadDir = StrUtil.isNotEmpty(downloadDir) ? downloadDir : defaultDownloadDir;
            // 确保下载目录存在
            File downloadDirFile = new File(actualDownloadDir);
            if (!downloadDirFile.exists()) {
                downloadDirFile.mkdirs();
                log.info("创建下载目录: " + actualDownloadDir);
            }
            // 第一步：调用获取文件信息的接口
            String fileInfoUrl = "http://10.30.30.97:8080/v1/datasets/" + kbId + "/documents/" + documentId + "/upload-file";
            log.info("调用获取文件信息接口，URL: " + fileInfoUrl);
            HttpResponse infoResponse = HttpUtil.createGet(fileInfoUrl)
                    .header("Authorization", "Bearer " + bearerToken).execute();
            if (infoResponse.getStatus() != 200) {
                log.error("获取文件信息失败，HTTP状态码: " + infoResponse.getStatus());
                return Result.error("获取文件信息失败，HTTP状态码: " + infoResponse.getStatus());
            }
            // 解析文件信息
            String infoResponseBody = infoResponse.body();
            log.info("文件信息响应: " + infoResponseBody);
            JSONObject fileInfo = JSONUtil.parseObj(infoResponseBody);
            // 获取下载信息
            String downloadUrl = fileInfo.getStr("download_url");
            String fileName = fileInfo.getStr("name");
            if (StrUtil.isEmpty(fileName)) {
                fileName = "document_" + documentId;
            }
            if (StrUtil.isEmpty(downloadUrl)) {
                return Result.error("未找到下载链接");
            }
            // 处理相对路径URL（模拟Python逻辑）
            if (downloadUrl.startsWith("/")) {
                downloadUrl = "http://10.30.30.97:8080" + downloadUrl;
            }
            log.info("实际下载URL: " + downloadUrl);
            log.info("文件名: " + fileName);
            // 第二步：下载实际文件
            HttpResponse downloadResponse = HttpUtil.createGet(downloadUrl).execute();
            if (downloadResponse.getStatus() != 200) {
                return Result.error("下载文件失败，状态码: " + downloadResponse.getStatus());
            }
            // 保存文件
            String filePath = actualDownloadDir + File.separator + fileName;
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                byte[] fileBytes = downloadResponse.bodyBytes();
                fos.write(fileBytes);
                fos.flush();
            }
            log.info("文件下载成功，保存路径: " + filePath);
            // 构造返回数据（模拟Python返回格式）
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("file_path", filePath);
            resultData.put("file_info", fileInfo);
            resultData.put("download_url", downloadUrl);
            return Result.OK("文档已成功下载到: " + filePath, resultData);
        } catch (Exception e) {
            log.error("下载文件异常", e);
            return Result.error("下载失败: " + e.getMessage());
        }
    }
    */


    @Override
    public Result deleteRagFile(List<String> docIdList) throws Exception {
        if (docIdList == null || docIdList.isEmpty()) {
            return Result.error("文档ID列表不能为空");
        }
        try {
            // 第一步：通过第一个文档ID获取知识库ID（假设同一批次删除的文档都在同一个知识库中）
            String findKbIdRes = getKbIdByDocumentId(docIdList.get(0));
            String kbId = extractDataOrMessage(findKbIdRes);
            if (StrUtil.isEmpty(kbId)) {
                throw new Exception("无法获取文档对应的知识库ID，文档ID: " + docIdList.get(0));
            }
            log.info("获取到知识库ID: " + kbId + "，准备删除文档: " + docIdList);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("kb_id", kbId);
            // 程序服务内部会统一将其转换为 doc id str list；如果只有一个文档，传递字符串；如果多个文档，传递列表；
            // 此过程是为了统一，非必须，因为到开始调用水务服务时，传入的一定是 docIdList，这是此接口要求的
            if (docIdList.size() == 1) {
                paramMap.put("doc_id", docIdList.get(0));
            } else {
                paramMap.put("doc_id", docIdList);
            }

            log.info("调用 dify 删除文档接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
            String resultStr = HttpUtil.createRequest(Method.DELETE, urlPrefix + "/document/rm")
                    .header("Content-Type", "application/json").body(JSONUtil.toJsonStr(paramMap)).execute().body();
            log.info("调用 dify 删除文档接口 返回报文: " + resultStr);
            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 删除文档接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result getDocumentSlicingList(String pageNo, String pageSize, String docId, String keyWord) throws Exception {
        try {
            // 第一步：通过文档ID获取知识库ID
            String findKbIdRes = getKbIdByDocumentId(docId);
            String kbId = extractDataOrMessage(findKbIdRes);
            if (StrUtil.isEmpty(kbId)) {
                throw new Exception("无法获取文档对应的知识库ID，文档ID: " + docId);
            }
            log.info("获取到知识库ID: " + kbId + "，文档ID: " + docId);
            // 使用 GET 方法，参数通过 URL 查询字符串传递
            HttpRequest request = HttpUtil.createGet(urlPrefix + "/chunk/list")
                    .header("Content-Type", "application/json")
                    .form("kb_id", kbId)
                    .form("doc_id", docId)
                    .form("page", pageNo != null ? pageNo : "1")
                    .form("size", pageSize != null ? pageSize : "100");

            // 可选参数：关键词
            if (keyWord != null && !keyWord.trim().isEmpty()) {
                request.form("keywords", keyWord);
            }
            String resultStr = request.execute().body();
            log.info("调用 dify 获取文档分段列表接口 返回报文: " + resultStr);
            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 获取文档分段列表接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result documentSlicingStatusSwitch(Integer status, String docId, List<String> slicingIdList) throws Exception {
        try {
            // 第一步：通过文档ID获取知识库ID
            String findKbIdRes = getKbIdByDocumentId(docId);
            String kbId = extractDataOrMessage(findKbIdRes);
            if (StrUtil.isEmpty(kbId)) {
                throw new Exception("无法获取文档对应的知识库ID，文档ID: " + docId);
            }
            log.info("获取到知识库ID: " + kbId + "，文档ID: " + docId);

            // 构建请求参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("kb_id", kbId);
            paramMap.put("doc_id", docId);
            paramMap.put("chunk_ids", slicingIdList);
            paramMap.put("available_int", status != null ? status : 0);

            log.info("调用 dify 文档分段状态切换接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
            // 使用 POST 方法调用 dify 接口
            String resultStr = HttpUtil.createPost(urlPrefix + "/chunk/switch")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap)).execute().body();

            log.info("调用 dify 文档分段状态切换接口 返回报文: " + resultStr);

            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }

            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }

            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);

            // 转换为标准 Result 格式
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));

            return result;

        } catch (Exception e) {
            log.error("调用 dify 文档分段状态切换接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    @Override
    public Result getLargeModelList() throws Exception {
        log.info("调用 dify 获取模型列表接口");

        try {
            // 使用 GET 方法，默认查询 llm 类型的模型
            String resultStr = HttpUtil.createGet(urlPrefix + "/llm/my_llms")
                    .header("Content-Type", "application/json")
                    .form("model_type", "llm")
                    .execute().body();

            log.info("调用 dify 获取模型列表接口 返回报文: " + resultStr);

            // 检查返回内容格式
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }

            // 解析响应
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 获取模型列表接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

    // 解析 model list 的返回结果，返回的是 dict
    private List<Map<String, String>> extractProviderAndModel(String jsonStr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode arrayNode = mapper.readTree(jsonStr);

            List<Map<String, String>> result = new ArrayList<>();
            for (JsonNode node : arrayNode) {
                String provider = node.get("provider").asText();
                JsonNode modelsNode = node.get("models");
                for (JsonNode modelNode : modelsNode) {
                    String model = modelNode.get("model").asText();
                    Map<String, String> item = new HashMap<>();
                    item.put("provider", provider);
                    item.put("model", model);
                    result.add(item);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // 解析 provider with model name
    private String findProviderByModel(String modelName, List<Map<String, String>> providerModelList) {
        for (Map<String, String> item : providerModelList) {
            if (modelName.equals(item.get("model"))) {
                return item.get("provider");
            }
        }
        return null; // 未找到返回 null
    }

    @Override
    public Result choiceLargeModel(String largeModelName) throws Exception {
        try {
            // 第一步：调用 getLargeModelList 获取模型列表
            Result modelListResult = getLargeModelList();
            if (modelListResult == null || modelListResult.getCode() != 0) {
                throw new Exception("获取模型列表失败: " + (modelListResult != null ? modelListResult.getMessage() : "返回为空"));
            }
            // 第二步：解析模型列表，获取 provider-model 列表
            Object resultData = modelListResult.getResult();
            if (resultData == null) {
                throw new Exception("模型列表数据为空");
            }
            String jsonStr = JSONUtil.toJsonStr(resultData);
            List<Map<String, String>> providerModelList = extractProviderAndModel(jsonStr);
            // 第三步：查找 provider
            String provider = findProviderByModel(largeModelName, providerModelList);
            if (provider == null) {
                throw new Exception("未找到模型 " + largeModelName + " 对应的 provider");
            }
            log.info("找到模型 " + largeModelName + " 对应的 provider: " + provider);
            // 第四步：构建请求参数并调用 dify API
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("model_type", "llm");
            paramMap.put("provider", provider);
            paramMap.put("model", largeModelName);
            log.info("调用 dify 设置默认模型接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
            String resultStr = HttpUtil.createPost(urlPrefix + "/user/set_tenant_info")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap)).execute().body();

            log.info("调用 dify 设置默认模型接口 返回报文: " + resultStr);
            // 标准响应处理
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }
            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }
            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 设置默认模型接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }



    @Override
    public Result choiceRag(ChoiceRagBO choiceRagBO) throws Exception {
        return null;
    }


    // 解析第一轮会话返回的 conversation_id
    private String extractConversationId(String jsonStr) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            return jsonObject.getStr("conversation_id");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result modelDialogue(String conversationId, List<MessageBO> messageBOList) throws Exception {
        if (messageBOList == null || messageBOList.isEmpty()) {
            return Result.error("消息列表不能为空");
        }

        Map<String, Object> paramMap = new HashMap<>();

        // 从第一个 MessageBO 中提取 dify API 需要的参数
        MessageBO firstMessage = messageBOList.get(0);

        // user_id：测试阶段使用时间戳
        String userId = firstMessage.getUser_id();
        if (userId == null || userId.trim().isEmpty()) {
            userId = String.valueOf(System.currentTimeMillis());
        }
        paramMap.put("user_id", userId);

        // kb_id：知识库ID
        if (firstMessage.getKb_id() != null && !firstMessage.getKb_id().trim().isEmpty()) {
            paramMap.put("kb_id", firstMessage.getKb_id());
        } else {
            return Result.error("知识库ID不能为空");
        }

        System.out.println("================================");
        System.out.println(firstMessage.getKb_id());

        // streaming：是否流式输出，默认false
        paramMap.put("streaming", firstMessage.getStreaming() != null ? firstMessage.getStreaming() : false);

        // conversation_id 处理：首次对话为空，后续对话必填
        if (conversationId != null && !conversationId.trim().isEmpty()) {
            paramMap.put("conversation_id", conversationId);
        }

        // message 参数：直接使用第一个消息的 content 作为 dify API 的 message 参数
        paramMap.put("message", firstMessage.getContent());

        log.info("调用 dify 模型对话接口 发送报文: " + JSONUtil.toJsonStr(paramMap));
        try {
            // 调用 dify 对话接口
            String resultStr = HttpUtil.createPost(urlPrefix + "/conversation/completion_db")
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap)).execute().body();

            log.info("调用 dify 模型对话接口 返回报文: " + resultStr);

            // 标准响应处理
            if (resultStr == null || resultStr.trim().isEmpty()) {
                return Result.error("接口返回空内容");
            }

            if (resultStr.trim().startsWith("<")) {
                return Result.error("接口调用失败，返回HTML错误页面");
            }

            if (!resultStr.trim().startsWith("{") && !resultStr.trim().startsWith("[")) {
                return Result.error("接口返回非JSON格式内容");
            }
            JSONObject responseJson = JSONUtil.parseObj(resultStr);
            Result result = new Result();
            result.setCode(responseJson.getInt("code", 0));
            result.setMessage(responseJson.getStr("message", ""));
            result.setResult(responseJson.get("data"));
            return result;
        } catch (Exception e) {
            log.error("调用 dify 模型对话接口失败: " + e.getMessage(), e);
            return Result.error("调用 dify 接口失败: " + e.getMessage());
        }
    }

}