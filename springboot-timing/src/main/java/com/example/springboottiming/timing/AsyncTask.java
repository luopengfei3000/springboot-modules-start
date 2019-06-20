package com.example.springboottiming.timing;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableAsync //异步任务注解开启
public class AsyncTask {

	@Async//异步
	public void test1() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(1000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务1耗时 : " + (end - begin) + " ]");
	}

	@Async//异步
	public void test2() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(2000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务2耗时 : " + (end - begin) + " ]");
	}

	@Async//异步
	public void test3() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务3耗时 : " + (end - begin) + " ]");
	}
}
