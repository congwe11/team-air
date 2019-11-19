package com.team.air.controller;

import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class HomeController {

    @Autowired
    UserService userService;


}
