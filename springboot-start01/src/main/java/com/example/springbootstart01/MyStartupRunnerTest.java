package com.example.springbootstart01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @classname:
 * @description:
 * @author luopf 2018/9/18
 */
@Component
@Order(value=1)
public class MyStartupRunnerTest implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------>>>>>>>>><<<<<<<<<<<-------------------");
	}
}