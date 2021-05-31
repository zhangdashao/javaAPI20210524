package com.zw.javaapi.Service;


import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserService {
    @Autowired
    private com.zw.javaapi.Mapper.UserMapper UserMapper;

    public List<User> UserService(String user_id,String username,String password){
        List<User>userlist=new ArrayList<>();
        try {
            String sql="select * from user where 1=1 ";
            if(user_id!=null&&!user_id.isEmpty()){
                sql+=" and id='"+user_id+"'";
            }
            if(username!=null&&!username.isEmpty()){
                sql+=" and username='"+username+"'";
            }
            if(password!=null&&!password.isEmpty()){
                sql+=" and password='"+password+"'";
            }
            userlist=UserMapper.getsql_list(sql);
        }catch (Exception e){
            throw new DescribeException("系统错误！",401);
        }
        return userlist;
    }

}
