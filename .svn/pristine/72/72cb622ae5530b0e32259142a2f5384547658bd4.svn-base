package com.hanwei.core.autoapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description:
 * @Auther:
 * @Date: 2025/03/31/13:48
 */
@Data
@Schema(title="cloudInfo", description="服务发现类型的服务端配置信息")
public class CloudInfoCore {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "服务发现方式，默认nacos;(dns/consul_kv/eureka)")
    private String discoveryType = "nacos";

    @Schema(description = "暴漏服务名")
    private String serviceName;

    @Schema(description = "nacos 分组名")
    private String groupName;

    @Schema(description = "nacos 命名空间ID")
    private String namespaceId;

    @Schema(description = "配置信息ID")
    private String baseId;

    @Schema(description = "服务分组ID")
    private String serviceId;

    @Schema(description = "统一规则ID")
    private String ruleId;

    @Schema(description = "删除标记  0 未删除 1已删除")
    private Integer delFlag;

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
