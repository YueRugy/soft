package com.yue.util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
    public static void setHttpServletResponse(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
    }


    public static void setCookie(String cookieName, String cookieValue, String path, long maxAge, HttpServletResponse response) {
        response.addHeader("Set-Cookie", cookieName + "=" + cookieValue + "; Path = " + path + "; HttpOnly; Max-Age = " + maxAge); //设置cookie失效时间

//		response.addHeader("Set-Cookie", "timeout=30; Path=/test; HttpOnly;Max-Age=375000");
    }
}
