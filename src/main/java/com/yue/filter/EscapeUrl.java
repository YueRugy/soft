package com.yue.filter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yue on 2017/9/14
 */
public class EscapeUrl {

    public static final Set<String> escapeUrls = new HashSet<String>();

    static {
        escapeUrls.add("/phone/user/register/");   // 注册用户
        escapeUrls.add("/phone/user/login/");   // 注册用户
        escapeUrls.add("phone/user/weLogin/");   // 注册用户

    }
}
