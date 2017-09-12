package com.yue.mapper;

import com.yue.entity.UserLastLogin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by yue on 2017/9/12
 */
@Repository
@Mapper
public interface UserLastLoginMapper {
    @Select("select * from user_last_login where user_id=#{userId}")
    UserLastLogin selectByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO user_last_login ( user_id, type,  update_time) " +
            "VALUES " +
            "(#{userId}, #{type}, now())")
    void insert(UserLastLogin userLastLogin);

    @Update("UPDATE user_last_login SET last_login_time = update_time, update_time = now() WHERE id = #{id}")
    void update(UserLastLogin userLastLogin);
}
