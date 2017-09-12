package com.yue.service.impl;

import com.yue.entity.User;
import com.yue.entity.UserLastLogin;
import com.yue.mapper.UserLastLoginMapper;
import com.yue.service.UserLastLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yue on 2017/9/12
 */
@Service
@Transactional
public class UserLastLoginServiceImpl implements UserLastLoginService {
    private final UserLastLoginMapper userLastLoginMapper;

    @Autowired
    public UserLastLoginServiceImpl(UserLastLoginMapper userLastLoginMapper) {
        this.userLastLoginMapper = userLastLoginMapper;
    }

    @Override
    public void log(User user) {
        UserLastLogin userLastLogin = userLastLoginMapper.selectByUserId(user.getId());
        //第一次登录
        if (userLastLogin == null) {
            userLastLogin = new UserLastLogin();
            userLastLogin.setUserId(user.getId());
            userLastLogin.setType(user.getType());

            userLastLoginMapper.insert(userLastLogin);
        } else {
            userLastLoginMapper.update(userLastLogin);
        }
    }
}
