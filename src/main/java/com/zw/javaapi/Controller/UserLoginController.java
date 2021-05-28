package com.zw.javaapi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Service.UserService;
import com.zw.javaapi.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zw.javaapi.Utils.JWTUtils.getToken;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private com.zw.javaapi.Service.UserService UserService;

    @RequestMapping ("/ceshi")
    public JSONObject login(String username, String password) {
        JSONObject jsonObject=new JSONObject();
        List<User> userForBase=UserService.UserService(null,username,password);
        if(userForBase!=null&&userForBase.size()>0){


            String token = getToken(userForBase.get(0));
            jsonObject.put("token", token);
            jsonObject.put("user", userForBase);
            return jsonObject;


        }else{
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }


    }

    @GetMapping("/test")
    public String test()  {
        return "访问test - API";
    }

}
