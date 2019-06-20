package com.example.springbootjsp.service.impl;

import java.io.Serializable;
import java.util.*;

import com.example.springbootjsp.entity.ConfigInfo;
import com.example.springbootjsp.exception.DaoException;
import com.example.springbootjsp.mapper.ConfigInfoMapper;
import com.example.springbootjsp.page.QueryRespBean;
import com.example.springbootjsp.service.ConfigInfoService;
import com.example.springbootjsp.utils.ComUtil;
import com.example.springbootjsp.utils.PojoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigInfoServiceImpl implements Serializable,ConfigInfoService {

	private static final Logger logger =  LoggerFactory.getLogger(ConfigInfoServiceImpl.class);
    private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConfigInfoMapper configInfoMapper;

	@Override
	public QueryRespBean<ConfigInfo> searchConfigInfoByPage(int pageNum, int pageSize) throws Exception {
		try{
			PageHelper.startPage(pageNum, pageSize);
			Page<ConfigInfo> dataList = configInfoMapper.searchConfigInfoByPage();
			QueryRespBean<ConfigInfo> queryRespBean = new QueryRespBean<ConfigInfo>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchConfigInfoByPage出错：", e);
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> searchConfigInfoData4Chart(ConfigInfo dto) throws Exception {
		List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
		List<ConfigInfo> configInfos = configInfoMapper.searchConfigInfoData4Chart(dto);
		for (ConfigInfo configInfo : configInfos) {
			Map<String,Object> map1 = new HashMap<>();
			map1.put("CONFIG_NAME", configInfo.getConfigName());
			map1.put("NUM", configInfo.getConfigCount());
			dataList.add(map1);
		}
		//根据NUM排序
		Collections.sort(dataList, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int qty1 = (int) o1.get("NUM");
				int qty2 = (int) o2.get("NUM");
				return qty2-qty1;
			}
		});
		return dataList;
	}

	@Override
	public List<ConfigInfo> searchConfigInfo(ConfigInfo configInfo) throws Exception {
	    try{
	    	List<ConfigInfo> dataList = configInfoMapper.searchConfigInfo(configInfo);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchConfigInfo出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
    }
	
	@Override
	public ConfigInfo queryConfigInfoByPrimaryKey(String id) throws Exception {
		try{
			ConfigInfo dto = configInfoMapper.queryConfigInfoByPrimaryKey(id);
			return dto;
		}catch(Exception e){
	    	logger.error("queryConfigInfoByPrimaryKey：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}

	@Override
	public String insertConfigInfo(ConfigInfo configInfo) throws Exception {
		try{
			String id = ComUtil.getId();
			configInfo.setId(id);
			configInfoMapper.insertConfigInfo(configInfo);
			return id;
		}catch(Exception e){
			logger.error("insertConfigInfo出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	
	@Override
	public int updateConfigInfoSensitive(ConfigInfo dto) throws Exception {
		ConfigInfo old = this.queryConfigInfoByPrimaryKey(dto.getId());
		PojoUtil.copyProperties(old, dto,true);
		int count = configInfoMapper.updateConfigInfoSensitive(old);
		if(count ==0){
			throw new DaoException("updateConfigInfoSensitive数据失效，请重新更新");
		}
		return count;
	}
	
	@Override
	public int deleteConfigInfoById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try{
			//删除主表
			return configInfoMapper.deleteConfigInfoById(id);
		}catch(Exception e){
			logger.error("deleteConfigInfoById：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	@Override
	public int deleteConfiginfoByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteConfigInfoById(id);
			result++;
		}
		return result;
	}
	
	@Override
	public int selectConfigInfoCountByCode(String configCode) throws Exception {
		int result=configInfoMapper.selectConfigInfoCountByCode(configCode);
		return result;
	}	

}

