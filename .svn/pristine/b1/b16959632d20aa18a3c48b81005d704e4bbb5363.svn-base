package com.hanwei.api.entity;

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
 * @Description: 接口提问语信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("api_question_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiQuestionConfig对象", description="接口提问语信息")
public class ApiQuestionConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

	/**接口Id*/
    @Schema(description = "接口Id")
    @NotEmpty(message = "接口Id不能为空")
	@ApiParameter(name = "apiId", description = "接口Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String apiId;

	/**提问内容*/
    @Schema(description = "提问内容")
    @NotEmpty(message = "提问内容不能为空")
	@ApiParameter(name = "questionContent", description = "提问内容", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String questionContent;

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
}
