package com.zw.javaapi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.Result;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Entity.User_Info;
import com.zw.javaapi.Enum.PassToken;
import com.zw.javaapi.Utils.JWTUtils;
import com.zw.javaapi.Utils.NotnullorEqualsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private com.zw.javaapi.Service.UserService UserService;

    @Autowired
    private com.zw.javaapi.Utils.JWTUtils JWTUtils;

    @Autowired
    private com.zw.javaapi.Service.User_InfoService User_InfoService;
    @Autowired
    private NotnullorEqualsUtils NotnullorEqualsUtils;

    @PassToken
    @RequestMapping ("/ceshi")
    public Result login(String username, String password)  {
        try {
            if(username!=null&&!username.isEmpty()){
                if(password!=null&&!password.isEmpty()){

                    JSONObject jsonObject=new JSONObject();
                    List<User> userForBase=UserService.UserService(null,username,password);
                    if(userForBase!=null&&userForBase.size()>0){

                        List<User_Info> list=new ArrayList();

                        list=User_InfoService.User_InfoService(userForBase.get(0).getId());

                        String token="";

                        if(NotnullorEqualsUtils.ListNull(list)){
                           if(new Date().before((Date) list.get(0).getToken_end_time())){
                               token=list.get(0).getToken();
                           }else{
                               token= JWTUtils.getToken(userForBase.get(0),list.get(0));
                           }
                        }else{
                            token= JWTUtils.getToken(userForBase.get(0),list.get(0));
                        }
                        jsonObject.put("token", token);
                        jsonObject.put("user", userForBase);
                        Result Result=new Result();
                        Result.setStatus(200);
                        Result.setData(jsonObject);
                        Result.setMsg("获取成功！");


                        return Result;

                    }else{
                        throw new DescribeException("当前用户不存在！",200);
                    }

                }else{
                    throw new DescribeException("密码不能为空！",200);
                }

            }else{
                throw new DescribeException("用户名不能为空！",200);
            }
        }catch (Exception e){
            throw new DescribeException("系统错误！",404);
        }
    }

    @GetMapping("/test")
    public String test()  {
        return "访问test - API";
    }

}
