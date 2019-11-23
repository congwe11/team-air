package com.team.air.mapper;

import com.team.air.bean.AirLine;
import com.team.air.bean.Plane;
import com.team.air.bean.SeatsInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PlaneMapper {
    /**
     * 获取空闲飞机
     * 0表示空闲
     * 1表示在忙
     */

    @Select("SELECT * FROM plane WHERE plane_id = #{id}")
    public Plane getPlaneByid(Integer id);

    @Select("Select * from seats_info where plane_id = #{plane_id}")
    public List<SeatsInfo> getSeatsById(Integer plane_id);

    @Select("select * from plane where status = 0")
    public List<Plane> getEasyPlane();

    @Select("Select count(*) from plane")
    public int countPlane();

    //查 座位数
    @Select("Select seatCount from seats_info where plane_id=#{plane_id} and seatType = 1")
    public int countPS1(String plane_id);

    @Select("Select seatCount from seats_info where plane_id=#{plane_id} and seatType = 2")
    public int countPS2(String plane_id);

    @Insert("insert into air_line(plane_id,flight_id) values(#{plane_id},#{flight_id})")
    public int addAirLine(AirLine airLine);

    @Insert("insert into plane(plane_id,planeName,status) values(#{plane_id},#{planeName},#{status})")
    public int addPlane(Plane plane);

    @Insert("insert into seats_info(plane_id,seatType,seatCount,seatPrice) " +
            "values(#{plane_id},#{seatType},#{seatCount},#{seatPrice})")
    public int addSeatsInfo(SeatsInfo seatsInfo);


    @Update("update plane set planeName=#{planeName} where plane_id = #{plane_id}")
    public int updatePlane(Plane plane);

    @Update("update seats_info set seatCount=#{seatCount},seatPrice=#{seatPrice} " +
            "where plane_id = #{plane_id} and seatType = 1")
    public int upSeats1(SeatsInfo s1);

    @Update("update seats_info set seatCount=#{seatCount},seatPrice=#{seatPrice} " +
            "where plane_id = #{plane_id} and seatType = 2")
    public int upSeats2(SeatsInfo s2);

    //飞机 1状态
    @Update("update plane set status = 1 where plane_id = #{plane_id}")
    public int updatePlaneStatus1(Integer plane_id);

    //飞机 0状态
    @Update("update plane set status = 0 where plane_id = #{plane_id}")
    public int updatePlaneStatus0(Integer plane_id);
}
