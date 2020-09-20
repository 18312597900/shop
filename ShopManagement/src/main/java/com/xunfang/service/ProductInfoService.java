package com.xunfang.service;

import com.xunfang.pojo.Pager;
import com.xunfang.pojo.ProductInfo;

import java.util.List;
import java.util.Map;

public interface ProductInfoService {
//    分页显示
    public List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager);
//    商品计数
    public int count(Map<String,Object> params);
//    添加
    public void addProductInfo(ProductInfo pi);
////    修改
    public void editProductInfo(ProductInfo pi);
//    更新状态
    public void updateState(String ids,int flag);
//    id查询
    public ProductInfo getProductInfoById(int id);

//    获得在售商品
    public List<ProductInfo> getOnSaleProduct();
}
