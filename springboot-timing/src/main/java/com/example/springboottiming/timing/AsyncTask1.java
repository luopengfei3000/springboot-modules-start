package com.example.springboottiming.timing;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@EnableAsync //异步任务注解开启
public class AsyncTask1 {

	@Async
	public Future<String> test1() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(1000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务1耗时 : " + (end - begin) + " ]");
		return new AsyncResult<String>("任务1");
	}

	@Async
	public Future<String> test2() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(2000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务2耗时 : " + (end - begin) + " ]");
		return new AsyncResult<String>("任务2");
	}

	@Async
	public Future<String> test3() throws InterruptedException {
		long begin = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		System.out.println("[ " + "任务3耗时 : " + (end - begin) + " ]");
		return new AsyncResult<String>("任务3");
	}
}
