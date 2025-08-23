package com.hanwei.core.autoapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description:
 * @Auther:
 * @Date: 2025/03/31/13:43
 */
@Data
@Schema(title="nodeInfo", description="节点类型的服务端配置信息")
public class NodeInfoCore {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "主机名：IP")
    private String host;

    @Schema(description = "端口号")
    private Integer port;


    @Schema(description = "权重，默认 1；权重越大优先级越高")
    private Integer weight = 1;

    @Schema(description = "配置信息ID")
    private String baseId;

    @Schema(description = "服务分组ID")
    private String serviceId;

    @Schema(description = "统一规则ID")
    private String ruleId;

    @Schema(description = "删除标记  0 未删除 1已删除")
    private Integer delFlag = 0;

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

}
