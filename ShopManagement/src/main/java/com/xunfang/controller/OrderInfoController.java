package com.xunfang.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonParseException;
import com.xunfang.pojo.OrderDetail;
import com.xunfang.pojo.OrderInfo;
import com.xunfang.pojo.Pager;
import com.xunfang.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    //    分页 /list   前端会传 page rows  需要返回 total rows
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(int page, int rows, OrderInfo orderInfo){

        System.out.println(orderInfo.toString());
//        初始化 pager对象
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);

//      初始化 params 对象
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("pager",pager);
//        返回 总数
        int totalCount = orderInfoService.count(params);
        System.out.println("总数"+totalCount);
//        获取 满足条件的 列表
        List<OrderInfo> orderInfoList = orderInfoService.getOrderInfo(orderInfo,pager);
        System.out.println("order集合"+orderInfoList.toString());
//      初始化 result 对象 保存 查询结果
        Map result = new HashMap();
        result.put("total",totalCount);
        result.put("rows",orderInfoList);
        return result;
    }


//通过 oid  获取到  orderinfo对象 其中要包含 usreinfo对象
//    orderinfo/getOrderInfo?oid=
    @RequestMapping("/getOrderInfo")
    public String getOrderInfo(String oid, ModelMap model){
        OrderInfo orderInfo = orderInfoService.getOrderInfoById(Integer.parseInt(oid));
//        将 orderinfo对象 存入 request 区域中
        model.addAttribute("oi",orderInfo);
        return "orderdetail";
    }

    //    订单明细   orderinfo/getOrderDetails?oid=${requestScope.oi.id }

    @RequestMapping("/getOrderDetails")
    @ResponseBody
    public List<OrderDetail> getOrderDetailByOid(String oid){
        List<OrderDetail> orderDetailList =  orderInfoService.getOrderDetailByOid(Integer.parseInt(oid));
        for (OrderDetail detail:orderDetailList
             ) {
//            设置单价
            detail.setPrice(detail.getPi().getPrice());
//            设置小计
            detail.setTotalprice(detail.getNum()*detail.getPrice());
        }
        return orderDetailList;
    }

    //    保存  commitOrder
    @RequestMapping("/commitOrder")
    @ResponseBody
    public String commitOrder(String inserted,String orderinfo) throws JsonParseException, IOException, JsonMappingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
//            过滤 javabean中 没有的 属性
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//          将json字符传的 orderinfo数组 转化成 OrderInfo
            OrderInfo oi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
//            保存 订单主表
            orderInfoService.saveOrderInfo(oi);
            System.out.println(oi.getId());

//            将json字符串 转化成 detail list集合
//            Type 'jdk.internal.org.objectweb.asm.TypeReference' does not have type parameters
            List<OrderDetail> orderDetailList = mapper.readValue(inserted,new TypeReference<ArrayList<OrderDetail>>(){});
            for (OrderDetail od:orderDetailList
                 ) {
                od.setOid(oi.getId());
                orderInfoService.saveDetail(od);
            }
//            成功
            return "success";
        }catch (Exception e){
//            失败
            return "false";
        }
    }

//    删除  deleteOrder
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public Map<String,Object> deletOrder(String oids){
        Map<String,Object> result = new HashMap<String, Object>();
        try{
            String [] ids= oids.split(",");
            for (String id:ids
                 ) {
                orderInfoService.deleteOrder(Integer.parseInt(id));
            }
            result.put("success","true");
            result.put("message","删除成功");
        }catch (Exception e){
            result.put("seccess","false");
            result.put("message","删除失败");
        }
        return  result;
    }
}
