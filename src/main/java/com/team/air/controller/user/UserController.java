package com.team.air.controller.user;

import com.team.air.bean.User;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController {

    private String prefile = "user/";
    @Autowired
    UserService userService;





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
            session.setAttribute("username",user.getUsername());
            System.out.println(user);
            System.out.println(user.getUsername());
            return "redirect:/index";
        }
    }

    @RequestMapping("/logout")
    public String sighOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("loginUser");
        request.getSession().invalidate();
        return "redirect:/index";
    }



    @RequestMapping("/book")
    public String userBook(){
        return "orderBook";
    }




    public User getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return user;
    }

}
