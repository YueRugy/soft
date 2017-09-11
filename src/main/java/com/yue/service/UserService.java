package com.yue.service;

import com.yue.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yue on 2017/9/9
 */
public interface UserService {
    User register(User user, Integer userType);

    User login(User user, HttpServletResponse response);
}
