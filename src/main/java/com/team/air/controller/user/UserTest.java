package com.team.air.controller.user;

import com.team.air.bean.User;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("usertest")
public class UserTest {

    @Autowired
    UserService userService;

    @RequestMapping("/{id}")
    public User getUserById(@PathVariable Integer id, HttpSession session){
        User user = userService.getUserById(id);
        session.setAttribute("loginUser",user);

        return user;
    }


}
