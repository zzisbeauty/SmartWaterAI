package com.hanwei.core.security;

import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.core.util.RedisUtil;
import com.hanwei.core.util.SensitiveInfoUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl {

    public LoginUser loadUserByUsername(String ticket) throws UsernameNotFoundException {
        LoginUser loginUser = null;

        //判断ticket是否为空
        if (!Optional.ofNullable(ticket).isPresent()){
            Result.error("ticket为空，请登录后重试!");
            return loginUser;
        }

        //判断ticket是否在缓存中
        if(RedisUtil.hasKey(ticket)){
            try {
                loginUser = (LoginUser) RedisUtil.get(ticket);
                //解密用户
                SensitiveInfoUtil.handlerObject(loginUser, false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            //TODO 新网关完成后重构
            //获取用户信息
//            ResultDTO httpResult = sysUserService.getUserInfo(ticket);
//
//            JSONObject json = JSONObject.parseObject(httpResult.getResult().toString());
//            if(!Optional.ofNullable(json).isPresent()){
//                return loginUser;
//            }
//            //实例化封装用户信息
//            LoginUser user = new LoginUser();
//            user.setTenementguid((String) Optional.ofNullable(json.get("tenementguid")).orElse(""));
//            user.setAvatar((String) Optional.ofNullable(json.get("useravataraddress")).orElse(""));
//            user.setPost((String) Optional.ofNullable(json.get("postname")).orElse(""));
//            user.setRealname((String) Optional.ofNullable(json.get("username")).orElse(""));
//            user.setUsername((String) Optional.ofNullable(json.get("usercode")).orElse(""));
//            user.setPhone((String) Optional.ofNullable(json.get("mobilephone")).orElse(""));
//            user.setId((String) Optional.ofNullable(json.get("userguid")).orElse(""));
//            user.setOrgCode((String) Optional.ofNullable(json.get("buguid")).orElse(""));
//            user.setOrgName((String) Optional.ofNullable(json.get("buname")).orElse(""));
//            user.setUserIdentity((Integer) Optional.ofNullable(json.get("usertype")).orElse(0));
//
//            //将ticket和对应的用户信息对象存入缓存中全局调用
//            RedisUtil.set(ticket,user,3600);
        }

        return loginUser;
    }

}
