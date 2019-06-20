package com.example.springbootjsp.mapper;
import java.util.List;

import com.example.springbootjsp.entity.ConfigInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigInfoMapper {

    Page<ConfigInfo> searchConfigInfoByPage() throws Exception;

    List<ConfigInfo> searchConfigInfoData4Chart(ConfigInfo configInfo) throws Exception;

    List<ConfigInfo> searchConfigInfo(ConfigInfo configInfo) throws Exception;

    ConfigInfo queryConfigInfoByPrimaryKey(String id) throws Exception;

    int insertConfigInfo(ConfigInfo configInfo) throws Exception;

    int  updateConfigInfoSensitive(ConfigInfo configInfo) throws Exception;

    int deleteConfigInfoById(String id) throws Exception;

    int selectConfigInfoCountByCode(String configCode) throws Exception;
}
