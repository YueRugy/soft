package com.yue.mapper;

import com.yue.entity.Clue;
import com.yue.mybatis.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
