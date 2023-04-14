package com.zut.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Date 2023-04-14 10:24 星期五
 * @Author: 聂建强
 * @Description:
 */

@SpringBootApplication
@MapperScan("com.zut.admin.mapper")
public class AuthingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthingApplication.class, args);
    }

}
