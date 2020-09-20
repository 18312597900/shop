package com.xunfang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunfang.pojo.Pager;
import com.xunfang.pojo.UserInfo;
import com.xunfang.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

//    获得 合法  /getValidUser
    @RequestMapping("/getValidUser")
    @ResponseBody
    public List<UserInfo> getValidUser(){
        List<UserInfo> userInfoList =userInfoService.getValidUser();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(0);
        userInfo.setUserName("请选择...");
        userInfoList.add(userInfo);
        return userInfoList;
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(int page,int rows,UserInfo userInfo){
        Pager pager= new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("pager",pager);
        int totalCont = userInfoService.count(params);
        List<UserInfo> userInfoList = userInfoService.findUserInfoByPage(userInfo,pager);

        Map result = new HashMap();
        result.put("total",totalCont);
        result.put("rows",userInfoList);

        return result;
    }

//    启用 禁用 客户
    @RequestMapping("/setIsEnableUser")
    @ResponseBody
    public Map<String,Object> setIsEnableUser(String uids,int flag){
        Map result = new HashMap();
        try{
            userInfoService.updateStatus(uids.substring(0,uids.length()-1),flag);
            result.put("success","true");
            result.put("message","设置成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("success","false");
            result.put("message","设置失败");
        }

        return  result;
    }
//    添加用户
    @RequestMapping(value = "/adduser",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addUserInfo(UserInfo userInfo) throws JsonProcessingException {
        Map message = new HashMap();
        try {
            userInfoService.addUserInfo(userInfo);
            message.put("success","true");
            message.put("message","添加用户成功");
        }catch (Exception e){
            e.printStackTrace();
            message.put("success","false");
            message.put("message","添加用户失败");
        }

        String result = new ObjectMapper().writeValueAsString(message);
        System.out.println(result);
        return result;
    }
}
