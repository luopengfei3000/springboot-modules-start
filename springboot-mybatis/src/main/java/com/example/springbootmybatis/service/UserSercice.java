package com.example.springbootmybatis.service;

import com.example.springbootmybatis.dto.User;
import com.example.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserSercice {

	@Autowired
	private UserMapper userMapper;

	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	public int updateUser(Integer id, User user) {
		return userMapper.updateUser(id, user);
	}
}
