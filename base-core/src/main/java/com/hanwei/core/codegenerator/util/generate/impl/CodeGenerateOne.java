package com.hanwei.core.codegenerator.util.generate.impl;


import com.hanwei.core.codegenerator.util.database.DbReadTableUtil;
import com.hanwei.core.codegenerator.util.generate.IGenerate;

import com.hanwei.core.codegenerator.util.generate.pojo.ColumnVo;
import com.hanwei.core.codegenerator.util.generate.pojo.TableVo;
import com.hanwei.core.codegenerator.util.generate.util.NonceUtils;
import com.hanwei.core.codegenerator.util.generate.util.g;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 15:24]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 15:24]
 * @updateRemark : [说明本次修改内容]
 */
public class CodeGenerateOne extends com.hanwei.core.codegenerator.util.generate.impl.a.a implements IGenerate {
    private static final Logger a = LoggerFactory.getLogger(CodeGenerateOne.class);
    private TableVo b;
    private List<ColumnVo> e;
    private List<ColumnVo> f;

    public CodeGenerateOne(TableVo tableVo) {
        this.b = tableVo;
    }

    public CodeGenerateOne(TableVo tableVo, List<ColumnVo> columns, List<ColumnVo> originalColumns) {
        this.b = tableVo;
        this.e = columns;
        this.f = originalColumns;
    }

    @Override
    public Map<String, Object> a() throws Exception {
        HashMap var1 = new HashMap();
        var1.put("bussiPackage", com.hanwei.core.codegenerator.util.a.a.g);
        var1.put("entityPackage", this.b.getEntityPackage());
        var1.put("entityName", this.b.getEntityName());
        var1.put("tableName", this.b.getTableName());
        var1.put("primaryKeyField", com.hanwei.core.codegenerator.util.a.a.l);
        if (this.b.getFieldRequiredNum() == null) {
            this.b.setFieldRequiredNum(StringUtils.isNotEmpty(com.hanwei.core.codegenerator.util.a.a.m) ? Integer.parseInt(com.hanwei.core.codegenerator.util.a.a.m) : -1);
        }

        if (this.b.getSearchFieldNum() == null) {
            this.b.setSearchFieldNum(StringUtils.isNotEmpty(com.hanwei.core.codegenerator.util.a.a.n) ? Integer.parseInt(com.hanwei.core.codegenerator.util.a.a.n) : -1);
        }

        if (this.b.getFieldRowNum() == null) {
            this.b.setFieldRowNum(Integer.parseInt(com.hanwei.core.codegenerator.util.a.a.p));
        }

        var1.put("tableVo", this.b);

        try {
            if (this.e == null || this.e.size() == 0) {
                this.e = DbReadTableUtil.a(this.b.getTableName());
            }

            var1.put("columns", this.e);
            if (this.f == null || this.f.size() == 0) {
                this.f = DbReadTableUtil.readOriginalTableColumn(this.b.getTableName());
            }

            var1.put("originalColumns", this.f);
            Iterator var2 = this.f.iterator();

            while(var2.hasNext()) {
                ColumnVo var3 = (ColumnVo)var2.next();
                if (var3.getFieldName().toLowerCase().equals(com.hanwei.core.codegenerator.util.a.a.l.toLowerCase())) {
                    var1.put("primaryKeyPolicy", var3.getFieldType());
                }
            }
        } catch (Exception var4) {
            throw var4;
        }

        long var5 = NonceUtils.c() + NonceUtils.g();
        var1.put("serialVersionUID", String.valueOf(var5));
        a.info("load template data: " + var1.toString());
        return var1;
    }

    @Override
    public List<String> generateCodeFile(String stylePath) throws Exception {
        a.debug("----hanwei---Code----Generation----[单表模型:" + this.b.getTableName() + "]------- 生成中。。。");
        String var2 = com.hanwei.core.codegenerator.util.a.a.f;
        Map var3 = this.a();
        String var4 = com.hanwei.core.codegenerator.util.a.a.j;
        if (a(var4, "/").equals("code-template/code-template")) {
            var4 = "/" + a(var4, "/") + "/one";
            com.hanwei.core.codegenerator.util.a.a.b(var4);
        }

        com.hanwei.core.codegenerator.util.generate.a.a var5 = new com.hanwei.core.codegenerator.util.generate.a.a(var4);
        var5.b(stylePath);
        if (this.b != null && this.b.getExtendParams() != null) {
            var5.a(com.hanwei.core.codegenerator.util.generate.util.g.a(this.b.getExtendParams().get("vueStyle"), "vue"));
        }

        this.a(var5, var2, var3);
        a.info(" ----- hanwei ---- generate  code  success =======> 表名：" + this.b.getTableName() + " ");
        return this.d;
    }

    @Override
    public List<String> generateCodeFile(String projectPath, String templatePath, String stylePath) throws Exception {
        if (projectPath != null && !"".equals(projectPath)) {
            com.hanwei.core.codegenerator.util.a.a.a(projectPath);
        }

        if (templatePath != null && !"".equals(templatePath)) {
            com.hanwei.core.codegenerator.util.a.a.b(templatePath);
        }

        this.generateCodeFile(stylePath);
        return this.d;
    }

    public static void main(String[] args) {
        TableVo var1 = new TableVo();
        var1.setTableName("rtu");
        var1.setPrimaryKeyPolicy("uuid");
        var1.setEntityPackage("testaa");
        var1.setEntityName("hanweiDemo");
        var1.setFtlDescription("hanwei 测试demo");

        try {
            (new CodeGenerateOne(var1)).generateCodeFile((String)null);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}
