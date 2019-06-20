package com.example.springbootmybatis.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_user")
public class User implements Serializable {

	/**
	 * 设置主键和自增
	 */
	@Id
	@GeneratedValue
	@Column(name="t_id")
	private Integer id;
	@Column(name="t_name")
	private String name;
	@Column(name="t_age")
	private Integer age;

	public User() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

