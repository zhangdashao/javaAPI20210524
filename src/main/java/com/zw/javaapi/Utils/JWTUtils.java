package com.zw.javaapi.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.deploy.security.JarSignature;
import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Entity.User_Info;
import com.zw.javaapi.Mapper.User_InfoMapper;
import com.zw.javaapi.Service.User_InfoService;
import io.jsonwebtoken.*;
import jdk.nashorn.internal.runtime.Context;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


@Component
public class JWTUtils {

    @Autowired
    User_InfoService User_InfoService;


    Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * 获取token
     * @param u user
     * @return token
     */
    public  String getToken(User u, User_Info user_info)  throws Exception{


//        Calendar instance = Calendar.getInstance();
//        //默认令牌过期时间7天
//        instance.add(Calendar.DATE, 7);
//
//        JWTCreator.Builder builder = JWT.create();
//        builder.withClaim("userId", u.getId())
//                .withClaim("username", u.getUsername());
//
//        return builder.withExpiresAt(instance.getTime())
//                .sign(Algorithm.HMAC256(u.getPassword()));
//
//        String token="";
//        token= JWT.create()
//                .withAudience(u.getId())
//                .sign(Algorithm.HMAC256(u.getPassword()));
//        return token;

        String token_password="";
        Integer outside_time=0;

        Properties properties =null;
        try {
            properties= PropertiesLoaderUtils.loadAllProperties("application.properties");
            token_password=properties.getProperty("token_password");
            outside_time=Integer.valueOf(properties.getProperty("outside_time"));

        }catch (Exception e){
            throw new DescribeException("获取配置文件失败，请重试！",401);
        }



         String secret = token_password;
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String token = Jwts.builder()
                .setId(u.getId())  //登录用户的id
                .setSubject(u.getUsername())  //登录用户的名称
                .setExpiration(new Date(System.currentTimeMillis() + outside_time*1000))//过期时间
                .setIssuedAt(new Date(System.currentTimeMillis()))//当前时间
                .signWith(SignatureAlgorithm.HS512,secret)//头部信息 第一个参数为加密方式为哈希512  第二个参数为加的盐为secret字符串
                .compact();

        //将token保存
        user_info.setToken(token);
        user_info.setToken_end_time(new Date(System.currentTimeMillis() + outside_time*1000));
        User_InfoService.User_info(user_info);


        System.out.println("token生成时间："+new Date(System.currentTimeMillis()));
        return token;
    }

    /**
     * 验证token合法性 成功返回token
     */
    public  Claims verify(String token,String secretKey) throws JWTVerificationException {
        if(StringUtils.isEmpty(token)){
            throw new DescribeException("token不能为空",200);
        }

        //获取登录用户真正的密码假如数据库查出来的是123456
        try{
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            throw new DescribeException("token已经过期，请重新登录！",200);
        }

    }


}
