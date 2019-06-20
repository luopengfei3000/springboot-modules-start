package com.example.springbootthymeleaf.dto;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	@Column(name="age")
	private Integer age;
	@Column(name="name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
