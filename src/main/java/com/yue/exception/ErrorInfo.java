package com.yue.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yue on 2017/9/7
 */
@Getter
@Setter
public class ErrorInfo {
    private boolean success = false;
    private String message;
    private int code;
    private String url;
}
