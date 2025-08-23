package com.hanwei.core.autoapi.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hanwei.core.autoapi.bo.ApiKindBo;
import com.hanwei.core.autoapi.bo.GateWayDeleteApiBO;
import com.hanwei.core.autoapi.bo.RegisterBo;
import com.hanwei.core.autoapi.service.IGateWayService;
import com.hanwei.core.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 调取新网关相关接口
 * @Author: hanwei
 * @Date: 2025-02-07
 * @Version: V1.0
 */
@Service
@Slf4j
public class GateWayServiceImpl implements IGateWayService {

    /**
     * 网关地址
     */
    @Value("${autoApi.gatewayAddress}")
    private String gatewayAddress;

    /**
     * 网关端口
     */
    @Value("${autoApi.gatewayPort}")
    private String gatewayPort;

    /**
     * 注册环境
     */
    @Value("${autoApi.env}")
    private String env;

    /**
     * 用户票
     */
    @Value("${autoApi.ticket}")
    private String ticket;


    /**
     * 根据名称查询api类型
     *
     * @return
     */
    @Override
    public List<ApiKindBo> getApiKindTree() {
        List<ApiKindBo> apiKindBoList = null;
        try {
            //网关请求前缀
            String url = "http://" + gatewayAddress + ":" + gatewayPort + "/4aa2e28c95b63bbd38201de03847df5a" + env + "/";
//            String result = HttpUtil.get("http://10.30.30.126:8102/call/apiAssort/getAssortTree");
            String result = HttpUtil.createGet(url).header("ticket", ticket).execute().body();
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray array = (JSONArray) jsonObject.get("result");
            apiKindBoList = JSONObject.parseObject(array.toJSONString(), new TypeReference<List<ApiKindBo>>() {
            });
        } catch (Exception e) {
            return null;
        }
        return apiKindBoList;
    }

    /**
     * 保存API类型信息
     *
     * @param apiKind
     * @return
     */
    @Override
    public String saveApiKind(ApiKindBo apiKind) {
        try {
            //网关请求前缀
            String url = "http://" + gatewayAddress + ":" + gatewayPort + "/3b455636492b0458ee2f3802ea6d7104" + env + "/";
//            String resultStr = HttpUtil.post("http://10.30.30.126:8102/call/apiAssort/add", JSON.toJSONString(apiKind));
            String resultStr = HttpUtil.createPost(url).header("ticket", ticket).body(JSON.toJSONString(apiKind)).execute().body();
            System.out.println(JSON.toJSONString(apiKind));
            JSONObject jsonObject = JSON.parseObject(resultStr);
            Result result = jsonObject.toJavaObject(Result.class);
            return result.getResult().toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存API类型信息失败", e.getMessage());

        }
        return null;
    }

    /**
     * 保存API服务信息
     *
     * @param registerBo
     * @return
     */
    @Override
    public Boolean saveApiInfo(RegisterBo registerBo) {
        try {
            //网关请求前缀
            String url = "http://" + gatewayAddress + ":" + gatewayPort + "/e97ee90d448707ff966e058eb4f52a82" + env + "/";
//            String resultStr = HttpUtil.post("http://10.30.30.126:8102/call/apiInfo/add", JSON.toJSONString(registerBo));
            String resultStr = HttpUtil.createPost(url).header("ticket", ticket).body(JSON.toJSONString(registerBo)).execute().body();
            JSONObject jsonObject = JSON.parseObject(resultStr);
            Result result = jsonObject.toJavaObject(Result.class);
            return result.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存API服务信息失败", e.getMessage());

        }
        return false;
    }

    /**
     * 删除API服务信息
     *
     * @param apiSecret
     * @return
     */
    @Override
    public Boolean deleteApiInfo(String apiSecret) {
        try {
            //网关请求前缀
            String url = "http://" + gatewayAddress + ":" + gatewayPort + "/12aff7ba7390848f1b48a953e1d5556f" + env + "/";
            GateWayDeleteApiBO gateWayDeleteApiBO = new GateWayDeleteApiBO();
            List<String> ids = new ArrayList<>();
            ids.add(apiSecret);
            gateWayDeleteApiBO.setIds(ids);
//            String resultStr = HttpUtil.post("http://10.30.30.126:8102/call/apiInfo/deleteApi", JSON.toJSONString(maps));
            String resultStr = HttpUtil.createPost(url).header("ticket", ticket).body(JSON.toJSONString(gateWayDeleteApiBO)).execute().body();
            JSONObject jsonObject = JSON.parseObject(resultStr);
            Result result = jsonObject.toJavaObject(Result.class);
            log.info("删除操作完成 返回结果" + result);
            return result.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除API服务信息失败", e.getMessage());

        }
        return false;
    }
}
