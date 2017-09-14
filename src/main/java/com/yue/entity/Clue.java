package com.yue.entity;

import com.yue.annotation.Invisible;
import lombok.*;

import java.util.Date;

/**
 * Created by yue on 2017/9/14
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Clue {
    private int id;
    private String title;
    private int status;
    private String name;
    private String phone;
    private int recommendUserId;
    private int isShow;
    private int adminUserId;
    private Date createTime;
    private Date updateTime;
    private int isDeal;

    @Invisible
    private String adminUserName;
    @Invisible
    private int businessId;
    // private ClueFlow clueFlow;
}
