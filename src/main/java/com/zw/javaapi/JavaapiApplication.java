package com.zw.javaapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zw.javaapi.Mapper")
public class JavaapiApplication {

    public static void main(String[] args) {

        SpringApplication.run(JavaapiApplication.class, args);
    }

}
