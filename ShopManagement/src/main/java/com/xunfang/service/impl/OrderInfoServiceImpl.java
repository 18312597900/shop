package com.xunfang.service.impl;

import com.xunfang.dao.OrderDao;
import com.xunfang.pojo.OrderDetail;
import com.xunfang.pojo.OrderInfo;
import com.xunfang.pojo.Pager;
import com.xunfang.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<OrderInfo> getOrderInfo(OrderInfo orderInfo, Pager pager) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("orderInfo",orderInfo);
        int recordCount = orderDao.count(params);
        if (recordCount>0){
            params.put("pager",pager);
        }
//         分页
        return orderDao.getOrderInfoByPage(params);
    }

    @Override
    public int count(Map<String, Object> params) {
        return orderDao.count(params);
    }

    @Override
    public OrderInfo getOrderInfoById(int id) {
        return orderDao.getOrderInfoById(id);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOid(int oid) {
        return orderDao.getOrderDetailByOrderInfoId(oid);
    }

    @Override
    public int saveOrderInfo(OrderInfo oi) {
        return orderDao.saveOrder(oi);
    }

    @Override
    public int saveDetail(OrderDetail od) {
        return orderDao.saveDetail(od);
    }

    @Override
    public int deleteOrder(int id) {
//        先删除 订单明细 在删除 订单主表  因为存在外键约束
        try {
            orderDao.deleteDetail(id);
            orderDao.deleteOrderInfo(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
