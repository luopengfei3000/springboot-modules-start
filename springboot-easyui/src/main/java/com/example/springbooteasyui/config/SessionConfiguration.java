package com.example.springbooteasyui.config;

import com.example.springbooteasyui.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ****科技有限责任公司
 * 定义  将SessionInterceptor拦截器添加到SpringBoot的配置中
 * @author luopf
 * @data 2019/3/1
 */
@Configuration
public class SessionConfiguration implements WebMvcConfigurer {
	
	/**
	 .addPathPatterns("/**")表示拦截所有的请求，
	 excludePathPatterns表示路径不需要拦截哪些路径
	 一定要在这里排除拦截static路径下的静态文件，或者在SpringBoot配置文件上配置，否则页面加载不到样式
	 .excludePathPatterns("/static/**");
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").
		        excludePathPatterns("/captcha/captchaImage","/login/userLogin");
    }

    /*
    addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
    registry.addViewController("请求路径").setViewName("请求页面文件路径")

    registry.addViewController("/").setViewName("forward:/login/login");
	**/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/login/").setViewName("/login/login");
    }
}
