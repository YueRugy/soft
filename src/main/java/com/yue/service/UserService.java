package com.yue.service;

import com.yue.entity.User;

/**
 * Created by yue on 2017/9/9
 */
public interface UserService {
    User register(User user, Integer userType);
}
