package com.team.air.mapper;

import com.team.air.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user where user_id = #{id}")
    public User getUserByid(Integer id);
}
