package com.yue.mapper;

import com.yue.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by yue on 2017/9/8
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);
    @Insert("INSERT INTO `user` ( `name`, `phone`, `password`, `invitation_code`, `company_id`, `type`, `status`, `register_status`, `recommend_user_id`, `balance_amount`, `create_time`, `open_id`)" +
            "VALUES" +
            "(#{name}, #{phone}, #{password}, #{invitationCode}, #{companyId}, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);")
    int insert(User user);
}
