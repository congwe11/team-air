package com.team.air.mapper;

import com.team.air.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AirLineMapper {

    /**
     * 增加记录都在这
     *
     * @param orderLine
     * @return
     */
    //添加订单
    @Insert("insert ignore into orderLine(order_id,user_id,time,all_price,status)" +
            "values(#{order_id},#{user_id},#{time},#{all_price},#{status})")
    public int addOrder(OrderLine orderLine);
    //添加机票
    @Options(useGeneratedKeys = true,keyProperty = "ticket_id")
    @Insert("INSERT INTO ticketLine(" +
            "passenger_id," +
            "order_id," +
            "start_time," +
            "end_time," +
            "origin," +
            "destination," +
            "FirstPrice," +
            "sType) " +
            "values(#{passenger_id},#{order_id},#{start_time},#{end_time},#{origin},#{destination},#{FirstPrice},#{sType})")
    public int addTicket(TicketLine ticket);

    //添加乘客
    @Insert("insert into passengers(passenger_id,flight_id,user_id,name,ID,sex,status,numbers)" +
            "values(#{passenger_id},#{flight_id},#{user_id},#{name},#{ID},#{sex},#{status},#{numbers})") //错误在这
    public int addPser(Passengers passengers);

    /**
     * 修改记录都在这里
     *
     * @param order_id
     * @return
     */
    @Update("update orderLine set status = 2 where order_id = #{ordee_id}")
    public int refund2(String order_id);

    @Update("update orderLine set status = 3 where order_id = #{ordee_id}")
    public int refund3(String order_id);

    @Update("update passengers set status = 1 where passenger_id = #{id}")
    public int updatePser(String id);

    @Update("update orderLine set status = 1 where order_id = #{id}")
    public int updateOder(String id);

    //修改余票数
    @Update("update Fleaves set leaveTickets=#{leaveTickets} where flight_id=#{flight_id} and seatType = #{seatType}")
    public int updateFleave(Fleave fleave);


    /**
     * 查找记录都在这
     * @param id
     * @return
     */

    @Select("select * from ticketLine where order_id = #{id}")
    public TicketLine getTicketByOrderId(String id);

    @Select("select * from orderLine")
    public List<OrderLine> getAllOrder();

    @Select("Select * from passengers where passenger_id = #{id}")
    public Passengers getPserById(String id);

    @Select("select * from orderLine where user_id = #{id}")
    public List<OrderLine> getOrderByUser(Integer id);

    @Select("select * from orderLine where order_id = #{id}")
    public OrderLine getOrderById(String id);

    @Select("SELECT count(*) FROM orderLine")
    public int countOrderline();

    @Select("SELECT count(*) FROM ticketLine")
    public int countTicketline();

    @Select("SELECT count(*) FROM passengers")
    public int countPser();

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

    @Select("SELECT * FROM Fleaves WHERE flight_id = #{flight_id} and seatType = #{seatType}")
    public Fleave getLeaveByOne(String flight_id,String seatType);





}
