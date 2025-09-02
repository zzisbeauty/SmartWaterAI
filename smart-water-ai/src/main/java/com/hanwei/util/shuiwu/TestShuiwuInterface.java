package com.hanwei.util.shuiwu;

import com.hanwei.core.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Test
 * @description 测试 PublicHelper 中的 shuiwu 接口方法
 * @createTime 2025/8/25
 */
@Component
@Slf4j
public class TestShuiwuInterface implements CommandLineRunner {
    @Autowired
    private PublicHelperFunc publicHelper;

    @Override
    public void run(String... args) throws Exception {
        // 可以通过启动参数控制是否执行测试
        if (args.length > 0 && "test-shuiwu".equals(args[0])) {
            testAddRagInfo();
        }
    }

    /**
     * 测试创建知识库接口
     */
    public void testAddRagInfo() {
        log.info("开始测试 addRagInfo 方法");

        try {
            // 测试用例1：正常创建知识库
            String testRagName = "测试知识库_" + System.currentTimeMillis();
            log.info("测试创建知识库，名称: {}", testRagName);

            Result result = publicHelper.addRagInfo(testRagName);

            if (result != null) {
                log.info("测试结果 - Code: {}, Message: {}", result.getCode(), result.getMessage());
                if (result.getCode() == 0) {
                    log.info("✅ 知识库创建成功，返回数据: {}", result.getResult());
                } else {
                    log.error("❌ 知识库创建失败: {}", result.getMessage());
                }
            } else {
                log.error("❌ 返回结果为空");
            }

            // 测试用例2：测试重复名称（应该失败）
            log.info("测试重复名称创建知识库");
            Result duplicateResult = publicHelper.addRagInfo(testRagName);
            if (duplicateResult != null && duplicateResult.getCode() != 0) {
                log.info("✅ 重复名称检测正常，错误信息: {}", duplicateResult.getMessage());
            } else {
                log.warn("⚠️ 重复名称检测可能有问题");
            }

            // 测试用例3：测试空名称（应该失败）
            log.info("测试空名称创建知识库");
            Result emptyNameResult = publicHelper.addRagInfo("");
            if (emptyNameResult != null && emptyNameResult.getCode() != 0) {
                log.info("✅ 空名称检测正常，错误信息: {}", emptyNameResult.getMessage());
            } else {
                log.warn("⚠️ 空名称检测可能有问题");
            }

        } catch (Exception e) {
            log.error("❌ 测试过程中发生异常: {}", e.getMessage(), e);
        }

        log.info("addRagInfo 方法测试完成");
    }

    /**
     * 手动调用测试方法（用于单独测试）
     */
    public void manualTest() {
        testAddRagInfo();
    }
}

// 自动测试：在应用启动时通过参数触发
// java -jar your-app.jar test-shuiwu


/**
 * @Autowired
 * private TestShuiwuInterface testInterface;
 *
 * // 在需要的地方调用
 * testInterface.manualTest();
 */