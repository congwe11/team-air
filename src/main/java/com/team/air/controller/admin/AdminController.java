package com.team.air.controller.admin;

import com.team.air.bean.*;
import com.team.air.mapper.AdminMapper;
import com.team.air.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    String pre = "admin/";

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/addplane")
    public String addPlane(){

        return pre + "addPlane";
    }
    @RequestMapping("/addflight")
    public String addFlight(){

        return pre + "addFlight";
    }


    @RequestMapping("/review")
    public String refund2(Model model){

        List<OrderLine> orderLines = orderMapper.getROrders();

        model.addAttribute("reOrders",orderLines);

        return pre + "adRefund";
    }

    //退票审核通过
    @RequestMapping("/approve/{id}")
    public String approve(@PathVariable("id")Integer id, Model model){

        orderMapper.approve(id);
        model.addAttribute("msg","退票审核已完成！结果：同意退票！");
        return "forward:/admin/review";
    }
    //退票审核不通过
    @RequestMapping("/refuse/{id}")
    public String refuse(@PathVariable("id")Integer id,Model model){

        orderMapper.refuse(id);
        model.addAttribute("msg","退票审核已完成！结果：拒绝退票！");
        return "forward:/admin/review";
    }
    @RequestMapping("/flights")
    public String allFlights(Model model){

        List<FlightPlus> flightPluses = adminMapper.getAllByFlights();

        for (FlightPlus f: flightPluses){
            List<Fleave> fl = adminMapper.getFleaveById(f.getFlight_id());
            f.setFleaves(fl);
        }
        model.addAttribute("fps",flightPluses);
        return pre + "allFlight";
    }

    @RequestMapping("/planes")
    public String allPlanes(Model model){

        List<PlanePlus> planePluses = adminMapper.getAllByPlanes();

        for(PlanePlus p : planePluses){
            List<SeatsInfo> si = adminMapper.getSeatsInfo(p.getPlane_id());
            p.setSeat(si);

            if( adminMapper.getAirline(p.getPlane_id()) ==null){
                p.setStatus(0); //未分配航班
            }else {
                p.setStatus(1); //已分配航班
            }
        }
        model.addAttribute("pps",planePluses);
        return pre + "allPlane";
    }
//    @PostMapping("/login")
//    public String sign(@RequestParam("ad_username")String username, @RequestParam("ad_psw")String psw,
//                        Model model, HttpSession session){
//
//        Admin admin = adminMapper.getAdByUsername(username);
//        System.out.println(admin.toString());
//        if (admin == null){
//
//            model.addAttribute("msg","不存在该用户！请重新输入");
//            return pre + "adSign";
//        }else if( !admin.getAd_psw().equals(psw)) {
//
//            model.addAttribute("msg","密码不正确！请重新输入");
//            return pre + "adSign";
//        }else {
//            //登录成功，并将用户信息存入session
//            session.setAttribute("loginAdmin", admin);
//            System.out.println(admin.getAd_username());
//            return "redirect:/admin/dashboard";
//        }
//    }
//
//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request){
//        request.getSession().removeAttribute("loginAdmin");
//        request.getSession().invalidate();
//        return "redirect:/admin/";
//    }

//    @RequestMapping("/addFlight")
//    public String addFlight(){
//
//
//        return pre + "addFlight";
//    }
//
//    @RequestMapping("/planes")
//    public String allPlanes(Model model){
//
//
//        return pre + "allPlane";
//    }
//

}
