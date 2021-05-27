package com.zw.javaapi.Controller;

import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserLoginController {
    @Value("admin")
    private String realUsername;

    @Value("admin")
    private String realPassword;

    @RequestMapping ("/ceshi")
    public String login(String username, String password) {
        if (username.equals(realUsername) && password.equals(realPassword)) {
            User u = new User();
            u.setPassword(password);
            u.setUsername(username);
            return JWTUtils.getToken(u);
        }
        return "登录失败！账号或者密码不对！";
    }

    @GetMapping("test")
    public String test()  {
        return "访问test - API";
    }

}
