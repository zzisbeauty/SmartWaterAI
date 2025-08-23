package com.hanwei.core.autoapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description:
 * @Auther:
 * @Date: 2025/03/31/13:21
 */

@Data
@Schema(title="nodeBaseInfo", description="API请求基本信息")
public class BaseServerInfoCore {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "版本信息ID")
    private String versionId;

    @Schema(description = "负载均衡算法；默认 roundrobin(带权轮训)")
    private String type = "roundrobin";

    @Schema(description = "哈希的输入位置（type为cash时保存此参数）：vars/header/cookie/consumer/vars_combinations")
    private String vars;

    @Schema(description = "哈希键（type为cash时保存此参数）：remote_addr/host/uri/server_name/server_addr/request_url/query_string/remote_port")
    private String key;

    @Schema(description = "重试次数（默认3次）")
    private Integer retries = 3;

    @Schema(description = "连接超时时间（默认 6 秒）")
    private Integer connect = 6;

    @Schema(description = "发送超时时间（默认 6 秒）")
    private Integer send = 6;

    @Schema(description = "从服务端接收数据的超时时间（默认 6秒）")
    private Integer read = 6;

    @Schema(description = "Host请求头，默认 :pass(保持与客户端请求一致的主机名)；node：使用目标节点列表中的主机名或IP")
    private String passHost = "pass";

    @Schema(description = "空闲超时时间（默认 60 秒）")
    private Integer idleTimeout = 60;

    @Schema(description = "请求数量（默认 1000）")
    private Integer requests = 1000;

    @Schema(description = "连接池大小（默认 320）")
    private Integer size = 320;

    @Schema(description = "超时重试时间(默认 5 秒)；限制是否继续重试的时间，若之前的请求和重试请求花费太多时间就不在继续重试，0代表不启用重试超时时间")
    private Integer retryTimeout = 5;

    @Schema(description = "服务端服务类型( 0：IP+端口；1：统一服务端配置；2：统一规则配置；3：nacos服务 )")
    private String serviceType;

    @Schema(description = "服务端配置ID")
    private String serviceId;

    @Schema(description = "统一规则配置ID")
    private String ruleId;

    @Schema(description = "环境（DEBUG/TEST/PRE/RELEASE）")
    private String environment;

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
