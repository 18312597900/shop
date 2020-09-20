package com.xunfang.dao;

import com.xunfang.dao.provider.UserInfoDynaSqlProvider;
import com.xunfang.pojo.ProductInfo;
import com.xunfang.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDao {
//    id查询
    @Select("select * from user_info where id = #{id}")
    public UserInfo selectById(int id);
//    合法用户
    @Select("select * from user_info where status = 1")
    public List<UserInfo> getValidUser();

//    动态条件分页查询
    @SelectProvider(type = UserInfoDynaSqlProvider.class,method = "selectWithParam")
    public List<UserInfo> selectBypage(Map<String,Object> params);

//    获取满足条件的 总数
    @SelectProvider(type = UserInfoDynaSqlProvider.class,method = "count")
    public int count(Map<String,Object> params);

//     启用禁用 用户
    @Update("update user_info set status = #{flag} where id in(${ids})")
    public int updateStatus(@Param("ids") String ids,@Param("flag") int flag);

//    添加用户
        @Insert("insert into user_info(userName,password,realName,sex,address,email,regDate,status)" +
        "values(#{userName},#{password},#{realName},#{sex},#{address},#{email},#{regDate},#{status})")
    public void save (UserInfo userInfo);
}
