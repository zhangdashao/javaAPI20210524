package com.zw.javaapi.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zw.javaapi.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.util.Calendar;



public class JWTUtils {

    Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * 获取token
     * @param u user
     * @return token
     */
    public static String getToken(User u) {
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

        String token="";
        token= JWT.create().withAudience(u.getId())
                .sign(Algorithm.HMAC256(u.getPassword()));
        return token;
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws JWTVerificationException {
        if(StringUtils.isEmpty(token)){
//            throw new MyException("token不能为空");
        }

        //获取登录用户真正的密码假如数据库查出来的是123456
        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);

    }


}
