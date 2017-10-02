package com.yue.service.impl;

import com.yue.entity.ProductTag;
import com.yue.enums.ErrorMessage;
import com.yue.exception.SoftException;
import com.yue.mapper.ProductTagMapper;
import com.yue.service.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by yue on 2017/9/15
 */
@Service
@Transactional
public class ProductTagServiceImpl implements ProductTagService {
    private final ProductTagMapper productTagMapper;

    @Autowired
    public ProductTagServiceImpl(ProductTagMapper productTagMapper) {
        this.productTagMapper = productTagMapper;
    }

    @Override
    public Object add(ProductTag productTag) {
        ProductTag data = productTagMapper.selectByName(productTag.getName());
        if (data != null) {
            throw new SoftException(ErrorMessage.tag_name_is_exists);
        }

        return productTagMapper.insert(productTag);
    }

    @Override
    public Object update(ProductTag productTag, Integer id) {
        ProductTag data = productTagMapper.selectByName(productTag.getName());
        if (data != null) {
            throw new SoftException(ErrorMessage.tag_name_is_exists);
        }

        productTag.setId(id);
        return productTagMapper.update(productTag);
    }

    @Override
    public Object delete(Integer id) {
        return productTagMapper.delete(id);
    }

    @Override
    public Object get(Pageable pageable) {
        return productTagMapper.getAllByPage(pageable);
    }

    public static void main(String[] args) {
        System.out.println("aaaaaa");
    }
}
