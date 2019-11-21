package com.team.air.controller.user;

import com.team.air.bean.OrderLine;
import com.team.air.bean.Passengers;
import com.team.air.bean.TicketLine;
import com.team.air.bean.User;
import com.team.air.mapper.AirLineMapper;
import com.team.air.mapper.UserMapper;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController {

    private String prefile = "custom/";
    @Autowired
    UserService userService;

    @Autowired
    AirLineMapper air2;

    //用户登录
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
            //登录成功，并将用户信息存入session
            session.setAttribute("loginUser",user);
            System.out.println(user);
            System.out.println(user.getUsername());
            return "redirect:/index";
        }
    }

    //用户登出
    @RequestMapping("/logout")
    public String sighOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("loginUser");
        request.getSession().invalidate();
        return "redirect:/index";
    }


    //用户注册
    //springMVC自动将请求参数和入参对象的属性进行一一绑定，要求请求参数的名字和javabean入参的对象里面的属性名是一样的
    @PostMapping("/resgister")
    public String resgister(User user,Model model){
        //rediect: 重定向到一个页面 /代表当前项目路径
        //forward: 转发到一个地址
        //来到登录页面
        System.out.println("用户注册的信息："+user);
        //判断用户是否已经存在
        if (userService.getUserByUsername(user.getUsername()) == null){
            //用户名可用，保存用户，返回登录页面
            //用户id自己获取用户总数+1
            int id = userService.countUser();
            user.setUser_id(id+1);

            //保存到数据库
            int no = userService.addUser(user);
            System.out.println("addUser():"+no);
            model.addAttribute("success","注册成功，请登录！");
            model.addAttribute("User",user);
            return prefile+"sign_in";
        }else {
            //用户名已存在，注册失败，返回注册页面
//            map.put("error","该用户名已存在！请重新输入");
//            model.addAttribute("user",user);
            model.addAttribute("error","该用户名已存在！请重新输入");
            return prefile+"sign_up";
        }
    }


    //用户修改个人信息
    @GetMapping("/upUserInfo")
    public String updateUserInfo(Model model,User user,HttpSession session){

        System.out.println(user.toString());
        userService.updateUser(user);
        //更新用户信息后，重新加载更新后的用户信息
        User newUser = userService.getUserById(user.getUser_id());
        session.setAttribute("loginUser",newUser);
        model.addAttribute("success","个人信息修改成功！");
        return prefile + "userInfo";
    }

    //用户修改密码
    @PostMapping("/upPsw")
    public String updatePsw(Model model,User user,HttpServletRequest request){

        System.out.println(user.toString());
        userService.updatePsw(user);
        model.addAttribute("success","密码修改成功！请重新登录！");
//        修改密码后，注销用户重新登录
        request.getSession().removeAttribute("loginUser");
        request.getSession().invalidate();
        return prefile + "sign_in";
    }

    /**
     *
     * 订单状态
     * 0 ：未支付
     * 1 ：正常
     * 2 : 申请退票中
     * 3 ：退票完成的
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/myorder")
    public String myorder(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("loginUser");

        List<OrderLine> myorder = air2.getOrderByUser(user.getUser_id());

        model.addAttribute("myorder",myorder);

        return prefile +"userOrder";
    }

    @RequestMapping("/myorder/{order_id}")
    public String orderInfo(HttpServletRequest request,Model model,@PathVariable("order_id") String order_id){

        TicketLine ticket = air2.getTicketByOrderId(order_id);
        OrderLine order = air2.getOrderById(order_id);
        Passengers pser = air2.getPserById(String.valueOf(ticket.getPassenger_id()));

        model.addAttribute("pser",pser);
        model.addAttribute("order_time",order.getTime());
        model.addAttribute("ticket",ticket);
        return prefile + "orderInfo";
    }

    @RequestMapping("/refund/{order_id}")
    public String orderRefund(@PathVariable("order_id")String order_id,Model model){


        air2.refund2(order_id);
        String msg = "退票订单已提交，请耐心等待我们审核通过后，即刻返回相应退款！";

        model.addAttribute("msg",msg);

        return "forward:/user/myorder";
    }


}
