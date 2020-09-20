package com.xunfang.dao;

import com.xunfang.pojo.Functions;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FunctionsDao {
//    通过 管理员 id 获得 权限里的 fid 再通过 fid 最终获得 function 数据
    @Select("select * from functions where id in(select fid from powers where aid=#{aid})")
    public List<Functions> selectByAdminInfoId(int aid);
}
