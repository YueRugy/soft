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
    phone_number_is_exists(Code.ERROR.getCode(), "手机号已经被注册"),
    type_is_error(Code.ERROR.getCode(), "type类型不正确"),
    phone_or_password_error(Code.ERROR.getCode(), "手机号或密码错误"),
    need_login(Code.NEED_LOGIN.getCode(), "账号未登录"),
    account_is_disabled(Code.ERROR.getCode(), "账号被禁用"),
    product_type_name_can_not_empty(Code.ERROR.getCode(), "产品类别名字不能为空"),
    product_type_name_repeat(Code.ERROR.getCode(), "产品类别名字重复"),
    product_type_is_not_exist(Code.ERROR.getCode(), "产品类别不存在"),
    tag_name_is_exists(Code.ERROR.getCode(), "标签名字已存在"),;
    private int code;
    private String message;

    ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
