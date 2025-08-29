package com.hanwei.util.shuiwu;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Test
 * @description shuiwu 接口测试入口
 * @createTime 2025/8/25
 */
public class TestMainV2 {

    // 配置信息
    private static final String URL_PREFIX = "http://10.0.15.21:5627/hanwei";
    private static final String BEARER_TOKEN = "dataset-T2Hi8kmyqQCMmh6ZEtwOV2Xt";

    public static void main(String[] args) {
        System.out.println("=== Shuiwu 接口测试入口 ===");
        System.out.println("1. 测试 addRagInfo (创建知识库)");
        System.out.println("2. 测试其他接口 (待添加)");
        System.out.println("0. 退出");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n请选择要测试的接口 (输入数字): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    testAddRagInfo(); break;
                case "0":
                    System.out.println("退出测试程序"); return;
                default:
                    System.out.println("无效选择，请重新输入"); break;
            }
        }
    }

    /**
     * 测试创建知识库接口
     */
    private static void testAddRagInfo() {
        System.out.println("\n=== 测试 addRagInfo 方法 ===");

        // 测试知识库名称
        String testRagName = "测试知识库_" + System.currentTimeMillis();
        System.out.println("测试知识库名称: " + testRagName);

        try {
            // 构建请求参数
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("name", testRagName);
            paramMap.put("description", "");
            paramMap.put("indexing_technique", "high_quality");
            paramMap.put("search_method", "semantic_search");
            paramMap.put("provider", "vendor");
            paramMap.put("embedding_model", "");
            paramMap.put("embedding_provider_name", "");
            paramMap.put("reranking_enable", false);
            paramMap.put("weights", 0.8);
            paramMap.put("score_threshold_enabled", true);
            paramMap.put("score_threshold", 0.3);
            paramMap.put("top_k", 3);

            System.out.println("发送请求参数: " + JSONUtil.toJsonStr(paramMap));

            // 调用 dify 接口
            String resultStr = HttpUtil.createPost(URL_PREFIX + "/kb/create")
                    .header("Authorization", "Bearer " + BEARER_TOKEN)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute().body();

            System.out.println("接口返回结果: " + resultStr);

            // 解析响应并输出结果
            cn.hutool.json.JSONObject response = JSONUtil.parseObj(resultStr);
            int code = response.getInt("code", 0);
            String message = response.getStr("message", "");
            Object data = response.get("data");

            if (code == 0) {
                System.out.println("✅ 测试成功！");
                System.out.println("返回码: " + code);
                System.out.println("消息: " + message);
                System.out.println("数据: " + data);
            } else {
                System.out.println("❌ 测试失败！");
                System.out.println("错误码: " + code);
                System.out.println("错误消息: " + message);
            }

        } catch (Exception e) {
            System.out.println("❌ 测试过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== addRagInfo 测试完成 ===");
    }

    // 后续可以添加其他接口的测试方法
    // private static void testEditRagInfo() { ... }
    // private static void testDeleteRagInfo() { ... }
    // private static void testGetRagInfoList() { ... }
}