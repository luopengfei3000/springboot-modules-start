package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.dto.User;
import com.example.springbootmybatis.service.UserSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserSercice userSercice;

	@GetMapping(value = "/finduser/{id}")
	public User findGirlById(@PathVariable("id") Integer id) {
		User user = userSercice.findUserById(id);
		return user;
	}
	@GetMapping(value = "/updateuser/{id}")
	public Integer updateGirlById(@PathVariable("id") Integer id) {
		User user = new User();
		user.setId(id);
		user.setAge(50);
		user.setName("haha");
		return userSercice.updateUser(id, user);
	}
}
