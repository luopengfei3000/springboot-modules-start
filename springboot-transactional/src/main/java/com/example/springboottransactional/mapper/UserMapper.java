package com.example.springboottransactional.mapper;

import java.util.List;

import com.example.springboottransactional.dto.User;
import org.apache.ibatis.annotations.*;

// @Mapper 这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，
// 所以也可以统一配置@MapperScan在扫描路径在application类中
@Mapper
public interface UserMapper {

	//查询出来的数据，与实体bean的字段不相匹配，导致null
	//实体bean的字段与查询出dto字段的必须匹配
	@Select("SELECT t_id as id,t_age as age,t_name as name FROM t_user WHERE t_id = #{id}")
	User findUserById(@Param("id") Integer id);

	@Select("SELECT t_id as id,t_age as age,t_name as name FROM t_user")
	List<User> findAll();

	@Insert("INSERT INTO t_user VALUES(#{id},#{age},#{name})")
	int addUser(User user);

	@Update("UPDATE t_user SET t_age = #{user.age},t_name = #{user.name} WHERE t_id = #{id}")
	int updateUser(@Param("id") Integer id, @Param("user") User user);

	@Delete("DELETE FROM t_user WHERE t_id = #{id}")
	int deleteUser(Integer id);
}
