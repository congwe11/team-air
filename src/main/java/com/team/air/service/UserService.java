package com.team.air.service;

import com.team.air.bean.User;
import com.team.air.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserById(Integer id){
        System.out.println("查询用户:"+id);
        User user = userMapper.getUserByid(id);
        return user;
    }

    public User getUserByUsername(String username){
        System.out.println("判断用户名:"+username);
        User user = userMapper.getUserByUsername(username);

        if (user == null || "".equals(user)){
            return null;
        }else {
            return user;
        }
    }
}
