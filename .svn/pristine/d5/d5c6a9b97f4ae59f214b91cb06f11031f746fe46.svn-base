package com.hanwei.system.entity;

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
 * @Description: 字典表
 * @Author: hanwei
 * @Date:   2025-05-13
 * @Version: V1.0
 */
@Data
@TableName("sys_dict")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="Dict对象", description="字典表")
public class Dict {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**dictName*/
    @Schema(description = "dictName")
    @NotEmpty(message = "dictName不能为空")
    @ExcelProperty("dictName")
    @ApiParameter(name = "dictName", description = "名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String dictName;
	/**dictCode*/
    @Schema(description = "dictCode")
    @NotEmpty(message = "dictCode不能为空")
    @ExcelProperty("dictCode")
    @ApiParameter(name = "dictCode", description = "编码", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String dictCode;
	/**description*/
    @Schema(description = "description")
    @ExcelProperty("description")
    @ApiParameter(name = "description", description = "描述", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.lang.String description;
	/**createBy*/
    @Schema(description = "createBy")
    @ExcelProperty("createBy")
	private java.lang.String createBy;
	/**createTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "createTime")
    @ExcelProperty("createTime")
	private java.util.Date createTime;
	/**updateBy*/
    @Schema(description = "updateBy")
    @ExcelProperty("updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "updateTime")
    @ExcelProperty("updateTime")
	private java.util.Date updateTime;
	/**tenementguid*/
    @Schema(description = "tenementguid")
	private java.lang.String tenementguid;
}
