package com.example.springbootjpa.controller;

import com.example.springbootjpa.dto.Person;
import com.example.springbootjpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
	/**
	 * 简单的逻辑，这里就暂时省略service了
	 */
	@Autowired
	private PersonRepository personRepository;

	//1 GET
	@GetMapping(value = "/person")
	public List<Person> getList() {
		// 直接调用findAll()即可，已经封装了！
		return personRepository.findAll();
	}

	//　2.POST
	/**
	 * 可以使用此方式进行参数绑定
	 */
    /*@PostMapping(value = "/Persons")
    public String addPerson(@RequestParam("age") Integer age, String name) {
        return null;
    }*/

	/**
	 * 更推荐bean进行绑定（这里规则和springMVC是一样的）
	 * @param person 实体
	 * @return
	 */
	@PostMapping(value = "/addPerson")
	public Person addPerson(Person person) {
		// 直接返回这个实体类也可以
		return personRepository.save(person);
	}

	//3.其他PUT GET DELETE
	@PutMapping(value = "/Persons/{id}")
	public Person updatePersonById(@PathVariable("id") Integer id, String name) {
		Person Person = new Person();
		Person.setId(id);
		Person.setName(name);
		return personRepository.save(Person);
	}
	@DeleteMapping(value = "/Persons/{id}")
	public String deletePersonById(@PathVariable("id") Integer id) {
		personRepository.deleteById(id);
		return "删除ID为：" + id + " 的女生成功！";
	}

	@GetMapping(value = "/girls/age/{age}")
	public List<Person> getListByAge(@PathVariable("age") Integer age) {
		return personRepository.findByAge(age);
	}
}
