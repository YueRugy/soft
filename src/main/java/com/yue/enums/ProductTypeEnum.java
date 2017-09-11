package com.yue.enums;

import lombok.Getter;

/**
 * Created by yue on 2017/9/11
 */
@Getter
public enum ProductTypeEnum {
    father(1),
    child(2),;

    private int value;

    ProductTypeEnum(int value) {
        this.value = value;
    }
}
