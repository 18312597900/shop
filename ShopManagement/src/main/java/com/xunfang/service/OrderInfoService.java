package com.xunfang.service;

import com.xunfang.pojo.OrderDetail;
import com.xunfang.pojo.OrderInfo;
import com.xunfang.pojo.Pager;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {
//   分页
    public List<OrderInfo> getOrderInfo(OrderInfo orderInfo, Pager pager);
//    总数
    public int count(Map<String,Object> params);
//    id获取 订单
    public OrderInfo getOrderInfoById(int id);
//    订单id 获取 明细
    public List<OrderDetail> getOrderDetailByOid(int oid);

//    保存订单主表
    public int saveOrderInfo(OrderInfo oi);

//    保存订单明细
    public int saveDetail(OrderDetail od);
//    删除
    public int deleteOrder(int id);
}
