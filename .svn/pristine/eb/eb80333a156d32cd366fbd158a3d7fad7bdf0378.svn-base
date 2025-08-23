package com.hanwei.process.util;


import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author CX
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/11/1 13:56]
 * @updateUser : [CX]
 * @updateTime : [2024/11/1 13:56]
 * @updateRemark : [说明本次修改内容]
 */
public class AuthenticationUtil {

    public static void main(String[] args) {
        try {
            //集团研究院鉴权
            long timestamp = System.currentTimeMillis();
            String userName = "H05583";
            String salt= encryptToSHA256(userName + timestamp );
            String tmpToken = AuthenticationUtil.encryptToSHA256(userName + timestamp + salt);
            System.out.println("Authentication: " + tmpToken);
            System.out.println("Timestamp: " + timestamp);
            System.out.println("UserName: " + userName);

            //数字人鉴权
//            String expiredTime = ZonedDateTime.now().plusDays(7).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//            String authorization = "i-qkam5etr720pv" + "/" + AuthenticationUtil.hmacSha256("hsfa0tqw2zpvejgjx9ev", "i-qkam5etr720pv" + expiredTime) + "/" + expiredTime;
//            System.out.println("Authentication: " + authorization);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: SHA256加密 集团研究院用
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encryptToSHA256(String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (byte b : encodedhash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }
        return hexString.toString();
    }


    /**
     * @Description: HMAC-SHA256加密 数字人鉴权用
     * @param key
     * @param data
     * @return
     */
    public static String hmacSha256(String key, String data) {
        HmacUtils hmac = new HmacUtils(MessageDigestAlgorithms.SHA_256, key);
        return hmac.hmacHex(data);
    }
}
