package com.example.springbootjsp.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.springbootjsp.entity.ConfigInfo;
import com.example.springbootjsp.page.QueryRespBean;
import com.example.springbootjsp.service.ConfigInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/configInfo")
public class ConfigInfoController{
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigInfoController.class);
	@Autowired
	private ConfigInfoService configInfoService;

	@RequestMapping(value = "/toConfigInfoManage")
	public ModelAndView toConfigInfoManage(HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("configinfo/ConfigInfoManage");
		request.setAttribute("url","/configInfo/operation/");
		request.setAttribute("surl","/configInfo/operation/sub/");
		return mav;
	}

	@RequestMapping(value = "/toConfigInfoChartManage")
	public ModelAndView toConfigInfoChartManage(HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/configinfo/ConfigInfoChartManage");
		request.setAttribute("url","/configInfo/operation/");
		return mav;
	}
	
	@RequestMapping(value = "/operation/getConfigInfoByPage")
	@ResponseBody
	public Map<String, Object> getConfigInfoByPage(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		QueryRespBean<ConfigInfo> result = null;
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSzie = Integer.parseInt(request.getParameter("rows"));// pageSzie
		try {
			result = configInfoService.searchConfigInfoByPage(page, pageSzie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("total", result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		return map;
	}

	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView operation(@PathVariable String type,@PathVariable String id, HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			ConfigInfo dto = configInfoService.queryConfigInfoByPrimaryKey(id);

			request.setAttribute("configInfo", dto);
		}
		mav.setViewName("configinfo/" + "ConfigInfo" + type);
		return mav;
	}

	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String jsonData = ServletRequestUtils.getStringParameter(request,"data", "");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			ConfigInfo configInfo = JSON.parseObject(jsonData, ConfigInfo.class);
			if (StringUtils.isBlank(configInfo.getId())) {// 新增
				configInfoService.insertConfigInfo(configInfo);
			} else {// 修改
				configInfoService.updateConfigInfoSensitive(configInfo);
			}
			map.put("flag", "success");
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			map.put("error", ex.getMessage());
			map.put("flag", "fail");
			return map;
		}
		return map;
	}

	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody String[] ids,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			configInfoService.deleteConfiginfoByIds(ids);
			map.put("flag", "success");
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			map.put("error", ex.getMessage());
			map.put("flag", "fail");
			return map;
		}
		return map;
	}
	
	@RequestMapping(value = "/operation/single", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> single(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		String flag="";
		String value = ServletRequestUtils.getStringParameter(request,"value", "");
		int count= 0;
		try {
			count = configInfoService.selectConfigInfoCountByCode(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//mav.addObject("count", count);
		map.put("count", count);
		return map;
	}

	@RequestMapping(value = "/operation/getConfigInfoData4Chart", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMesWasteRecordData4Chart(HttpServletRequest request, HttpServletResponse reponse){
		Map<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		ConfigInfo param = new ConfigInfo();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<Map<String,Object>> dataList = configInfoService.searchConfigInfoData4Chart(param);
			map.put("dataList", dataList);
			map.put("flag", "success");
		} catch (Exception e) {
			map.put("error", e.getMessage());
			map.put("flag", "fail");
			return map;
		}
		return map;
	}
}
