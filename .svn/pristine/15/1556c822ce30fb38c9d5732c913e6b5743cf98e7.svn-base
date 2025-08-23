package com.hanwei.core.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lgk
 * @date 2025/3/21 9:48
 */
@Slf4j
public class AesUtils {
    public static String encryptBase64(String content,String key){
        try{
            byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),
                    key.getBytes()).getEncoded();
            SymmetricCrypto aes = SecureUtil.aes(byteKey);
            // 加密
            return aes.encryptBase64(content);
        }catch (Exception e){
            log.error(" 加密异常:{}",e.getMessage());
        }
        return null;
    }

    public static String decryptStr(String encryptString,String key){
        try{
            byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),
                    key.getBytes()).getEncoded();
            SymmetricCrypto aes = SecureUtil.aes(byteKey);
            //解密
            return aes.decryptStr(encryptString);
        }catch (Exception e){
            log.error(" 解密异常:{}",e.getMessage());
        }
        return null;
    }
}
