package com.hanwei.log.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 模型对话日志
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Data
@TableName("dialogue_log")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="DialogueLog对象", description="模型对话日志")
public class DialogueLog {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    @NotEmpty(message = "id不能为空")
    @ExcelProperty("id")
	private java.lang.String id;
	/**应用id*/
    @Schema(description = "应用id")
    @NotEmpty(message = "应用id不能为空")
    @ExcelProperty("应用id")
    @ApiParameter(name = "applicationId", description = "应用id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String applicationId;
	/**用户id*/
    @Schema(description = "用户id")
    @NotEmpty(message = "用户id不能为空")
    @ExcelProperty("用户id")
    @ApiParameter(name = "userId", description = "用户id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String userId;
	/**客户端id*/
    @Schema(description = "客户端id")
    @NotEmpty(message = "客户端id不能为空")
    @ExcelProperty("客户端id")
    @ApiParameter(name = "clientId", description = "客户端id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String clientId;
	/**文件id*/
    @Schema(description = "文件id")
    @NotEmpty(message = "文件id不能为空")
    @ExcelProperty("文件id")
    @ApiParameter(name = "fileId", description = "文件id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String fileId;
	/**提问内容*/
    @Schema(description = "提问内容")
    @NotEmpty(message = "提问内容不能为空")
    @ExcelProperty("提问内容")
    @ApiParameter(name = "questionContent", description = "提问内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String questionContent;
	/**回答内容*/
    @Schema(description = "回答内容")
    @NotEmpty(message = "回答内容不能为空")
    @ExcelProperty("回答内容")
    @ApiParameter(name = "answerContent", description = "回答内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String answerContent;
	/**问题发起时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "问题发起时间")
    @NotEmpty(message = "问题发起时间不能为空")
    @ExcelProperty("问题发起时间")
    @ApiParameter(name = "questionTime", description = "问题发起时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date questionTime;
	/**回答生成时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "回答生成时间")
    @NotEmpty(message = "回答生成时间不能为空")
    @ExcelProperty("回答生成时间")
    @ApiParameter(name = "answerTime", description = "回答生成时间", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date answerTime;
	/**用时(分钟)*/
    @Schema(description = "用时(分钟)")
    @NotEmpty(message = "用时(分钟)不能为空")
    @ExcelProperty("用时(分钟)")
    @ApiParameter(name = "costTime", description = "用时(分钟)", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer costTime;
	/**操作类型*/
    @Schema(description = "操作类型")
    @NotEmpty(message = "操作类型不能为空")
    @ExcelProperty("操作类型")
    @ApiParameter(name = "operatorTime", description = "操作类型", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String operatorTime;
	/**处理状态*/
    @Schema(description = "处理状态")
    @NotEmpty(message = "处理状态不能为空")
    @ExcelProperty("处理状态")
    @ApiParameter(name = "handleStatus", description = "处理状态", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String handleStatus;
	/**用户反馈标记*/
    @Schema(description = "用户反馈标记")
    @NotEmpty(message = "用户反馈标记不能为空")
    @ExcelProperty("用户反馈标记")
    @ApiParameter(name = "feedBackSign", description = "用户反馈标记", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String feedBackSign;
	/**输入tokens数量*/
    @Schema(description = "输入tokens数量")
    @NotEmpty(message = "输入tokens数量不能为空")
    @ExcelProperty("输入tokens数量")
    @ApiParameter(name = "inputToken", description = "输入tokens数量", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer inputToken;
	/**输出tokens数量*/
    @Schema(description = "输出tokens数量")
    @NotEmpty(message = "输出tokens数量不能为空")
    @ExcelProperty("输出tokens数量")
    @ApiParameter(name = "outputToken", description = "输出tokens数量", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.Integer outputToken;
}
