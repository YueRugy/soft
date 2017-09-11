package com.yue.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CipherUtil {

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");

            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());

            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");

            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());

            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encode(String tokenStr, String key) {
        if (tokenStr == null) {
            return null;
        }

        byte[] buf = encrypt(tokenStr, key);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String decode(String tokenStr, String key) {
        if (tokenStr == null) {
            return null;
        }

        if (tokenStr.length() < 1)
            return null;

        byte[] result = new byte[tokenStr.length() / 2];
        for (int i = 0; i < tokenStr.length() / 2; i++) {
            int high = Integer.parseInt(tokenStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(tokenStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return new String(decrypt(result, key));
    }


    public static String changeMD5(String str) {
        String changeStr = MD5(str);
        if (changeStr == null)
            return null;

        String head = changeStr.substring(0, 5);
        String middle = changeStr.substring(5, 10);
        String tail = changeStr.substring(10, changeStr.length());

        return tail + head + middle;
    }


    public static String adminChangeMD5(String str) {
        String changeStr = MD5(str);
        if (changeStr == null)
            return null;

        String head = changeStr.substring(0, 4);
        String middle = changeStr.substring(4, 12);
        String tail = changeStr.substring(12, changeStr.length());

        return tail + head + middle;
    }


    public static String urlChangeMD5(String str) {
        String changeStr = MD5(str);
        if (changeStr == null)
            return null;

        String head = changeStr.substring(0, 6);
        String middle = changeStr.substring(6, 12);
        String tail = changeStr.substring(12, changeStr.length());

        return tail + head + middle;
    }

    public static void main(String[] args) {
        String str = encode("123456", "hello");
        System.out.println(str);
        String str2 = decode(str, "hello");
        System.out.println(str2);
        System.out.println(changeMD5("123456"));
        System.out.println(adminChangeMD5("Snh*VZ1!&FI2f*1"));

    }

}
