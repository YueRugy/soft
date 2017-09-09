package com.yue.enums;

import lombok.Getter;

/**
 * Created by yue on 2017/9/9
 */
@Getter
public enum ErrorMessage {
    phone_number_is_empty(Code.ERROR.getCode(), "手机为空"),
    phone_number_is_illegal(Code.ERROR.getCode(), "手机号非法"),
    password_is_empty(Code.ERROR.getCode(), "密码为空"),
    phone_number_is_exists(Code.ERROR.getCode(), "手机号已经被注册"),;
    private int code;
    private String message;

    ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
