package com.hanwei.core.codegenerator.util.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 15:14]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 15:14]
 * @updateRemark : [说明本次修改内容]
 */
public class a {
    private static final Logger a = LoggerFactory.getLogger(a.class);

    public a() {
    }

    public static boolean a(String var0) {
        return a(var0, "mysql") || a(var0, "mariadb") || a(var0, "sqlite") || a(var0, "clickhouse") || a(var0, "polardb");
    }

    public static boolean b(String var0) {
        return a(var0, "oracle9i") || a(var0, "oracle") || a(var0, "dm") || a(var0, "edb");
    }

    public static boolean c(String var0) {
        return a(var0, "sqlserver") || a(var0, "sqlserver2012") || a(var0, "derby");
    }

    public static boolean d(String var0) {
        return a(var0, "postgresql") || a(var0, "kingbase") || a(var0, "zenith");
    }

    private static boolean a(String var0, String var1) {
        String var2 = "jdbc:" + var1;
        return var0.toLowerCase().contains(var2);
    }
}
