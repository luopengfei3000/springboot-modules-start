package com.example.springbootjsp.controller;

import com.example.springbootjsp.config.PowerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

@Controller
public class TestJspController extends Observable {

	@Autowired
	private PowerConfig powerConfig;

	@RequestMapping(value = "/test")
	public String test1(Model model){
		model.addAttribute("haha",powerConfig.getName());
		return "index";
	}

	/**
	 * 练习观察者模式（Photo和User service中都实现Observer，执行update方法，两张表中均插入数据）
	 * @param model
	 * @return java.lang.String
	 * @Author luopf 2019/2/28
	 */
	@RequestMapping(value = "/test1")
	@ResponseBody
	public String test(Model model){
		setChanged();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test","test");
		notifyObservers(map);
		return "练习观察者模式";
	}

}
