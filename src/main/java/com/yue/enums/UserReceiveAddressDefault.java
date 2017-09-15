package com.yue.enums;

public enum UserReceiveAddressDefault {
    isDefault(1), //默认的
    isNotDefault(2)  //不是默认的
    ;

    private int code;

    UserReceiveAddressDefault(int code) {
        this.code = code;
    }

    public int getValue() {
        return code;
    }
}
