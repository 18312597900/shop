package com.xunfang.service.impl;

import com.xunfang.dao.AdminInfoDao;
import com.xunfang.pojo.AdminInfo;
import com.xunfang.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    private AdminInfoDao adminInfoDao;
    @Override
    public AdminInfo login(AdminInfo ai) {
        return adminInfoDao.selectByNameAndPwd(ai);
    }

    @Override
    public AdminInfo getAdminInfoAndFunctions(int id) {
        return adminInfoDao.selectByAdminId(id);
    }

    @Override
    public int insertuser(AdminInfo ai){
        int result;
        try{
            result = adminInfoDao.insertuser(ai);
        }catch (Exception e){
            result=0;
        }
        return result;
    }

    @Override
    public int insertpowers(int id){
        int result2;
        try{
            result2 = adminInfoDao.insertpowers(id);
        }catch (Exception e){
            result2=0;
        }
        return result2;
    }
}
