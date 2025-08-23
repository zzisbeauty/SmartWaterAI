package com.hanwei.process.modelinvoking.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.vo.ResultForFrontVo;

/**
 * @Description: 模型调用信息表Service接口
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
public interface IModelInvokingService extends IService<ModelInvokingInfo> {
    /**
     * 模型请求控制器
     * @param ChannelCode 渠道号
     * @param message 文本
     * @return
     */
    ResultForFrontVo messageHandleController(String ChannelCode, String message);

    /**
     * 数字人鉴权接口 获取token
     * @return
     */
    String getDigitalHumanToken();

    /**
     * 模型切换
     * @param message
     * @return
     */
    ResultForFrontVo changeModel(String message);
}
