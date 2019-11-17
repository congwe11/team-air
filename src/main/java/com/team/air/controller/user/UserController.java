package com.team.air.controller.user;

import com.team.air.bean.User;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController {

    private String prefile = "user/";
    @Autowired
    UserService userService;

    @RequestMapping("/sign")
    public String user(){
        return prefile + "sign_in";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("psw") String psw,
                        Map<String,Object> map, HttpSession session){

        User user = userService.getUserByUsername(username);
        if (user == null){

            map.put("msg","不存在该用户！请重新输入。");
            return prefile + "sign_in";
        }else if( !user.getPsw().equals(psw)) {
            map.put("username",username);
            map.put("msg","密码不正确！请重新输入");
            return prefile + "sign_in";
        }else {
            //登录成功

            session.setAttribute("loginUser",user);
            return "redirect:/index.html";
        }
    }






    public User getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return user;
    }

}
