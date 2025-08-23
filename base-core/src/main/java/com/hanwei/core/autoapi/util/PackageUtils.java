package com.hanwei.core.autoapi.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.autoapi.bo.*;
import com.hanwei.core.autoapi.entity.*;
import com.hanwei.core.autoapi.service.IApiAccessService;
import com.hanwei.core.autoapi.service.IApiInfoService;
import com.hanwei.core.autoapi.service.IApiKindService;
import com.hanwei.core.autoapi.service.IGateWayService;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.core.common.CommonConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [自动注册]
 * @createTime : [2025/1/23 10:30]
 */
@Slf4j
@Component
//@DS("netConfig")
public class PackageUtils {
    /**
     * 应用ID
     */
//    @Value("${autoApi.appId}")
    private String appId;

    /**
     * 应用密钥
     */
//    @Value("${autoApi.appSecret}")
    private String appSecret;
    /**
     * 在配置管理创建项目的租户ID
     */
//    @Value("${autoApi.tenementId}")
    private String tenementId;

    /**
     * 服务根路径
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Autowired
    private IApiInfoService apiInfoService;

    @Autowired
    private IApiAccessService apiAccessService;

    @Autowired
    private IApiKindService apiKindService;

    @Autowired
    private IGateWayService gateWayService;


    /**
     * 获取funcationId 如果没有对应分类则新建
     *
     * @param value
     * @return
     */
    public String getFunctionId(String value) {
        String functionId = "";
        if (Optional.ofNullable(value).isPresent()) {
            String[] functionNameArr = value.split("-");
            for (int i = 0; i < functionNameArr.length; i++) {
                String functionName = functionNameArr[i];
                MPJLambdaWrapper<com.hanwei.core.autoapi.entity.ApiKind> queryWrapper = new MPJLambdaWrapper();
                queryWrapper.eq(com.hanwei.core.autoapi.entity.ApiKind::getAppid, appId)
                        .eq(com.hanwei.core.autoapi.entity.ApiKind::getAppsecret, appSecret)
                        .eq(com.hanwei.core.autoapi.entity.ApiKind::getKindName, functionName)
                        .eq(com.hanwei.core.autoapi.entity.ApiKind::getParentKindId, functionId)
                        .eq(com.hanwei.core.autoapi.entity.ApiKind::getTenementId, tenementId);
                com.hanwei.core.autoapi.entity.ApiKind apiKind = apiKindService.getOne(queryWrapper);

                if (null == apiKind) {
                    //新建分类
                    com.hanwei.core.autoapi.entity.ApiKind apiKindNew = new com.hanwei.core.autoapi.entity.ApiKind();
                    apiKindNew.setAppid(appId);
                    apiKindNew.setAppsecret(appSecret);
                    apiKindNew.setTenementId(tenementId);
                    apiKindNew.setParentKindId(functionId);
                    apiKindNew.setKindName(functionName);
                    Integer kindLevel = i + 1;
                    apiKindNew.setKindLevel(kindLevel);
                    String hierarchyCode = getHierarchyCode(kindLevel, functionId);
                    apiKindNew.setHierarchyCode(hierarchyCode);
                    apiKindService.save(apiKindNew);
                    functionId = apiKindNew.getId();
                } else {
                    functionId = apiKind.getId();
                }
            }
        }

        return functionId;
    }

    /**
     * 获取funcationId 如果没有对应分类则新建 新网管用
     *
     * @param value
     * @return
     */
    public String getFunctionIdNew(String value) {
        String functionId = "";

        //获取最新API分类数据
        List<ApiKindBo> apiKindBoList = gateWayService.getApiKindTree();

        if (Optional.ofNullable(value).isPresent()) {
            String[] functionNameArr = value.split("-");
            for (int i = 0; i < functionNameArr.length; i++) {
                String functionName = functionNameArr[i];
                ApiKindBo apiKindBo = findApiKindByName(apiKindBoList, functionName);
                if (null == apiKindBo) {
                    //新建分类
                    ApiKindBo apiKindBoNew = new ApiKindBo();
                    //根节点处理
                    if (StrUtil.isEmpty(functionId)) {
                        functionId = "-1";
                    }
                    apiKindBoNew.setAssortName(functionName);
//                    Integer kindLevel = i + 1;
//                    String hierarchyCode = getHierarchyCode(kindLevel,functionId);
                    apiKindBoNew.setAssortCode("Api" + RandomUtil.randomString(4));
                    apiKindBoNew.setSort(99);
                    apiKindBoNew.setState(CommonConstant.STATUS_NORMAL);
                    apiKindBoNew.setParentId(functionId);

                    functionId = gateWayService.saveApiKind(apiKindBoNew);

                    //处理剩余节点
                    for (int j = i + 1; j < functionNameArr.length; j++) {
                        String functionNameNext = functionNameArr[j];
                        apiKindBoNew = new ApiKindBo();

                        apiKindBoNew.setAssortName(functionNameNext);
//                        kindLevel = j + 1;
//                        hierarchyCode = getHierarchyCode(kindLevel,functionId);
                        apiKindBoNew.setAssortCode("Api" + RandomUtil.randomString(4));
                        apiKindBoNew.setSort(99);
                        apiKindBoNew.setState(CommonConstant.STATUS_NORMAL);
                        apiKindBoNew.setParentId(functionId);

                        functionId = gateWayService.saveApiKind(apiKindBoNew);
                    }
                    break;

                } else {
                    functionId = apiKindBo.getId();
                    //处理分类树，仅保留子分类
                    for(ApiKindBo item : apiKindBoList){
                        if(item.getId().equals(functionId)){
                            apiKindBoList = item.getChild();
                            break;
                        }
                    }
                }
            }
        }

        return functionId;
    }

    /**
     * 根据名称查找API分类
     *
     * @param apiKindBoList
     * @param functionName
     * @return
     */
    private ApiKindBo findApiKindByName(List<ApiKindBo> apiKindBoList, String functionName) {
        if (null == apiKindBoList || StrUtil.isEmpty(functionName)) {
            return null;
        }
        for (ApiKindBo apiKindBo : apiKindBoList) {
            if (StrUtil.equals(apiKindBo.getAssortName(), functionName)) {
                return apiKindBo;
            } else {
                //递归查找子节点
                ApiKindBo child = findApiKindByName(apiKindBo.getChild(), functionName);
                if (null != child) {
                    return child;
                }
            }
        }
        return null;
    }


    /**
     * 获取层级编码
     *
     * @param kindLevel
     * @return
     */
    private String getHierarchyCode(Integer kindLevel, String funcationId) {
        MPJLambdaWrapper<com.hanwei.core.autoapi.entity.ApiKind> queryWrapper = new MPJLambdaWrapper();
        queryWrapper.selectMax(com.hanwei.core.autoapi.entity.ApiKind::getHierarchyCode)
                .eq(com.hanwei.core.autoapi.entity.ApiKind::getAppid, appId)
                .eq(com.hanwei.core.autoapi.entity.ApiKind::getAppsecret, appSecret)
                .eq(com.hanwei.core.autoapi.entity.ApiKind::getKindLevel, kindLevel);

        if (StrUtil.isNotEmpty(funcationId)) {
            queryWrapper.eq(com.hanwei.core.autoapi.entity.ApiKind::getParentKindId, funcationId);
        }
        String maxHierarchyCode = apiKindService.getMax(queryWrapper);

        //层级不存在时，层级编码默认为0000
        if (StrUtil.isEmpty(maxHierarchyCode)) {
            maxHierarchyCode = "0000";
        }

        String preHierarchyCode = "";

        if (StrUtil.isNotEmpty(funcationId)) {
            MPJLambdaWrapper<com.hanwei.core.autoapi.entity.ApiKind> queryWrapperPre = new MPJLambdaWrapper();
            queryWrapperPre.eq(com.hanwei.core.autoapi.entity.ApiKind::getId, funcationId);
            com.hanwei.core.autoapi.entity.ApiKind apiKind = apiKindService.getOne(queryWrapperPre);
            preHierarchyCode = apiKind.getHierarchyCode();
            maxHierarchyCode = preHierarchyCode + "." + maxHierarchyCode + ".";
        }

        String[] hierarchyCodeArr = maxHierarchyCode.split("\\.");
        if (hierarchyCodeArr.length == 0) {
            hierarchyCodeArr = new String[]{maxHierarchyCode};
        }
        String hierarchyCode = hierarchyCodeArr[hierarchyCodeArr.length - 1];
        while (hierarchyCode.length() < 4) {
            hierarchyCode = hierarchyCode + "0";
        }
        Integer hierarchyCodeInt = Integer.parseInt(hierarchyCode) + 1;
        hierarchyCode = String.valueOf(hierarchyCodeInt);
        while (hierarchyCode.length() < 4) {
            hierarchyCode = "0" + hierarchyCode;
        }

        //不拼接最后一个层级，层级编码拼接规则为：父级层级编码.当前层级编码
        for (int i = 0; i < hierarchyCodeArr.length - 1; i++) {
            hierarchyCode = hierarchyCodeArr[i] + "." + hierarchyCode;
        }

        return hierarchyCode;
    }

    /**
     * 数据入库 针对老网关
     *
     * @param apiRegisterBOList
     * @return 成功注册数量
     */
    @Transactional
    public Integer registerApi(List<ApiRegisterBO> apiRegisterBOList) {

        List<ApiInfo> apiInfoList = new ArrayList<>();
        List<ApiAccess> apiAccessList = new ArrayList<>();
        Integer successMethodCount = 0;
        try {
            for (ApiRegisterBO apiRegisterBO : apiRegisterBOList) {
                System.out.println(apiRegisterBO);
                // 组装入库数据
                ApiInfo apiInfo = apiInfoBuilder(apiRegisterBO);
                List<ApiAccess> apiAccess = apiAccessBuilder(apiRegisterBO);
                apiInfoList.add(apiInfo);
                apiAccessList.addAll(apiAccess);

                //查询是否已经存在，如存在则删除
                MPJLambdaWrapper<ApiInfo> queryWrapper = new MPJLambdaWrapper();
                queryWrapper.eq(ApiInfo::getAppid, appId)
                        .eq(ApiInfo::getAppsecret, appSecret)
                        .eq(ApiInfo::getServicePath, apiRegisterBO.getServicePath());
                List<ApiInfo> apiInfos = apiInfoService.list(queryWrapper);
                if (apiInfos.size() > 0) {
                    for (ApiInfo info : apiInfos) {
                        MPJLambdaWrapper<ApiAccess> apiAccessWrapper = new MPJLambdaWrapper();
                        apiAccessWrapper.eq(ApiAccess::getApisecret, info.getApisecret());
                        List<ApiAccess> apiAccesses = apiAccessService.list(apiAccessWrapper);
                        if (apiAccesses.size() > 0) {
                            apiAccessService.removeByIds(apiAccesses);
                        }
                        apiInfoService.removeById(info.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("API信息组装失败");
        }

        //入库
        try {
            apiInfoService.saveBatch(apiInfoList);
            apiAccessService.saveBatch(apiAccessList);
            successMethodCount = apiInfoList.size();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("API信息入库失败");
        }
        return successMethodCount;
    }

    /**
     * 数据入库 针对新网关
     *
     * @param apiRegisterBOList
     * @return 成功注册数量
     */
    public RegisterResultBO registerApiNew(List<ApiRegisterBO> apiRegisterBOList) {
        RegisterResultBO registerResultBO = new RegisterResultBO();
        Integer successMethodCount = 0;
        Integer failMethodCount = 0;
        List<String> failMethodList = new ArrayList<>();
        String methodName = "";
        try {
            for (ApiRegisterBO apiRegisterBO : apiRegisterBOList) {
                               // 组装入库数据
                RegisterBo registerBo = registerBoBuilder(apiRegisterBO);

                methodName = registerBo.getBaseInfo().getName();
                String apiSecret = apiRegisterBO.getApiSecret();
//                System.out.println(JSON.toJSONString(registerBo));

                //先删除 后新增
                Boolean isSuccess = false;
                if(gateWayService.deleteApiInfo(apiSecret)){
                    isSuccess = gateWayService.saveApiInfo(registerBo);
                }

                if (isSuccess) {
                    successMethodCount++;
                    log.info(registerBo.getBaseInfo().getName() + "注册成功");
                }else{
                    failMethodCount++;
                    failMethodList.add(methodName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("API信息注册失败");
            failMethodCount++;
        }
        registerResultBO.setSuccessMethodCount(successMethodCount);
        registerResultBO.setFailMethodCount(failMethodCount);
        registerResultBO.setFailMethodList(failMethodList);
        return registerResultBO;
    }

    /**
     * 组装API访问权限数据
     *
     * @param apiRegisterBO
     * @return
     */
    private List<ApiAccess> apiAccessBuilder(ApiRegisterBO apiRegisterBO) {
        ApiAccess apiAccessTest = new ApiAccess();
        apiAccessTest.setAppid(apiRegisterBO.getAppId());
        apiAccessTest.setAppsecret(apiRegisterBO.getAppSercet());
        apiAccessTest.setApisecret(apiRegisterBO.getApiSecret());
        apiAccessTest.setAuthorizeDate(DateUtil.date());
        apiAccessTest.setTenementId(apiRegisterBO.getTenementId());
        apiAccessTest.setAuthorizeBy("systemAuto");
        apiAccessTest.setApiEnvironment(0);

        ApiAccess apiAccessPre = new ApiAccess();
        ApiAccess apiAccessPro = new ApiAccess();
        BeanUtil.copyProperties(apiAccessTest, apiAccessPre);
        BeanUtil.copyProperties(apiAccessTest, apiAccessPro);

        apiAccessPre.setApiEnvironment(1);
        apiAccessPro.setApiEnvironment(2);

        List<ApiAccess> apiAccessList = new ArrayList<>();
        apiAccessList.add(apiAccessTest);
        apiAccessList.add(apiAccessPre);
        apiAccessList.add(apiAccessPro);
        return apiAccessList;
    }

    /**
     * 组装API信息数据 老网关
     *
     * @param apiRegisterBO
     * @return
     */
    private ApiInfo apiInfoBuilder(ApiRegisterBO apiRegisterBO) {
        ApiInfo apiInfo = new ApiInfo();

        /**API名称*/
        apiInfo.setApiName(apiRegisterBO.getApiName());
        /**API类型（1:公开，0:私有）*/
        apiInfo.setApiType(apiRegisterBO.getApiType());
        /**API描述*/
        apiInfo.setApiDescription(apiRegisterBO.getApiDesc());
        /**请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）*/
        apiInfo.setRequestProtocol(apiRegisterBO.getApiProtocol());
        /**API密钥*/
        apiInfo.setApisecret(apiRegisterBO.getApiSecret());
        /**后端服务地址*/
        apiInfo.setServiceHost(apiRegisterBO.getServiceIp() + ":" + apiRegisterBO.getServicePort());
        /**后端路径*/
        apiInfo.setServicePath(apiRegisterBO.getServicePath());
        /**后端请求方式*/
        apiInfo.setServiceMethod(apiRegisterBO.getRequestMethod());
        /**后端请求时长*/
        apiInfo.setServiceRequesttime(apiRegisterBO.getRequestTimeOut());

        /**组装请求参数json*/
        List<Parameter> parameterList = apiRegisterBO.getServiceParams();
        List<ParameterBO> parameterBOList = parameterBOListBuilder(parameterList);
        apiInfo.setRequestParameter(JSONUtil.toJsonStr(parameterBOList));

        /**前后端映射参数*/
        List<ServiceParameterBO> serviceParameterBOList = serviceParameterBOListBuilder(parameterBOList);
        apiInfo.setServiceParameter(JSONUtil.toJsonStr(serviceParameterBOList));

        /**api分类 （功能guid）*/
        String functionId = apiRegisterBO.getFunctionId();
        apiInfo.setFunctionId(functionId);

        /**常量参数参数*/
        apiInfo.setConstantParameter("[{\"name\":\"Content-Type\",\"defaults\":\"application/json\",\"position\":\"0\",\"description\":\"Content-Type\"}]");
        /**自定义系统参数*/
        apiInfo.setCustomParameter("");
        /**返回类型*/
        apiInfo.setResultType(apiRegisterBO.getReturnType());
        /**返回参数注释json*/
        apiInfo.setResultNotes("");
        /**状态*/
        apiInfo.setState("1");
        /**服务端请求协议（0:HTTP;1:HTTPS;2HTTP/HTTPS）*/
        apiInfo.setServiceProtocol(apiRegisterBO.getApiProtocol());
        /**前端请求方式*/
        apiInfo.setRequestMethod(apiRegisterBO.getRequestMethod());
        /**成功返回结果示例*/
        apiInfo.setResultSuccessexample(apiRegisterBO.getResultSuccessexample());
        /**失败返回结果示例*/
        apiInfo.setResultFailexample(apiRegisterBO.getResultFailexample());
        /**请求enctype.0:form-data;1:x-www-form-urlencoded;2:raw;3:binary*/
//        apiInfo.setRequestEnctype(apiRegisterBO.getFormEncType());
        /**post请求body描述*/
        apiInfo.setBodyDescreption("");
        /**appid*/
        apiInfo.setAppid(appId);
        /**app密钥*/
        apiInfo.setAppsecret(appSecret);
        /**创建时间*/
        apiInfo.setCreateTime(DateUtil.date());
        /**创建人*/
        apiInfo.setCreateBy("systemAuto");
        /**修改时间*/
        apiInfo.setModifieTime(DateUtil.date());
        /**修改人*/
        apiInfo.setModifieBy("systemAuto");
        /**租户GUID*/
        apiInfo.setTenementId(tenementId);


        return apiInfo;

    }

    /**
     * 组装API信息数据 新网关
     *
     * @param apiRegisterBO
     * @return
     */
    private RegisterBo registerBoBuilder(ApiRegisterBO apiRegisterBO) {
        RegisterBo registerBo = new RegisterBo();

        /**组装API基础信息*/
        ApiInfoVoCore apiInfoVoCore = new ApiInfoVoCore();
        // API分类ID
        apiInfoVoCore.setAssortId(apiRegisterBO.getFunctionId());
        // API 名称
        apiInfoVoCore.setName(apiRegisterBO.getApiName());
        // API密匙
        apiInfoVoCore.setApiSecret(apiRegisterBO.getApiSecret());
        // API类型：1公开 0私有（预留）
        apiInfoVoCore.setApiType(apiRegisterBO.getApiType());
        // API描述
        apiInfoVoCore.setDescInfo(apiRegisterBO.getApiDesc());
        // 请求方式
        apiInfoVoCore.setMethod(apiRegisterBO.getRequestMethod());
        // 请求协议 HTTP HTTPS
        apiInfoVoCore.setRequestAgreement(apiRegisterBO.getApiProtocol());
        // 是否校验 0 校验 1 不校验
        apiInfoVoCore.setIsCheck(ApiEnum.CHECK);
        // 真实请求路径
        apiInfoVoCore.setUrl(apiRegisterBO.getServicePath());
        // 服务端请求方式 0 常规模式 1 集群服务方式 2 选择服务 3 选择统一规则
        apiInfoVoCore.setServiceType(ApiEnum.REQUEST_MODE_CLUSTER);
        // 1: form-data 2: x-www-form-urlencoded 3: json
        apiInfoVoCore.setParameterType(apiRegisterBO.getFormEncType());
        /**组装请求参数json*/
        List<Parameter> parameterList = apiRegisterBO.getServiceParams();
        List<ParameterBO> parameterBOList = parameterBOListBuilder(parameterList);
        List<ParameterBONew> parameterBONewBodyList = parameterBOListNewBuilder(parameterBOList, ApiEnum.PARAMETER_LOCATION_BODY);
        apiInfoVoCore.setRequestParameter(JSONArray.parseArray(JSON.toJSONString(parameterBONewBodyList)));
        // query 参数
        List<ParameterBONew> parameterBONewQueryList = parameterBOListNewBuilder(parameterBOList, ApiEnum.PARAMETER_LOCATION_QUERY);
        apiInfoVoCore.setQueryParameter(JSONArray.parseArray(JSON.toJSONString(parameterBONewQueryList)));
        // header 参数
        List<ParameterBONew> parameterBONewHeaderList = parameterBOListNewBuilder(parameterBOList, ApiEnum.PARAMETER_LOCATION_HEAD);
        apiInfoVoCore.setHeaderParameter(JSONArray.parseArray(JSON.toJSONString(parameterBONewHeaderList)));
        // 预留: 前后端映射参数
        List<ParameterBONew> parameterAllList = new ArrayList<>();
        parameterAllList.addAll(parameterBONewBodyList);
        parameterAllList.addAll(parameterBONewQueryList);
//        apiInfoVoCore.setServiceParameter(JSONArray.parseArray(JSON.toJSONString(parameterAllList)));
        // 常量参数参数
        apiInfoVoCore.setConstantParameter(new JSONArray());
        // 返回参数注释json
        apiInfoVoCore.setResultNotes(new JSONArray());
        // 自定义系统参数
        apiInfoVoCore.setCustomParameter(new JSONArray());
        // 返回类型
        apiInfoVoCore.setResultType(apiRegisterBO.getReturnType());


        /**组装分布式服务信息*/
        CloudInfoCore cloudInfoCore = new CloudInfoCore();

        //服务发现方式，默认nacos;(dns/consul_kv/eureka)
        cloudInfoCore.setDiscoveryType(ApiEnum.SERVICE_DISCOVERY_NACOS);

        //暴漏服务名
        cloudInfoCore.setServiceName(apiRegisterBO.getAppName());

        // 服务分组名
        cloudInfoCore.setGroupName(apiRegisterBO.getGroupName());

        //命名空间ID
        cloudInfoCore.setNamespaceId(apiRegisterBO.getNamespaceId());


        /**组装节点信息*/
        NodeInfoCore nodeInfoCore = new NodeInfoCore();

        //主机名：IP
        nodeInfoCore.setHost(apiRegisterBO.getServiceIp());

        // 端口号
        nodeInfoCore.setPort(apiRegisterBO.getServicePort());


        /**组装基础服务信息*/
        BaseServerInfoCore baseServerInfoCore = new BaseServerInfoCore();

        //连接超时时间
        baseServerInfoCore.setConnect(apiRegisterBO.getRequestTimeOut());


        registerBo.setBaseInfo(apiInfoVoCore);
        registerBo.setCloudInfo(cloudInfoCore);
//        registerBo.setNodeInfo(nodeInfoCore);
        registerBo.setServerInfo(baseServerInfoCore);


        return registerBo;

    }

    /**
     * 组装服务端参数BO列表 老网关使用
     *
     * @param parameterBOList
     * @return
     */
    private List<ServiceParameterBO> serviceParameterBOListBuilder(List<ParameterBO> parameterBOList) {
        List<ServiceParameterBO> serviceParameterBOList = new ArrayList<>();
        for (ParameterBO parameterBO : parameterBOList) {
            ServiceParameterBO serviceParameterBO = new ServiceParameterBO();
            serviceParameterBO.setName(parameterBO.getName());
            serviceParameterBO.setBackname(parameterBO.getName());
            serviceParameterBO.setBackposition(parameterBO.getLocation());
            serviceParameterBO.setType(getType(parameterBO.getType()));
            serviceParameterBO.setPositionvalue("1");
            serviceParameterBO.setPosition(getPosition(parameterBO.getLocation()));
            serviceParameterBOList.add(serviceParameterBO);
        }

        return serviceParameterBOList;
    }

    /**
     * 组装参数BO列表 新网关使用
     *
     * @param parameterBOList
     * @param location        参数位置 header;query;path;body
     * @return
     */
    private List<ParameterBONew> parameterBOListNewBuilder(List<ParameterBO> parameterBOList, String location) {
        List<ParameterBONew> parameterBONewList = new ArrayList<>();
        for (ParameterBO parameterBO : parameterBOList) {
            if (parameterBO.getLocation().equals(location)) {
                ParameterBONew parameterBONew = new ParameterBONew();
                parameterBONew.setId(UUID.randomUUID().toString());
                parameterBONew.setKey(parameterBO.getName());
                parameterBONew.setDescribe(parameterBO.getDescription());
                //是否必填 0:必填;1:非必填
                parameterBONew.setIsRequired(parameterBO.getRequired() ? 1 : 0);
                parameterBONew.setType(parameterBO.getType());
                parameterBONewList.add(parameterBONew);
            }
        }
        return parameterBONewList;
    }


    /**
     * 获取参数类型 按老网关值转换 0:String;1:Int;2:Long;3:Float;4:Double;5:Boolean;6:File
     *
     * @param type
     * @return
     */
    private String getType(String type) {
        switch (type) {
            case "0":
                return "String";
            case "1":
                return "Int";
            case "2":
                return "Long";
            case "3":
                return "Float";
            case "4":
                return "Double";
            case "5":
                return "Boolean";
            case "6":
                return "File";
            default:
                return "";
        }
    }

    /**
     * 获取参数位置按老网关值转换 0:header;1:query;2:path;3:body
     *
     * @param location
     * @return
     */
    private String getPosition(String location) {
        switch (location) {
            case "0":
                return "Head";
            case "1":
                return "Query";
            case "2":
                return "Parameter Path";
            case "3":
                return "Body";
            default:
                return "";
        }
    }

    /**
     * 组装请求参数BO列表
     *
     * @param parameterList
     * @return
     */
    private List<ParameterBO> parameterBOListBuilder(List<Parameter> parameterList) {
        List<ParameterBO> parameterBOList = new ArrayList<>();
        //标记方法是否有ApiParam注解
        Boolean methodExistAnnotation = false;
        for (Parameter parameter : parameterList) {

            //获取参数注解 检查是否有ApiParam注解
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiParameter) {
                    ApiParameter apiParam = (ApiParameter) annotation;
                    ParameterBO parameterBO = new ParameterBO();
                    parameterBO.setName(apiParam.name());
                    parameterBO.setDescription(apiParam.description());
                    parameterBO.setDefaultvalue(apiParam.defaultvalue());
                    parameterBO.setRequired(apiParam.required());
                    parameterBO.setLocation(apiParam.location());
                    parameterBO.setType(apiParam.type());
                    parameterBO.setDemovalue(apiParam.demovalue());
                    parameterBOList.add(parameterBO);
                    methodExistAnnotation = true;
                    break;
                }
            }

            if (methodExistAnnotation) {
                methodExistAnnotation = false;
                continue;
            }
            //检查对象属性是否有ApiParam注解
            Class clazz = parameter.getType();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Annotation[] fieldAnnotations = field.getAnnotations();
                for (Annotation annotation : fieldAnnotations) {
                    if (annotation instanceof ApiParameter) {
                        ApiParameter apiParam = (ApiParameter) annotation;
                        ParameterBO parameterBO = new ParameterBO();
                        parameterBO.setName(apiParam.name());
                        parameterBO.setDescription(apiParam.description());
                        parameterBO.setDefaultvalue(apiParam.defaultvalue());
                        parameterBO.setRequired(apiParam.required());
                        parameterBO.setType(apiParam.type());
                        parameterBO.setLocation(apiParam.location());
                        parameterBO.setDemovalue(apiParam.demovalue());
                        parameterBOList.add(parameterBO);
                    }
                }
            }
        }

        // 统一在head中增加ticket参数
        ParameterBO parameterBO = new ParameterBO();
        parameterBO.setName("ticket");
        parameterBO.setDescription("用户票");
        parameterBO.setDefaultvalue("");
        parameterBO.setRequired(true);
        parameterBO.setType(ApiEnum.PARAMETER_TYPE_STRING);
        parameterBO.setLocation(ApiEnum.PARAMETER_LOCATION_HEAD);
        parameterBO.setDemovalue("");
        parameterBOList.add(parameterBO);
        return parameterBOList;
    }

    /**
     * 根据应用ID和方法路由生成API密钥 sha256加密截取前32位做为密钥长度可配置 默认32位
     *
     * @param appId
     * @param servicePath
     * @param appPrefix
     * @param secretLength
     * @return
     */
    public static String generateApiSecret(String appId, String servicePath, String appPrefix, int secretLength) {
        /**
         * 根据应用ID和方法路由生成API密钥
         */
        String orgStr = StrUtil.join("", appId, servicePath);
        String handleStr = appPrefix + DigestUtils.sha256Hex(orgStr);
        if (secretLength < 16 || secretLength > 64) {
            log.error("密钥长度不符合规范，请设置为16-64位,将生成32位密钥替代");
            secretLength = 32;
        }
        return handleStr.substring(0, secretLength);
    }

    /**
     * 根据系统名称和方法路由生成API密钥 MD5方式32位
     *
     * @param appName
     * @param servicePath
     * @return
     */
    public static String generateApiSecretMd5(String appName, String servicePath) {
        /**
         * 根据应用名称和方法路由生成API密钥
         */
        String orgStr = StrUtil.join(appName, servicePath);
        String handleStr = DigestUtils.md5Hex(orgStr);
        return handleStr;
    }


    /**
     * 获取服务路由
     *
     * @param method
     * @return
     */
    public String getServicePath(Method method, ApiRegisterBO apiRegisterBO) {
        //根路由+类路由+方法路由
        String rootPath = contextPath;
        String classPath = "";
        String methodPath = "";

        Class<?> clazz = method.getDeclaringClass();

        //类路由
        if (clazz.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = (RequestMapping) clazz.getDeclaredAnnotation(RequestMapping.class);
            classPath = StrUtil.join("/", requestMapping.value());
        } else if (clazz.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = (GetMapping) clazz.getDeclaredAnnotation(GetMapping.class);
            classPath = StrUtil.join("/", getMapping.value());
        } else if (clazz.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = (PostMapping) clazz.getDeclaredAnnotation(PostMapping.class);
            classPath = StrUtil.join("/", postMapping.value());
        }

        //方法路由
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = (RequestMapping) method.getDeclaredAnnotation(RequestMapping.class);
            methodPath = StrUtil.join("/", requestMapping.value());
            apiRegisterBO.setRequestMethod(requestMapping.method()[0].name());
        } else if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = (GetMapping) method.getDeclaredAnnotation(GetMapping.class);
            methodPath = StrUtil.join("/", getMapping.value());
            apiRegisterBO.setRequestMethod("GET");
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = (PostMapping) method.getDeclaredAnnotation(PostMapping.class);
            methodPath = StrUtil.join("/", postMapping.value());
            apiRegisterBO.setRequestMethod("POST");
        }

        return StrUtil.join("", rootPath, classPath, methodPath);

    }


    /**
     * 必要注解校验
     *
     * @param method
     */
    public Boolean validAnnotation(Method method) {
        Class clazz = method.getDeclaringClass();

        //待校验注解
        List<Class> classAnnotations = List.of(Tag.class, ApiKind.class);
        List<Class> methodAnnotations = List.of(Operation.class);

        //注解校验
        for (Class classAnnotation : classAnnotations) {
            if (!clazz.isAnnotationPresent(classAnnotation)) {
                log.error("类" + clazz + "缺少必要注解：" + classAnnotation);
                return false;
            }
        }

        for (Class methodAnnotation : methodAnnotations) {
            if (!method.isAnnotationPresent(methodAnnotation)) {
                log.error("方法" + method + "缺少必要注解：" + methodAnnotation);
                return false;
            }
        }

        return true;
    }


    /**
     * 扫描包路径下的所有类
     *
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws UnknownHostException
     */
    public static Set<BeanDefinition> getClassByPackage(String packageName) throws ClassNotFoundException, UnknownHostException {

        // 扫描包路径下的所有类
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(AutoRegister.class));
        Set<BeanDefinition> annotatedClasses = scanner.findCandidateComponents(packageName);
        return annotatedClasses;
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(PackageUtils.generateApiSecret("1","/api/v1/test","hw_",32));
//        String result = HttpUtil.get("http://10.0.15.40:8102/call/apiAssort/getAssortTree");
//        JSONObject jsonObject = JSON.parseObject(result);
//        JSONArray array = (JSONArray) jsonObject.get("result");
//        List<ApiKindBo> zfxqZdxList  =  JSONObject.parseObject(array.toJSONString(), new TypeReference<List<ApiKindBo>>(){});
//        ApiKindBo apiKindBo = findApiKindByName(zfxqZdxList,"基础模块1");
        System.out.println(RandomUtil.randomInt(1000, 9999));
    }
}
