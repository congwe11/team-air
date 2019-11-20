package com.team.air.service;

import com.team.air.bean.AirLine;
import com.team.air.bean.Flight;
import com.team.air.bean.SeatsInfo;
import com.team.air.mapper.AirLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AirLineService {

    @Autowired
    AirLineMapper airLineMapper;

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
}
