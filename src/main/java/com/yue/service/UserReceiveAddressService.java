package com.yue.service;

import com.yue.entity.UserReceiveAddress;

/**
 * Created by yue on 2017/9/15
 */
public interface UserReceiveAddressService {
    Object add(UserReceiveAddress userReceiveAddress);

    Object update(Integer id, UserReceiveAddress userReceiveAddress);

    Object delete(Integer id);

    Object get();

    Object selectById(Integer id);
}
