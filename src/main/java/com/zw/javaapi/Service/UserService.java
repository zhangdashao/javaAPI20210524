package com.zw.javaapi.Service;


import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private com.zw.javaapi.Mapper.UserMapper UserMapper;

    public List<User> UserService(){
        List<User>userlisst=new ArrayList<>();

        String sql="select * from user";
        userlisst=UserMapper.getsql_list(sql);


        return userlisst;
    }

}
