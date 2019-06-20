package com.example.springbootdubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.springbootdubboapi.service.HelloService;
import org.springframework.stereotype.Component;

//dubbo注解，暴露服务
@Service
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
