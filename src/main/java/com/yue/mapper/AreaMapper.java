package com.yue.mapper;

import com.yue.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yue on 2017/9/15
 */
@Repository
@Mapper
public interface AreaMapper {
    @Select("select * from area where pid=#{pid}")
    List<Area> selectByPid(@Param("pid") Integer pid);

    @Select("select * from area where id=#{id}")
    Area selectByid(@Param("id") Integer id);
}
