package com.yue.exception;

import com.yue.enums.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yue on 2017/9/7
 */
@Getter
@Setter
public class MyException extends RuntimeException {
    private int code;

    public MyException(String message) {
        super(message);
    }

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
    }
}
