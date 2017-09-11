package com.yue.service.impl;

import com.yue.entity.ProductType;
import com.yue.enums.ErrorMessage;
import com.yue.enums.ProductTypeEnum;
import com.yue.enums.ProductTypeShow;
import com.yue.exception.SoftException;
import com.yue.mapper.ProductTypeMapper;
import com.yue.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yue on 2017/9/11
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeMapper productTypeMapper;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public ProductType insert(ProductType productType) {
        if (productType.getTypeName() == null || "".equals(productType.getTypeName())) {
            throw new SoftException(ErrorMessage.product_type_name_can_not_empty);
        }

        ProductType existProductType = productTypeMapper.selectByTypeName(productType.getTypeName());
        if (existProductType != null) {
            throw new SoftException(ErrorMessage.product_type_name_repeat);
        }

        if (productType.getPid() == null || productType.getPid() == 0) {
            productType.setType(ProductTypeEnum.father.getValue());
            productType.setPid(0);
        } else {
            existProductType = productTypeMapper.selectByPid(productType.getPid());
            if (existProductType == null) {
                throw new SoftException(ErrorMessage.product_type_is_not_exist);
            }
            productType.setType(ProductTypeEnum.child.getValue());
        }

        productType.setIsShow(ProductTypeShow.show.getValue());
        productTypeMapper.insert(productType);
        return productType;
    }

    @Override
    public List<ProductType> select(Integer pid) {
        /*List<ProductType> list;
        if (pid == null || pid == 0) {
            list = productTypeMapper.selectFather();
        } else {
            list = productTypeMapper.selectListByPid(pid);
        }
        return list;*/

        return pid == null || pid == 0 ? productTypeMapper.selectFather(ProductTypeEnum.father.getValue()) : productTypeMapper.selectListByPid(pid);

    }

    @Override
    public int delete(Integer id) {
        ProductType productType = productTypeMapper.select(id);
        if (productType == null) {
            throw new SoftException(ErrorMessage.product_type_is_not_exist);
        }
        productType.setIsShow(ProductTypeShow.hidden.getValue());
        return productTypeMapper.updateShow(productType);
    }

    @Override
    public ProductType put(Integer id, ProductType productType) {
        ProductType data = productTypeMapper.select(id);
        if (data == null) {
            throw new SoftException(ErrorMessage.product_type_is_not_exist);
        }
        productType.setId(id);
        productTypeMapper.updateName(productType);
        return null;
    }
}
