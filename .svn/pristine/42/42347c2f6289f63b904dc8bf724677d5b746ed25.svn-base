package com.hanwei.core.codegenerator.util.generate.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 15:26]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 15:26]
 * @updateRemark : [说明本次修改内容]
 */
public class a {
    private static final Logger a = LoggerFactory.getLogger(a.class);
    private String b;
    private List<File> c = new ArrayList();
    private String d;
    private String e;

    public a(String var1) {
        a.debug("----templatePath-----------------" + var1);
        this.b = var1;
    }

    private void a(File var1) {
        this.a(var1.getParentFile().listFiles());
    }

    private void a(File... var1) {
        this.c = Arrays.asList(var1);
    }

    public String a() {
        return this.e;
    }

    public void a(String var1) {
        this.e = var1;
    }

    public String b() {
        return this.d;
    }

    public void b(String var1) {
        this.d = var1;
    }

    public List<File> c() {
        String var1 = this.getClass().getResource(this.b).getFile();

        var1 = URLDecoder.decode(var1, StandardCharsets.UTF_8);

        var1 = var1.replaceAll("%20", " ");
        a.debug("-------classpath-------" + var1);

        this.a(new File(var1));
        return this.c;
    }

    public void a(List<File> var1) {
        this.c = var1;
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append("{\"templateRootDirs\":\"");
        var1.append(this.c);
        var1.append("\",\"stylePath\":\"");
        var1.append(this.d);
        var1.append("\",\"vueStyle\":\"");
        var1.append(this.e);
        var1.append("\"} ");
        return var1.toString();
    }
}
