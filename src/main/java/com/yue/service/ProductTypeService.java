package com.yue.service;

import com.yue.entity.ProductType;

import java.util.List;

/**
 * Created by yue on 2017/9/11
 */
public interface ProductTypeService {
    ProductType insert(ProductType productType);

    List<ProductType> select(Integer pid);

    int delete(Integer id);

    ProductType put(Integer id, ProductType productType);
}
