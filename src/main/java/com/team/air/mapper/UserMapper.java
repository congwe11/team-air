package com.team.air.mapper;

import com.team.air.bean.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_id = #{id}")
    public User getUserByid(Integer id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    public User getUserByUsername(String username);


    @Insert("INSERT INTO user(user_id,username,psw,nickname,name,numbers,ID,address,sex) " +
            "values(#{user_id},#{username},#{psw},#{nickname},#{name},#{numbers},#{ID},#{address},#{sex})")
    public int addUser(User user);

    @Select("SELECT count(*) FROM user")
    public int countUser();

    //修改个人信息
    @Update("UPDATE user set nickname=#{nickname},numbers=#{numbers},address=#{address} WHERE user_id=#{user_id}")
    public int updateUser(User user);

    //修改密码
    @Update("UPDATE user set psw=#{psw} WHERE user_id=#{user_id}")
    public int updatePsw(User user);
}
