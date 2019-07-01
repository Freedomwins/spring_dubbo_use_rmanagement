package com.qf.user_email_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf")
@DubboComponentScan("com.qf.service.impl")
public class UserEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserEmailServiceApplication.class, args);
    }

}
