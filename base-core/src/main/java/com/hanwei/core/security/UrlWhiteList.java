package com.hanwei.core.security;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zht
 * @Description: URL白名单配置类，用于配置不需要进行登录用户处理的URL
 */
public class UrlWhiteList {

    public static final List<String> WHITELIST = new ArrayList<>();

    static {
        //登录接口例外处理
        WHITELIST.add("/sysidentity/sso/login/**");
    }
}
