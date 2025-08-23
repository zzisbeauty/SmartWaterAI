package com.hanwei.core.autoapi.process;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hanwei.core.annotation.*;
import com.hanwei.core.autoapi.bo.ApiRegisterBO;
import com.hanwei.core.autoapi.bo.RegisterResultBO;
import com.hanwei.core.autoapi.util.PackageUtils;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.api.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @version : [v1.0]
 * @description : [根据注解自动注册api网关]
 * @createTime : [2025/1/23 9:54]
 * @updateUser : [zht]
 * @updateTime : [2025/1/23 9:54]
 * @updateRemark : [初版]
 */
@Component
@Slf4j
public class AutoApiRegister {

    /**
     * 启动开关
     */
    @Value("${autoApi.enable}")
    private String enable;

    /**
     * 扫描包路径
     */
    @Value("${autoApi.packageScanPath}")
    private String packageScanPath;

//    /**
//     * 应用ID
//     */
//    @Value("${autoApi.appId}")
//    private String appId;

//    /**
//     * 应用密钥
//     */
//    @Value("${autoApi.appSecret}")
//    private String appSecret;

//    /**
//     * 应用前缀用于拼接API密钥
//     */
//    @Value("${autoApi.appPrefix}")
//    private String appPrefix;

//    /**
//     * 在配置管理创建项目的租户ID
//     */
//    @Value("${autoApi.tenementId}")
//    private String tenementId;

    /**
     * 服务端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * 服务根路径
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String appName;

    /**
     * nacos命名空间ID
     */
    @Value("${spring.cloud.nacos.discovery.name-space:}")
    private String namespaceNacos;

    /**
     * nacos分组名
     */
    @Value("${spring.cloud.nacos.discovery.group:}")
    private String groupNameNacos;

    /**
     * dubbo命名空间ID
     */
//    @Value("${dubbo.registry.parameters.namespace:}")
//    private String namespaceDubbo;

    /**
     * dubbo分组名
     */
//    @Value("${dubbo.registry.group:}")
//    private String groupNameDubbo;

    @Autowired
    private PackageUtils packageUtils;

    /**
     * 参数校验
     * @return
     */
    private Boolean validParam(){

        // 校验应用名称
        if(StrUtil.isEmpty(appName)){
            log.error("应用ID未配置，请先配置应用ID");
            return false;
        }

//        // 校验应用ID
//        if(StrUtil.isEmpty(appId)){
//            log.error("应用ID未配置，请先配置应用ID");
//            return false;
//        }

//        // 校验应用密钥
//        if(StrUtil.isEmpty(appSecret)){
//            log.error("应用ID未配置，请先配置应用密钥");
//            return false;
//        }

//        // 校验租户ID
//        if(StrUtil.isEmpty(tenementId)){
//            log.error("租户ID未配置，请先配置租户ID");
//            return false;
//        }

//        // 校验应用ID
//        if(StrUtil.isEmpty(appId)){
//            log.error("应用ID未配置，请先配置应用ID");
//            return false;
//        }

        // 校验扫描包根路径
        if(StrUtil.isEmpty(packageScanPath)){
            log.error("扫描包路径未配置，请先配置扫描包路径");
            return false;
        }

        // 校验Nacos命名空间ID
        if(StrUtil.isEmpty(namespaceNacos)){
            log.error("Nacos命名空间ID未配置，请先配置Nacos命名空间ID");
            return false;
        }

        // 校验Nacos分组名
        if(StrUtil.isEmpty(groupNameNacos)){
            log.error("Nacos分组名未配置，请先配置Nacos分组名");
            return false;
        }

        // 校验Dubbo命名空间ID
//        if(StrUtil.isEmpty(namespaceDubbo)){
//            log.error("Dubbo命名空间ID未配置，请先配置Dubbo命名空间ID");
//            return false;
//        }

        // 校验Dubbo分组名
//        if(StrUtil.isEmpty(groupNameDubbo)){
//            log.error("Dubbo分组名未配置，请先配置Dubbo分组名");
//            return false;
//        }

        // 校验扫描包根路径
        if(Optional.ofNullable(port).isEmpty()){
            log.error("服务端口未配置，请先配置服务端口");
            return false;
        }

        // 校验扫描包根路径
        if(StrUtil.isEmpty(contextPath)){
            log.error("服务根路径未配置，请先配置服务根路径");
            return false;
        }

        return true;
    }

    /**
     * 自动注册处理
     */
    @PostConstruct
    public void register() {
        log.info("服务自动注册处理开始");

        try {
            if(!"true".equals(enable)){
                log.info("服务自动注册功能未开启，不进行自动注册");
                return;
            }


            if(!validParam()){
                log.info("服务自动注册处理结束");
                return;
            }

            // 扫描包路径下的所有类
            Set<BeanDefinition> annotatedClasses = null;
            try {
                annotatedClasses =PackageUtils.getClassByPackage(packageScanPath);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("扫描包过程发生异常",e);
                return;
            }

            if(!Optional.ofNullable(annotatedClasses).isPresent()){
                log.info("没有找到需要注册的类");
                return;
            }

            log.info("扫描到"+annotatedClasses.size()+"个类需要注册 开始注册处理");

            // 入库集合
            List<ApiRegisterBO> apiRegisterBOList = new ArrayList<>();

            // 获取对应参数，封装业务BO
            for (BeanDefinition className : annotatedClasses) {
                Class clazz = null;
                try {
                    clazz = Class.forName(className.getBeanClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    log.error(className.getBeanClassName() + "类实例化失败",e);
                    continue;
                }
                Method[] methods = clazz.getDeclaredMethods();

                for(Method method : methods){
                    //排除例外方法
                    if(method.isAnnotationPresent(IgnoreRegister.class)){
                        continue;
                    }

                    //必要注解校验
                    if(!packageUtils.validAnnotation(method)){
                        continue;
                    }

                    //组装业务BO
                    ApiRegisterBO apiRegisterBO = new ApiRegisterBO();
//                    // 应用ID
//                    apiRegisterBO.setAppId(appId);

                    // 应用(服务)名称
                    apiRegisterBO.setAppName(appName);

                    // API名称
                    Operation operation = method.getDeclaredAnnotation(Operation.class);
                    apiRegisterBO.setApiName(operation.summary());

                    //获取API分类ID
                    if(clazz.isAnnotationPresent(ApiKind.class)){
                        ApiKind apiKind = (ApiKind) clazz.getDeclaredAnnotation(ApiKind.class);
                        String functionId = null;
                        try {
                            functionId = packageUtils.getFunctionIdNew(apiKind.value());
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error(className.getBeanClassName() + "类获取API分类ID失败",e);
                            continue;
                        }
                        apiRegisterBO.setFunctionId(functionId);
                    }

                    //获取服务命名空间ID和组名 仅支持nacos
                    apiRegisterBO.setNamespaceId(namespaceNacos);
                    apiRegisterBO.setGroupName(groupNameNacos);



                    // API类型
                    if(method.isAnnotationPresent(ApiType.class)){
                        ApiType apiType = method.getDeclaredAnnotation(ApiType.class);
                        apiRegisterBO.setApiType(apiType.value());
                    }

//                    // APP密钥
//                    apiRegisterBO.setAppSercet(appSecret);

//                    // 租户ID
//                    apiRegisterBO.setTenementId(tenementId);

                    // API描述
                    apiRegisterBO.setApiDesc(operation.description());

                    // 服务协议 HTTP、HTTPS、HTTP 和 HTTPS(默认 HTTP,可手动写)
                    if(method.isAnnotationPresent(ApiProtocol.class)){
                        ApiProtocol apiProtocol = method.getDeclaredAnnotation(ApiProtocol.class);
                        apiRegisterBO.setApiProtocol(apiProtocol.value());
                    }else{
                        apiRegisterBO.setApiProtocol(ApiEnum.HTTP);
                    }

                    // 服务IP
                    try {
                        String ip = InetAddress.getLocalHost().getHostAddress();
                        apiRegisterBO.setServiceIp(ip);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                        log.error(method.getName() + "获取服务IP失败",e);
                        continue;
                    }

                    // 服务端口
                    apiRegisterBO.setServicePort(port);

                    // 服务路由 及请求方式
                    String servicePath = packageUtils.getServicePath(method,apiRegisterBO);
                    apiRegisterBO.setServicePath(servicePath);

                    // form编码
                    if(method.isAnnotationPresent(RequestEnctype.class)){
                        RequestEnctype requestEnctype = method.getDeclaredAnnotation(RequestEnctype.class);
                        apiRegisterBO.setFormEncType(requestEnctype.value());
                    }
                    if(!Optional.ofNullable(apiRegisterBO.getFormEncType()).isPresent()) {
                        apiRegisterBO.setFormEncType(ApiEnum.RAW);
                    }


                    // 方法入参
                    apiRegisterBO.setServiceParams(List.of(method.getParameters()));

                    // 返回类型:JSON、文本、二进制、XML、HTML(手动写)
                    if(method.isAnnotationPresent(ReturnType.class)){
                        ReturnType returnType = method.getDeclaredAnnotation(ReturnType.class);
                        apiRegisterBO.setReturnType(returnType.value());
                    }

                    // 请求超时时间
                    if(method.isAnnotationPresent(RequestTimeOut.class)){
                        RequestTimeOut requestTime = method.getDeclaredAnnotation(RequestTimeOut.class);
                        apiRegisterBO.setRequestTimeOut(requestTime.timeout());
                    }

                    // 返回成功示例
                    if(method.isAnnotationPresent(ApiSuccessReturn.class)){
                        ApiSuccessReturn apiSuccessReturn = method.getDeclaredAnnotation(ApiSuccessReturn.class);
                        Result result = new Result();
                        if(Optional.ofNullable(apiSuccessReturn.returnJson()).isPresent()){
                            result = result.success(apiSuccessReturn.returnJson());
                        }else if(Optional.ofNullable(apiSuccessReturn.message()).isPresent()){
                            result = result.success(apiSuccessReturn.message());
                        }else{
                            result = result.success("操作成功");
                        }
                        apiRegisterBO.setResultSuccessexample(JSONUtil.toJsonStr(result));
                    }

                    // 返回失败示例
                    if(method.isAnnotationPresent(ApiFailReturn.class)){
                        ApiFailReturn apiFailReturn = method.getDeclaredAnnotation(ApiFailReturn.class);
                        Result result = new Result();
                        if(Optional.ofNullable(apiFailReturn.returnJson()).isPresent()){
                            result = result.error500(apiFailReturn.returnJson());
                        }else if(Optional.ofNullable(apiFailReturn.message()).isPresent()){
                            result = result.error500(apiFailReturn.message());
                        }else{
                            result = result.success("操作失败");
                        }
                        apiRegisterBO.setResultFailexample(JSONUtil.toJsonStr(result));
                    }

                    // 服务返回
                    apiRegisterBO.setReturnResult(method.getReturnType());

                    //API密钥 生成32位API密钥
                    String apiSecret = PackageUtils.generateApiSecretMd5(appName,servicePath);
                    apiRegisterBO.setApiSecret(apiSecret);

                    System.out.println(apiRegisterBO.getApiName() + "数据组装完成");

                    //待入库数据
                    apiRegisterBOList.add(apiRegisterBO);
                }
            }
            System.out.println("数据组装全部完成，准备注册");
//            数据入库 针对老网关
//            successMethodCount = packageUtils.registerApi(apiRegisterBOList);

            //数据入库 针对新网关
            RegisterResultBO registerResultBO = packageUtils.registerApiNew(apiRegisterBOList);
            Integer total = registerResultBO.getSuccessMethodCount() + registerResultBO.getFailMethodCount();

            log.info("服务自动注册处理结束 共" + total + "个API");
            log.info("成功注册"+registerResultBO.getSuccessMethodCount()+"个API");
            log.info("失败注册"+registerResultBO.getFailMethodCount()+"个API");
            if(registerResultBO.getFailMethodCount() > 0){
                log.info("以下方法注册失败："+registerResultBO.getFailMethodList());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            log.error("服务自动注册处理异常",e);
        }

    }

}
