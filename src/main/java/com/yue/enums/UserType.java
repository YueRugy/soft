package com.yue.enums;

import lombok.Getter;

/**
 * Created by yue on 2017/9/10
 */
@Getter
public enum UserType {
    ordinary(1), //普通用户
    distributor(2)  //分销者
    ;
    private int value;

    UserType(int value) {
        this.value = value;
    }

    public static boolean validateType(int type) {
        for (UserType u : UserType.values()) {
            if (u.getValue() == type) {
                return true;
            }
        }
        return false;
    }

}
