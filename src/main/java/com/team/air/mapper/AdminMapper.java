package com.team.air.mapper;

import com.team.air.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from ad where ad_username = #{ad_username}")
    public Admin getAdByUsername(String ad_username);

    @Select("select * from flight")
    public List<FlightPlus> getAllByFlights();

    @Select("select * from flight where status = 1 ")
    public List<FlightPlus> getNorByFlights();

    @Select("select * from flight where status != 1")
    public List<FlightPlus> getFailByFlights();

    @Select("SELECT * FROM Fleaves WHERE flight_id = #{flight_id}")
    public List<Fleave> getFleaveById(Integer flight_id);

    @Select("select * from plane")
    public List<PlanePlus> getAllByPlanes();

    @Select("select * from seats_info where plane_id = #{id}")
    public List<SeatsInfo> getSeatsInfo(Integer id);

    @Select("select * from air_line where plane_id = #{id}")
    public AirLine getAirline(Integer id);


}
