package com.example.springbootmybatis.service;

import com.example.springbootmybatis.dto.Person;
import com.example.springbootmybatis.dto.User;
import com.example.springbootmybatis.mapper.PersonMapper;
import com.example.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonSercice{

	@Autowired
	private PersonMapper personMapper;

	public Person findPersonById(Integer id) {
		return personMapper.findPersonById(id);
	}

	public List<Person> findAll() {
		return personMapper.findAll();
	}

	public int addPerson(Person person) {
		return personMapper.addPerson(person);
	}

	public int updatePerson(Integer id, Person person) {
		return personMapper.updatePerson(id,person);
	}

	public int deletePerson(Integer id) {
		return personMapper.deletePerson(id);
	}
}
