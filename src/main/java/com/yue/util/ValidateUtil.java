package com.yue.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    /**
     * 手机号校验
     */
    public static boolean isMobile(String phoneNumber) {
        String reg = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\\d{8})$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 身份证号校验
     */
    public static boolean isIDCard(String idNumber) {
        String reg = "^(\\d{15,15}|\\d{17,17}(x|X|\\d))$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(idNumber);
        return m.matches();
    }


}
