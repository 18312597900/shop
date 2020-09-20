package com.xunfang.dao;

import com.xunfang.dao.provider.ProductInfoDynaSqlProvider;
import com.xunfang.pojo.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface ProductInfoDao {
//    动态条件 分页查询
    @Results({
            @Result(id = true,column ="id" ,property ="id" ),
            @Result(column = "code",property = "code"),
            @Result(column = "name",property = "name"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "num",property = "num"),
            @Result(column = "price",property = "price"),
            @Result(column = "intro",property = "intro"),
            @Result(column = "status",property = "status"),
            @Result(column = "brand",property = "brand"),
            @Result(column = "tid",property = "type",
                    one = @One(select ="com.xunfang.dao.TypeDao.selectById",fetchType = FetchType.EAGER))
    })
    @SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "selectWithParam")
    public List<ProductInfo> selectBypage(Map<String,Object> params);

//    动态条件 获得总数

    @SelectProvider(type = ProductInfoDynaSqlProvider.class,method = "count")
    public int count(Map<String,Object> params);
//    添加商品
    @Insert("insert into product_info(code,name,tid,brand,pic,num,price,intro,status)" +
            "values(#{code},#{name},#{type.id},#{brand},#{pic},#{num},#{price},#{intro},#{status})")
    public void save(ProductInfo pi);
//    修改
    @Update("update product_info set code=#{code},name=#{name},tid=#{type.id},brand=#{brand},pic=#{pic},num=#{num},price=#{price},intro=#{intro},status=#{status} where id = #{id}")
    public void edit(ProductInfo pi);
//    更新状态
    @Update("update product_info set status=#{flag} where id in (${ids})")
    public void updateState(@Param("ids") String ids,@Param("flag") int flag);

//    查询在售商品列表
    @Select("select * from product_info where status = 1")
    public List<ProductInfo> getOnSaleProduct();
//    id查询
@Select("select * from product_info where id = #{id}")
    public ProductInfo getProductInfoById(int id);
}
