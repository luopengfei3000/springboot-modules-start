package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.dto.Person;
import com.example.springbootmybatis.service.PersonSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonSercice personSercice;

	@GetMapping(value = "/findperson/{id}")
	public Person findGirlById(@PathVariable("id") Integer id) {
		Person person = personSercice.findPersonById(id);
		return person;
	}

	@GetMapping(value = "/findAll")
	public List<Person> findAll() {
		List<Person> persons = personSercice.findAll();
		return persons;
	}
}
