package com.hanwei.core.codegenerator.util.generate.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 15:00]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 15:00]
 * @updateRemark : [说明本次修改内容]
 */
public class c {
    public c() {
    }

    public static String a(byte[] var0, int var1) {
        return (new BigInteger(1, var0)).toString(var1);
    }

    public static String a(byte[] var0) {
        return Base64.getEncoder().encodeToString(var0);
    }

    public static byte[] a(String var0) throws Exception {
        return g.b(var0)==0 ? null : Base64.getDecoder().decode(var0);
    }

    public static byte[] b(byte[] var0) throws Exception {
        MessageDigest var1 = MessageDigest.getInstance("MD5");
        var1.update(var0);
        return var1.digest();
    }

    public static byte[] b(String var0) throws Exception {
        return g.b(var0)==0 ? null : b(var0.getBytes());
    }

    public static String c(String var0) throws Exception {
        return g.b(var0)==0 ? null : a(b(var0));
    }

    public static byte[] a(String var0, String var1) throws Exception {
        KeyGenerator var2 = KeyGenerator.getInstance("AES");
        SecureRandom var3 = SecureRandom.getInstance("SHA1PRNG");
        var3.setSeed(var1.getBytes());
        var2.init(128, var3);
        Cipher var4 = Cipher.getInstance("AES");
        var4.init(1, new SecretKeySpec(var2.generateKey().getEncoded(), "AES"));
        return var4.doFinal(var0.getBytes("utf-8"));
    }

    public static String b(String var0, String var1) throws Exception {
        return a(a(var0, var1));
    }

    public static String a(byte[] var0, String var1) throws Exception {
        KeyGenerator var2 = KeyGenerator.getInstance("AES");
        SecureRandom var3 = SecureRandom.getInstance("SHA1PRNG");
        var3.setSeed(var1.getBytes());
        var2.init(128, var3);
        Cipher var4 = Cipher.getInstance("AES");
        var4.init(2, new SecretKeySpec(var2.generateKey().getEncoded(), "AES"));
        byte[] var5 = var4.doFinal(var0);
        return new String(var5);
    }

    public static String c(String var0, String var1) throws Exception {
        return g.b(var0)==0 ? null : a(a(var0), var1);
    }

    public static String d(String var0, String var1) {
        try {
            return g.b(var0)==0 ? null : a(a(var0), var1);
        } catch (Exception var3) {
            return "CC ERROR";
        }
    }
}
