package com.hanwei.core.base.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hanwei.core.base.mapper.BaseCommonMapper;
import com.hanwei.core.base.service.BaseCommonService;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.dto.LogDTO;
import com.hanwei.core.common.api.vo.LoginUser;

import com.hanwei.core.util.IpUtils;
import com.hanwei.core.util.SpringContextUtils;
import com.hanwei.core.util.oConvertUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: common实现类
 */
@Service
@Slf4j
public class BaseCommonServiceImpl implements BaseCommonService {

    @Resource
    private BaseCommonMapper baseCommonMapper;

    @Resource
    private CommonAPI commonAPI;

    @Override
    public void addLog(LogDTO logDTO) {
        if(oConvertUtils.isEmpty(logDTO.getId())){
            logDTO.setId(String.valueOf(IdWorker.getId()));
        }
        //保存日志（异常捕获处理，防止数据太大存储失败，导致业务失败）
        try {
            logDTO.setCreateTime(new Date());
            baseCommonMapper.saveLog(logDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(" LogContent length : "+logDTO.getLogContent().length());
            log.warn(e.getMessage());
        }
    }

    @Override
    public void addLog(String logContent, Integer logType, Integer operatetype, LoginUser user) {
        LogDTO sysLog = new LogDTO();
        sysLog.setId(String.valueOf(IdWorker.getId()));
        //注解上的描述,操作日志内容
        sysLog.setLogContent(logContent);
        sysLog.setLogType(logType);
        sysLog.setOperateType(operatetype);
        try {
            //获取request
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(IpUtils.getIpAddr(request));
            //获取登录用户信息
            if(user==null){
                user = commonAPI.getLoginUser();
            }
        } catch (Exception e) {
            sysLog.setIp("127.0.0.1");
        }

        if(user!=null){
            sysLog.setUserid(user.getId());
            sysLog.setUsername(user.getUsername());
        }
        sysLog.setCreateTime(new Date());
        //保存日志（异常捕获处理，防止数据太大存储失败，导致业务失败）
        try {
            baseCommonMapper.saveLog(sysLog);
        } catch (Exception e) {
            log.warn(" LogContent length : "+sysLog.getLogContent().length());
            log.warn(e.getMessage());
        }
    }

    @Override
    public void addLog(String logContent, Integer logType, Integer operateType) {
        addLog(logContent, logType, operateType, null);
    }



}
