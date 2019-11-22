package com.team.air.mapper;

import com.team.air.bean.OrderLine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from orderLine where status = 2")
    public List<OrderLine> getROrders();

    @Select("select * from orderLine")
    public List<OrderLine> getAllOrder();


    @Update("update orderLine set status = 1 where order_id = #{id}")
    public int refuse(Integer id);

    @Update("update orderLine set status = 3 where order_id = #{id}")
    public int approve(Integer id);
}
