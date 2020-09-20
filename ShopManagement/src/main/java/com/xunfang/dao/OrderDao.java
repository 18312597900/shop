package com.xunfang.dao;

import com.xunfang.dao.provider.OrderInfoDynaSqlProvider;
import com.xunfang.dao.provider.ProductInfoDynaSqlProvider;
import com.xunfang.pojo.OrderDetail;
import com.xunfang.pojo.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface OrderDao {
//    分页查询
    @Results({
            @Result(column = "uid",property = "ui",
                    one = @One(select = "com.xunfang.dao.UserDao.selectById",fetchType = FetchType.EAGER))
    })
    @SelectProvider(type = OrderInfoDynaSqlProvider.class,method = "selectWithParam")
    public List<OrderInfo> getOrderInfoByPage(Map<String,Object> params);

//    总数
    @SelectProvider(type = OrderInfoDynaSqlProvider.class,method = "count")
    public int count(Map<String,Object> params);

//    通过id返回对象
    @Results({
            @Result(column = "uid",property = "ui",
                    one = @One(select = "com.xunfang.dao.UserDao.selectById",
                            fetchType = FetchType.EAGER))
    })
    @Select("select * from order_info where id = #{id} ")
    public OrderInfo getOrderInfoById(int id);

//    通过订单id 返回 明细对象
    @Results({
            @Result(column ="pid" ,property ="pi",
                    one = @One(select = "com.xunfang.dao.ProductInfoDao.getProductInfoById",fetchType = FetchType.EAGER))
    })
    @Select("select * from order_detail where oid = #{oid}")
    public List<OrderDetail> getOrderDetailByOrderInfoId(int oid);

//    保存 订单主表
    @Insert("insert into order_info(uid,status,ordertime,orderprice)" +
            "values(#{uid},#{status},#{ordertime},#{orderprice})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int saveOrder(OrderInfo oi);

//    保存 订单明细
    @Insert("insert into order_detail(oid,pid,num)" +
            "values(#{oid},#{pid},#{num})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int saveDetail(OrderDetail od);

//    删除主表
    @Delete("delete from order_info where id = #{id}")
    public int deleteOrderInfo(int id);

//    删除明细表
    @Delete("delete from order_detail where oid = #{id}")
    public int deleteDetail(int id);
}
