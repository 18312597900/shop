package com.xunfang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunfang.pojo.Pager;
import com.xunfang.pojo.ProductInfo;
import com.xunfang.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {

//    自动装配 Service
    @Autowired
    private ProductInfoService productInfoService;
//    分页查询
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(int page,int rows,ProductInfo productInfo){
//        初始化pager 分页信息对象
        Pager pager = new Pager();
        pager.setCurPage(page);
        pager.setPerPageRows(rows);

//       初始化 Map params 对象 存储多个信息
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("pager",pager);
//        获得满足条件的 总数
        int totalCount = productInfoService.count(params);
//        获取满足条件的 商品列表
        List<ProductInfo> productInfoList = productInfoService.findProductInfo(productInfo,pager);
//        创建 返回数据的 map集合 并 添加 数据
        Map<String,Object> result = new HashMap<String, Object>();
//         储存总数
        result.put("total",totalCount);
//        列表
        result.put("rows",productInfoList);
        return result;
    }


//    保存商品

    @RequestMapping(value = "/addProduct",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addProductInfo(ProductInfo productInfo, @RequestParam(value = "file",required = false) MultipartFile file,
                                 HttpServletRequest request, ModelMap model) throws JsonProcessingException {
//        处理文件
//        获取服务器文件物理路径
        String path = request.getSession().getServletContext().getRealPath("product_images");
        System.out.println(path);
//        获取文件名
//        String fileName = file.getOriginalFilename();
        String fileName =file.getOriginalFilename();
        int pointIndex = fileName.lastIndexOf(".");
        String prefixName =fileName.substring(0,pointIndex);
        String extendName = fileName.substring(pointIndex,fileName.length());
        String newFileName = prefixName+UUID.randomUUID()+extendName;
//        实例化一个文件 对象 File
        File targetFile = new File(path,newFileName);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
//        将上传的文件写道服务器的 文件里
        Map message = new HashMap();
        try{
            file.transferTo(targetFile);
            productInfo.setPic(newFileName);
//           保存 商品
            productInfoService.addProductInfo(productInfo);
            message.put("success","true");
            message.put("message","添加商品成功");
        }catch (Exception e){
            e.printStackTrace();
            message.put("success","false");
            message.put("message","添加商品失败");
        }
        String result = new ObjectMapper().writeValueAsString(message);
        return result;
    }

//    修改商品

    @RequestMapping(value = "/updateProduct",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String editProductInfo(ProductInfo productInfo, @RequestParam(value = "file",required = false) MultipartFile file,
                                 HttpServletRequest request, ModelMap model) throws JsonProcessingException {
//        处理文件
//        获取服务器文件物理路径
        String path = request.getSession().getServletContext().getRealPath("product_images");
        System.out.println(path);
//        获取文件名
        String fileName =file.getOriginalFilename();
        int pointIndex = fileName.lastIndexOf(".");
        String prefixName =fileName.substring(0,pointIndex);
        String extendName = fileName.substring(pointIndex,fileName.length());
        String newFileName = prefixName+UUID.randomUUID()+extendName;
//        实例化一个文件 对象 File
        File targetFile = new File(path,newFileName);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
//        将上传的文件写道服务器的 文件里
        Map message = new HashMap();
        try{
            file.transferTo(targetFile);
            productInfo.setPic(newFileName);
//           保存 商品
            productInfoService.editProductInfo(productInfo);
            message.put("success","true");
            message.put("message","修改商品成功");
        }catch (Exception e){
            e.printStackTrace();
            message.put("success","false");
            message.put("message","修改商品失败");
        }
        String result = new ObjectMapper().writeValueAsString(message);
        return result;
    }
//    下架商品
    @RequestMapping("/deleteProduct")
    @ResponseBody
    public Map deleteProductInfo(@RequestParam(value = "id") String id,@RequestParam(value = "flag") int flag){
        Map message = new HashMap();
//        1,2,3,4,5,6,7,   末尾有个 ,  这个 ids 最终会作为 mysql的 条件
        String ids = id.substring(0,id.length()-1);
        try{
            productInfoService.updateState(ids,flag);
            message.put("success","true");
            message.put("message","商品下架成功");
        }catch (Exception e){
            message.put("success","false");
            message.put("message","商品下架失败");
        }
        return message;
    }


//    获得在线商品
//    productinfo/getOnSaleProduct
    @RequestMapping("/getOnSaleProduct")
    @ResponseBody
    public List<ProductInfo> getOnSaleProduct(){
        List<ProductInfo> productInfoList = productInfoService.getOnSaleProduct();
        for (ProductInfo p:productInfoList
             ) {
            System.out.println(p.getPrice());
        }
       return productInfoList;
    }

//    通过id获得 商品 单价 price
    @RequestMapping("/getPriceById")
    @ResponseBody
    public Double getPriceById(String pid){
        if (pid!=null&&!pid.equals("")){
            ProductInfo productInfo = productInfoService.getProductInfoById(Integer.parseInt(pid));
            double price = productInfo.getPrice();
            return price;
        }else {
            return null;
        }
    }
}
