package com.zw.javaapi.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Entity.User_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface User_InfoMapper extends BaseMapper<User_Info> {

    List<User_Info> getUser_info(@Param(value = "user_id") String user_id);
}
