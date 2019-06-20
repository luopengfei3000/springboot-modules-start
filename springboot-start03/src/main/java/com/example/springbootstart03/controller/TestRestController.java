package com.example.springbootstart03.controller;

import org.springframework.web.bind.annotation.*;

//就是相当于之前的@Controller加上@ResponseBody的组合
@RestController
public class TestRestController {

	@RequestMapping(value = "/restindex", method = RequestMethod.GET)
	public String restindex() {
		//直接返回了字符串，而不进行视图解析
		return "index";
	}
	//http://localhost:8080/hi  or  http://localhost:8080/hello  都可以访问
	@RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
	public String hello() {
		return "index";
	}
	/**
	 * url
	 * @param name
	 * @return java.lang.String
	 * @Author luopf 2018/12/12
	 */
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable("name") String name) {
		return "your name is" + name;
	}

	/**
	 * 这个就是传统的?=xxx形式的了(必须传name值)
	 * @param name
	 * @return java.lang.String
	 * @Author luopf 2018/12/12
	 */
	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello1(@RequestParam("name") String name) {
		return "your name is:" + name;
	}

	/**
	 * 这个就是传统的?=xxx形式的了(可以不传name，默认值为luopf)
	 * @param name
	 * @return java.lang.String
	 * @Author luopf 2018/12/12
	 */
	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public String hello2(@RequestParam(value = "name", required = false, defaultValue = "luopf") String name) {
		return "your name is:" + name;
	}

	// @RequestMapping(value = "/hello", method = RequestMethod.GET)
	@GetMapping(value = "/hello3")
	public String hello3(@RequestParam(value = "name", required = false, defaultValue = "luopf") String name) {
		return "your name is:" + name;
	}
}
