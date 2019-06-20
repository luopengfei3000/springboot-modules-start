package com.example.springbootjpa.repository;

import com.example.springbootjpa.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	//@Query是用来配置自定义SQL的注解，后面参数nativeQuery = true才是表明了使用原生的sql，如果不配置，默认是false，则使用HQL查询方式
	@Query(value = "select * from t_user",nativeQuery=true)
	List<User> findByUserAll();

	//@Query配合@Modifying
	//从名字上可以看到我们的@Query注解好像只是用来查询的，
	// 但是如果配合@Modifying注解一块使用，则可以完成数据的删除、添加、更新操作
	@Modifying
	//SpringDataJPA自定义SQL时需要在对应的接口或者调用接口的地方添加事务注解@Transactional，
	// 来开启事务自动化管理
	@Transactional
	@Query(value = "delete from t_user where t_id = ?1",nativeQuery=true)
	int deleteByUserId(String id);
}
