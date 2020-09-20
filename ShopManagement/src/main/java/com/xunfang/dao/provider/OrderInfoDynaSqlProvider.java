package com.xunfang.dao.provider;

import com.xunfang.pojo.OrderInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class OrderInfoDynaSqlProvider {
//    分页动态条件查询
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("order_info");
                if (params.get("orderInfo")!=null){
                    OrderInfo orderInfo = (OrderInfo) params.get("orderInfo");
                    if (orderInfo.getId()!=null&&orderInfo.getId()>0){
                        WHERE(" id=#{orderInfo.id} ");
                    }else {

                        if (orderInfo.getStatus()!=null&&!orderInfo.getStatus().equals("请选择")){
                            WHERE(" status=#{orderInfo.status} ");
                        }
                        if (orderInfo.getUid()>0){
                            WHERE(" uid=#{orderInfo.uid} ");
                        }
                        if (orderInfo.getOrderTimeFrom()!=null&&!orderInfo.getOrderTimeFrom().equals("")){
                            WHERE(" ordertime >= #{orderInfo.orderTimeFrom} ");
                        }
                        if (orderInfo.getOrderTimeTo()!=null&&!orderInfo.getOrderTimeTo().equals("")){
                            WHERE(" ordertime < #{orderInfo.orderTimeTo} ");
                        }
                    }
                }
            }
        }.toString();
        if (params.get("pager")!=null){
            sql+=" limit #{pager.firstLimitParam},#{pager.perPageRows} ";
        }
        return sql;
    }

//    总数
    public String count(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM("order_info");
                if (params.get("orderInfo")!=null){
                    OrderInfo orderInfo = (OrderInfo) params.get("orderInfo");
                    if (orderInfo.getId()!=null&&orderInfo.getId()>0){
                        WHERE(" id=#{orderInfo.id} ");
                    }else {

                        if (orderInfo.getStatus()!=null&&!orderInfo.getStatus().equals("请选择")){
                            WHERE(" status=#{orderInfo.status} ");
                        }
                        if (orderInfo.getUid()>0){
                            WHERE(" uid=#{orderInfo.uid} ");
                        }
                        if (orderInfo.getOrderTimeFrom()!=null&&!orderInfo.getOrderTimeFrom().equals("")){
                            WHERE(" ordertime >= #{orderInfo.orderTimeFrom}");
                        }
                        if (orderInfo.getOrderTimeTo()!=null&&!orderInfo.getOrderTimeTo().equals("")){
                            WHERE(" ordertime < #{orderInfo.orderTimeTo} ");
                        }
                    }
                }
            }
        }.toString();
    }
}
