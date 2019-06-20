package com.example.springbootjsp.service.impl;
import java.io.Serializable;
import java.util.List;

import com.example.springbootjsp.entity.ConfigLInfo;
import com.example.springbootjsp.exception.DaoException;
import com.example.springbootjsp.mapper.ConfigLInfoMapper;
import com.example.springbootjsp.service.ConfigLInfoService;
import com.example.springbootjsp.utils.ComUtil;
import com.example.springbootjsp.utils.PojoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigLInfoServiceImpl implements ConfigLInfoService,Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(ConfigLInfoServiceImpl.class);
    private static final long serialVersionUID = 1L;
    
	@Autowired
	private ConfigLInfoMapper configLInfoMapper;
	
	@Override
	public List<ConfigLInfo> searchConfigLInfo(ConfigLInfo configLInfo) throws Exception {
	    try{
	    	List<ConfigLInfo> dataList = configLInfoMapper.searchConfigLInfo(configLInfo);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchConfigLInfo：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
    }
	
	@Override
	public ConfigLInfo queryConfigLInfoByPrimaryKey(String id) throws Exception {
		try{
			ConfigLInfo dto = configLInfoMapper.queryConfigLInfoByPrimaryKey(id);
			return dto;
		}catch(Exception e){
	    	logger.error("queryConfigLInfoByPrimaryKey出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	
	@Override
	public String insertConfigLInfo(ConfigLInfo configLInfo) throws Exception {
		try{
			String id = ComUtil.getId();
			configLInfo.setId(id);
			configLInfoMapper.insertConfigLInfo(configLInfo);
			return id;
		}catch(Exception e){
			logger.error("insertConfigLInfo出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	@Override
	public int updateConfigLInfoSensitive(ConfigLInfo dto) throws Exception {
		try{
			ConfigLInfo old = this.queryConfigLInfoByPrimaryKey(dto.getId());
			PojoUtil.copyProperties(old, dto,true);
			int count = configLInfoMapper.updateConfigLInfoSensitive(old);
			if(count ==0){
				throw new DaoException("updateConfigLInfoSensitive数据失效，请重新更新");
			}
			return count;
		}catch(Exception e){
			logger.error("updateConfigLInfoSensitive出错：", e);
			throw new DaoException(e.getMessage(),e);
		}

	}
	
	@Override
	public int deleteConfigLInfoById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		throw new Exception("删除失败！传入的参数主键为null");
		}
		try{
			//记录日志
			return configLInfoMapper.deleteConfigLInfoById(id);
		}catch(Exception e){
			logger.error("deleteConfigLInfoById：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	
	@Override
	public int deleteConfiginfoByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteConfigLInfoById(id);
			result++;
		}
		return result;
	}

	@Override
	public List<ConfigLInfo> findConfigLInfoByConfigCode(String configCode) throws Exception {
		try {
			return configLInfoMapper.findConfigLInfoByConfigCode(configCode);
		} catch (Exception e) {
			logger.error("findConfigLInfoByConfigCode" + e);
			throw new DaoException(e.getMessage());
		}
	}
}

