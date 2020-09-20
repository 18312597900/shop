package com.xunfang.service.impl;

import com.xunfang.dao.UserDao;
import com.xunfang.pojo.Pager;
import com.xunfang.pojo.UserInfo;
import com.xunfang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserInfo getUserInfoById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public List<UserInfo> getValidUser() {
        return userDao.getValidUser();
    }

    @Override
    public List<UserInfo> findUserInfoByPage(UserInfo userInfo, Pager pager) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("pager",pager);
        int coreCont = userDao.count(params);
        if (coreCont>0){
            params.put("userInfo",userInfo);
        }
        return userDao.selectBypage(params);
    }

    @Override
    public int count(Map<String, Object> params) {
        return userDao.count(params);
    }

    @Override
    public int updateStatus(String ids, int flag) {
        return userDao.updateStatus(ids, flag);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userDao.save(userInfo);
    }
}
