package com.hanwei.api.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @Description: 接口结果展示样式关联信息
 * @Author: hanwei
 * @Date:   2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("api_style_relation")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="ApiStyleRelation对象", description="接口结果展示样式关联信息")
public class ApiStyleRelation {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;

	/**接口Id*/
    @Schema(description = "接口Id")
	private java.lang.String apiId;

	/**样式id*/
    @Schema(description = "样式id")
	private java.lang.String styleId;

	/**样式名称*/
	@TableField(exist = false)
	private java.lang.String styleName;

	/**样式类型*/
	@TableField(exist = false)
	private java.lang.String styleType;

	/**样式方案*/
	@TableField(exist = false)
	private java.lang.String styleScheme;

	/**示例数据*/
	@TableField(exist = false)
	private java.lang.String sampleData;

	/**示例图片地址*/
	@TableField(exist = false)
	private java.lang.String iconUrl;

	/**返回结果类型编码*/
	@Schema(description = "返回结果类型编码")
	@NotEmpty(message = "返回结果类型编码不能为空")
	@Dict(dicCode = "result_type")
	@TableField(exist = false)
	private java.lang.String resultType;
}
