package com.yue.service;

import com.yue.entity.User;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yue on 2017/9/9
 * @see Interceptor
 */
public interface UserService {
    User register(User user, Integer userType);

    User login(User user, HttpServletResponse response);

    Object weLogin(String openId, HttpServletResponse response);

    Object getAll(Pageable pageable);

    Object getTest();

    Object getUserRecommendContacts();
}
