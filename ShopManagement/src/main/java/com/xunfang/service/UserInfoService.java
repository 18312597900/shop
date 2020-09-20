package com.xunfang.service;

import com.xunfang.pojo.Pager;
import com.xunfang.pojo.ProductInfo;
import com.xunfang.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
//    id
    public UserInfo getUserInfoById(int id);
//    合法
    public List<UserInfo> getValidUser();

//    分页
    public List<UserInfo> findUserInfoByPage(UserInfo userInfo, Pager pager);
//    总数
    public int count(Map<String,Object> params);
//    改变状态
    public int updateStatus(String ids,int flag);
//    添加用户
    public void addUserInfo(UserInfo userInfo);
}
