package com.example.springbootjsp.mapper;

import com.example.springbootjsp.entity.Photo;
import com.example.springbootjsp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/*@Component*/
@Mapper
public interface PhotoMapper {

    int insertPhotoinfo(Photo record);

    Photo selectPhotoinfoByUserId(String UserId);

    Photo selectPhotoinfoById(String id);

    int updateByPrimaryKeySelective(Photo record);

}