package com.hanwei.process.vo;


import lombok.Data;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [外部链接数据封装类]
 * @createTime : [2024/11/10 9:43]
 * @updateUser : [CX]
 * @updateTime : [2024/11/10 9:43]
 * @updateRemark : [说明本次修改内容]
 */
@Data
public class UrlResult {
    /**
     * 标题
     */
    private String title;
    /**
     * 链接
     */
    private String url;
    /**
     * 播报文本
     */
    private String speechText;

}
