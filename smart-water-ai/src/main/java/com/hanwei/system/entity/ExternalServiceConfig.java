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
 * @Description: 外部服务配置表(配置基本不变并且难以获取的外部服务数据)
 * @Author: hanwei
 * @Date:   2025-07-16
 * @Version: V1.0
 */
@Data
@TableName("external_service_config")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@Schema(title="ExternalServiceConfig对象", description="外部服务配置表(配置基本不变并且难以获取的外部服务数据)")
public class ExternalServiceConfig {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    @NotEmpty(message = "id不能为空")
    @ApiParameter(name = "id", description = "id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**服务Id*/
    @Schema(description = "服务Id")
    @NotEmpty(message = "服务Id不能为空")
    @ApiParameter(name = "serviceId", description = "服务Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String serviceId;
	/**key*/
    @Schema(description = "key")
    @NotEmpty(message = "key不能为空")
    @ApiParameter(name = "key", description = "key", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String key;
	/**展示文本*/
    @Schema(description = "展示文本")
    @NotEmpty(message = "展示文本不能为空")
    @ApiParameter(name = "text", description = "展示文本", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String text;
	/**指标*/
    @Schema(description = "指标")
    @ApiParameter(name = "target", description = "指标", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String target;

    /**备注标记*/
    @Schema(description = "备注标记")
    @NotEmpty(message = "备注标记不能为空")
    @ApiParameter(name = "remark", description = "备注标记", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String remark;
}
