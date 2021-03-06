package com.zw.javaapi;

import com.zw.javaapi.Service.JWTInterceptor;
import com.zw.javaapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {

    //关键，将拦截器作为bean写入配置中
    @Autowired
    private UserService UserService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //拦截的路径
                .addPathPatterns("/**")
                //排除登录接口
                .excludePathPatterns("/user/login");
    }



}
