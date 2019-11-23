package com.team.air.mapper;

import com.team.air.bean.AirLine;
import com.team.air.bean.Fleave;
import com.team.air.bean.Flight;
import com.team.air.bean.Plane;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FlightMapper {

    @Insert("insert into flight(flight_id,origin,destination,start_time,end_time,status)" +
            "values(#{flight_id},#{origin},#{destination},#{start_time},#{end_time},#{status})")
    public int addFlight(Flight flight);

    @Insert("insert into Fleaves(flight_id,seatType,leaveTickets)" +
            "values(#{flight_id},#{seatType},#{leaveTickets})")
    public int addFl(Fleave fleave);

    @Insert("insert into air_line(flight_id,plane_id) " +
            "values(#{flight_id},#{plane_id})")
    public int addAirLine(AirLine airLine);

    @Insert("insert into plane(plane_id,planeName,status) values(#{plane_id},#{planeName},#{status})")
    public int addPlane(Plane plane);

    @Update("update flight set origin=#{origin},destination=#{destination},start_time=#{start_time},end_time=#{end_time}" +
            "where flight_id=#{flight_id}")
    public int upFliht(Flight flight);

    @Update("update flight set status=2 where flight_id = #{id}")
    public int upDelFlight(Integer id);


    @Delete("delete from Fleaves where flight_id = #{id}")
    public int delFl(Integer id);


    @Delete("delete from air_line where flight_id = #{id}")
    public int delAirline(Integer id);


    @Select("select count(*) from flight")
    public int countFliht();

    @Select("select * from flight where flight_id = #{id}")
    public Flight getFlightById(Integer id);

    @Select("select * from air_line where flight_id = #{id}")
    public AirLine getAirline(Integer id);


}
