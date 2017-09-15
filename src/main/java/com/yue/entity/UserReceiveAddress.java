package com.yue.entity;

import com.yue.annotation.Invisible;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by yue on 2017/9/14
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserReceiveAddress {
    private int id;
    private int userId;
    private int provinceId;
    private int cityId;
    private int districtId;
    private String address;
    private String contact;
    private String contactPhone;
    private int isDefault;
    private Date createTime;
    @Invisible
    private String provinceName;
    @Invisible
    private String cityName;
    @Invisible
    private String districtName;

}
