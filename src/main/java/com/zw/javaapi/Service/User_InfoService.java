package com.zw.javaapi.Service;


import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Entity.User_Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class User_InfoService {
    @Autowired
    private com.zw.javaapi.Mapper.User_InfoMapper User_InfoMapper;

    public List<User_Info> User_InfoService(String user_id){
       List<User_Info> list=new ArrayList();
        list= User_InfoMapper.getUser_info(user_id);
        return list;
    }

    public void User_info(User_Info user_Info){
        User_InfoMapper.updateById(user_Info);
    }
}
