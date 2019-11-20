package com.team.air.service;

import com.team.air.bean.AirLine;
import com.team.air.bean.Flight;
import com.team.air.bean.SeatsInfo;
import com.team.air.mapper.AirLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AirLineService {

    @Autowired
    AirLineMapper airLineMapper;

    public Flight getFlightById(Integer id){
        return airLineMapper.getFlightById(id);
    }


    //获取飞机id，为获取机票价格做准备
    public List<Double> getEachFlight(List<Flight> flights){

        List<Double> price = new ArrayList<Double>();
        List<Integer> plane = new ArrayList<Integer>();
        for(int i=0; i<flights.size(); i++){
            plane.add(airLineMapper.getEachFlight(flights.get(i).getFlight_id()));
            System.out.println(plane.get(i));
        }
        for (int i=0; i<plane.size(); i++){
            price.add(airLineMapper.getEachPrice(plane.get(i)));
            System.out.println("机票价格：："+price.get(i));
        }

        return price;
    }

    public Collection<AirLine> getAllAirline(){

        Collection<AirLine> allAirline = airLineMapper.getAllAirline();

        return allAirline;
    }


    public Collection<SeatsInfo> getSeatsInfo(){

        Collection<SeatsInfo> seatsInfo = airLineMapper.getSeatsInfo();

        return seatsInfo;
    }

    public Collection<Flight> getallFlight(){
        return airLineMapper.getAllFlight();
    }

    public List<Flight> getSearchFlight(Flight flight){
        return airLineMapper.getSearchFlight(flight);
    }
}
