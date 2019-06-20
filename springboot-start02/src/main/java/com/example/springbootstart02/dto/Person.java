package com.example.springbootstart02.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 使用bean进行属性映射
 需要特别注意的是bean上面的两个注解：@Componet和@ConfigurationProperties，
 通过prefix来匹配前缀（类似分组思想），当然也可以
 通过 @ConfigurationProperties(prefix = "master.ds",locations = "classpath:application.properties")的形式，指定配置文件
  */

@ConfigurationProperties(prefix = "person")
@Component
public class Person {

	private String name;
	private Integer age;
	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
