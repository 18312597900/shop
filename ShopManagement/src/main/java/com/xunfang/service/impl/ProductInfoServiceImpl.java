package com.xunfang.service.impl;

import com.xunfang.dao.ProductInfoDao;
import com.xunfang.pojo.Pager;
import com.xunfang.pojo.ProductInfo;
import com.xunfang.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public List<ProductInfo> findProductInfo(ProductInfo productInfo, Pager pager) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("productInfo",productInfo);
        int recordCount = productInfoDao.count(params);
        if(recordCount>0){
            params.put("pager",pager);
        }
//        分页查询
        return  productInfoDao.selectBypage(params);
    }

    @Override
    public int count(Map<String, Object> params) {
        return productInfoDao.count(params);
    }

    @Override
    public void addProductInfo(ProductInfo pi) {
        productInfoDao.save(pi);
    }

    @Override
    public void editProductInfo(ProductInfo pi) {
        productInfoDao.edit(pi);
    }

    @Override
    public void updateState(String ids, int flag) {
        productInfoDao.updateState(ids,flag);
    }

    @Override
    public ProductInfo getProductInfoById(int id) {
        return productInfoDao.getProductInfoById(id);
    }

    @Override
    public List<ProductInfo> getOnSaleProduct() {
        return productInfoDao.getOnSaleProduct();
    }
}
