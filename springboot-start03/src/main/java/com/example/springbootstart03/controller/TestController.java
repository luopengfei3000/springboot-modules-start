package com.example.springbootstart03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	/**
	 * 进入到index.html
	 * @param
	 * @return java.lang.String
	 * @Author luopf 2018/12/12
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String hello() {
		return "index";
	}
}
