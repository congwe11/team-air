package com.team.air.controller.user;

import com.team.air.bean.AirLine;
import com.team.air.bean.Flight;
import com.team.air.bean.SeatsInfo;
import com.team.air.service.AirLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
