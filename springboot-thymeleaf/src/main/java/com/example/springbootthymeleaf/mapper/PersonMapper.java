package com.example.springbootthymeleaf.mapper;

import com.example.springbootthymeleaf.dto.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，
// 所以也可以统一配置@MapperScan在扫描路径在application类中
@Mapper
public interface PersonMapper {

	Person findPersonById(Integer id);

	List<Person> findAll();

	void addPerson(Person person);

	int updatePerson(@Param("id") Integer id, @Param("person") Person person);

	int deletePerson(Integer id);
	
}
