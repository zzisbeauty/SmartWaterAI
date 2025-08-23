package com.hanwei.asr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: ASR关键词信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("asr_key_word")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "AsrKeyWord对象", description = "ASR关键词信息")
public class AsrKeyWord {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private java.lang.String id;

    /**
     * 唤醒词
     */
    @Schema(description = "唤醒词")
    @NotEmpty(message = "唤醒词不能为空")
    @ApiParameter(name = "wakeUpWord", description = "唤醒词", required = true, demovalue = "1",
            location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String wakeUpWord;
    /**
     * 休眠词
     */
    @Schema(description = "休眠词")
    @ApiParameter(name = "sleepWord", description = "休眠词", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String sleepWord;
    /**
     * 欢迎词
     */
    @Schema(description = "欢迎词")
    @ApiParameter(name = "entryWord", description = "欢迎词", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String entryWord;

    /**
     * 退场词
     */
    @Schema(description = "退场词")
    @ApiParameter(name = "exitWord", description = "退场词", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String exitWord;

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
