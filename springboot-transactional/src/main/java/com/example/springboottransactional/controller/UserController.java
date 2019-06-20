package com.example.springboottransactional.controller;

import com.example.springboottransactional.dto.User;
import com.example.springboottransactional.service.UserSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping(value = "/test")
	public String saveOrUpdateUser() throws Exception {
		try {
			userSercice.saveUser();
		}catch (Exception e){
			return e.getMessage();
		}
		return "success";
	}
}
