package com.yue.service.impl;

import com.yue.entity.Area;
import com.yue.entity.UserReceiveAddress;
import com.yue.mapper.AreaMapper;
import com.yue.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yue on 2017/9/15
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    private final AreaMapper areaMapper;

    @Autowired
    public AreaServiceImpl(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Override
    public List<Area> selectByPid(Integer pid) {
        if (pid == null) {
            pid = 0;
        }
        return areaMapper.selectByPid(pid);
    }

    @Override
    public void setName(UserReceiveAddress userReceiveAddress) {
        if (userReceiveAddress.getCityId() != null) {
            userReceiveAddress.setCityName(areaMapper.selectByid(userReceiveAddress.getCityId()).getName());
        }

        if (userReceiveAddress.getDistrictId() != null) {
            userReceiveAddress.setDistrictName(areaMapper.selectByid(userReceiveAddress.getDistrictId()).getName());
        }

        if (userReceiveAddress.getProvinceId() != null) {
            userReceiveAddress.setProvinceName(areaMapper.selectByid(userReceiveAddress.getProvinceId()).getName());
        }
    }
}
