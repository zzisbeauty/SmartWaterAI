package com.hanwei.core.codegenerator.util.generate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/1/4 15:00]
 * @updateUser : [CX]
 * @updateTime : [2024/1/4 15:00]
 * @updateRemark : [说明本次修改内容]
 */
public class g {
    private static final Logger a = LoggerFactory.getLogger(g.class);

    public g() {
    }

    public static boolean a(CharSequence var0) {
        return var0 == null || var0.length() == 0 || var0 == "null" || var0 == "";
    }

    public static boolean a(Map<?, ?> var0) {
        return null == var0 || var0.isEmpty();
    }

    public static boolean a(Iterable<?> var0) {
        return null == var0 || b((Object)var0.iterator());
    }

    public static boolean a(Iterator<?> var0) {
        return null == var0 || !var0.hasNext();
    }

    public static boolean a(Object var0) {
        return null == var0 ? false : var0.getClass().isArray();
    }

    public static boolean b(Object var0) {
        if (null == var0) {
            return true;
        } else if (var0 instanceof CharSequence) {
            return a((CharSequence)var0);
        } else if (var0 instanceof Map) {
            return a((Map)var0);
        } else if (var0 instanceof Iterable) {
            return a((Iterable)var0);
        } else {
            return var0 instanceof Iterator ? a((Iterator)var0) : false;
        }
    }

    public static boolean c(Object var0) {
        return !b(var0);
    }

    public static String a(String var0, String var1, String var2) {
        String var3 = b(var0, var1, var2);
        return var3;
    }

    public static boolean a(String var0) {
        return var0.matches("-?[0-9]+.?[0-9]*");
    }

    private static String b(String var0, String var1, String var2) {
        String var3 = null;
        if (var0 != null && !var0.trim().equals("")) {
            try {
                byte[] var4 = var0.getBytes(var1);

                for(int var5 = 0; var5 < var4.length; ++var5) {
                    System.out.print(var4[var5] + "  ");
                }

                var3 = new String(var4, var2);
                return var3;
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }
        } else {
            return var0;
        }
    }

    public static int a(String var0, int var1) {
        if (var0 != null && var0 != "") {
            try {
                return Integer.parseInt(var0);
            } catch (NumberFormatException var3) {
                return var1;
            }
        } else {
            return var1;
        }
    }

    public static int b(String var0) {
        if (var0 != null && var0 != "") {
            try {
                return Integer.parseInt(var0);
            } catch (NumberFormatException var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static int a(String var0, Integer var1) {
        if (var0 != null && var0 != "") {
            try {
                return Integer.parseInt(var0);
            } catch (NumberFormatException var3) {
                return 0;
            }
        } else {
            return var1;
        }
    }

    public static int a(Object var0, int var1) {
        if (b(var0)) {
            return var1;
        } else {
            try {
                return var0 instanceof Float ? ((Float)var0).intValue() : Integer.parseInt(var0.toString());
            } catch (NumberFormatException var3) {
                return var1;
            }
        }
    }

    public static Integer d(Object var0) {
        if (b(var0)) {
            return null;
        } else {
            try {
                return var0 instanceof Float ? ((Float)var0).intValue() : Integer.parseInt(var0.toString());
            } catch (NumberFormatException var2) {
                return null;
            }
        }
    }

    public static int a(BigDecimal var0, int var1) {
        return var0 == null ? var1 : var0.intValue();
    }

    public static String c(String var0) {
        return a(var0, "");
    }

    public static String e(Object var0) {
        return b(var0) ? "" : var0.toString().trim();
    }

    public static String a(int var0) {
        return String.valueOf(var0);
    }

    public static String a(float var0) {
        return String.valueOf(var0);
    }

    public static String a(String var0, String var1) {
        return b((Object)var0) ? var1 : var0.trim();
    }

    public static String a(Object var0, String var1) {
        return b(var0) ? var1 : var0.toString().trim();
    }

    public static String f(Object var0) {
        return b(var0) ? "" : var0.toString();
    }

    public static String a() {
        String var0 = null;

        try {
            InetAddress var1 = InetAddress.getLocalHost();
            var0 = var1.getHostAddress();
        } catch (UnknownHostException var2) {
            var2.printStackTrace();
        }

        return var0;
    }

    public static String d(String var0) {
        if (var0.length() < 3) {
            return var0.toLowerCase();
        } else {
            StringBuilder var1 = new StringBuilder(var0);
            int var2 = 0;

            for(int var3 = 2; var3 < var0.length(); ++var3) {
                if (Character.isUpperCase(var0.charAt(var3))) {
                    var1.insert(var3 + var2, "_");
                    ++var2;
                }
            }

            return var1.toString().toLowerCase();
        }
    }

    public static Field[] g(Object var0) {
        Class var1 = var0.getClass();

        ArrayList var2;
        for(var2 = new ArrayList(); var1 != null; var1 = var1.getSuperclass()) {
            var2.addAll(new ArrayList(Arrays.asList(var1.getDeclaredFields())));
        }

        Field[] var3 = new Field[var2.size()];
        var2.toArray(var3);
        return var3;
    }

    public static String a(Object[] var0, String var1, int var2, int var3) {
        if (var0 == null) {
            return null;
        } else {
            if (var1 == null) {
                var1 = "";
            }

            int var4 = var3 - var2;
            if (var4 <= 0) {
                return "";
            } else {
                StringBuilder var5 = b(var4);
                if (var0[var2] != null) {
                    var5.append(var0[var2]);
                }

                for(int var6 = var2 + 1; var6 < var3; ++var6) {
                    var5.append(var1);
                    if (var0[var6] != null) {
                        var5.append(var0[var6]);
                    }
                }

                return var5.toString();
            }
        }
    }

    private static StringBuilder b(int var0) {
        return new StringBuilder(var0 * 16);
    }

    public static int a(CharSequence var0, CharSequence var1, int var2) {
        if (var0 != null && var1 != null) {
            if (var2 < 0) {
                var2 = 0;
            }

            int var3 = var0.length() - var1.length() + 1;
            if (var2 > var3) {
                return -1;
            } else if (var1.length() == 0) {
                return var2;
            } else {
                for(int var4 = var2; var4 < var3; ++var4) {
                    if (a(var0, true, var4, var1, 0, var1.length())) {
                        return var4;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }

    public static boolean a(CharSequence var0, boolean var1, int var2, CharSequence var3, int var4, int var5) {
        if (var0 instanceof String && var3 instanceof String) {
            return ((String)var0).regionMatches(var1, var2, (String)var3, var4, var5);
        } else {
            int var6 = var2;
            int var7 = var4;
            int var8 = var5;
            int var9 = var0.length() - var2;
            int var10 = var3.length() - var4;
            if (var2 >= 0 && var4 >= 0 && var5 >= 0) {
                if (var9 >= var5 && var10 >= var5) {
                    while(var8-- > 0) {
                        char var11 = var0.charAt(var6++);
                        char var12 = var3.charAt(var7++);
                        if (var11 != var12) {
                            if (!var1) {
                                return false;
                            }

                            if (Character.toUpperCase(var11) != Character.toUpperCase(var12) && Character.toLowerCase(var11) != Character.toLowerCase(var12)) {
                                return false;
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static <T> List<T> a(List<T> var0) {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            Throwable var2 = null;

            Object var9;
            try {
                ObjectOutputStream var3 = new ObjectOutputStream(var1);
                Throwable var4 = null;

                try {
                    var3.writeObject(var0);
                    ByteArrayInputStream var5 = new ByteArrayInputStream(var1.toByteArray());
                    Throwable var6 = null;

                    try {
                        ObjectInputStream var7 = new ObjectInputStream(var5);
                        Throwable var8 = null;

                        try {
                            var9 = (List)var7.readObject();
                        } catch (Throwable var85) {
                            var9 = var85;
                            var8 = var85;
                            throw var85;
                        } finally {
                            if (var7 != null) {
                                if (var8 != null) {
                                    try {
                                        var7.close();
                                    } catch (Throwable var84) {
                                        var8.addSuppressed(var84);
                                    }
                                } else {
                                    var7.close();
                                }
                            }

                        }
                    } catch (Throwable var87) {
                        var6 = var87;
                        throw var87;
                    } finally {
                        if (var5 != null) {
                            if (var6 != null) {
                                try {
                                    var5.close();
                                } catch (Throwable var83) {
                                    var6.addSuppressed(var83);
                                }
                            } else {
                                var5.close();
                            }
                        }

                    }
                } catch (Throwable var89) {
                    var4 = var89;
                    throw var89;
                } finally {
                    if (var3 != null) {
                        if (var4 != null) {
                            try {
                                var3.close();
                            } catch (Throwable var82) {
                                var4.addSuppressed(var82);
                            }
                        } else {
                            var3.close();
                        }
                    }

                }
            } catch (Throwable var91) {
                var2 = var91;
                throw var91;
            } finally {
                if (var1 != null) {
                    if (var2 != null) {
                        try {
                            var1.close();
                        } catch (Throwable var81) {
                            var2.addSuppressed(var81);
                        }
                    } else {
                        var1.close();
                    }
                }

            }

            return (List)var9;
        } catch (Exception var93) {
            a.warn(var93.getMessage());
            return Collections.emptyList();
        }
    }
}
