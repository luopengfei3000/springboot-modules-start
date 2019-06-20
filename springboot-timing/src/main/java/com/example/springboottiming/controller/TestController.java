package com.example.springboottiming.controller;

import com.example.springboottiming.timing.AsyncTask;
import com.example.springboottiming.timing.AsyncTask1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class TestController {
	@Autowired
	private AsyncTask task;
	@Autowired
	private AsyncTask1 task1;

	/**
	  * 任务还未全部完成执行test()中方法
	  * @return
	  * @Author luopf 2019/5/11
	  */
	@GetMapping("/test")
	private Object test() {
		long begin = System.currentTimeMillis();
		try {
			task.test1();
			task.test2();
			task.test3();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("[ " + "Controller耗时 : " + (end - begin) + " ]");
		return "test";
	}

	/**
	  * 任务全部完成才往下执行
	  * @return
	  * @Author luopf 2019/5/11
	  */
	@GetMapping("/test1")
	private Object test1() {
		long begin = System.currentTimeMillis();
		try {
			Future<String> task1Result = task1.test1();
			Future<String> task2Result = task1.test2();
			Future<String> task3Result = task1.test3();
			while (true) {
				if (task1Result.isDone() && task2Result.isDone() && task3Result.isDone()) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("[ " + "Controller耗时 : " + (end - begin) + " ]");
		return "test";
	}
}
