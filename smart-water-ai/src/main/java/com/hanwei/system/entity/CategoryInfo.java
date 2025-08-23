package com.hanwei.system.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.Dict;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 分类信息
 * @Author: hanwei
 * @Date: 2025-05-14
 * @Version: V1.0
 */
@Data
@TableName("sys_category_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title = "CategoryInfo对象", description = "分类信息")
public class CategoryInfo {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private java.lang.String id;

    /**
     * 分类分组
     */
    @Schema(description = "分类分组")
    @NotEmpty(message = "分类分组不能为空")
    @ExcelProperty("分类分组")
    @Dict(dicCode = "category_group")
    @ApiParameter(name = "categoryGroup", description = "分类分组", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String categoryGroup;

    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    @NotEmpty(message = "分类名称不能为空")
    @ExcelProperty("分类名称")
    @ApiParameter(name = "categoryName", description = "分类名称", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String categoryName;

    /**
     * 分类编码
     */
    @Schema(description = "分类编码")
    @ExcelProperty("分类编码")
    @ApiParameter(name = "categoryCode", description = "分类编码", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String categoryCode;

    /**
     * 层级编码
     */
    @Schema(description = "层级编码")
    @ApiParameter(name = "hierarchy", description = "层级编码", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String hierarchy;

    /**
     * 父Id
     */
    @Schema(description = "父Id")
    @ApiParameter(name = "parentId", description = "父Id", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private java.lang.String parentId;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private java.lang.String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private java.util.Date createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private java.lang.String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private java.util.Date updateTime;
}
