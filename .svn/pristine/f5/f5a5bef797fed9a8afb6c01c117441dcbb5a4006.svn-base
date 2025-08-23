package com.hanwei.core.codegenerator.util.generate.util;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 14:59]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 14:59]
 * @updateRemark : [说明本次修改内容]
 */
public class d {
    private static final String a = "YFerfHcGuyXedCm3e8DyBWvoeFCyk1h9Z6wWGOdoZTYQyiFO6H1cYJSHMDE0NBwUpew7GoqwH72m3AqhVm5cbrIbqAlDa2FZh7Qr5t1OlcxbaKMkn+x9Ki4KRzqHVjapXIwaUXQojqZvPN4PoExx3L/26na7cjydBX0iOIAO8mw=";
    private static final String b = "Kq7UG64QROq+0aypnXvWrmJZ24SM3m3hWAKb9cbewrDmAOtkzKEw2eS9kjn+N+5OGsSxOvnN70yuIBBDBReZxUrSUzw0B2DSAjqLK3V/uTqTU1T1tfWjYeNtPcn1+RjgSmdC9YUv0EK8fZOhNJfWUf6j6N1fVXIjkjD/fm/Ww30=";
    private static final String c = "KRwcxkNJy6SohZ08dA47oLbMFqh8AhGhNFyyIBcIqjrqGU3ziTfS8m9mRq9jQEOrFVFQlDGAU/XMxK630FhOk6TgbN93fNH/KWQCl/Ci8WXWmfS3yb/RhJMfZ8g2ODi/vRZbbsWv6VgVn+sLewfN2uJGvHPHRqmdJQKg0eZ0/Gg=";
    private static final String d = "FRX99fEhUaduyArgg7vlJUjzPogerz+FQ8OqSdf5TP3+zQFZIdUPjaK9NmyvGGgTylo4VvM/zjyYZf/635vGcfLs89h49axuyKzlmQ6dIDBXQ03ixlNxVytJEG8jJZzz4OgJzJXyxVpuBHf37uVxNUHyzjfKHHFRzVfGFTw557E=";
    private static final String e = "i9/uJTvvNt5YYj7UQUc2KIYaYsIRTQFgLxIegadXuPfqzOi3HeQjxpGX7YA89DF+B3+VLLHr5mR6Ao9itRbokr0/AwsP9slQKcrrMUnOIMb62GR6SVVRCayyHHRP/gj9CKqKmal5H2e8LEqLj9BtYCNjW07lLuU/3fb1Cnws6Vc=";
    private static final String f = "zm8KOEPvKUF2W3pMbGm32L5xBhqkTFDdAzz0TyN/S1fp4a9Qhb9m/IGVn0tOakqqBFz6G6nT4/uNu78PVgfTJTNdAUvvAfNSjRrEm6SHOHiVUUz8MCa8AGZ319rvytJqEaydnGcPuJuJCJOCiRqGlpYCeul8GUjDnHktYi2WcQdzKU2B2lKjKlRHArIB9Z2HZnqqkhuWSTlAIBPCD7xGYcWUBeHeO3ucEJ4/2TruAN06K+Gk5RxGMkZY24SpIop2Y7483IcyNXJziI251VNGLRdVxakKeg54WwR8S8H2kGc=";

    public d() {
    }

    public static byte[] a(byte[] var0, byte[] var1) {
        try {
            PKCS8EncodedKeySpec var2 = new PKCS8EncodedKeySpec(var0);
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            PrivateKey var4 = var3.generatePrivate(var2);
            Signature var5 = Signature.getInstance("SHA1withRSA");
            var5.initSign(var4);
            var5.update(var1);
            return var5.sign();
        } catch (Exception var6) {
            return null;
        }
    }

    public static boolean a(byte[] var0, byte[] var1, byte[] var2) {
        try {
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            Signature var4 = Signature.getInstance("SHA1withRSA");
            X509EncodedKeySpec var5 = new X509EncodedKeySpec(var0);
            PublicKey var6 = var3.generatePublic(var5);
            var4.initVerify(var6);
            var4.update(var1);
            return var4.verify(var2);
        } catch (Exception var7) {
            return false;
        }
    }

    public static Object[] a() {
        KeyPair var0 = c();
        if (var0 == null) {
            return null;
        } else {
            Object[] var1 = new Object[2];
            if (var0 != null) {
                PrivateKey var2 = var0.getPrivate();
                byte[] var3 = var2.getEncoded();
                PublicKey var4 = var0.getPublic();
                byte[] var5 = var4.getEncoded();
                var1[0] = var3;
                var1[1] = var5;
                return var1;
            } else {
                return null;
            }
        }
    }

    public static String[] b() {
        KeyPair var0 = c();
        if (var0 == null) {
            return null;
        } else {
            String[] var1 = new String[2];
            if (var0 != null) {
                PrivateKey var2 = var0.getPrivate();
                byte[] var3 = var2.getEncoded();
                String var4 = Base64.getEncoder().encodeToString(var3);
                PublicKey var5 = var0.getPublic();
                byte[] var6 = var5.getEncoded();
                String var7 = Base64.getEncoder().encodeToString(var6);
                var1[0] = var4;
                var1[1] = var7;
                return var1;
            } else {
                return null;
            }
        }
    }

    public static KeyPair c() {
        long var1 = System.currentTimeMillis();

        try {
            KeyPairGenerator var3 = KeyPairGenerator.getInstance("RSA");
            SecureRandom var4 = SecureRandom.getInstance("SHA1PRNG", "SUN");
            var4.setSeed(var1);
            var3.initialize(1024, var4);
            KeyPair var0 = var3.generateKeyPair();
            return var0;
        } catch (Exception var5) {
            return null;
        }
    }

    public static byte[] b(byte[] var0, byte[] var1) {
        try {
            KeyFactory var2 = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec var3 = new X509EncodedKeySpec(var0);
            PublicKey var4 = var2.generatePublic(var3);
            Cipher var5 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            var5.init(1, var4);
            return var5.doFinal(var1);
        } catch (Exception var6) {
            return null;
        }
    }

    public static byte[] c(byte[] var0, byte[] var1) {
        try {
            PKCS8EncodedKeySpec var2 = new PKCS8EncodedKeySpec(var0);
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            PrivateKey var4 = var3.generatePrivate(var2);
            Cipher var5 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            var5.init(2, var4);
            return var5.doFinal(var1);
        } catch (Exception var6) {
            return null;
        }
    }

    public static byte[] d(byte[] var0, byte[] var1) {
        try {
            PKCS8EncodedKeySpec var2 = new PKCS8EncodedKeySpec(var0);
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            PrivateKey var4 = var3.generatePrivate(var2);
            Cipher var5 = Cipher.getInstance(var3.getAlgorithm());
            var5.init(1, var4);
            return var5.doFinal(var1);
        } catch (Exception var6) {
            return null;
        }
    }

    public static byte[] a(String var0, byte[] var1) {
        try {
            byte[] var2 = Base64.getDecoder().decode(var0);
            PKCS8EncodedKeySpec var3 = new PKCS8EncodedKeySpec(var2);
            KeyFactory var4 = KeyFactory.getInstance("RSA");
            PrivateKey var5 = var4.generatePrivate(var3);
            Cipher var6 = Cipher.getInstance(var4.getAlgorithm());
            var6.init(1, var5);
            return var6.doFinal(var1);
        } catch (Exception var7) {
            return null;
        }
    }

    public static byte[] e(byte[] var0, byte[] var1) {
        try {
            KeyFactory var2 = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec var3 = new X509EncodedKeySpec(var0);
            PublicKey var4 = var2.generatePublic(var3);
            Cipher var5 = Cipher.getInstance(var2.getAlgorithm());
            var5.init(2, var4);
            return var5.doFinal(var1);
        } catch (Exception var6) {
            return null;
        }
    }

    public static byte[] b(String var0, byte[] var1) {
        try {
            byte[] var2 = Base64.getDecoder().decode(var0);
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec var4 = new X509EncodedKeySpec(var2);
            PublicKey var5 = var3.generatePublic(var4);
            Cipher var6 = Cipher.getInstance(var3.getAlgorithm());
            var6.init(2, var5);
            return var6.doFinal(var1);
        } catch (Exception var7) {
            return null;
        }
    }

    public static byte[] a(String var0, String var1) {
        try {
            byte[] var2 = Base64.getDecoder().decode(var1);
            byte[] var3 = Base64.getDecoder().decode(var0);
            KeyFactory var4 = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec var5 = new X509EncodedKeySpec(var3);
            PublicKey var6 = var4.generatePublic(var5);
            Cipher var7 = Cipher.getInstance(var4.getAlgorithm());
            var7.init(2, var6);
            return var7.doFinal(var2);
        } catch (Exception var8) {
            return null;
        }
    }

    public static byte[] a(String var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA");
            byte[] var2 = var1.digest(var0.getBytes("UTF-8"));
            return var2;
        } catch (Exception var3) {
            return null;
        }
    }

    public static String d() {
        String var0 = "";
        byte[] var1 = a(i(), "YFerfHcGuyXedCm3e8DyBWvoeFCyk1h9Z6wWGOdoZTYQyiFO6H1cYJSHMDE0NBwUpew7GoqwH72m3AqhVm5cbrIbqAlDa2FZh7Qr5t1OlcxbaKMkn+x9Ki4KRzqHVjapXIwaUXQojqZvPN4PoExx3L/26na7cjydBX0iOIAO8mw=");

        try {
            var0 = new String(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return var0.substring(0, var0.indexOf(","));
    }

    public static String e() {
        String var0 = "";
        byte[] var1 = a(i(), "Kq7UG64QROq+0aypnXvWrmJZ24SM3m3hWAKb9cbewrDmAOtkzKEw2eS9kjn+N+5OGsSxOvnN70yuIBBDBReZxUrSUzw0B2DSAjqLK3V/uTqTU1T1tfWjYeNtPcn1+RjgSmdC9YUv0EK8fZOhNJfWUf6j6N1fVXIjkjD/fm/Ww30=");

        try {
            var0 = new String(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return var0.substring(0, var0.indexOf(","));
    }

    public static String f() {
        String var0 = "";
        byte[] var1 = a(i(), "KRwcxkNJy6SohZ08dA47oLbMFqh8AhGhNFyyIBcIqjrqGU3ziTfS8m9mRq9jQEOrFVFQlDGAU/XMxK630FhOk6TgbN93fNH/KWQCl/Ci8WXWmfS3yb/RhJMfZ8g2ODi/vRZbbsWv6VgVn+sLewfN2uJGvHPHRqmdJQKg0eZ0/Gg=");

        try {
            var0 = new String(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return var0.substring(0, var0.indexOf(","));
    }

    public static String g() {
        String var0 = "";
        byte[] var1 = a(i(), "FRX99fEhUaduyArgg7vlJUjzPogerz+FQ8OqSdf5TP3+zQFZIdUPjaK9NmyvGGgTylo4VvM/zjyYZf/635vGcfLs89h49axuyKzlmQ6dIDBXQ03ixlNxVytJEG8jJZzz4OgJzJXyxVpuBHf37uVxNUHyzjfKHHFRzVfGFTw557E=");

        try {
            var0 = new String(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return var0.substring(0, var0.indexOf(","));
    }

    public static String h() {
        String var0 = "";
        byte[] var1 = a(i(), "i9/uJTvvNt5YYj7UQUc2KIYaYsIRTQFgLxIegadXuPfqzOi3HeQjxpGX7YA89DF+B3+VLLHr5mR6Ao9itRbokr0/AwsP9slQKcrrMUnOIMb62GR6SVVRCayyHHRP/gj9CKqKmal5H2e8LEqLj9BtYCNjW07lLuU/3fb1Cnws6Vc=");

        try {
            var0 = new String(var1, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return var0.substring(0, var0.indexOf(","));
    }

    public static String i() {
        try {
            return com.hanwei.core.codegenerator.util.generate.util.c.c("zm8KOEPvKUF2W3pMbGm32L5xBhqkTFDdAzz0TyN/S1fp4a9Qhb9m/IGVn0tOakqqBFz6G6nT4/uNu78PVgfTJTNdAUvvAfNSjRrEm6SHOHiVUUz8MCa8AGZ319rvytJqEaydnGcPuJuJCJOCiRqGlpYCeul8GUjDnHktYi2WcQdzKU2B2lKjKlRHArIB9Z2HZnqqkhuWSTlAIBPCD7xGYcWUBeHeO3ucEJ4/2TruAN06K+Gk5RxGMkZY24SpIop2Y7483IcyNXJziI251VNGLRdVxakKeg54WwR8S8H2kGc=", "136");
        } catch (Exception var1) {
            return null;
        }
    }
}
