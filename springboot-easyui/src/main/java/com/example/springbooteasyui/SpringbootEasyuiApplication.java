package com.example.springbooteasyui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@MapperScan("com.example.springbooteasyui.mapper")*/
public class SpringbootEasyuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEasyuiApplication.class, args);
        System.out.println("==================启动成功==================");
    }

}
