package com.hanwei.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import com.hanwei.style.entity.StyleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * @Description: 接口基础信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("api_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiInfo对象", description="接口基础信息")
public class ApiServiceInfo {

    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private java.lang.String id;

    /**接口编号*/
    @Schema(description = "接口编号")
    @ApiParameter(name = "apiCode", description = "接口编号",  demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiCode;

    /**接口名称*/
    @Schema(description = "接口名称")
    @NotEmpty(message = "接口名称不能为空")
    @ApiParameter(name = "apiName", description = "接口名称",  demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiName;

    /**所属分类*/
    @Schema(description = "所属分类")
    @ApiParameter(name = "categoryId", description = "所属分类",  demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "api_categray")
    private java.lang.String categoryId;

    /**业务系统编码*/
    @Schema(description = "业务系统编码")
    @NotEmpty(message = "所属业务系统不能为空")
    @Dict(dicCode = "business_code")
    @ApiParameter(name = "businessSystemCode", description = "业务系统编码",  demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String businessSystemCode;

    /**类型(网关接口，一般接口，链接)*/
    @Schema(description = "类型(网关接口，一般接口，链接)")
    @NotEmpty(message = "类型(网关接口，一般接口，链接)不能为空")
    @Dict(dicCode = "api_type")
    @ApiParameter(name = "type", description = "类型", demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String type;

    /**接口描述*/
    @Schema(description = "接口描述")
    @ApiParameter(name = "apiDesc", description = "接口描述",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiDesc;

    /**接口密钥*/
    @Schema(description = "接口密钥")
    @ApiParameter(name = "apiSecret", description = "接口密钥",location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiSecret;

    /**访问url*/
    @Schema(description = "访问url")
    @ApiParameter(name = "url", description = "访问url",location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String url;

    /**链接地址*/
    @Schema(description = "链接地址")
    @ApiParameter(name = "linkUrl", description = "链接地址",location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String linkUrl;

    /**请求方式*/
    @Schema(description = "请求方式")
    @ApiParameter(name = "requestType", description = "请求方式",location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String requestType;

    /**接口播报语*/
    @Schema(description = "接口播报语")
    @ApiParameter(name = "apiSpeech", description = "接口播报语",location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String apiSpeech;

    /**创建人*/
    @Schema(description = "创建人")
    private java.lang.String createBy;

    /**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private java.util.Date createTime;

    /**更新人*/
    @Schema(description = "更新人")
    private java.lang.String updateBy;

    /**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private java.util.Date updateTime;

    /**接口参数配置*/
    @TableField(exist = false)
    private List<ApiParamConfig> apiParamConfigList;

    /**接口提问语配置*/
    @TableField(exist = false)
    private List<ApiQuestionConfig> apiQuestionConfigList;

    /**接口关联问题配置*/
    @TableField(exist = false)
    private List<ApiRelationConfig> apiRelationConfigList;

    /**接口结果样式配置*/
    @TableField(exist = false)
    private List<ApiStyleRelation> apiStyleRelationList;
}
