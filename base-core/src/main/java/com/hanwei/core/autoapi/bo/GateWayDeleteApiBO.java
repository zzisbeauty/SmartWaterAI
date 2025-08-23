package com.hanwei.core.autoapi.bo;

import lombok.Data;

import java.util.List;

/**
 * @version : [v1.0]
 * @description : [网关根据密钥删除API BO]
 * @createTime : [2025/6/3 17:08]
 * @updateUser : [CX]
 * @updateTime : [2025/6/3 17:08]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class GateWayDeleteApiBO {
    /**
     * ID 单条删除用
     */
    private String id;

    /**
     * ids 批量删除用
     */
    private List<String> ids;
}
