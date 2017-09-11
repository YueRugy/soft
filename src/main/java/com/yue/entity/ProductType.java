package com.yue.entity;

import lombok.*;

/**
 * Created by yue on 2017/9/11
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductType {
    private Integer id;
    private Integer pid;
    private String typeName;
    private Integer type;
    private Integer isShow;
}
