package com.xunfang.service;

import com.xunfang.pojo.AdminInfo;

public interface AdminInfoService {
//    登录
    public AdminInfo login(AdminInfo ai);

//    通过管理员id 获得管理员和他的 功能集合
    public AdminInfo getAdminInfoAndFunctions(int id);

    //  注册管理员
    public int insertuser(AdminInfo ai);
    public int insertpowers(int id);
}
