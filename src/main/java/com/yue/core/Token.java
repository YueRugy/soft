package com.yue.core;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yue on 2017/9/10
 */
@Getter
@Setter
public class Token {
    private int id;
    private int ths;// token hash code
    @JsonIgnore
    private String strToken;


}
