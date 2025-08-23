package com.hanwei.style.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.api.entity.ApiServiceInfo;
import com.hanwei.api.entity.ApiStyleRelation;
import com.hanwei.api.mapper.ApiServiceInfoMapper;
import com.hanwei.api.service.IApiServiceInfoService;
import com.hanwei.api.service.IApiStyleRelationService;
import com.hanwei.core.autoapi.entity.ApiInfo;
import com.hanwei.core.autoapi.service.IApiInfoService;
import com.hanwei.core.common.CommonConstant;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.dto.FileDTO;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.style.entity.StyleInfo;
import com.hanwei.style.mapper.StyleInfoMapper;
import com.hanwei.style.service.IStyleInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @Description: 展示样式管理
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Service
public class StyleInfoServiceImpl extends ServiceImpl<StyleInfoMapper, StyleInfo> implements IStyleInfoService {

    @Autowired
    private IApiStyleRelationService apiStyleRelationService;

    @Autowired
    private ApiServiceInfoMapper apiServiceInfoMapper;

    @Autowired
    private CommonAPI commonApi;

    /**
     * 保存展示样式信息
     * @param styleInfo
     * @return
     */
    @Override
    public boolean saveStyleInfo(@Valid StyleInfo styleInfo) {
        try {
            styleInfo.setStatus(CommonConstant.STATUS_NORMAL);
            save(styleInfo);
            commonApi.updateFileRelation(styleInfo.getId(),styleInfo.getFileId());
            List<FileDTO> fileDTOS = commonApi.findFileList(styleInfo.getId());
            if(Optional.ofNullable(fileDTOS).isPresent() && fileDTOS.size() > 0){
                styleInfo.setIconUrl(fileDTOS.get(0).getFilePath());
            }
            updateById(styleInfo);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 根据apiId删除展示样式信息
     * @param apiServiceInfoId
     */
    @Override
    public void removeByApiId(String apiServiceInfoId) {
        ApiStyleRelation apiStyleRelation = apiStyleRelationService.getOne(new LambdaQueryWrapper<ApiStyleRelation>().eq(ApiStyleRelation::getApiId,apiServiceInfoId),false);
        if(Optional.ofNullable(apiStyleRelation).isPresent()){
            apiStyleRelationService.removeById(apiStyleRelation.getId());
            removeById(apiStyleRelation.getStyleId());
        }
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Override
    public Result<?> removeStyleById(String id){
        //是否有Api使用
        List<ApiStyleRelation> apiStyleRelationList = apiStyleRelationService.list(new LambdaQueryWrapper<ApiStyleRelation>()
                .eq(ApiStyleRelation::getStyleId,id));
        if(Optional.ofNullable(apiStyleRelationList).isPresent() && apiStyleRelationList.size() > 0){
            StringBuilder sb = new StringBuilder();
            sb.append("当前样式正在被接口使用,不允许删除。使用得接口有:");
            for(ApiStyleRelation apiStyleRelation : apiStyleRelationList){
                ApiServiceInfo apiServiceInfo = apiServiceInfoMapper.selectById(apiStyleRelation.getApiId());
                sb.append(apiServiceInfo.getApiName()).append(" ");
            }
            return Result.error(200,sb.toString());
        }

        removeById(id);
        return Result.OK("删除成功");
    }

    /**
     * 修改展示样式信息
     * @param styleInfo
     * @return
     */
    @Override
    public Boolean updateStyleInfoById(StyleInfo styleInfo) {
        try {
            commonApi.updateFileRelation(styleInfo.getId(),styleInfo.getFileId());
            List<FileDTO> fileDTOS = commonApi.findFileList(styleInfo.getId());
            if(Optional.ofNullable(fileDTOS).isPresent() && fileDTOS.size() > 0){
                styleInfo.setIconUrl(fileDTOS.get(0).getFilePath());
            }
            return updateById(styleInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
