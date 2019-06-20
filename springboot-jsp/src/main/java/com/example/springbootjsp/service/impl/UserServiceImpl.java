package com.example.springbootjsp.service.impl;

import com.example.springbootjsp.controller.TestJspController;
import com.example.springbootjsp.entity.User;
import com.example.springbootjsp.exception.DaoException;
import com.example.springbootjsp.mapper.UserMapper;
import com.example.springbootjsp.page.QueryRespBean;
import com.example.springbootjsp.service.UserService;
import com.example.springbootjsp.utils.ComUtil;
import com.example.springbootjsp.utils.PojoUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * ****科技有限责任公司
 * 定义 用户实现类
 * @author luopf
 * @data 2019/1/18
 */
@Service
public class UserServiceImpl implements UserService,Observer {

    // 注入调用数据库操作mapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestJspController testJspController;

    @PostConstruct
    public void init() {
        // 注册加入观察者
        testJspController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Map<String, String> map = (Map<String, String>) arg;
            User u = new User();
            u.setUserName(map.get("test"));
            u.setPassword(map.get("test"));
            u.setPhone(map.get("test"));
            this.insertUserinfo(u);
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    /**
     * 按条件分页查询
     * @param pageNum
      * @param pageSize
     * @return com.kyh.system.page.QueryRespBean<com.kyh.system.entity.User>
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
     * @return com.kyh.system.entity.User
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

    public int deleteByPrimaryKey(String userId) {
        int result = userMapper.deleteByPrimaryKey(userId);
        return result;
    }

    /**
     * 根据用户登录信息查询用户
     * @param user
     * @return com.kyh.system.entity.User
     * @Author luopf 2019/1/18
     */
    @Override
    public User findUserinfoByloginInfo(User user) {
        return userMapper.selectselectByKeyAndPassword(user);
    }

    @Override
    public User findUserinfoUnionPhotoByUserId(String userId) {
        return userMapper.selectUserinfoUnionPhotoByUserId(userId);
    }

}
