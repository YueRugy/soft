package com.yue.service;

import com.yue.entity.ProductTag;
import org.springframework.data.domain.Pageable;


/**
 * Created by yue on 2017/9/15
 */

public interface ProductTagService {
    Object add(ProductTag productTag);

    Object update(ProductTag productTag, Integer id);

    Object delete(Integer productTag);

    Object get(Pageable pageable);
}
