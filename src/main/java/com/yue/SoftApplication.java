package com.yue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.yue.mapper")
public class SoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftApplication.class, args);
    }
}
