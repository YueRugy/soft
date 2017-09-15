package com.yue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yue.util.JsonDateFormatFull;
import lombok.*;

import java.util.Date;

/**
 * Created by yue on 2017/9/8
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String phone;
    @JsonIgnore
    private String password;
    private String invitationCode;
    private Integer companyId;
    private Integer type;
    private Integer status;
    private Integer registerStatus;
    private Integer recommendUserId;
    private double balanceAmount;
    @JsonSerialize(using = JsonDateFormatFull.class)
    private Date createTime;
    private String openId;
}
