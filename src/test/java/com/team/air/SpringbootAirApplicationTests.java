package com.team.air;

import com.team.air.bean.*;
import com.team.air.mapper.AdminMapper;
import com.team.air.mapper.AirLineMapper;
import com.team.air.mapper.PlaneMapper;
import com.team.air.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAirApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AirLineMapper airLineMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PlaneMapper planeMapper;

    @Test
    public void contextLoads() {



        Integer i = planeMapper.countPS1("7");

        Integer j = planeMapper.countPS2("7");

        System.out.println(i);
        System.out.println(j);


//        List<FlightPlus> flightPluses = adminMapper.getAllByFlights();
//
//        for (FlightPlus f: flightPluses){
//            List<Fleave> fl = adminMapper.getFleaveById(f.getFlight_id());
//            f.setFleaves(fl);
//
//            System.out.println(f.toString());
//        }



//        List<OrderLine> allOrder = airLineMapper.getAllOrder();
//
//        for (OrderLine o : allOrder){
//            System.out.println(o.toString());
//        }
//        Collection<AirLine> allAirline = airLineMapper.getAllAirline();
//        Collection<SeatsInfo> seatsInfo = airLineMapper.getSeatsInfo();
//        Collection<AirLine> a = airLineMapper.getAllAirline();
//        Collection<Flight> b = airLineMapper.getAllFlight();
//        for(SeatsInfo s : seatsInfo){
//            System.out.println(s.toString());
//        }
//        for(AirLine s : a){
//            System.out.println(s.getFlight_id()+"  ; plane: "+ s.getPlane_id());
//        }
//        for(Flight s : b){
//            System.out.println(s.toString());
//        }
//        Date date = new Date();
//        SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();
//        System.out.println("当前时间：：：："+sdf.format(date));

    }

}
