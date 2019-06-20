package com.example.springbootjsp.service.impl;

import com.example.springbootjsp.controller.TestJspController;
import com.example.springbootjsp.entity.Photo;
import com.example.springbootjsp.exception.DaoException;
import com.example.springbootjsp.mapper.PhotoMapper;
import com.example.springbootjsp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootjsp.entity.User;
import com.example.springbootjsp.mapper.UserMapper;
import com.example.springbootjsp.page.QueryRespBean;
import com.example.springbootjsp.service.UserService;
import com.example.springbootjsp.utils.ComUtil;
import com.example.springbootjsp.utils.PojoUtil;
import com.github.pagehelper.Page;
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
public class PhotoServiceImpl implements PhotoService,Observer {

    // 注入调用数据库操作mapper
    @Autowired
    private PhotoMapper photoMapper;
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
            Photo p = new Photo();
            p.setName(map.get("test"));
            this.insertPhotoinfo(p);//练习观察者模式，插入的垃圾数据可删除
        } catch (Exception e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public int insertPhotoinfo(Photo photo) {
        String id = ComUtil.getId();
        photo.setId(id);
        int result = photoMapper.insertPhotoinfo(photo);
        return result;
    }

    @Override
    public int UpdatePhotoinfo(Photo dto) {
        int ret = 0;
        try {
            Photo old = this.queryPhotoinfoByPrimaryKey(dto.getId());
            PojoUtil.copyProperties(old, dto,true);
            ret = photoMapper.updateByPrimaryKeySelective(old);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public Photo selectPhotoinfoByUserId(String userId) {
        Photo photo = photoMapper.selectPhotoinfoByUserId(userId);
        return photo;
    }

    @Override
    public Photo queryPhotoinfoByPrimaryKey(String id) {
        return photoMapper.selectPhotoinfoById(id);
    }

}
