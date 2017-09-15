package com.yue.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yue on 2017/9/10
 */

public final class AppContent {
    private static final ThreadLocal<ThreadContextHolder> context = new ThreadLocal<>();

    public static void destroy() {
        context.set(null);
    }

    public static void init(HttpServletRequest req, HttpServletResponse res) {
        context.set(new ThreadContextHolder(req, res));
    }

    public static int getUserId() {
        try {
            return context.get().getToken().getId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getStrToken() {
        return context.get().getToken().getStrToken();
    }

    public static Token getToken() {
        return context.get().getToken();
    }

    public static void setToken(Token token) {
        System.out.println(context);
        context.get().setToken(token);
    }

    public static ThreadContextHolder getContext() {
        return context.get();
    }
}

