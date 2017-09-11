package com.yue.core;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by yue on 2017/9/10
 */
@Getter
@Setter
public class ThreadContextHolder {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Token token;

    public ThreadContextHolder(HttpServletRequest req, HttpServletResponse res) {
        this.request = req;
        this.response = res;
    }


}
