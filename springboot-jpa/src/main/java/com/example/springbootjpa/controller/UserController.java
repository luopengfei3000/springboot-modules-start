package com.example.springbootjpa.controller;

import com.example.springbootjpa.dto.User;
import com.example.springbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	/**
	 * 简单的逻辑，这里就暂时省略service了
	 */
	@Autowired
	private UserRepository userRepository;


	@GetMapping(value = "/user")
	public List<User> getList() {
		return userRepository.findByUserAll();
	}

	@GetMapping(value = "/delete")
	public int deleteByUserId(@RequestParam String id) {
		return userRepository.deleteByUserId(id);
	}

}
