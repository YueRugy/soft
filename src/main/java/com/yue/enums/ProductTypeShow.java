package com.yue.enums;

import lombok.Getter;

/**
 * Created by yue on 2017/9/11
 */
@Getter
public enum ProductTypeShow {
    show(1),
    hidden(2),;

    private int value;

    ProductTypeShow(int value) {
        this.value = value;
    }
}
