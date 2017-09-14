package com.yue.mapper;

import com.yue.entity.Clue;
import com.yue.mybatis.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yue on 2017/9/14
 */
@Mapper
@Repository
public interface ClueMapper {
    @Lang(SimpleInsertLangDriver.class)
    @Insert("insert into clue (#{clue})")
    int insert(Clue clue);

    @Select("select * from clue where recommend_user_id=#{id}")
    List<Clue> selectClueByCommIdByPage(Map<String, Object> param);

    @Select("select * from clue where id=#{id}")
    Clue selectById(@Param("id") Integer id);
    @Delete("delete from clue where id=#{id}")
    int deleteById(Integer id);
}
