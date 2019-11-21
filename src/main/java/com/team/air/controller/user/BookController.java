package com.team.air.controller.user;

import com.team.air.bean.*;
import com.team.air.mapper.AirLineMapper;
import com.team.air.service.AirLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("book")
@Controller
public class BookController {

    String pre = "custom/";

    @Autowired
    AirLineService airLineService;

    @Autowired
    AirLineMapper air;

    @GetMapping("/airline")
    public String getAllAirline(Model model) {

        /**
         * 航班的状态分为三种
         * 0 为未分配客机
         * 1 已分配客机
         * 2 失效航班
         */
        Collection<AirLine> airline = airLineService.getAllAirline();
        Collection<SeatsInfo> seatsInfo = airLineService.getSeatsInfo();
        Collection<Flight> flights = airLineService.getallFlight();
        List<Double> price = new ArrayList<Double>();
        List<Flight> flightList = new ArrayList<Flight>();
        for (AirLine a : airline){
            for(SeatsInfo b : seatsInfo){
                if (a.getPlane_id() == b.getPlane_id()){
                    price.add(b.getSeatPrice());
                    break;
                }
            }
        }
        for(Flight f : flights){
            flightList.add(f);
        }
        for (int i=0; i<flightList.size(); i++){
            flightList.get(i).setPrice(price.get(i));
        }
//        model.addAttribute("Airline", airline);
//        model.addAttribute("SeatsInfo", seatsInfo);
//        model.addAttribute("price",price);
        model.addAttribute("flights",flightList);
        return pre + "flightInfo";
    }

    //航班查询
    @GetMapping("/search")
    public String searchFlight(Flight flight,Model model,HttpServletRequest request){
        if(flight.getOrigin().equals("")){
            flight.setOrigin(null);
        }
        if(flight.getDestination().equals("")){
            flight.setDestination(null);
        }
        if (flight.getStart_time().equals("")){
            flight.setStart_time(null);
        }
//        String time = request.getParameter("time");
//        System.out.println(time);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
//        Date date = formatter.parse();

//        flight.setStart_time(date);
        List<Flight> flights = airLineService.getSearchFlight(flight);

        List<Double> price = airLineService.getEachFlight(flights);

        for (int i=0; i<price.size(); i++){
            flights.get(i).setPrice(price.get(i));
        }
        model.addAttribute("flight",flight);
        model.addAttribute("flights",flights);
        return pre + "flightInfo";

    }

    //航班预定1，在此处选择航班和舱位
    @RequestMapping("/airline/{id}")
    public String oneFlightInfo(@PathVariable("id")Integer id,Model model){

        //返回对象
        Flight flight = air.getFlightById(id);

        Integer plane_id = air.getAirLineById(id);
        //返回对象
        Plane plane = air.getPlaneByid(plane_id);
        //返回对象
        List<SeatsInfo> seatsInfo = air.getSeatsInfoByPlaneId(plane_id);

        SeatsInfo seatsInfo1 = seatsInfo.get(0);
        SeatsInfo seatsInfo2 = seatsInfo.get(1);
        System.out.println(seatsInfo1.toString());
        System.out.println(seatsInfo2.toString());
        List<Fleave> fleave = air.getLeaveByFlightId(id);

        //航班座位信息
        String msg1 = "";
        String msg2 = "";
        String error = "";
        //判断有无票
        for(int i=0; i<fleave.size(); i++){
            if (fleave.get(i).getSeatType()==1){
                if (fleave.get(i).getLeaveTickets()<=0){
                    msg1 = "无票";
                }else {
                    msg1 = "有票";
                }
            }else {
                if(fleave.get(i).getLeaveTickets()<=0) msg2 = "无票";
                else msg2 = "有票";
            }
        }

        if(msg1.equals("无票") && msg2.equals("无票") ){
            error = "该航班机票以售完";
        }
        model.addAttribute("flight",flight);
        model.addAttribute("plane",plane);
        model.addAttribute("seatsInfo1",seatsInfo1);
        model.addAttribute("seatsInfo2",seatsInfo2);
        model.addAttribute("msg1",msg1);
        model.addAttribute("msg2",msg2);
        model.addAttribute("error",error);
        return pre + "flightDetails";
    }


    //航班预定2
    @RequestMapping(value = "/pserinfo",method = RequestMethod.GET)
    public String pserInfo(SeatsInfo seatsInfo,Model model,HttpServletRequest request,HttpSession session){

        User user = (User) request.getSession().getAttribute("loginUser");
        String flight_id = request.getParameter("flight_id");
        System.out.println("航班ID："+flight_id);
        System.out.println("用户ID："+user.getUser_id());
//        System.out.println(seatsInfo.toString());
//        if (seatsInfo == null){
////            return pre + "orderChoose";
////        }
        /**
         * 订单
         * 1、id 2、价格 3、用户id 4、状态 5时间
         * 状态 0 无效  1 有效
         */
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();

//        System.out.println("当前时间：：：："+sdf.format(date));
        OrderLine orderLine = new OrderLine();
//        int orderId = ;
        orderLine.setOrder_id(air.countOrderline()+1);
        orderLine.setAll_price(seatsInfo.getSeatPrice());
        orderLine.setUser_id(user.getUser_id());
        orderLine.setStatus(0);
        orderLine.setTime(sdf.format(date));

//        air.addOrder(orderLine);
        session.setAttribute("order",orderLine);
        model.addAttribute("order",orderLine.getOrder_id());
        model.addAttribute("sType",seatsInfo.getSeatType());
        model.addAttribute("flight_id",flight_id);
        return pre + "orderChoose";
    }



    //航班预定3，在此处生成乘客信息
    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public String confirm(Passengers pser,HttpServletRequest request,Model model){

        String flight_id = request.getParameter("flight_id");
        String order_id = request.getParameter("order_id"); //确认订单有效
        String sType = request.getParameter("sType");

        Fleave fleave = air.getLeaveByOne(flight_id,sType);

        OrderLine order = (OrderLine) request.getSession().getAttribute("order");
        pser.setPassenger_id(air.countPser()+1);
        pser.setStatus(0);
        System.out.println(pser.toString());
        System.out.println("我进入了订单确定页！");
//        System.out.println("order_id："+order_id +";  sType:" +sType);

        //添加乘客,订单到数据库 事务
        airLineService.Prefinish(order,pser,fleave);



        Flight flight = air.getFlightById(pser.getFlight_id());
//        OrderLine order = air.getOrderById(order_id);
        model.addAttribute("order",order);
        model.addAttribute("sType",sType);
        model.addAttribute("start_time",flight.getStart_time());
        model.addAttribute("pser_id",pser.getPassenger_id());

        request.getSession().removeAttribute("order");
        return pre + "orderConfirm";
    }

    //航班预定4，完成
    @RequestMapping("/finish")
    public String finish(HttpServletRequest request){
        System.out.println("我进入了订单完成页！");
        String order_id = request.getParameter("order_id");
        String pser_id = request.getParameter("pser_id");
        Integer sType = Integer.parseInt(request.getParameter("sType"));
        Passengers p = air.getPserById(pser_id);
        Flight f = air.getFlightById(p.getFlight_id());
        OrderLine o = air.getOrderById(order_id);

        TicketLine ticket = new TicketLine();

        ticket.setOrigin(f.getOrigin());
        ticket.setDestination(f.getDestination());
        ticket.setStart_time(f.getStart_time());
        ticket.setEnd_time(f.getEnd_time());
        ticket.setPassenger_id(p.getPassenger_id());
        ticket.setOrder_id(o.getOrder_id());
        ticket.setFirstPrice(o.getAll_price());
        ticket.setsType(sType);
        //确认订单，乘客，and 添加机票
        airLineService.finish(order_id,pser_id,ticket);


        return pre + "orderFinish";
    }
}
