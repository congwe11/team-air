package com.team.air.controller.admin;

import com.team.air.bean.*;
import com.team.air.mapper.*;
import com.team.air.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.text.resources.cldr.lg.FormatData_lg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    String pre = "admin/";

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PlaneMapper planeMapper;

    @Autowired
    FlightMapper flightMapper;



    @RequestMapping("/delFlight/{id}")
    public String delFlight(@PathVariable("id")Integer id){

        adminService.delFlight(id);

        return "redirect:/admin/flights";
    }

    @RequestMapping("/upFlight/{id}")
    public String upFlight1(@PathVariable("id")Integer id,Model model){

        Flight flight = flightMapper.getFlightById(id);

        model.addAttribute("flight",flight);
        return pre + "upFlight";
    }
    @PostMapping("/upFlight")
    public String upFlight2(Flight flight,Model model){


        flightMapper.upFliht(flight);

        model.addAttribute("msg","航班信息修改成功！");

        return pre + "upFlight";
    }

    @RequestMapping("/upPlane/{id}")
    public String uoPlane1(@PathVariable("id")Integer id,Model model){

        //一个飞机对象,和两个座位信息,

        Plane plane = planeMapper.getPlaneByid(id);
        List<SeatsInfo> seats = planeMapper.getSeatsById(id);
        SeatsInfo s1 =new SeatsInfo();
        SeatsInfo s2 =new SeatsInfo();

        for(SeatsInfo s : seats){
            if(s.getSeatType()==1){
                s1 = s;
            }else {
                s2 = s;
            }
        }

        model.addAttribute("s1",s1);
        model.addAttribute("s2",s2);
        model.addAttribute("plane",plane);
        return pre + "upPlane";
    }
    @PostMapping("/upPlane")
    public String upPlane2(HttpServletRequest request,Plane plane,Model model){

        SeatsInfo s1 = new SeatsInfo();
        SeatsInfo s2 = new SeatsInfo();
        s1.setPlane_id(plane.getPlane_id());
        s2.setPlane_id(plane.getPlane_id());

        s1.setPlane_id(plane.getPlane_id());
        s2.setPlane_id(plane.getPlane_id());

        s1.setSeatCount(Integer.parseInt(request.getParameter("seatCount1")));
        s2.setSeatCount(Integer.parseInt(request.getParameter("seatCount2")));

        s1.setSeatPrice(Double.parseDouble(request.getParameter("seatPrice1")));
        s2.setSeatPrice(Double.parseDouble(request.getParameter("seatPrice2")));


        adminService.upPlane(plane,s1,s2);

        model.addAttribute("msg","客机信息修改成功！");

        return pre + "addPlane";
    }





    @RequestMapping("/addplane")
    public String addPlane1(Model model){

        return pre + "addPlane";
    }
    @RequestMapping(value = "/p_finish",method = RequestMethod.POST)
    public String addPlane2(HttpServletRequest request,Plane plane,Model model){
        //1获取一个飞机对象，2 生成两个座位信息对象

        //生成飞机的ID,和状态
        plane.setPlane_id(planeMapper.countPlane() +1);
        plane.setStatus(0);

        SeatsInfo s1 = new SeatsInfo();
        SeatsInfo s2 = new SeatsInfo();
        s1.setPlane_id(plane.getPlane_id());
        s2.setPlane_id(plane.getPlane_id());

        s1.setSeatCount(Integer.parseInt(request.getParameter("seatCount1")));
        s2.setSeatCount(Integer.parseInt(request.getParameter("seatCount2")));

        s1.setSeatPrice(Double.parseDouble(request.getParameter("seatPrice1")));
        s2.setSeatPrice(Double.parseDouble(request.getParameter("seatPrice2")));

        s1.setSeatType(1);
        s2.setSeatType(2);

//        System.out.println(s1.toString());
//        System.out.println(s2.toString());
//        System.out.println(plane.toString());

        adminService.onePlaneFinish(plane,s1,s2);

        model.addAttribute("msg","客机添加成功！");
        return pre + "addPlane";
    }

    //添加航班1
    @RequestMapping("/addflight")
    public String addFlight(Model model){

        //先查出空闲客机
        List<Plane> planes = planeMapper.getEasyPlane();


        model.addAttribute("planes",planes);
        return pre + "addFlight";
    }
    //添加航班2
    @PostMapping(value = "/f_finish")
    public String addFlight2(HttpServletRequest request,Flight flight,Model model){

        String s = request.getParameter("plane_id");
        if (s == null || "".equals(s)){
            model.addAttribute("msg","添加失败,请为航班分配一个客机！");
            return pre + "addFlight";
        }

        //一条航班信息, 两条余票信息, 一条airline信息
        flight.setFlight_id(flightMapper.countFliht()+1);
        flight.setStatus(1);



        //航班联系表
        AirLine airLine =new AirLine();
        airLine.setFlight_id(flight.getFlight_id());
        airLine.setPlane_id(Integer.parseInt(s));

        //余票表
        Fleave fleave1 = new Fleave();
        Fleave fleave2 = new Fleave();

        fleave1.setFlight_id(flight.getFlight_id());
        fleave2.setFlight_id(flight.getFlight_id());

        fleave1.setSeatType(1);
        fleave2.setSeatType(2);

        fleave1.setLeaveTickets(planeMapper.countPS1(s));
        fleave2.setLeaveTickets(planeMapper.countPS2(s));

        System.out.println(airLine.toString());
        System.out.println(flight.toString());
        System.out.println(fleave1.toString());
        System.out.println(fleave2.toString());

        //返回航班
        List<Plane> planes = planeMapper.getEasyPlane();
        adminService.oneFlightFinish(flight,fleave1,fleave2,airLine,s);
        model.addAttribute("msg","添加航班成功！");
        model.addAttribute("planes",planes);
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
    //正常航班
    @RequestMapping("/normalFlight")
    public String nromalFlight(Model model){

        List<FlightPlus> flightPluses = adminMapper.getNorByFlights();

        for (FlightPlus f: flightPluses){
            List<Fleave> fl = adminMapper.getFleaveById(f.getFlight_id());
            f.setFleaves(fl);
        }
        model.addAttribute("fps",flightPluses);
        return pre + "allFlight";
    }

    //失效航班
    @RequestMapping("/failFlight")
    public String failFlight(Model model){

        List<FlightPlus> flightPluses = adminMapper.getFailByFlights();

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
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String sign(@RequestParam("ad_username")String username, @RequestParam("ad_psw")String psw,
                        Model model, HttpSession session){

        Admin admin = adminMapper.getAdByUsername(username);
        System.out.println(admin.toString());
        if (admin == null){

            model.addAttribute("msg","不存在该用户！请重新输入");
            return pre + "adSign";
        }else if( !admin.getAd_psw().equals(psw)) {

            model.addAttribute("msg","密码不正确！请重新输入");
            return pre + "adSign";
        }else {
            //登录成功，并将用户信息存入session
            session.setAttribute("loginAdmin", admin);
            System.out.println(admin.getAd_username());
            return "redirect:/admin/dashboard";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginAdmin");
        request.getSession().invalidate();
        return "redirect:/admin/";
    }



}
