package com.example.springbootstart01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

	private Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/date")
	public String getDate(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "当前时间:"+sdf.format(d);
	}

	@GetMapping("/log")
	private Object log() {
		logger.debug("test");
		logger.info("test");
		logger.warn("test");
		logger.error("test");
		return "log";
	}
}
