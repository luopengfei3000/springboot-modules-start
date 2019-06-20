package com.example.springbootjsp.mapper;
import java.util.List;

import com.example.springbootjsp.entity.ConfigLInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigLInfoMapper {

    Page<ConfigLInfo> searchConfigLInfoByPage() throws Exception;

    List<ConfigLInfo> searchConfigLInfo(ConfigLInfo configLInfo) throws Exception;

    ConfigLInfo queryConfigLInfoByPrimaryKey(String id) throws Exception;

    String insertConfigLInfo(ConfigLInfo configLInfo) throws Exception;

    int  updateConfigLInfoSensitive(ConfigLInfo configLInfo) throws Exception;

    int deleteConfigLInfoById(String id) throws Exception;

    List<ConfigLInfo> findConfigLInfoByConfigCode(String configCode);

}
