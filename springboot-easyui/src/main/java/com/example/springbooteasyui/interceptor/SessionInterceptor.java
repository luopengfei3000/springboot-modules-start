package com.example.springbooteasyui.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * *****科技有限责任公司
 * 定义  拦截器实体类
 * @author luopf
 * @data 2019/3/1
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(request.getRequestURI());
        // 登录不做拦截
        if (request.getRequestURI().equals("/login/")) {
            return true;
        }
        // 验证session是否存在
        Object obj = request.getSession().getAttribute("user");
        if (obj == null) {
            response.sendRedirect("/login/");
            //未登陆，返回登陆界面
            //request.setAttribute("msg","没有权限请先登陆");
            //request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object o, Exception e) throws Exception {

    }
}
