package com.example.springbootthymeleaf.controller;

import com.example.springbootthymeleaf.dto.Person;
import com.example.springbootthymeleaf.service.PersonSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
/**
 * **科技有限责任公司
 * 定义 人员控制层
 * @author luopf
 * @data 2018/12/19
 */
public class PersonController {

	@Autowired
	private PersonSercice personSercice;


	/**
	 * 查询所有数据
	 * @param model
	 * @return org.springframework.web.servlet.ModelAndView
	 * @Author luopf 2018/12/19
	 */
	@GetMapping(value = "/findAll")
	public ModelAndView findAll(Model model) {
		model.addAttribute("userList", personSercice.findAll());
		model.addAttribute("title", "用户管理");
		return new ModelAndView("list", "userModel", model);
	}

	/**
	 * 通过id查询数据
	 * @param id
	 * @return com.example.springbootthymeleaf.dto.Person
	 * @Author luopf 2018/12/19
	 */
	@GetMapping(value = "/findperson/{id}")
	public Person findGirlById(@PathVariable("id") Integer id) {
		Person person = personSercice.findPersonById(id);
		return person;
	}
	/**
	 * 根据id查询用户跳转到详细页
	 * @return
	 * @Author luopf 2018/12/19
	 */
	@GetMapping("/users/{id}")
	public ModelAndView view(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", personSercice.findPersonById(id));
		model.addAttribute("title", "查看用户");
		return new ModelAndView("view", "userModel", model);
	}

	/**
	 * 通过id删除用户
	 * @param id
	 * @return
	 * @Author luopf 2018/12/19
	 */
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id, Model model) {
		personSercice.deletePerson(id);
		model.addAttribute("userList", personSercice.findAll());
		model.addAttribute("title", "删除用户");
		return new ModelAndView("list", "userModel", model);
	}

	/**
	 * 跳转到用户添加页
	 * @return
	 * @Author luopf 2018/12/19
	 */
	@GetMapping(value = "/add")
	public ModelAndView add(Model model) {
		model.addAttribute("title", "添加页面");
		return new ModelAndView("add", "userModel", model);
	}

	/**
	 * 保存数据
	 * @param person
	 * @param model
	 * @return org.springframework.web.servlet.ModelAndView
	 * @Author luopf 2018/12/19
	 */
	@PostMapping(value = "/save")
	public ModelAndView insert(Person person,Model model) {
		if(person.getId() == null){
			personSercice.addPerson(person);
		}else{
			personSercice.updatePerson(person.getId(),person);
		}
		model.addAttribute("userList", personSercice.findAll());
		model.addAttribute("title", "新增或者修改用户");
		return new ModelAndView("list", "userModel", model);
	}

	/**
	 * 修改用户
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Integer id, Model model) {
		Person person = personSercice.findPersonById(id);
		model.addAttribute("user", person);
		model.addAttribute("title", "修改用户");
		return new ModelAndView("form", "userModel", model);
	}
}
