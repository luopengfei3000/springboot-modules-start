package com.example.springbootthymeleaf.service;

import com.example.springbootthymeleaf.dto.Person;
import com.example.springbootthymeleaf.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PersonSercice {

	@Autowired
	private PersonMapper personMapper;

	public Person findPersonById(Integer id) {
		return personMapper.findPersonById(id);
	}

	public List<Person> findAll() {
		return personMapper.findAll();
	}

	public void addPerson(Person person) {
		person.setId((int)(Math.random()*10000000));
		personMapper.addPerson(person);
	}

	public int updatePerson(Integer id, Person person) {
		return personMapper.updatePerson(id,person);
	}

	public int deletePerson(Integer id) {
		return personMapper.deletePerson(id);
	}
}
