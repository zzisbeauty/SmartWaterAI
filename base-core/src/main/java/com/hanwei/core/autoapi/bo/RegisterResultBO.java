package com.hanwei.core.autoapi.bo;

import lombok.Data;

import java.util.List;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [注册结果返回类]
 * @createTime : [2025/6/25 16:33]
 */
@Data
public class RegisterResultBO {
    private Integer successMethodCount;
    private Integer failMethodCount;
    private List<String> failMethodList;
}
