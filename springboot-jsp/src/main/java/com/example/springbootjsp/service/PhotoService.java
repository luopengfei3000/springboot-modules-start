package com.example.springbootjsp.service;


import com.example.springbootjsp.entity.Photo;

/**
 * ****科技有限责任公司
 * 定义 用户接口
 * @author luopf
 * @data 2019/1/18
 */
public interface PhotoService {

    /**
      * 添加用户
      * @param photo
      * @return 
      * @Author luopf 2019/1/18
      */
     int insertPhotoinfo(Photo photo);

    int UpdatePhotoinfo(Photo photo);

     Photo selectPhotoinfoByUserId(String userId);

    Photo queryPhotoinfoByPrimaryKey(String id);


}

