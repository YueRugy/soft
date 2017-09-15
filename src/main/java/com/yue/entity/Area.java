package com.yue.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by yue on 2017/9/15
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Area {
    private Integer id;
    private String name;
    private Integer pid;
    private String shortName;
    private String levelType;
    private String cityCode;
    private String zipCode;
    private String mergerName;
    private String lng;
    private String lat;
    private String pinyin;
    private Integer sort;
}
