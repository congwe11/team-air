package com.team.air.mapper;

import com.team.air.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

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


    @Select(value = {"<script>" +
            "SELECT * FROM flight" +
            "<where>" +
            "<if test='origin != null'> and origin = #{origin} </if>" +
            "<if test= 'destination != null'> and destination = #{destination} </if>" +
            "<if test= 'start_time !=null'> and start_time >= #{start_time} </if>" +
            "and status = 1" +
            "</where>" +
            "</script>"
    })
    public List<Flight> getSearchFlight(Flight flight);

    @Select("SELECT plane_id FROM air_line WHERE flight_id = #{flight}")
    public Integer getEachFlight(Integer flight);

    @Select("SELECT seatPrice FROM seats_info WHERE plane_id = #{plane} and seatType = 2")
    public Double getEachPrice(Integer plane);


    /**
     * 航线信息页面需要的对象
     * 1、flight
     * 2、plane
     * 3、air_line
     * 4、seat_info
     * 5、Fleave
     *
     */
    @Select("SELECT * FROM flight WHERE flight_id = #{id}")
    public Flight getFlightById(Integer id);

    @Select("SELECT * FROM plane WHERE plane_id = #{id}")
    public Plane getPlaneByid(Integer id);

    //得到与flight_id 相对应的 plane_id
    @Select("SELECT plane_id FROM air_line WHERE flight_id = #{flight_id}")
    public Integer getAirLineById(Integer flight_id);

    //座位信息
    @Select("SELECT * FROM seats_info WHERE plane_id = #{plane_id}")
    public List<SeatsInfo> getSeatsInfoByPlaneId(Integer plane_id);

    //余票
    @Select("SELECT * FROM Fleaves WHERE flight_id = #{flight_id}")
    public List<Fleave> getLeaveByFlightId(Integer flight_id);


}
