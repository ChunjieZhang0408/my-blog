package com.example.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {
    private static Logger logger = LoggerFactory.getLogger(AESUtils.class);
    /**
     * 密钥算法 AES
     */

    private static final String KEY_ALGORITHM_AES = "AES";

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 根据key字符串生成AES密钥
     *
     * @param password key字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM_AES);
        keyGenerator.init(256, new SecureRandom(password.getBytes("utf-8")));
        //产生原始对称密钥
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM_AES);
    }

    /**
     * @param text 待加密字符串
     * @param key  密钥
     * @return BASE64（加密后字符串）
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encrypt(String text, String key)  {
        logger.info("加密前的数据：{}", text);
        byte[] rows = key.getBytes();

        Cipher cipher = null;//"算法/模式/补码方式"
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] encrypted = new byte[0];
        try {
            encrypted = cipher.doFinal(text.getBytes("utf-8"));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        String encodeToString = new Base64().encodeToString(encrypted);
        logger.info("加密后的数据：{}", encodeToString);
        return encodeToString;
    }

    /**
     * @param encryptString 待解密字符串
     * @param sKey          密钥字符串
     * @return 解密后字符串
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String decrypt(String encryptString, String sKey) {
        logger.info("解密前的数据：{}", encryptString);
        try {
            byte[] raw = sKey.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(sKey));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //先用base64解密
        byte[] encrypted = new Base64().decode(encryptString);
        byte[] original = new byte[0];
        try {
            original = cipher.doFinal(encrypted);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        String originalString = null;
        try {
            originalString = new String(original, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("解密后的数据：{}", originalString);
        return originalString;

    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
         String KEY = "video_platform";
        String encrypt = encrypt("812560654",KEY);
        decrypt(encrypt,KEY);
    }
}
