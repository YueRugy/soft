package com.yue.core;

import com.yue.constant.SoftConstant;
import com.yue.constant.TimeConstant;
import com.yue.entity.User;
import com.yue.enums.ErrorMessage;
import com.yue.exception.SoftException;
import com.yue.util.CipherUtil;
import com.yue.util.JsonUtil;
import com.yue.util.ResponseUtil;

import javax.servlet.http.HttpServletResponse;

/**
 * @author beter
 */
public class TokenOperation {

    private static int getHashCode(User user) {
        String str = user.getPhone() + "|" + user.getPassword();
        return str.hashCode();
    }


    // 解码
    public static Token toPhoneToken(String strToken, HttpServletResponse response) {
        String authToken = "";
        try {
            authToken = CipherUtil.decode(strToken, "wL14Xpd9");
        } catch (Exception e) {
            ResponseUtil.setCookie(SoftConstant.COOKIE_PHONE_NAME, null, SoftConstant.PATH, TimeConstant.TIME_DELETE_COOKIE_MILLISECOND, response);
            throw new SoftException(ErrorMessage.need_login);
        }

        Token token = JsonUtil.jsonToObject(authToken, Token.class);
        if (token == null) {
            return null;
        }
        token.setStrToken(strToken);
        return token;
    }

    // 加密
    public static String genPhoneUser(User user) {
        Token token = new Token();
        token.setId(user.getId());
        token.setThs(getHashCode(user));
        String str = JsonUtil.objectToJson(token);
        return CipherUtil.encode(str, "wL14Xpd9");
    }


}
