package com.hanwei.asr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: ASR热词信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("asr_hot_word")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "AsrHotWord对象", description = "ASR热词信息")
public class AsrHotWord {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private java.lang.String id;

    /**
     * 热词表名称
     */
    @Schema(description = "热词表名称")
    @NotEmpty(message = "热词表名称不能为空")
    @ApiParameter(name = "hotWordName", description = "热词表名称", required = true, demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String hotWordName;

    /**
     * 热词表描述
     */
    @Schema(description = "热词表描述")
    @ApiParameter(name = "hotWordDesc", description = "热词表描述", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String hotWordDesc;

    /**
     * 热词内容
     */
    @Schema(description = "热词内容")
    @NotEmpty(message = "热词内容不能为空")
    @Size(max = 2048, message = "热词内容长度不能超过2048字符")
    @ApiParameter(name = "hotWordContent", description = "热词内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String hotWordContent;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private java.lang.String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private java.util.Date createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private java.lang.String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private java.util.Date updateTime;
}
