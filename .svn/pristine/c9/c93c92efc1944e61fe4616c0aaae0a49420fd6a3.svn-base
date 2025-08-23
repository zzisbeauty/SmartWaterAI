package com.hanwei.application.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 应用接口关系表
 * @Author: hanwei
 * @Date:   2025-05-28
 * @Version: V1.0
 */
@Data
@TableName("application_api_relation")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ApplicationApiRelation对象", description="应用接口关系表")
public class ApplicationApiRelation {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**applicationId*/
    @Schema(description = "applicationId")
    @NotEmpty(message = "applicationId不能为空")
    @ExcelProperty("applicationId")
	private java.lang.String applicationId;
	/**apiId*/
    @Schema(description = "apiId")
    @NotEmpty(message = "apiId不能为空")
    @ExcelProperty("apiId")
	private java.lang.String apiId;
}
