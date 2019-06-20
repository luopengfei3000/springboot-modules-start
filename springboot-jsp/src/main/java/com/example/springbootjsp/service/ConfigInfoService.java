package com.example.springbootjsp.service;


import com.example.springbootjsp.entity.ConfigInfo;
import com.example.springbootjsp.page.QueryRespBean;

import java.util.List;
import java.util.Map;

public interface ConfigInfoService {

    QueryRespBean<ConfigInfo> searchConfigInfoByPage(int pageNum, int pageSize) throws Exception;

    List<Map<String, Object>> searchConfigInfoData4Chart(ConfigInfo configInfo) throws Exception;

    List<ConfigInfo> searchConfigInfo(ConfigInfo configInfo) throws Exception;

    ConfigInfo queryConfigInfoByPrimaryKey(String id) throws Exception;

    String insertConfigInfo(ConfigInfo configInfo) throws Exception;

    int  updateConfigInfoSensitive(ConfigInfo configInfo) throws Exception;

    int deleteConfigInfoById(String id) throws Exception;

    int deleteConfiginfoByIds(String[] ids) throws Exception;

    int selectConfigInfoCountByCode(String configCode) throws Exception;

}

