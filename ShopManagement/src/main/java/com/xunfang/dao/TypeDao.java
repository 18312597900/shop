package com.xunfang.dao;

import com.xunfang.pojo.Type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TypeDao {
//    查找所有
    @Select("select * from type")
    public List<Type> selectAll();
//    查id
    @Select("select * from type where id = #{id}")
    public Type selectById(int id);
//    增
    @Insert("insert into type(name) values(#{name})")
//            获取自增主键的值          自动注入实体类id属性
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public int add(Type type);
//    修改
    @Update("update type set name = #{name} where id=#{id}")
    public int update(Type type);
}
