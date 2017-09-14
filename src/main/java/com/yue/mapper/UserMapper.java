package com.yue.mapper;

import com.yue.entity.User;
import com.yue.mybatis.SimplePageLangDriver;
import com.yue.mybatis.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yue on 2017/9/8
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);

    @Insert("insert into user(name,phone,password,invitation_code,company_id,type,status,register_status,recommend_user_id,balance_amount,create_time,open_id)" +
            " VALUES " +
            "(#{name},#{phone},#{password},#{invitationCode},#{companyId},#{type},#{status},#{registerStatus},#{recommendUserId},#{balanceAmount},now(),#{openId})")
    int insert(User user);

    @Select("select * from user where invitation_dode=#{invitationCode}")
    User selectInvitationCode(@Param("invitationCode") String invitationCode);

    @Select("select * from user where phone=#{phone} and password=#{password}")
    User selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    @Lang(SimpleUpdateLangDriver.class)
    @Update("update user  (#{user}) where id=#{id}")
    void update(User user);

    @Select("select * from user where open_id=#{openId}")
    User selectByOpenId(@Param("openId") String openId);

    @Lang(SimplePageLangDriver.class)
    @Select("select * from user (#{user})")
    List<User> selectAllByPage(Pageable pageable);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where recommend_user_id=#{userId}")
    List<User> getUserRecommendContacts(@Param("userId") int userId);

    @Select("select * from user where id=#{id}")
    User selectByUserId(@Param("id") Integer id);
}
