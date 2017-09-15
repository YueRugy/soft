package com.yue.service.impl;

import com.yue.entity.UserReceiveAddress;
import com.yue.enums.UserReceiveAddressDefault;
import com.yue.mapper.UserReceiveAddressMapper;
import com.yue.service.UserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by yue on 2017/9/15
 */
@Service
@Transactional
public class UserReceiveAddressServiceImpl implements UserReceiveAddressService {
    private final UserReceiveAddressMapper userReceiveAddressMapper;

    @Autowired
    public UserReceiveAddressServiceImpl(UserReceiveAddressMapper userReceiveAddressMapper) {
        this.userReceiveAddressMapper = userReceiveAddressMapper;
    }

    @Override
    public Object add(UserReceiveAddress userReceiveAddress) {

        // int userId = AppContent.getUserId();
        //测试用
        int userId = 1;
        userReceiveAddress.setUserId(userId);

        if (userReceiveAddressMapper.selectByUserIdAndIsDefault(userId, UserReceiveAddressDefault.isDefault.getValue()) == null) {
            userReceiveAddress.setIsDefault(UserReceiveAddressDefault.isDefault.getValue());
        } else {
            userReceiveAddress.setIsDefault(UserReceiveAddressDefault.isNotDefault.getValue());
        }
        return userReceiveAddressMapper.insert(userReceiveAddress);
    }

    @Override
    public Object update(Integer id, UserReceiveAddress userReceiveAddress) {
        userReceiveAddress.setId(id);
        UserReceiveAddress defaultAddress = userReceiveAddressMapper.selectByUserIdAndIsDefault(1, UserReceiveAddressDefault.isDefault.getValue());
        //如果修改默认 原来的默认改为不默认


        if (defaultAddress != null && Objects.equals(defaultAddress.getId(), id)) {
            userReceiveAddress.setIsDefault(UserReceiveAddressDefault.isDefault.getValue());
        }

        if (userReceiveAddress.getIsDefault() != null && userReceiveAddress.getIsDefault() == UserReceiveAddressDefault.isDefault.getValue()
                && defaultAddress != null && !Objects.equals(defaultAddress.getId(), id)) {
            defaultAddress.setIsDefault(UserReceiveAddressDefault.isNotDefault.getValue());
        }

        return userReceiveAddressMapper.update(userReceiveAddress);
    }

    @Override
    public Object delete(Integer id) {
        return userReceiveAddressMapper.delete(id);
    }

    @Override
    public Object get() {
        return userReceiveAddressMapper.selectByUserId(1);
    }

    @Override
    public Object selectById(Integer id) {
        return userReceiveAddressMapper.selectById(id);
    }
}
