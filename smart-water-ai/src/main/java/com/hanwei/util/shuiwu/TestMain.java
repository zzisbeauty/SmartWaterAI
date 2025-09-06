package com.hanwei.util.shuiwu;

import com.hanwei.rag.entity.RagInfo;
import com.hanwei.rag.vo.RagRecallVO;

import java.io.File;
import com.hanwei.core.common.api.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import com.hanwei.rag.bo.MessageBO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import cn.hutool.json.JSONObject;
import cn.hutool.http.HttpResponse;
//import org.apache.http.HttpResponse;

import java.util.List;
import java.util.Arrays;

/**
 * @author Test
 * @description shuiwu 接口测试入口
 * @createTime 2025/8/25
 */
public class TestMain {

    public static void main(String[] args) {
        System.out.println("=== Shuiwu 接口测试入口 ===");
        PublicHelperFunc publicHelper = new PublicHelperFunc();
        publicHelper.setUrlPrefix("http://10.0.15.21:5627/hanwei/v1");
        publicHelper.setBearerToken("dataset-T2Hi8kmyqQCMmh6ZEtwOV2Xt");
        publicHelper.setFileUploadUrlPrefix("http://10.30.30.97:8080/v1/datasets");

        // testAddRagInfo(publicHelper);
        // testEditRagInfo(publicHelper);
        // testDeleteRagInfo(publicHelper);
         testGetRagInfoList(publicHelper);
        // testGetRagInfoDetail(publicHelper);
        // testRagRecall(publicHelper);
        // testRagFileUpload(publicHelper);
        // testGetRagFileList(publicHelper);
        // testRagFileSwitch(publicHelper);

        // testGetKbIdByDocumentId(publicHelper);   // 测试获取 kb id with doc id
        // testDownLoadRagFile(publicHelper);

        // testDeleteRagFile(publicHelper);

        // testGetDocumentSlicingList(publicHelper);

        // testDocumentSlicingStatusSwitch(publicHelper);

        // testGetLargeModelList(publicHelper);

        // testChoiceLargeModel(publicHelper);

        // testModelDialogue(publicHelper);

        System.out.println("=== 所有测试完成 ===");
    }

    /**
     * 测试创建知识库接口
     */
    private static void testAddRagInfo(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.addRagInfo 方法...");

        try {
            // 测试知识库名称
            String testRagName = "测试知识库_" + System.currentTimeMillis();
            System.out.println("测试知识库名称: " + testRagName);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.addRagInfo(testRagName);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 知识库创建成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 知识库创建失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.addRagInfo 方法测试完成\n");
    }


    private static void testEditRagInfo(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.editRagInfo 方法...");

        try {
            // 创建测试用的 RagInfo 对象，使用 dify API 的默认值
            RagInfo ragInfo = new RagInfo();
            ragInfo.setId("cf767ccf-7127-47ba-8cd3-71da36ff829d"); // 需要使用实际存在的知识库ID
            ragInfo.setName(""); // 可选，空值
            ragInfo.setDescription(""); // 可选，空值
            ragInfo.setIndexingTechnique("high_quality");
            ragInfo.setEmbdId("");
            ragInfo.setEmbeddingProviderName("");  // 目前 python 接口内部指定了模型，因为本地只有一个 embedding，因此这里是空没事
            ragInfo.setSearchMethod("hybrid_search");
            ragInfo.setVectorSimilarityWeight(0.8);
            ragInfo.setTopN(15);
            ragInfo.setSimilarityThreshold(0.66);
            ragInfo.setScoreThresholdEnabled(false);
            ragInfo.setRerankingEnable(false);
            ragInfo.setRerankingMode("weight_score");
            ragInfo.setRerankingModelName("");
            ragInfo.setRerankingProviderName("");

            Result result = publicHelper.editRagInfo(ragInfo);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 知识库编辑成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 知识库编辑失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.editRagInfo 方法测试完成\n");
    }


    /**
     * 测试删除知识库接口
     */
    private static void testDeleteRagInfo(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.deleteRagInfo 方法...");

        try {
            // 测试知识库ID（需要使用实际存在的知识库ID）
            String testKbId = "cf767ccf-7127-47ba-8cd3-71da36ff829d";
            System.out.println("测试知识库ID: " + testKbId);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.deleteRagInfo(testKbId);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 知识库删除成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 知识库删除失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.deleteRagInfo 方法测试完成\n");
    }


    /**
     * 测试获取知识库列表接口
     */
    private static void testGetRagInfoList(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.getRagInfoList 方法...");

        try {
            // 测试参数
            String pageNo = "1";
            String pageSize = "1";
            String ragName = ""; // 可以设置为空或具体的搜索关键词

            System.out.println("测试参数 - pageNo: " + pageNo + ", pageSize: " + pageSize + ", ragName: " + ragName);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.getRagInfoList(pageNo, pageSize, ragName);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 获取知识库列表成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 获取知识库列表失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.getRagInfoList 方法测试完成\n");
    }

    /**
     * 测试获取知识库详情接口
     */
    private static void testGetRagInfoDetail(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.getRagInfoDetail 方法...");

        try {
            // 测试知识库ID（需要使用实际存在的知识库ID）
            String testKbId = "a26e10d8-c37e-44a5-afbc-e21b2314c1e2";
            System.out.println("测试知识库ID: " + testKbId);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.getRagInfoDetail(testKbId);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 获取知识库详情成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 获取知识库详情失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.getRagInfoDetail 方法测试完成\n");
    }

    /**
     * 测试知识库召回接口
     */
    private static void testRagRecall(PublicHelperFunc publicHelper) {
        System.out.println("\n开始测试 PublicHelper.ragRecall 方法...");

        try {
            // 创建测试用的 RagRecallVO 对象
            RagRecallVO ragRecallVO = new RagRecallVO();
            ragRecallVO.setRagId("e5176734-ead9-44cf-8bd6-124bc73564e0"); // 需要使用实际存在的知识库ID
            ragRecallVO.setQuestion("肥胖的黄蜂伏在菜花上");
            ragRecallVO.setSearch_method("semantic_search");
            ragRecallVO.setVectorSimilarityWeight(0.6);
            ragRecallVO.setSimilarityThreshold(0.2);
            ragRecallVO.setTopN(3);
            ragRecallVO.setReranking_enable(false);
            ragRecallVO.setScore_threshold_enabled(false);
            System.out.println("测试参数 - ragId: " + ragRecallVO.getRagId() + ", question: " + ragRecallVO.getQuestion());
            Result result = publicHelper.ragRecall(ragRecallVO);
            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 知识库召回成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 知识库召回失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }
        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.ragRecall 方法测试完成\n");
    }

    /**
     * 测试文件上传接口
     */
    private static void testRagFileUpload(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试直接上传文件到知识库 ---");
        String testKbId = "7cfa0b54-452e-4ce0-8d5c-a71fff0bc027";
        String testFilePath = "E:\\manager\\数据相关\\data-demodata\\从百草园到三味书屋.txt";
        System.out.println("知识库ID: " + testKbId);
        System.out.println("文件路径: " + testFilePath);
        try {
            // 创建 File 对象并转换为 MultipartFile
            File file = new File(testFilePath);
            if (!file.exists()) {
                System.out.println("❌ 文件不存在: " + testFilePath);
                return;
            }
            System.out.println("文件信息 - 名称: " + file.getName() + ", 大小: " + file.length() + " bytes");
            // 使用项目中的文件处理方式创建 MultipartFile
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes = fis.readAllBytes();
            fis.close();
            // 创建一个简单的 MultipartFile 实现
            MultipartFile multipartFile = new MultipartFile() {
                @Override
                public String getName() {
                    return "file";
                }

                @Override
                public String getOriginalFilename() {
                    return file.getName();
                }

                @Override
                public String getContentType() {
                    return "text/plain";
                }

                @Override
                public boolean isEmpty() {
                    return fileBytes.length == 0;
                }

                @Override
                public long getSize() {
                    return fileBytes.length;
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return fileBytes;
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return new ByteArrayInputStream(fileBytes);
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {
                    try (FileOutputStream fos = new FileOutputStream(dest)) {
                        fos.write(fileBytes);
                    }
                }
            };

            // 调用 PublicHelper 的实际方法
            Result result = publicHelper.ragFileUpload(testKbId, multipartFile);
            System.out.println("结果: " + (result.isSuccess() ? "✅ 成功" : "❌ 失败"));
            if (result.getResult() != null) {
                System.out.println("返回数据: " + result.getResult());
            }
        } catch (Exception e) {
            System.out.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PublicHelper.ragFileUpload 方法测试完成\n");
    }

    /**
     * 测试获取知识库文件列表接口
     */
    private static void testGetRagFileList(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试获取知识库文件列表 ---");
        try {
            // 测试参数
            String testKbId = "7cfa0b54-452e-4ce0-8d5c-a71fff0bc027";
            String pageNo = "1";
            String pageSize = "10";
            String fileName = ""; // 可以设置为空或具体的搜索关键词
            System.out.println("测试参数 - ragId: " + testKbId + ", pageNo: " + pageNo + ", pageSize: " + pageSize + ", fileName: " + fileName);
            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.getRagFileList(pageNo, pageSize, fileName, testKbId);
            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 获取知识库文件列表成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 获取知识库文件列表失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }
        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PublicHelper.getRagFileList 方法测试完成\n");
    }

    /**
     * 测试文档状态切换接口
     */
    private static void testRagFileSwitch(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试文档状态切换 ---");
        try {
            String testDocId = "550b8df8-e200-4439-b38c-6b982e4256ac";
            Integer status = 0; // 1=启用, 0=禁用
            System.out.println("测试参数 - docId: " + testDocId + ", status: " + status);
            Result result = publicHelper.ragFileSwitch(testDocId, status);
            System.out.println("结果: " + (result.isSuccess() ? "✅ 成功" : "❌ 失败"));
            if (result.getResult() != null) {
                System.out.println("返回数据: " + result.getResult());
            }
        } catch (Exception e) {
            System.out.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PublicHelper.ragFileSwitch 方法测试完成\n");
    }


    // 测试通过文档ID获取知识库ID接口
    private static void testGetKbIdByDocumentId(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试通过文档ID获取知识库ID ---");
        try {
            // 测试参数 - 使用一个实际存在的文档ID # 92e25c68-ab36-4727-8ab2-a7fbba9c40a3
            String testDocId = "92e25c68-ab36-4727-8ab2-a7fbba9c40a3"; // 需要替换为实际的文档ID
            System.out.println("测试文档ID: " + testDocId);
            // 由于 getKbIdByDocumentId 是私有方法，需要将其改为 public 或者通过反射调用
            // 建议临时将方法改为 public 进行测试
            String kbId = publicHelper.getKbIdByDocumentId(testDocId);
            if (kbId != null && !kbId.trim().isEmpty()) {
                System.out.println("✅ 获取知识库ID成功: " + kbId);
            } else {
                System.out.println("❌ 未找到对应的知识库ID或返回为空");
            }
        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("testGetKbIdByDocumentId 方法测试完成\n");
    }






    /**
     * 测试文件下载接口
     */
    private static void testDownLoadRagFile(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试文件下载 ---");
        try {
            // 测试参数 - 使用实际的文档ID
            String testFileId = "92e25c68-ab36-4727-8ab2-a7fbba9c40a3";
            System.out.println("测试参数 - fileId: " + testFileId);
            // 调用 PublicHelper 中实际实现的方法 - 这里会抛出异常
            HttpResponse response = publicHelper.downLoadRagFile(testFileId);
            // 如果没有抛出异常，检查响应
            if (response != null && response.getStatus() == 200) {
                System.out.println("✅ 文件下载成功");
                System.out.println("文件已保存到用户桌面");
            } else {
                System.out.println("❌ 文件下载失败，HTTP状态码: " + (response != null ? response.getStatus() : "null"));
            }
        } catch (Exception e) {
            // 正确处理方法抛出的异常
            System.out.println("❌ 文件下载过程中抛出异常: " + e.getMessage());
            // 根据异常类型提供更详细的信息
            if (e.getMessage().contains("无法获取文档对应的知识库ID")) {
                System.out.println("   原因：文档ID不存在或无法找到对应的知识库");
            } else if (e.getMessage().contains("获取文件信息失败")) {
                System.out.println("   原因：文件信息接口调用失败");
            } else if (e.getMessage().contains("未找到下载链接")) {
                System.out.println("   原因：文件信息中缺少下载URL");
            } else if (e.getMessage().contains("下载文件失败")) {
                System.out.println("   原因：文件下载请求失败");
            }
            // 可选：打印完整的异常堆栈用于调试
            // e.printStackTrace();
        }
        System.out.println("PublicHelper.downLoadRagFile 方法测试完成\n");
    }

    /**
     * 测试删除文档接口
     */
    private static void testDeleteRagFile(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试删除文档 ---");
        try {
            // 测试参数 - 单个文档删除 - 转化成了 doc id list
            List<String> singleDocList = Arrays.asList("1ead7466-dd54-4caf-a0a4-dd1e3174901f");
            System.out.println("测试单个文档删除 - docIds: " + singleDocList);

            Result result1 = publicHelper.deleteRagFile(singleDocList);
            System.out.println("单个删除结果: " + (result1.isSuccess() ? "✅ 成功" : "❌ 失败"));
            if (result1.getResult() != null) {
                System.out.println("返回数据: " + result1.getResult());
            }
            // 测试参数 - 批量文档删除 - 直接就是 doc id list
            List<String> multipleDocList = Arrays.asList("doc_id_1", "doc_id_2", "doc_id_3");
            System.out.println("测试批量文档删除 - docIds: " + multipleDocList);

            Result result2 = publicHelper.deleteRagFile(multipleDocList);
            System.out.println("批量删除结果: " + (result2.isSuccess() ? "✅ 成功" : "❌ 失败"));
            if (result2.getResult() != null) {
                System.out.println("返回数据: " + result2.getResult());
            }
        } catch (Exception e) {
            System.out.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PublicHelper.deleteRagFile 方法测试完成\n");
    }

    /**
     * 测试获取文档分段列表接口
     */
    private static void testGetDocumentSlicingList(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试获取文档分段列表 ---");

        try {
            // 测试参数
            String testDocId = "550b8df8-e200-4439-b38c-6b982e4256ac";
            String pageNo = "1";
            String pageSize = "10";
            String keyWord = ""; // 可以设置为空或具体的搜索关键词

            System.out.println("测试参数 - docId: " + testDocId + ", pageNo: " + pageNo + ", pageSize: " + pageSize + ", keyWord: " + keyWord);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.getDocumentSlicingList(pageNo, pageSize, testDocId, keyWord);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 获取文档分段列表成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 获取文档分段列表失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.getDocumentSlicingList 方法测试完成\n");
    }

    /**
     * 测试文档分段状态切换接口
     */
    private static void testDocumentSlicingStatusSwitch(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试文档分段状态切换 ---");
        try {
            // 测试参数
            String testDocId = "48bc77c2-b5c5-4115-98e6-8ed9834f1d52";
            Integer status = 1; // 1=启用, 0=禁用
            List<String> slicingIdList = Arrays.asList("ca619ac2-b109-4982-a53b-efbad5664f50", "60090f88-cd49-49b1-b668-c9dec0dcb5cc");
            System.out.println("测试参数 - docId: " + testDocId + ", status: " + status + ", slicingIds: " + slicingIdList);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.documentSlicingStatusSwitch(status, testDocId, slicingIdList);
            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 文档分段状态切换成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 文档分段状态切换失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.documentSlicingStatusSwitch 方法测试完成\n");
    }

    /**
     * 测试获取模型列表接口
     */
    private static void testGetLargeModelList(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试获取模型列表 ---");

        try {
            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.getLargeModelList();

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 获取模型列表成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 获取模型列表失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.getLargeModelList 方法测试完成\n");
    }

    /**
     * 测试设置默认模型接口
     */
    private static void testChoiceLargeModel(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试设置默认模型 ---");

        try {
            // 测试参数 - 使用实际的模型名称
            String testModelName = "DeepSeek-R1-Distill-Llama-70B";

            System.out.println("测试参数 - largeModelName: " + testModelName);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.choiceLargeModel(testModelName);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 设置默认模型成功，返回数据: " + result.getResult());
                } else {
                    System.out.println("❌ 设置默认模型失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("PublicHelper.choiceLargeModel 方法测试完成\n");
    }


    /**
     * 测试对话接口; 此方法作废，保持 bo 不变的话，就要删掉 bo 中多加的属性，因此这里的方法不再可用
    private static void testModelDialogue(PublicHelperFunc publicHelper) {
        System.out.println("\n--- 测试第一轮模型对话 ---");
        try {
            // 创建第一轮对话消息
            MessageBO userMessage = new MessageBO();
            userMessage.setContent("三味书屋的匾额下方都有什么");
            userMessage.setRole("user");
            userMessage.setKb_id("e5176734-ead9-44cf-8bd6-124bc73564e0"); // 需要实际的知识库ID
            userMessage.setStreaming(false);
            // user_id 会在方法内部自动设置为时间戳
            List<MessageBO> messageList = Arrays.asList(userMessage);
            // 第一轮对话，conversation_id 为空
            String conversationId = null;
            System.out.println("测试参数 - conversationId: " + conversationId + ", messages: " + messageList.size() + "条");
            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.modelDialogue(conversationId, messageList);
            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 0) {
                    System.out.println("✅ 第一轮对话成功，返回数据: " + result.getResult());
                    // 从返回结果中提取 conversation_id 用于后续对话
                    if (result.getResult() instanceof JSONObject) {
                        JSONObject resultData = (JSONObject) result.getResult();
                        String newConversationId = resultData.getStr("conversation_id");
                        System.out.println("获取到 conversation_id: " + newConversationId + "，可用于后续对话");
                    }
                } else {
                    System.out.println("❌ 第一轮对话失败: " + result.getMessage());
                }
            } else {
                System.out.println("❌ 返回结果为空");
            }
        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PublicHelper.modelDialogue 方法测试完成\n");
    }
     */

}