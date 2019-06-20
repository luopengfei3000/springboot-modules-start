package com.example.springbootangular.mapper;

import java.util.List;

import com.example.springbootangular.dto.Order;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，
// 所以也可以统一配置@MapperScan在扫描路径在application类中
@Mapper
public interface OrderMapper {

	/**
	  * 分页查询信息
	  * @return
	  * @Author luopf 2019/6/11
	  */
	Page<Order> searchOrderByPage(@Param("bean")Order order);

	Order findOrderById(String id);

	List<Order> findAll();

	void addOrder(Order order);

	int updateOrder(@Param("order") Order order);

	int deleteOrder(String id);
	
}
