package com.example.springbootangular.controller;

import com.example.springbootangular.dto.Order;
import com.example.springbootangular.page.QueryRespBean;
import com.example.springbootangular.service.OrderSercice;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ordertwo")
public class OrderTwoController {

	@Autowired
	private OrderSercice orderSercice;

	@GetMapping("/brand")
	public String index() {
		return "admin/brand";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public List<Order> findAll() {
		return orderSercice.findAll();
	}

	@PostMapping(value = "/operation/getOrderByPage")
	@ResponseBody
	public Map<String, Object> OrderinforlistByPage(@RequestBody Order order, HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		QueryRespBean<Order> result = null;
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSzie = Integer.parseInt(request.getParameter("rows"));// pageSzie
		result = orderSercice.searchOrderByPage(order,page, pageSzie);
		map.put("total", result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		return map;
	}

	@PostMapping("/operation/save")
	@ResponseBody
	public Map<String, Object> save(@RequestBody Order order) {
		Map<String, Object> result = new HashMap<>();
		try {
			if (StringUtils.isNullOrEmpty(order.getId())){
				orderSercice.addOrder(order);
			} else {
				orderSercice.updateOrder(order);
			}
			result.put("success",true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("fail",false);
		}
		return result;
	}

	@GetMapping("/operation/findById")
	@ResponseBody
	public Order findById(String id) {
		return orderSercice.findOrderById(id);
	}

	@PostMapping("/operation/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody String[] ids) {
		Map<String, Object> result = new HashMap<>();
		try {
			orderSercice.deleteOrderBatch(ids);
			result.put("success",true);
		}catch(Exception e){
			e.printStackTrace();
			result.put("fail",false);
		}
		return result;
	}
}
