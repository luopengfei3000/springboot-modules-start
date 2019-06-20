package com.example.springbootjpa.repository;

import com.example.springbootjpa.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  PersonRepository extends JpaRepository<Person, Integer> {

	// 新增自定义查询方法
	List<Person> findByAge(Integer age);

}
