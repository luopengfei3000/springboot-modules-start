package com.example.springbootjsp.mapper;

import com.example.springbootjsp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;

/*@Component*/
@Mapper
public interface UserMapper {

    // 查询全部用户
    Page<User> searchUserinfoByPage();

    User selectUserinfoById(String userId);

    User selectUserinfoUnionPhotoByUserId(String userId);

    int insertUserinfo(User record);

    int deleteByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectselectByKeyAndPassword(User user);

}