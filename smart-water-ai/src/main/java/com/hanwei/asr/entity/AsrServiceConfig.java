package com.hanwei.asr.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: ASR实例配置信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("asr_service_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="AsrServiceConfig对象", description="ASR实例配置信息")
public class AsrServiceConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**ASRId*/
    @Schema(description = "ASRId")
    @NotEmpty(message = "ASRId不能为空")
    @ExcelProperty("ASRId")
    @Dict(dicCode = "id",dicText = "name",dictTable = "asr_service_info")
    @ApiParameter(name = "asrId", description = "ASRId", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String asrId;
    /**ASR名称*/
    @Schema(description = "ASR名称")
    @NotEmpty(message = "ASR名称不能为空")
    @ExcelProperty("ASR名称")
    @ApiParameter(name = "name", description = "ASR名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String name;
	/**语音格式*/
    @Schema(description = "语音格式")
    @NotEmpty(message = "语音格式不能为空")
    @ExcelProperty("语音格式")
    @ApiParameter(name = "phoneticMatric", description = "语音格式", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String phoneticMatric;
	/**模型特征串*/
    @Schema(description = "模型特征串")
    @NotEmpty(message = "模型特征串不能为空")
    @ExcelProperty("模型特征串")
    @ApiParameter(name = "modelCharacter", description = "模型特征串", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String modelCharacter;
	/**结果是否添加标点*/
    @Schema(description = "结果是否添加标点")
    @Digits(integer = 1, fraction = 0, message = "数字是否识别为阿拉伯数字只能是整数")
    @ExcelProperty("结果是否添加标点")
    @Dict(dicCode = "yes_no")
    @ApiParameter(name = "addPunctuation", description = "结果是否添加标点", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer addPunctuation;
	/**数字是否识别为阿拉伯数字*/
    @Schema(description = "数字是否识别为阿拉伯数字")
    @Digits(integer = 1, fraction = 0, message = "数字是否识别为阿拉伯数字只能是整数")
    @ExcelProperty("数字是否识别为阿拉伯数字")
    @Dict(dicCode = "yes_no")
    @ApiParameter(name = "toArabicNumberals", description = "数字是否识别为阿拉伯数字", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer toArabicNumberals;
	/**头静音时间(秒)*/
    @Schema(description = "头静音时间(秒)")
    @NotNull(message = "头静音时间(秒)不能为空")
    @ExcelProperty("头静音时间(秒)")
    @ApiParameter(name = "headSilenceTime", description = "头静音时间(秒)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer headSilenceTime;
	/**尾静音时间(秒)*/
    @Schema(description = "尾静音时间(秒)")
    @NotNull(message = "尾静音时间(秒)不能为空")
    @ExcelProperty("尾静音时间(秒)")
    @ApiParameter(name = "tailSilenceTime", description = "尾静音时间(秒)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer tailSilenceTime;
	/**是否输出中间结果*/
    @Schema(description = "是否输出中间结果")
    @Digits(integer = 1, fraction = 0, message = "数字是否识别为阿拉伯数字只能是整数")
    @ExcelProperty("是否输出中间结果")
    @ApiParameter(name = "outputMiddleResult", description = "是否输出中间结果", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "yes_no")
	private Integer outputMiddleResult;
	/**热词表id*/
    @Schema(description = "热词表id")
    @ExcelProperty("热词表id")
    @ApiParameter(name = "hotWordId", description = "热词表id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String hotWordId;
	/**关键词语ID*/
    @Schema(description = "关键词语ID")
    @ExcelProperty("关键词语ID")
    @ApiParameter(name = "keyWordId", description = "关键词语ID", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String keyWordId;
	/**是否输出分词*/
    @Schema(description = "是否输出分词")
    @Digits(integer = 1, fraction = 0, message = "数字是否识别为阿拉伯数字只能是整数")
    @ExcelProperty("是否输出分词")
    @ApiParameter(name = "outputPartticple", description = "是否输出分词", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "yes_no")
	private Integer outputPartticple;
    /**启停状态*/
    @Schema(description = "启停状态")
    @ExcelProperty("启停状态")
    @ApiParameter(name = "status", description = "启停状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
    @Dict(dicCode = "until_status")
    private Integer status;
	/**创建人*/
    @Schema(description = "创建人")
    @ExcelProperty("创建人")
	private java.lang.String createBy;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
	private java.util.Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    @ExcelProperty("更新人")
	private java.lang.String updateBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;
}
