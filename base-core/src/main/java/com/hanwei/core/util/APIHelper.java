package com.hanwei.core.util;


import com.alibaba.fastjson.JSON;
import com.hanwei.core.common.api.vo.HttpResult;
import com.hanwei.core.common.api.vo.LoginUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:网关公用方法 后期需要处理成实体对象 TODO
 */
@Component
public class APIHelper {
    public static String identitySystemHost ;

//    @Value("${hanwei.identity.host}")
    public void setIdentitySystemHost(String host) {
        identitySystemHost = host;
    }

    /**
     * 获取用户guid
     * @param ticket
     * @return
     */
    public static HttpResult GetUserGUID(String ticket)
    {
        return  Get("/api/account/getUserGUID",ticket);
    }

    /**
     * 获取租户guid
     * @param ticket
     * @return
     */
    public static HttpResult GetTenementGUID(String ticket)
    {
        return  Get("/api/account/getTenementGUID",ticket);
    }

    /**
     * 获用户信息
     * @param ticket
     * @return
     */
    public static HttpResult GetUserInfo(String ticket)
    {
        return  Get("/api/account/getUserInfo",ticket);
    }

    /**
     * 获用户导航信息
     * @param ticket
     * @return
     */
    public static HttpResult GetClientData(String ticket)
    {
        return  Get("/api/account/GetClientData",ticket);
    }

    /**
     * 获用户及租户guid
     * @param ticket
     * @return
     */
    public static HttpResult GetUserGUIDAndTenementGUID(String ticket)
    {
        return  Get("/api/account/getUserGUIDAndTenementGUID",ticket);
    }

    /**
     * 请求
     * @param path
     * @param ticket
     * @return
     */
    public static HttpResult Get(String path, String ticket)
    {
        HttpResult httpResult=new HttpResult();
        Map param = new HashMap();
        param.put("ticket",ticket);
        String req="";
        try
        {
            req= SmartHttpUtil.sendGet(identitySystemHost+path,param,null);

        }catch (Exception e){
            httpResult.setResult(false);
            httpResult.setErrorMessage(e.toString());
            return httpResult;
        }

        return JSON.parseObject(req, HttpResult.class);
    }
}
