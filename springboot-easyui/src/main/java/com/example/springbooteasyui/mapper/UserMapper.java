package com.example.springbooteasyui.mapper;

import com.example.springbooteasyui.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/*@Component*/
@Mapper
public interface UserMapper {

    // 查询全部用户
    Page<User> searchUserinfoByPage();

    User selectUserinfoById(String userId);

    int insertUserinfo(User record);

    int deleteByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectselectByKeyAndPassword(User user);

}