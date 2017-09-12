package com.yue.mapper;

import com.yue.entity.ProductType;
import com.yue.mybatis.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yue on 2017/9/11
 */
@Repository
@Mapper
public interface ProductTypeMapper {
    @Select("select * from product_type where type_name=#{typeName}")
    ProductType selectByTypeName(@Param("typeName") String typeName);

    @Select("select * from product_type where id=#{pid}")
    ProductType selectByPid(@Param("pid") Integer pid);

    @Insert("insert  into product_type (#{productType})")
    @Lang(SimpleInsertLangDriver.class)
    int insert(ProductType productType);

    @Select("select * from product_type where type=#{type}")
    List<ProductType> selectFather(@Param("type") Integer type);

    @Select("select * from product_type where pid=#{pid}")
    List<ProductType> selectListByPid(@Param("pid") Integer pid);

    @Select("select * from product_type where id=#{id}")
    ProductType select(@Param("id") Integer id);

    @Update("update product_type set is_show=#{isShow} where id=#{id}")
    int updateShow(ProductType productType);

    @Update("update product_type set type_name=#{typeName} where id=#{id}")
    void updateName(ProductType productType);
}
