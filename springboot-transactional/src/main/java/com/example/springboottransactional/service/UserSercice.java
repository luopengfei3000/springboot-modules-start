package com.example.springboottransactional.service;

import com.example.springboottransactional.dto.User;
import com.example.springboottransactional.exception.DaoException;
import com.example.springboottransactional.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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

	//@Transactional(rollbackFor = {Exception.class},propagation = Propagation.REQUIRED, readOnly = false)
	//@Transactional
	public void saveUser() throws Exception{
		try {
			User u = new User();
			u.setId(new Random().nextInt(10000) +1);
			u.setAge(88);
			u.setName("88");
			userMapper.addUser(u);
			u.setId(null);
			userMapper.addUser(u);//id不为空，发生异常
		}catch (Exception e){
			e.printStackTrace();;
			throw new DaoException("saveOrUpdateUser错误",e);
		}
	}
}
