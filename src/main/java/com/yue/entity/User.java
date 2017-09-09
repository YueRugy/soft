package com.yue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by yue on 2017/9/8
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String name;
    private String phone;
    @JsonIgnore
    private String password;
    private String invitationCode;
    private int companyId;
    private int type;
    private int status;
    private int registerStatus;
    private int recommendUserId;
    private double balanceAmount;
    private Date createTime;
    private String openId;
}
