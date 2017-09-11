package com.yue.service.impl;

import com.yue.constant.SoftConstant;
import com.yue.constant.TimeConstant;
import com.yue.entity.User;
import com.yue.enums.ErrorMessage;
import com.yue.enums.UserRegisterStatus;
import com.yue.enums.UserStatus;
import com.yue.enums.UserType;
import com.yue.exception.SoftException;
import com.yue.mapper.UserMapper;
import com.yue.service.UserService;
import com.yue.util.ResponseUtil;
import com.yue.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by yue on 2017/9/9
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


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

        User existsUser = userMapper.selectByPhone(user.getPhone());
        if (existsUser != null) {
            throw new SoftException(ErrorMessage.phone_number_is_exists);
        }

        if (!UserType.validateType(userType)) {
            throw new SoftException(ErrorMessage.type_is_error);
        }

        if (user.getInvitationCode() != null && !"".equals(user.getInvitationCode().trim())) {
            User invitationUser = userMapper.selectInvitationCode(user.getInvitationCode().trim());
            if (invitationUser != null)
                user.setRecommendUserId(invitationUser.getId());
        }


        if (userType == UserType.distributor.getValue()) {
            user.setInvitationCode(getInviteCode());
        }

        user.setType(userType);
        user.setStatus(UserStatus.normal.getValue());
        user.setRegisterStatus(UserRegisterStatus.register.getValue());
        user.setBalanceAmount(0.0);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(User user, HttpServletResponse response) {

        user = userMapper.selectByPhoneAndPassword(user.getPhone(), user.getPassword());
        if (user == null) {
            ResponseUtil.setCookie(SoftConstant.COOKIE_PHONE_NAME, "", SoftConstant.PATH, TimeConstant.TIME_DELETE_COOKIE_MILLISECOND, response);
            throw new SoftException(ErrorMessage.phone_or_password_error);
        }

        if (user.getStatus() == UserStatus.disabled.getValue()) {
            throw new SoftException(ErrorMessage.account_is_disabled);
        }

        return null;
    }


    private synchronized String getInviteCode() {
        return "A" + new Date().getTime();
    }
}
