package com.qf.user_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.qf")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UserWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserWebApplication.class, args);
	}

}
