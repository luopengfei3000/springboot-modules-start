package com.example.springbootstart01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author luopf 2018/9/18
 * @classname:
 * @description:
 * CommandLineRunner接口的运行顺序是依据@Order注解的value由小到大执行，即value值越小优先级越高。
 */
@Component
@Order(value = 2)
public class MyStartupRunnerTest2 implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=================================");
		System.out.println("=============启动成功===============");
		System.out.println("=================================");
	}
}
