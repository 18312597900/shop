package com.xunfang.dao;

import com.xunfang.pojo.AdminInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.mapping.FetchType;

public interface AdminInfoDao {

//    登录 根据账号和密码 查询管理员信息
    @Select("select * from admin_info where name=#{name} and pwd=#{pwd}")
    public AdminInfo selectByNameAndPwd(AdminInfo ai);

//    通过根据管理员的id 获取到 对应的 功能集合
    @Results({
            @Result(id=true,column = "id",property ="id" ),
            @Result(column = "name",property = "name"),
            @Result(column = "pwd",property = "pwd"),
            @Result(column = "id",property = "fs",
                    many = @Many(select = "com.xunfang.dao.FunctionsDao.selectByAdminInfoId",fetchType = FetchType.EAGER ))
    })
    @Select("select * from admin_info where id=#{id}")
    public AdminInfo selectByAdminId(int id);


    @Insert("insert into admin_info(name,pwd,email) values(#{name},#{pwd},#{email})")
    public int insertuser(AdminInfo ai);

    @Insert("insert into powers(aid,fid) values(#{id},1),(#{id},2),(#{id},3),(#{id},4)," +
            "(#{id},5),(#{id},6),(#{id},7),(#{id},8),(#{id},9),(#{id},11)")
    public int insertpowers(int id);
}
