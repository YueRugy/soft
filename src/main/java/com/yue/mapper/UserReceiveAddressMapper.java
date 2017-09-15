package com.yue.mapper;

import com.yue.entity.UserReceiveAddress;
import com.yue.mybatis.SimpleInsertLangDriver;
import com.yue.mybatis.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yue on 2017/9/15
 */
@Mapper
@Repository
public interface UserReceiveAddressMapper {
    @Select("select * from user_receive_address where user_id=#{userId} and is_default=#{isDefault}")
    UserReceiveAddress selectByUserIdAndIsDefault(@Param("userId") int userId, @Param("isDefault") int isDefault);

    @Insert("insert into user_receive_address (#{UserReceiveAddress})")
    @Lang(SimpleInsertLangDriver.class)
    int insert(UserReceiveAddress userReceiveAddress);

    @Lang(SimpleUpdateLangDriver.class)
    @Update("update user_receive_address (#{UserReceiveAddress})")
    int update(UserReceiveAddress userReceiveAddress);

    @Delete("delete from user_receive_address where id=#{id}")
    int delete(@Param("id") Integer id);

    @Select("select * from user_receive_address where user_id=#{userId}")
    List<UserReceiveAddress> selectByUserId(@Param("userId") int userId);

    @Select("select * from user_receive_address where id=#{id}")
    UserReceiveAddress selectById(@Param("id") Integer id);
}
