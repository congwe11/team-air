package com.team.air.controller;

import com.team.air.bean.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("index")
    public String index(){

        return "index";
    }
}
