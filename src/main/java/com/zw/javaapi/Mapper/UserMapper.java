package com.zw.javaapi.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.javaapi.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getsql_list(@Param(value="sqlStr") String sqlStr);

}
