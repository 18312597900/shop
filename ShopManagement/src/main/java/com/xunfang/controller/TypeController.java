package com.xunfang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunfang.pojo.Type;
import com.xunfang.service.TypeService;
import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
//    返回所有类型
    @RequestMapping("/getType/{flag}")
    @ResponseBody
    public List<Type> getType(@PathVariable("flag") int flag){
        List<Type> typeList = typeService.getAll();
        if (flag==1){
            Type type = new Type() ;
            type.setName("请选择...");
            type.setId(0);
            typeList.add(type);
        }
        return typeList;
    }

//    添加
    @RequestMapping(value = "/addType",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addType(Type type) throws JsonProcessingException {
        Map map = new HashMap();
        try{
            typeService.addType(type);
            map.put("success","true");
            map.put("message","添加成功");

        }catch (Exception e){
            map.put("success","false");
            map.put("message","添加失败");
        }
        String  result = new ObjectMapper().writeValueAsString(map);
        System.out.println(result);
        return result;
    }
//    修改
    @RequestMapping(value = "/updateType",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String updateType(Type type) throws JsonProcessingException {
        Map map = new HashMap();
        try{
            typeService.updateType(type);
            map.put("success","true");
            map.put("message","修改成功");

        }catch (Exception e){
            map.put("success","false");
            map.put("message","修改失败");
        }
        String  result = new ObjectMapper().writeValueAsString(map);
        System.out.println(result);
        return result;
    }
}
