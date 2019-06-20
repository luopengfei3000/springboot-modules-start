package com.example.springboottiming.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //使spring管理
//@EnableScheduling //定时任务注解(这个注解是定时任务的注解,这个注解添加在定时的类上或者Application类上都可以,效果是一样的.)
public class Timer {

	private Logger logger = LoggerFactory.getLogger(Timer.class);
	/**
	 * 每隔5秒执行一次
	 */
	//@Scheduled(cron = "*/5 * * * * *")  //这个注解加在方法上,注明这是一个定时方法.
	public void initTimerOne(){
		System.out.println("[ " + "当前时间 : " + new Date() + " ]");
		logger.info("5秒触发一次定时器运行");
	}

	/**
	 * 每隔12秒执行一次
	 */
	//@Scheduled(cron = "*/12 * * * * *")
	public void initTimerTwo(){
		System.out.println("[ " + "当前时间 : " + new Date() + " ]");
		logger.info("10秒触发一次定时器运行");
	}
}
