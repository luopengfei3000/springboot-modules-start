package com.example.springbootstart02.controller;

import com.example.springbootstart02.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	private Person person;

	//使用@Value注解读取值：
	// 使用@Value的类如果被其他类作为对象引用，必须要使用注入的方式，而不能new
	@Value("${name}")
	private String name;
	@Value("${salary}")
	private Integer salary;
	@Value("${twoname.first_name}")
	private String fname;
	@Value("${twoname.last_name}")
	private String lname;
	@Value("${multiname.full_name}")
	private String fullname;

	@RequestMapping(value = "/getname", method = RequestMethod.GET)
	public String getValue() {
		//return "name:" + name + " salary:" + salary;
		return "name:" + name + " salary:" + salary + " fname:" + fname + " lname:" + lname + " fullname:" + fullname;
	}

	@RequestMapping(value = "/test")
	public String hello() {
		return "Test Springboot";
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String person() {
		return person.getName();
	}
}
