package com.team.air.service;

import com.team.air.bean.*;
import com.team.air.mapper.FlightMapper;
import com.team.air.mapper.PlaneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    PlaneMapper planeMapper;

    @Autowired
    FlightMapper flightMapper;

    @Transactional
    public void delFlight(Integer id){
        int i = -1;
        int j = -1;
        int k = -1;
        int n = -1;
        i = flightMapper.upDelFlight(id);
        j = flightMapper.delFl(id);
        AirLine airLine = flightMapper.getAirline(id);
        k = planeMapper.updatePlaneStatus0(airLine.getPlane_id());
        n = flightMapper.delAirline(id);
        if( i != 1 && j !=1 && k !=1 && n !=1){
            throw new RuntimeException("删除 抛异常了");
        }
    }

    public void upPlane(Plane plane,SeatsInfo s1,SeatsInfo s2){
        int i = -1;
        int j = -1;
        int k = -1;

        i = planeMapper.updatePlane(plane);
        j = planeMapper.upSeats1(s1);
        k = planeMapper.upSeats2(s2);
        if( i != 1 && j !=1 && k !=1){
            throw new RuntimeException("插入 抛异常了");
        }
    }

    @Transactional
    public void oneFlightFinish(Flight flight, Fleave f1, Fleave f2, AirLine airLine,String plane_id){

        int i = -1;
        int j = -1;
        int k = -1;
        int n = -1;
        int m = -1;
        i = flightMapper.addFlight(flight);
        j = flightMapper.addFl(f1);
        k = flightMapper.addFl(f2);
        n = flightMapper.addAirLine(airLine);
        m = planeMapper.updatePlaneStatus1(Integer.parseInt(plane_id));
        if( i != 1 && j !=1 && k !=1 && n !=1 && m !=1){
            throw new RuntimeException("插入 抛异常了");
        }
    }

    @Transactional
    public void onePlaneFinish(Plane plane,SeatsInfo s1,SeatsInfo s2){

        int i = -1;
        int j = -1;
        int k = -1;
        i = planeMapper.addPlane(plane);
        j = planeMapper.addSeatsInfo(s1);
        k = planeMapper.addSeatsInfo(s2);
        if( i != 1 && j !=1 && k !=1){
            throw new RuntimeException("插入 抛异常了");
        }
    }

}
