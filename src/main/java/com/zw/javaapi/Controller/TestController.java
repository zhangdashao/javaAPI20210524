package com.zw.javaapi.Controller;



import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Enum.UserLoginToken;
import com.zw.javaapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Test")
public class TestController {
    @Autowired
    private  com.zw.javaapi.Service.UserService UserService;

    @RequestMapping("/ceshi")
    @UserLoginToken
    public User shouye(Model model){
        List<User>userlist=new ArrayList<>();
        try {
            userlist=UserService.UserService(null,null,null);

        }catch (Exception e){
            throw new DescribeException("无token，请重新登录！",200);
        }
        return userlist.get(0);
    }

}
