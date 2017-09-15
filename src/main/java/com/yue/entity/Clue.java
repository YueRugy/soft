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
    private Integer id;
    private String title;
    private Integer status;
    private String name;
    private String phone;
    private Integer recommendUserId;
    private Integer isShow;
    private Integer adminUserId;
    private Date createTime;
    private Date updateTime;
    private Integer isDeal;

    @Invisible
    private String adminUserName;
    @Invisible
    private Integer businessId;
    // private ClueFlow clueFlow;
}
