package com.example.springbootangular.controller;

import com.example.springbootangular.dto.Order;
import com.example.springbootangular.service.OrderSercice;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * **科技有限责任公司
 * 定义 人员控制层
 * @author luopf
 * @data 2019/3/19
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderSercice orderSercice;


	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/save")
	public @ResponseBody
	Map<String, Object> save(@RequestBody Order order) {
		Map<String, Object> result = new HashMap<>();
		if (StringUtils.isNullOrEmpty(order.getId())){
			String id = orderSercice.addOrder(order);
			result.put("id",id);
		} else {
			orderSercice.updateOrder(order);
			result.put("id",order.getId());
		}
		return result;
	}

	@PostMapping("/get")
	public @ResponseBody
	Object get(String id) {
		return orderSercice.findOrderById(id);
	}

	@GetMapping("/findAll")
	public @ResponseBody Object findAll() {
		return orderSercice.findAll();
	}

	@PostMapping("/delete")
	public @ResponseBody Map<String, Object> delete(String id) {
		Map<String, Object> result = new HashMap<>();
		orderSercice.deleteOrder(id);
		result.put("id", id);
		return result;
	}

}
