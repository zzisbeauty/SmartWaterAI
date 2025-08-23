package com.hanwei.core.autoapi.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.autoapi.entity.ApiInfoVoCore;
import com.hanwei.core.autoapi.entity.BaseServerInfoCore;
import com.hanwei.core.autoapi.entity.CloudInfoCore;
import com.hanwei.core.autoapi.entity.NodeInfoCore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 新网关API注册
 * @Author: hanwei
 * @Date:   2025-02-07
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title="RegisterBo对象", description="新网关API注册实体")
public class RegisterBo {

	/**API基础信息*/
    @Schema(description = "API基础信息")
	private ApiInfoVoCore baseInfo;
	/**分布式服务信息*/
    @Schema(description = "分布式服务信息")
	private CloudInfoCore cloudInfo;
	/**节点信息*/
    @Schema(description = "节点信息")
	private NodeInfoCore nodeInfo;
	/**基础服务信息*/
    @Schema(description = "基础服务信息")
	private BaseServerInfoCore serverInfo;
}
