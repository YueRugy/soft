package com.yue.service.impl;

import com.yue.entity.User;
import com.yue.enums.ErrorMessage;
import com.yue.exception.SoftException;
import com.yue.mapper.UserMapper;
import com.yue.service.UserService;
import com.yue.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yue on 2017/9/9
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User register(User user, Integer userType) {
        if (StringUtils.isBlank(user.getPhone())) {
            throw new SoftException(ErrorMessage.phone_number_is_empty);
        }

        if (!ValidateUtil.isMobile(user.getPhone())) {
            throw new SoftException(ErrorMessage.phone_number_is_illegal);
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new SoftException(ErrorMessage.password_is_empty);
        }

        User existsUser=userMapper.selectByPhone(user.getPhone());
        return null;
    }
}
