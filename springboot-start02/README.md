# 项目属性配置
### 一、概述
application.properties就是springboot的属性配置文件<br/>
> 在使用spring boot过程中，可以发现项目中只需要极少的配置就能完成相应的功能，这归功于spring boot中的模块化配置，在pom.xml中依赖的每个Starter都有默认配置，而这些默认配置足以满足正常的功能开发。<br/>

除此之外，还有application.yml形式的配置文件
### 二、修改默认配置
示例：修改端口和context-path:
```
#更换端口号
port: 8082
#项目路径 http://localhost:8082/luopf/*
servlet:
context-path: /luopf
```
###  三、自定义属性配置
* 在yml文件中定义：
```
#定义变量
salary: 10010
name: luopf
```
* 使用@Value注解读取值：<br/>

使用@Value的类如果被其他类作为对象引用，必须要使用注入的方式，而不能new
```
@RestController
public class TestController {

	//使用@Value注解读取值：
	// 使用@Value的类如果被其他类作为对象引用，必须要使用注入的方式，而不能new
	@Value("${name}")
	private String name;
	@Value("${salary}")
	private Integer salary;

	@RequestMapping(value = "/getname", method = RequestMethod.GET)
	public String getValue() {
		return "name:" + name + " salary:" + salary;
	}
}
```
读取多级属性：
 ```
 twoname:
   first_name: luo
   last_name: pf
 ```
 ```
    @Value("${twoname.first_name}")
    private String fname;
    @Value("${twoname.last_name}")
    private String lname;
 ```
 属性中引用其他属性：类似shell变量
```
multiname:
  first_name: luo
  last_name: pf
  full_name: "${twoname.first_name}${twoname.last_name}"
```
使用bean进行属性映射<br/>
需要特别注意的是bean上面的两个注解：@Componet和@ConfigurationProperties，通过prefix来匹配前缀（类似分组思想），当然也可以
通过 @ConfigurationProperties(prefix = "master.ds",locations = "classpath:application.properties")  的形式，指定配置文件
```
#person类
person:
  name: luopf
  age: 20
  sex: GG
```
```
@ConfigurationProperties(prefix = "person")
@Component
public class Person {
	private String name;
	private Integer age;
	private String sex;
	// getter setter未列出
}
```
```
@RestController
public class TestController {
	@Autowired
	private Person person;

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String person() {
		return person.getName();
	}
}
```
多配置文件切换：<br/>
例如生产和开发的配置文件需要切换，那我们只要定义application-xxx.yml，然后在application.yml里面使用spring.profile.active切换即可！<br/>
在application.yml中进行配置：
```
spring:
  profiles:
    active: dev
```
这个时候再在application.yml文件里面写配置的话，dev里面也是可以直接用到的！