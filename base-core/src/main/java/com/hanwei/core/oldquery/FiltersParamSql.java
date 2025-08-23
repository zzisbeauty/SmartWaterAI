package com.hanwei.core.oldquery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/***
 * 查询条件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltersParamSql {

    //查询条件 and or
    private String w;
    //分组
    private String g;
    //字段名称
    private String f;
    //判断条件 例如= < > like
    private String op;
    //字段类型
    private String t;
    //字段值
    private String v;
    //参数
    private String para;



}
