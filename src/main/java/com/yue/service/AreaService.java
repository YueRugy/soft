package com.yue.service;

import com.yue.entity.UserReceiveAddress;

/**
 * Created by yue on 2017/9/15
 */
public interface AreaService {
    Object selectByPid(Integer pid);

    void setName(UserReceiveAddress userReceiveAddress);
}
