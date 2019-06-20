package com.example.springbooteasyui.controller;

import com.example.springbooteasyui.entity.User;
import com.example.springbooteasyui.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ****科技有限责任公司 定义 登录控制层
 * 
 * @author luopf
 * @data 2019/1/18
 */
@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面(拦截器中实现，无需controller中写)
     * 
     * @return
     * @Author luopf 2019/1/18
     */
    /*@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "/login/login";
    }*/

    /**
     * 这是一种返回形式 以ModelAndView 视图返回数据的形式返回 和返回map一样 调用方式不一样
     * 
     * @param request
     * @return
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/userLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String validateCode = request.getParameter("validateCode");
        // 从session中获取图形吗字符串
        String captcha = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(validateCode) || !captcha.equals(validateCode)) {
            model.addObject("MSG", "验证码不正确");
            model.setViewName("/login/login");
            return model;
        }
        User user = new User();
        user.setPassword(password);
        user.setUserName(userName);
        User loginUser = userService.findUserinfoByloginInfo(user);
        if (null != loginUser) {
            request.getSession().setAttribute("user", loginUser);
            //session.setAttribute("user", loginUser);
            model.setViewName("login/index");
        } else {
            model.addObject("MSG", "用户名或密码错误");
            model.setViewName("/login/login");
        }
        return model;
    }

    /**
     * 登录成功 加载欢迎页面 返回String y页面的路径和名称
     * 
     * @return
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "welcome", method = {RequestMethod.POST, RequestMethod.GET})
    public String welcome() {
        return "/login/welcome";
    }
}
