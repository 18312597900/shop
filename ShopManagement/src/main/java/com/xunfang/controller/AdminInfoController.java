package com.xunfang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunfang.pojo.AdminInfo;
import com.xunfang.pojo.Functions;
import com.xunfang.pojo.TreeNode;
import com.xunfang.service.AdminInfoService;
import com.xunfang.util.TreeBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admininfo")
//@SessionAttributes(value = {"admin"})
public class AdminInfoController {
    @Autowired
    private AdminInfoService adminInfoService;
//    管理员登录验证

    @RequestMapping(value = "/login",produces ="text/html;charset=utf-8" )
    @ResponseBody
    public String login(@RequestBody AdminInfo ai, /*ModelMap model*/ HttpSession session) throws JsonProcessingException {
        AdminInfo adminInfo = adminInfoService.login(ai);
        System.out.println(adminInfo);
        System.out.println(ai);
        if (adminInfo!=null&&!adminInfo.getName().equals("")){
//            System.out.println(adminInfo.toString());
//          验证通过后 还需要 验证 是否有  分配权限
//          需要通过 admin获得 function
            if(adminInfoService.getAdminInfoAndFunctions(adminInfo.getId()).getFs().size()>0){
//                System.out.println(adminInfoService.getAdminInfoAndFunctions(adminInfo.getId()).toString());
//                将admininfo 存入 session
//                model.addAttribute("admin",adminInfo);
                session.setAttribute("admin",adminInfo);
//            第一种返回 json格式的字符串类型数据
                return "{\"success\":\"true\",\"message\":\"登录成功\"}";
            }else {
                return "{\"success\":\"false\",\"message\":\"没有分配权限\"}";
            }
        }else {
//            第二种返回
//            return "{\"success\":\"false\",\"message\":\"账号密码错误\"}";
            Map map = new HashMap();
            map.put("success","false");
            map.put("message","账号密码错误");
            String result = new ObjectMapper().writeValueAsString(map);
            return result;
        }
    }

    //普通用户登录
    @RequestMapping(value = "/userlogin",produces ="text/html;charset=utf-8" )
    @ResponseBody
    public String userlogin(@RequestBody AdminInfo ai, /*ModelMap model*/ HttpSession session) throws JsonProcessingException {
        AdminInfo adminInfo = adminInfoService.login(ai);
        System.out.println(adminInfo);
        System.out.println(ai);
        if (adminInfo!=null&&!adminInfo.getName().equals("")){
//            System.out.println(adminInfo.toString());
//            第一种返回 json格式的字符串类型数据
            System.out.println(ai);
            return "{\"success\":\"true\",\"message\":\"登录成功\"}";

        }else {
//            第二种返回
            return "{\"success\":\"false\",\"message\":\"账号密码错误\"}";
        }
    }

//    返回节点树
    @RequestMapping("/getTree")
    @ResponseBody
    public List<TreeNode> getTree(int adminid){
//        根据 传到服务其的id  获得 admininfo对象和它的 functions集合
        AdminInfo adminInfo = adminInfoService.getAdminInfoAndFunctions(adminid);
        List<Functions> functions = adminInfo.getFs();
        List<TreeNode> nodes = new ArrayList<TreeNode>();
//        对Liset<Functions> 排序
        Collections.sort(functions);
//       functions 转化 为 TreeNode
        for (Functions f : functions
        ) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(f.getId());
            treeNode.setFid(f.getParentid());
            treeNode.setText(f.getName());
            nodes.add(treeNode);
        }
//        调用工具 TreeBuild 的 buildtree方法，给每个treeNode的集合 添加 treeNode
        List<TreeNode> treeNodeList = TreeBuild.buildtree(nodes,0);
        for (TreeNode tree:treeNodeList
             ) {
//            System.out.println(tree.toString());
        }
        return treeNodeList;
    }

//    注销
    @RequestMapping("/logout")
    public String out(/*SessionStatus status*/ HttpSession session){
       /* status.setComplete();
        System.out.println(status.isComplete());*/
       session.invalidate();
        return "admin_login";
    }
}
