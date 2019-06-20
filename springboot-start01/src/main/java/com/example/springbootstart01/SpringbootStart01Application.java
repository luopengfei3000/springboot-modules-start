package com.example.springbootstart01;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringbootStart01Application {

    public static void main(String[] args) {
        /*SpringApplication.run(SpringbootStart01Application.class, args);
        System.out.println("============启动成功==============");*/

        // 启动方式一：
        SpringApplication.run(SpringbootStart01Application.class, args);

        // 启动方式二：（停用banner）
//        SpringApplication springApplication = new SpringApplication(SpringbootStart01Application.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.run(args);

        // 启动方式三：（停用banner）
        /*new SpringApplicationBuilder(SpringbootStart01Application.class)
                .bannerMode(Banner.Mode.OFF)
                .build()
                .run(args);*/

        //效果和MyStartupRunnerTest2一样
        /**
         System.out.println("=================================");
         System.out.println("=============启动成功===============");
         System.out.println("=================================");
         */
    }

}
