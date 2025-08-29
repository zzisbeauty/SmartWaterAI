package com.hanwei.util.shuiwu;

import com.hanwei.core.common.api.vo.Result;
import com.hanwei.core.util.SpringContextUtils;

/**
 * @author Test
 * @description shuiwu 接口测试入口 - 一种基于 SpringBoot 上下文进行测试的方法，需要 spring boot 服务启动才可以正常运行
 * @createTime 2025/8/25
 */
public class TestMainV3 {

    public static void main(String[] args) {
        System.out.println("=== Shuiwu 接口测试入口 ===");

        try {
            // 通过 Spring 容器获取 PublicHelper 实例
            PublicHelper publicHelper = SpringContextUtils.getBean(PublicHelper.class);

            // 调用各个测试方法
            testAddRagInfo(publicHelper);



            // 后续添加新接口测试时，在这里添加新的调用即可
            // testEditRagInfo(publicHelper);
            // testDeleteRagInfo(publicHelper);

        } catch (Exception e) {
            System.out.println("❌ 获取 PublicHelper 实例失败: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== 所有测试完成 ===");
    }

    /**
     * 测试创建知识库接口
     */
    private static void testAddRagInfo(PublicHelper publicHelper) {
        System.out.println("\n开始测试 PublicHelper.addRagInfo 方法...");

        try {
            // 测试知识库名称
            String testRagName = "测试知识库_" + System.currentTimeMillis();
            System.out.println("测试知识库名称: " + testRagName);

            // 调用 PublicHelper 中实际实现的方法
            Result result = publicHelper.addRagInfo(testRagName);

            if (result != null) {
                System.out.println("测试结果 - Code: " + result.getCode() + ", Message: " + result.getMessage());
                if (result.getCode() == 200) {
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
}