package com.hanwei.core.codegenerator.util.generate;

import java.util.List;
import java.util.Map;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 16:46]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 16:46]
 * @updateRemark : [说明本次修改内容]
 */
public interface IGenerate {
    Map<String, Object> a() throws Exception;

    List<String> generateCodeFile(String var1) throws Exception;

    List<String> generateCodeFile(String var1, String var2, String var3) throws Exception;
}
