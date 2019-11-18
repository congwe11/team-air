package com.team.air.controller;

import com.team.air.bean.Flight;
import com.team.air.bean.User;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

//    @RequestMapping("/index")
//    public ModelAndView index(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
//
//        User user = (User) request.getSession().getAttribute("loginUser");
//        mv.setViewName("index");
//        mv.addObject("user",user);
//        return mv;
//    }

//    @RequestMapping("/main")
//    public ModelAndView main(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){
//
//        User user = (User) request.getSession().getAttribute("loginUser");
//        mv.setViewName("index");
//        mv.addObject("user",user);
//        return mv;
//    }
}
