package com.hanwei.core.autoapi.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Api分类信息 新网关用
 * @Author: hanwei
 * @Date:   2025-03-09
 * @Version: V1.0
 */
@Data
@Schema(title="ApiKindBo对象", description="Api分类信息")
public class ApiKindBo {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "分类名称")
    private String assortName;

    @Schema(description = "分类编码")
    private String assortCode;

    @Schema(description = "排序编号")
    private Integer sort;

    @Schema(description = "分类状态 1 启用 0 禁用")
    private Integer state;

    @Schema(description = "父级ID -1 代表父级")
    private String parentId;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "租户ID")
    private Integer tenementGuid;

    @Schema(description = "创建人")
    private String createBy;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private java.util.Date createTime;

    @Schema(description = "最后更新人")
    private String updateBy;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最后更新时间")
    private java.util.Date updateTime;

    private List<ApiKindBo> child = new ArrayList<>();
}
