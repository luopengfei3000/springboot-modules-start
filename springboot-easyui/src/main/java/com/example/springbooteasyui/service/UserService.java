package com.example.springbooteasyui.service;


import com.example.springbooteasyui.entity.User;
import com.example.springbooteasyui.page.QueryRespBean;

/**
 * ****科技有限责任公司
 * 定义 用户接口
 * @author luopf
 * @data 2019/1/18
 */
public interface UserService {

    /**
     * 按条件分页查询
     * @param pageNum
     * @param pageSize
     * @return
     * @Author luopf 2019/1/18
     */
    QueryRespBean<User> searchUserinfoByPage(int pageNum, int pageSize);

    /**
      * 根据用户id查询数据
      * @param userId
      * @return 
      * @Author luopf 2019/1/18
      */
    public User queryUserinfoByPrimaryKey(String userId);

    /**
      * 添加用户
      * @param user
      * @return 
      * @Author luopf 2019/1/18
      */
     int insertUserinfo(User user);

     /**
       * 修改用户
       * @param user
       * @return
       * @Author luopf 2019/1/18
       */
     int  updateUserinfoSensitive(User user);
     /**
       * 根据用户登录信息查询用户
       * @param user
       * @return
       * @Author luopf 2019/1/18
       */
     User findUserinfoByloginInfo(User user);

     /**
       * 批量删除
       * @param ids
       * @return 
       * @Author luopf 2019/1/18
       */
    int deleteUserinfoByIds(String[] ids);

}

