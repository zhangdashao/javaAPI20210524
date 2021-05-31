package com.zw.javaapi.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.zw.javaapi.Entity.DescribeException;
import com.zw.javaapi.Entity.User;
import com.zw.javaapi.Enum.PassToken;
import com.zw.javaapi.Enum.UserLoginToken;
import com.zw.javaapi.Utils.JWTUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;



@Configuration
public class JWTInterceptor implements HandlerInterceptor {


    @Autowired
    UserService userService;

    @Autowired
    com.zw.javaapi.Utils.JWTUtils JWTUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
//                    throw new RuntimeException("无token，请重新登录");
                    throw new DescribeException("无token，请重新登录！",200);
                }
                // 获取 token 中的 user id
                String userId;
                try {
//                    userId = JWT.decode(token).getAudience().get(0);
                    userId= JWT.decode(token).getId();
                } catch (JWTDecodeException j) {
//                    throw new RuntimeException("401");
                    throw new DescribeException("系统错误，无法获取用户！",401);
                }

                //解决service为null无法注入问题
                if (userService == null) {
                    System.out.println("loginTickerService is null!!!");
                    BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
                    userService = factory.getBean(UserService.class);
                }


                List<User> userForBase=userService.UserService(userId,null,null);

                if(userForBase!=null&&userForBase.size()>0){

                    try {
                        if (JWTUtils == null) {
                            System.out.println("loginTickerService is null!!!");
                            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
                            JWTUtils = factory.getBean(JWTUtils.class);
                        }

                        JWTUtils.verify(token,"a1g2y47dg3dj59fjhhsd7cnewy73j");
                    } catch (JWTVerificationException e) {
//                        throw new RuntimeException("401");
                        throw new DescribeException("系统错误，无法验证用户！",401);
                    }
                    return true;
                }else{
//                    throw new RuntimeException("用户不存在，请重新登录");
                    throw new DescribeException("用户不存在，请重新登录！",200);
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
