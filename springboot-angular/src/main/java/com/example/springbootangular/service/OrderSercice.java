package com.example.springbootangular.service;


import com.example.springbootangular.dto.Order;
import com.example.springbootangular.mapper.OrderMapper;
import com.example.springbootangular.page.QueryRespBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderSercice {

	@Autowired
	private OrderMapper orderMapper;

	public QueryRespBean<Order> searchOrderByPage(Order order, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Order> dataList = orderMapper.searchOrderByPage(order);
		QueryRespBean<Order> queryRespBean = new QueryRespBean<Order>();
		queryRespBean.setResult(dataList);
		return queryRespBean;
	}

	public Order findOrderById(String id) {
		return orderMapper.findOrderById(id);
	}

	public List<Order> findAll() {
		return orderMapper.findAll();
	}

	public String addOrder(Order order) {
		String id = UUID.randomUUID().toString();
		order.setId(id);
		orderMapper.addOrder(order);
		return id;
	}

	public int updateOrder(Order order) {
		return orderMapper.updateOrder(order);
	}

	public int deleteOrder(String id) {
		return orderMapper.deleteOrder(id);
	}

	public int deleteOrderBatch(String[] ids) {
		int result = 0;
		for (String id : ids) {
			orderMapper.deleteOrder(id);
			result++;
		}
		return result;
	}
}
