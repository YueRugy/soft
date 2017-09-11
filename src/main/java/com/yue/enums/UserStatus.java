package com.yue.enums;

/**
 * Created by yue on 2017/9/10
 */
public enum UserStatus {
    normal(1), //正常
    disabled(2)  //禁用
    ;

    private int code;

    UserStatus(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
