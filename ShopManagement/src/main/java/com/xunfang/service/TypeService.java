package com.xunfang.service;

import com.xunfang.pojo.Type;

import java.util.List;

public interface TypeService {
//    获取所有类型
    public List<Type> getAll();

//添加商品类型
    public int addType(Type type);

//    更新商品类型
    public int updateType(Type type);
}
