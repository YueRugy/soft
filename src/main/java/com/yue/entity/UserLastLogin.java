package com.yue.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by yue on 2017/9/12
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserLastLogin {
    private int id;
    private int userId;
    private int type;
    private Date lastLoginTime;
    private Date updateTime;
}
