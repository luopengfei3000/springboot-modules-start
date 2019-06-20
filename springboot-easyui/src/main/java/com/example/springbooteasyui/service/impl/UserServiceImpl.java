package com.example.springbooteasyui.service.impl;

import com.example.springbooteasyui.entity.User;
import com.example.springbooteasyui.mapper.UserMapper;
import com.example.springbooteasyui.page.QueryRespBean;
import com.example.springbooteasyui.service.UserService;
import com.example.springbooteasyui.utils.ComUtil;
import com.example.springbooteasyui.utils.PojoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ****科技有限责任公司
 * 定义 用户实现类
 * @author luopf
 * @data 2019/1/18
 */
@Service
public class UserServiceImpl implements UserService {

    // 注入调用数据库操作mapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 按条件分页查询
     * @param pageNum
      * @param pageSize
     * @return
     * @Author luopf 2019/1/18
     */
    @Override
    public QueryRespBean<User> searchUserinfoByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<User> dataList = userMapper.searchUserinfoByPage();
        QueryRespBean<User> queryRespBean = new QueryRespBean<User>();
        queryRespBean.setResult(dataList);
        return queryRespBean;
    }

    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     * @Author luopf 2019/1/18
     */
    @Override
    public User queryUserinfoByPrimaryKey(String userId) {
        return userMapper.selectUserinfoById(userId);
    }

    /**
     * 新增用户
     * @param user
     * @return int
     * @Author luopf 2019/1/18
     */
    @Override
    public int insertUserinfo(User user) {
        String id = ComUtil.getId();
        user.setUserId(id);
        return userMapper.insertUserinfo(user);
    }

    /**
     * 修改用户
     * @param dto
     * @return int
     * @Author luopf 2019/1/18
     */
    @Override
    public int updateUserinfoSensitive(User dto) {
        int ret = 0;
        try {
            User old = this.queryUserinfoByPrimaryKey(dto.getUserId());
            PojoUtil.copyProperties(old, dto,true);
            ret = userMapper.updateByPrimaryKeySelective(old);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 批量删除用户
     * @param ids
     * @return int
     * @Author luopf 2019/1/18
     */
    @Override
    public int deleteUserinfoByIds(String[] ids) {
        int result = 0;
        for (String id : ids) {
            userMapper.deleteByPrimaryKey(id);
            result++;
        }
        return result;
    }

    /**
     * 根据用户登录信息查询用户
     * @param user
     * @return
     * @Author luopf 2019/1/18
     */
    @Override
    public User findUserinfoByloginInfo(User user) {
        return userMapper.selectselectByKeyAndPassword(user);
    };

}
