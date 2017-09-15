package com.yue.mapper;

import com.yue.entity.ProductTag;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yue on 2017/9/15
 */
@Mapper
@Repository
public interface ProductTagMapper {
    @Select("select * from product_tag where name=#{name}")
    ProductTag selectByName(@Param("name") String name);

    @Insert("insert into product_tag (name) values (#{name})")
    int insert(ProductTag productTag);

    @Update("update product_tag set name=#{name} where id=#{id}")
    int update(ProductTag productTag);

    @Delete("delete from product_tag where id=#{id}")
    int delete(@Param("id") Integer id);

    @Select("select * from product_tag ")
    List<ProductTag> getAllByPage(Pageable pageable);

}
