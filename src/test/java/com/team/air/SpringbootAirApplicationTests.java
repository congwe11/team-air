package com.team.air;

import com.team.air.bean.*;
import com.team.air.mapper.AirLineMapper;
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

    @Test
    public void contextLoads() {

        List<OrderLine> allOrder = airLineMapper.getAllOrder();

        for (OrderLine o : allOrder){
            System.out.println(o.toString());
        }
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
