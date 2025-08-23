package com.hanwei.process.business.hotline.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.process.business.hotline.service.IHotLineService;
import com.hanwei.process.modelinvoking.entity.ModelInvokingInfo;
import com.hanwei.process.modelinvoking.mapper.ModelInvokingMapper;
import com.hanwei.process.vo.ResultForFrontVo;
import com.hanwei.process.vo.UrlResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: 模型调用信息ServiceImpl
 * @Author: zwyx
 * @Date:   2024-10-21
 * @Version: V1.0
 */
@Service
@Slf4j
public class HotLineServiceImpl extends ServiceImpl<ModelInvokingMapper, ModelInvokingInfo> implements IHotLineService {

    /**
     * 热线数据总览
     * 热线用户画像
     * 热线数据分析汇总
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics(String ChannelCode) {
        // 模拟数据
        ResultForFrontVo resultForFrontVo = new ResultForFrontVo();
        resultForFrontVo.setResultTo(CommonConstant.RESULT_TO_SYSTEM);
        resultForFrontVo.setResultType(CommonConstant.RESULT_TYPE_URL);
        UrlResult urlResult = new UrlResult();
        urlResult.setTitle("热线数据总览");
        urlResult.setUrl("http://10.0.15.41:8000/fkrx/#/info");
        urlResult.setSpeechText("为您展示热线数据总览");

        resultForFrontVo.setUrlResult(urlResult);

        //TODO 调用业务接口 替换数据

        return resultForFrontVo;
    }

    /**
     * 热线数据总览
     * 热线用户画像
     * 热线数据分析汇总
     * @return
     */
    @Override
    public ResultForFrontVo dataStatistics() {
        return dataStatistics(CommonConstant.CHANNEL_CODE_BIGSCREEN);
    }
}
