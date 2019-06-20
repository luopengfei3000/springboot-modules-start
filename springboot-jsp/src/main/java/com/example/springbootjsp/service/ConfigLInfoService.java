package com.example.springbootjsp.service;


import java.util.List;

import com.example.springbootjsp.entity.ConfigLInfo;
import com.example.springbootjsp.page.QueryRespBean;

public interface ConfigLInfoService {

    //QueryRespBean<ConfigLInfo> searchConfigLInfoByPage(int pageNum, int pageSize) throws Exception;

    List<ConfigLInfo> searchConfigLInfo(ConfigLInfo configLInfo) throws Exception;

    ConfigLInfo queryConfigLInfoByPrimaryKey(String id) throws Exception;

    String insertConfigLInfo(ConfigLInfo configLInfo) throws Exception;

    int  updateConfigLInfoSensitive(ConfigLInfo configLInfo) throws Exception;

    int deleteConfigLInfoById(String id) throws Exception;

    int deleteConfiginfoByIds(String[] ids) throws Exception;

    List<ConfigLInfo> findConfigLInfoByConfigCode(String configCode) throws Exception;
}

