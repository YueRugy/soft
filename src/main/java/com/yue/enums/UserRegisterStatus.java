package com.yue.enums;

/**
 * Created by yue on 2017/9/10
 */
public enum UserRegisterStatus {
    register(1), //注册
    notRegister(2)  //未注册
    ;

    private int code;

    UserRegisterStatus(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
