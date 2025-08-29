package com.hanwei.util;

import cn.hutool.http.HttpResponse;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.rag.bo.ChoiceRagBO;
import com.hanwei.rag.bo.MessageBO;
import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.vo.RagRecallVO;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [公共基座服务接口]
 * @createTime : [2025/8/14 19:29]
 */
public interface IModelBaseService {
    //================================公共方法=========================================/

    /**
     * 将其他类型的返回值转换为Result类型
     * @param otherResult
     * @return
     */
    public Result changeResult(Object otherResult);

    //================================公共方法=========================================/

    //================================知识库管理=========================================/

    /**
     * 创建知识库
     * 接口参数
     * @param ragName
     * @return
     */
    public Result addRagInfo(String ragName) throws Exception;

    /**
     * 更新知识库
     *
     * @param ragInfo 知识库实体类
     * @return
     */
    public Result editRagInfo(RagInfo ragInfo) throws Exception;

    /**
     * 知识库ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Result deleteRagInfo(String id) throws Exception;

    /**
     * 获取知识库列表
     *
     * @param pageNo
     * @param pageSize
     * @param ragName
     * @return
     * @throws Exception
     */
    public Result getRagInfoList(String pageNo, String pageSize, String ragName) throws Exception;

    /**
     * 获取知识库详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Result getRagInfoDetail(String id) throws Exception;

    /**
     * 查询知识库知识图谱
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Result getRagGraphInfo(String id) throws Exception;

    /**
     * 召回测试
     *
     * @param ragRecallVo
     * @return
     * @throws Exception
     */
    public Result ragRecall(RagRecallVO ragRecallVo) throws Exception;


    //================================知识库管理=========================================/


    //================================知识库文件管理=========================================/

    /**
     * 知识库文件上传
     *
     * @param ragId
     * @param file
     * @return
     * @throws Exception
     */
    public Result ragFileUpload(String ragId, MultipartFile file) throws Exception;

    /**
     * 知识库文件查询
     *
     * @param pageNo
     * @param pageSize
     * @param fileName
     * @param ragId
     * @return
     * @throws Exception
     */
    public Result getRagFileList(String pageNo, String pageSize, String fileName, String ragId) throws Exception;

    /**
     * 知识库文件解析
     *
     * @param docIdList
     * @param deleteFlag 是否清除之前的分块
     * @param runModel   模式 1:文件解析 2:终止解析
     * @return
     * @throws Exception
     */
    public Result ragFileParsing(List<String> docIdList, Boolean deleteFlag, Integer runModel) throws Exception;

    /**
     * 知识库文件状态切换
     * @param docId
     * @param status
     * @return
     * @throws Exception
     */
    public Result ragFileSwitch(String docId, Integer status) throws Exception;

    /**
     * 知识库文件下载
     *
     * @param fileId
     * @return
     * @throws Exception
     */
    public HttpResponse downLoadRagFile(String fileId) throws Exception;

    /**
     * 知识库文件删除
     * 水务此接口说明文档：
     *    1.删除的文档都必须在一个db id中，因为程序内部要根据某个的doc id获取db id，水务此接口需要此db id，因此程序内部会去查询db id
     * @param docIdList
     * @return
     * @throws Exception
     */
    public Result deleteRagFile(List<String> docIdList) throws Exception;


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
    public Result getDocumentSlicingList(String pageNo, String pageSize, String docId, String keyWord) throws Exception;

    /**
     * 文档切片状态切换
     *
     * @param status        1 启用 0 禁用
     * @param docId
     * @param slicingIdList
     * @return
     * @throws Exception
     */
    public Result documentSlicingStatusSwitch(Integer status, String docId, List<String> slicingIdList) throws Exception;


    //================================知识库文件管理=========================================/


    //================================大模型管理=========================================/

    /**
     * 获取模型列表
     *
     * @return
     * @throws Exception
     */
    public Result getLargeModelList() throws Exception;

    /**
     * 设置模型
     * 水务模型相关
     *    目前只允许系应该 LLM、其他 rerank、embedding 暂时不要处理；其实也不是不能实现，就是 llm 是用户最常见的修改位置
     * @param largeModelName
     * @return
     * @throws Exception
     */
    public Result choiceLargeModel(String largeModelName) throws Exception;

    //设置知识库

    /**
     * 设置知识库
     * 水务不存在此接口，直接选择知识库，直接对话
     * @param choiceRagBO
     * @return
     * @throws Exception
     */
    public Result choiceRag(ChoiceRagBO choiceRagBO) throws Exception;

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
    public Result modelDialogue(String conversationId, List<MessageBO> messageBOList) throws Exception;

    //================================大模型知识库调用=========================================/
}
