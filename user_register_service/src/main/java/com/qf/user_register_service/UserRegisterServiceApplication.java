package com.qf.user_register_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.service.impl")
@MapperScan("com.qf.dao")
public class UserRegisterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRegisterServiceApplication.class, args);
    }

}
