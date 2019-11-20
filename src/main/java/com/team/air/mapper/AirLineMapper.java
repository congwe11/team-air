package com.team.air.mapper;

import com.team.air.bean.AirLine;
import com.team.air.bean.Flight;
import com.team.air.bean.SeatsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface AirLineMapper {

    @Select("SELECT * FROM air_line")
    public Collection<AirLine> getAllAirline();

//    @Options(useGeneratedKeys = true,keyProperty = "plane_id,seatType",keyColumn = "plane_id,seatType")
    @Select("SELECT * FROM seats_info WHERE seatType = 2")
    public Collection<SeatsInfo> getSeatsInfo();

//    @Options(useGeneratedKeys = true,keyProperty = "flight_id",keyColumn = "flight_id")
    @Select("SELECT * FROM flight WHERE status = 1")
    public Collection<Flight> getAllFlight();

}
