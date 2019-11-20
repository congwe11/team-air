package com.team.air.controller.user;

import com.team.air.bean.*;
import com.team.air.mapper.AirLineMapper;
import com.team.air.service.AirLineService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("book")
@Controller
public class UserBookController {

    String pre = "custom/";

    @Autowired
    AirLineService airLineService;

    @Autowired
    AirLineMapper air;

    @GetMapping("/airline")
    public String getAllAirline(Model model) {

        /**
         * 航班的状态分为三种
         * 0 为
         * 1
         * 2
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
    public String searchFlight(Flight flight,Model model){
        if(flight.getOrigin().equals("")){
            flight.setOrigin(null);
        }
        if(flight.getDestination().equals("")){
            flight.setDestination(null);
        }
        List<Flight> flights = airLineService.getSearchFlight(flight);

        List<Double> price = airLineService.getEachFlight(flights);

        for (int i=0; i<price.size(); i++){
            flights.get(i).setPrice(price.get(i));
        }
        model.addAttribute("flight",flight);
        model.addAttribute("flights",flights);
        return pre + "flightInfo";

    }

    //航班预定1
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


    @RequestMapping("/pserinfo")
    public String pserInfo(){

        return pre + "orderChoose";
    }
}
