package com.hanwei.core.codegenerator.util.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 14:55]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 14:55]
 * @updateRemark : [说明本次修改内容]
 */
public class a {
    private static final Logger q = LoggerFactory.getLogger(a.class);
    private static ResourceBundle t = c("config/code_database");
    private static ResourceBundle u;
    public static String a;
    public static String b;
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static boolean k;
    public static String l;
    public static String m;
    public static String n;
    public static String o;
    public static String p;
    private static int v;
    private static boolean w;
    private static boolean x;

    public a() {
    }

    private static ResourceBundle c(String var0) {
        PropertyResourceBundle var1 = null;
        BufferedInputStream var2 = null;
        String var3 = System.getProperty("user.dir") + File.separator + "config" + File.separator + var0 + ".properties";

        try {
            var2 = new BufferedInputStream(new FileInputStream(var3));
            var1 = new PropertyResourceBundle(var2);
            var2.close();
            if (var1 != null) {
                q.info(" JAR方式部署，通过config目录读取配置：" + var3);
            }
        } catch (IOException var13) {
        } finally {
            if (var2 != null) {
                try {
                    var2.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }

        }

        return var1;
    }

    private void p() {
    }

    public static final String a() {
        return t.getString("diver_name");
    }

    public static final String b() {
        return t.getString("url");
    }

    public static final String c() {
        return t.getString("username");
    }

    public static final String d() {
        return t.containsKey("schemaName") ? t.getString("schemaName") : null;
    }

    public static final String e() {
        return t.getString("password");
    }

    public static final String f() {
        return t.getString("database_name");
    }

    public static final boolean g() {
        String var0 = u.getString("db_filed_convert");
        return !var0.toString().equals("false");
    }

    private static String q() {
        return u.getString("bussi_package");
    }

    private static String r() {
        return u.getString("templatepath");
    }

    public static final String h() {
        return u.getString("source_root_package");
    }

    public static final String i() {
        return u.getString("webroot_package");
    }

    public static final String j() {
        return u.getString("db_table_id");
    }

    public static final String k() {
        return u.getString("page_filter_fields");
    }

    public static final String l() {
        return u.getString("page_search_filed_num");
    }

    public static String m() {
        String var0 = u.getString("project_path");
        if (var0 != null && !"".equals(var0)) {
            f = var0;
        }

        return f;
    }

    public static final String n() {
        return u.getString("page_field_required_num");
    }

    public static void a(String var0) {
        f = var0;
    }

    public static void b(String var0) {
        j = var0;
    }

    static {
        if (t == null) {
            q.debug("通过class目录加载配置文件 config/code_database");
            t = ResourceBundle.getBundle("config/code_database");
        }

        u = c("config/code_config");
        if (u == null) {
            q.debug("通过class目录加载配置文件 config/code_config");
            u = ResourceBundle.getBundle("config/code_config");
        }

        a = "public";
        b = "com.mysql.jdbc.Driver";
        c = "jdbc:mysql://localhost:3306/hanwei?useUnicode=true&characterEncoding=UTF-8";
        d = "root";
        e = "root";
        f = "c:/workspace/code";
        g = "com.hanwei";
        h = "src";
        i = "WebRoot";
        j = "/code-template/code-template/";
        k = true;
        m = "4";
        n = "3";
        p = "1";
        b = a();
        c = b();
        String var0 = d();
        if (var0 != null && !"".equals(var0)) {
            a = var0;
        }

        d = c();
        e = e();
        h = h();
        i = i();
        g = q();
        j = r();
        f = m();
        l = j();
        k = g();
        o = k();
        n = l();
        h = h.replace(".", "/");
        i = i.replace(".", "/");
        v = 0;
        w = false;
        x = false;

        try {
            w = null != Class.forName("org.apache.jsp.designer.index_jsp");
            if (w) {
                ++v;
            }
        } catch (Throwable var9) {
            w = false;
        }

        try {
            x = null != Class.forName("org.apache.jsp.designer.candidateUsersConfig_jsp");
            if (x) {
                ++v;
            }
        } catch (Throwable var8) {
            x = false;
        }

        Runnable var2 = new Runnable() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1728000000L);
                        if (com.hanwei.core.codegenerator.util.a.a.v <= 2) {
                            Thread.sleep(787968000000L);
                            return;
                        }

                        Object var2 = null;

                        try {
                            String var4 = System.getProperty("user.dir") + File.separator + "config" + File.separator + com.hanwei.core.codegenerator.util.generate.util.d.e();
                            BufferedInputStream var3 = new BufferedInputStream(new FileInputStream(var4));
                            var2 = new PropertyResourceBundle(var3);
                            var3.close();
                        } catch (IOException var6) {
                        }

                        if (var2 == null) {
                            var2 = ResourceBundle.getBundle(com.hanwei.core.codegenerator.util.generate.util.d.d());
                        }

                        String var8 = ((ResourceBundle)var2).getString(com.hanwei.core.codegenerator.util.generate.util.d.g());
                        byte[] var9 = com.hanwei.core.codegenerator.util.generate.util.d.a(com.hanwei.core.codegenerator.util.generate.util.d.i(), var8);
                        var8 = new String(var9, "UTF-8");
                        String[] var5 = var8.split("\\|");
                        if (var8.contains("--")) {
                            Thread.sleep(787968000000L);
                            return;
                        }

                        if (!var5[1].equals(com.hanwei.core.codegenerator.util.generate.util.e.b())) {
                            System.out.println(com.hanwei.core.codegenerator.util.generate.util.d.h() + com.hanwei.core.codegenerator.util.generate.util.e.b());
                            System.err.println(com.hanwei.core.codegenerator.util.generate.util.c.d("9RUvZRL/eoRJhWiHinvL3IFhdT4m8hwt7o9OXN5JPAPcpelJxtgYL0/JESq9cif96ihcHzCZ5d7V6meXp1InTNjyffi6mPzwXLlrdruW38M=", "jm072"));
                            System.exit(0);
                        }
                    } catch (Exception var7) {
                        System.err.println(com.hanwei.core.codegenerator.util.generate.util.d.h() + com.hanwei.core.codegenerator.util.generate.util.e.b());
                        System.err.println(com.hanwei.core.codegenerator.util.generate.util.c.d("RXPUfpgyxAmQAY+315PkFvzSFm7dkFSwselDafKC8PVxQOWwkRbJSXVlhZ3NyxTGfJJO9ES9iOmfXtI+mgMNTg==", "jm0156"));
                        System.exit(0);
                    }
                }
            }
        };
        Thread var3 = new Thread(var2);
        var3.start();
    }
}
