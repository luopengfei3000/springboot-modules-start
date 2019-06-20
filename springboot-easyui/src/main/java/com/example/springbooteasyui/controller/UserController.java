package com.example.springbooteasyui.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbooteasyui.entity.User;
import com.example.springbooteasyui.page.QueryRespBean;
import com.example.springbooteasyui.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
/**
 * ****科技有限责任公司 定义 用户控制层
 *
 * @author luopf
 * @data 2019/1/17
 */
public class UserController {
    static final int pageSize = 10;
    @Autowired
    private UserService userService;

    /**
     * 查询用户测试数据
     * 
     * @param request
     * @param response
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @ResponseBody
    @RequestMapping(value = "/allPage", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> findAllUserByPage(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryRespBean<User> result = null;
        String pageNum = request.getParameter("pageNum");
        int pageNumber = 0;
        if (null == pageNum) {
            pageNumber = 1;
        }
        result = userService.searchUserinfoByPage(pageNumber, pageSize);
        map.put("total", result.getPageParameter().getTotalCount());
        map.put("rows", result.getResult());
        return map;
    }

    /**
     * 跳转到用户管理页面
     * 
     * @param request
     * @param reponse
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/toUserinfoManage")
    public ModelAndView toPmsInvoiceFRManage(HttpServletRequest request, HttpServletResponse reponse) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/userinfo/userinfoManage");
        request.setAttribute("url", "/user/operation/");
        return mav;
    }

    /**
     * 按条件用户信息分页查询
     * 
     * @param request
     * @return java.util.Map
     * @Author luopf 2019/1/17
     */
    @PostMapping(value = "/operation/getUserinfoByPage")
    @ResponseBody
    public Map userinforlistByPage(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryRespBean<User> result = null;
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));// pageSzie
        result = userService.searchUserinfoByPage(page, pageSzie);
        map.put("total", result.getPageParameter().getTotalCount());
        map.put("rows", result.getResult());
        return map;
    }

    /**
     * 根据id跳转到添加/编辑页面
     * 
     * @param type
     * @param id 
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/{type}/{id}")
    public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id, HttpServletRequest request)
        throws Exception {
        ModelAndView mav = new ModelAndView();
        if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
            User dto = userService.queryUserinfoByPrimaryKey(id);
            request.setAttribute("user", dto);
        }
        mav.setViewName("/userinfo/" + "userinfo" + type);
        return mav;
    }

    /**
     * 保存数据
     * 
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/save", method = RequestMethod.POST)
    @ResponseBody
    // 返回ModelAndView有问题 求解？？？
    public Map<String, Object> toSaveUserDTO(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            User user = JSON.parseObject(jsonData, User.class);
            if (StringUtils.isBlank(user.getUserId())) {// 新增
                userService.insertUserinfo(user);
            } else {// 修改
                userService.updateUserinfoSensitive(user);
            }
            map.put("user", user);
            map.put("flag", "success");
        } catch (Exception ex) {
            map.put("error", ex.getMessage());
            map.put("flag", "fail");
            return map;
        }
        return map;

    }

    /**
     * 删除数据
     * 
     * @param ids
     * @param request 
     * @param session
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
    @ResponseBody
    // 一定加ResponseBody 否则报错
    public Map<String, Object> toDeleteUserinfoDTO(@RequestBody String[] ids, HttpServletRequest request,
        HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            for (String id : ids) {
                if (((User)session.getAttribute("user")).getUserId().equals(id)) {
                    map.put("msg", "违法操作！不能删除自己！");
                    return map;
                }
            }
            userService.deleteUserinfoByIds(ids);
            map.put("flag", "success");
        } catch (Exception ex) {
            map.put("error", ex.getMessage());
            map.put("flag", "fail");
            return map;
        }
        return map;
    }

    /**
     * 修改自己信息
     * 
     * @param userId
     * @param userName 
     * @param password 
     * @param phone 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/updateMyInfo", method = {RequestMethod.POST, RequestMethod.GET}) // 保存新增用户和修改的用户信息
    @ResponseBody
    public ModelAndView updateMyInfo(@RequestParam("userId") String userId, @RequestParam("userName") String userName,
        @RequestParam("password") String password, @RequestParam("phone") String phone, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.updateUserinfoSensitive(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/error");
        return model;
    }

    /**
     * 跳转到个人信息修改页面
     * 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView myInfo(HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = (User)session.getAttribute("user");
        User logUser = userService.findUserinfoByloginInfo(user);
        session.setAttribute("user", logUser);
        model.addObject("user", logUser);
        model.setViewName("/common/myInfo");
        return model;
    }

    /**
     * 修改密码
     * 
     * @param userId
     * @param oldpassword 
     * @param newpassword1 
     * @param newpassword2 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/modifypassword", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView modifypassword(@RequestParam("userId") String userId,
        @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword1") String newpassword1,
        @RequestParam("newpassword2") String newpassword2, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User Loginuser = (User)session.getAttribute("user");
        if (oldpassword == null || "".equals(oldpassword)) {
            model.addObject("messag", "初始密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword1 == null || "".equals(newpassword1)) {
            model.addObject("messag", "新密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword2 == null || "".equals(newpassword2)) {
            model.addObject("messag", "确认密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (!newpassword2.equals(newpassword1)) {
            model.addObject("messag", "两次密码不一致");
            model.setViewName("/common/success");
            return model;
        } else if (!Loginuser.getPassword().equals(oldpassword)) {
            model.addObject("messag", "初始密码不正确");
            model.setViewName("/common/success");
            return model;
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(newpassword2);
        try {
            userService.updateUserinfoSensitive(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/success");
        return model;
    }

    /**
     * 退出登录
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/exit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView exit(HttpSession session) {
        session.removeAttribute("user");
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/login/");
        return model;
    }
}
