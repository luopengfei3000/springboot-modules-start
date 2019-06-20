package com.example.springbootjsp.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.example.springbootjsp.entity.ConfigLInfo;
import com.example.springbootjsp.service.ConfigLInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/configLInfo")
public class ConfigLInfoController {
	private static final Logger logger = LoggerFactory.getLogger(ConfigLInfoController.class);
	@Autowired
	private ConfigLInfoService configLInfoService;

	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView toOpertionSubPage(@PathVariable String type,@PathVariable String id, HttpServletRequest request)throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"Add".equals(type)) {// 编辑窗口或者详细页窗口
			// 主表数据
			ConfigLInfo dto = configLInfoService.queryConfigLInfoByPrimaryKey(id);
			request.setAttribute("configLInfo", dto);
		}
		mav.setViewName("configlinfo/" + "ConfigLInfo" + type);
		return mav;
	}

	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request,@PathVariable String pid) {
		ModelAndView mav = new ModelAndView();
		try {
			String jsonData = ServletRequestUtils.getStringParameter(request,"data", "");
			ConfigLInfo configLInfo = JSON.parseObject(jsonData, ConfigLInfo.class);
			if (StringUtils.isBlank(configLInfo.getId())) {// 新增
				configLInfoService.insertConfigLInfo(configLInfo);
			} else {// 修改
				configLInfoService.updateConfigLInfoSensitive(configLInfo);
			}
			mav.addObject("flag", "success");
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", "fail");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestBody String[] ids,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			configLInfoService.deleteConfiginfoByIds(ids);
			mav.addObject("flag", "success");
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", "fail");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value="/operation/getConfigLInfoByConfigCode/{configCode}")
	@ResponseBody
	public List<ConfigLInfo> getConfigLInfoByConfigCode(HttpServletRequest request, @PathVariable String configCode){
		try {
			return configLInfoService.findConfigLInfoByConfigCode(configCode);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

}
