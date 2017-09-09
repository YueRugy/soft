package com.yue.exception;

import com.yue.enums.ErrorMessage;
import lombok.Getter;

/**
 * Created by yue on 2017/9/9
 */
@Getter
public class SoftException extends RuntimeException {
    private int code;

    public SoftException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SoftException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
    }
}
