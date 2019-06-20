# springboot集成前端框架Easyui实现增删改查
### SpringBoot项目中使用拦截器
#### 目标
> 学习使用SpringBoot中是如何配置拦截器，使用拦截器来完成简单的用户登录状态判定

##### 1.创建拦截器
  创建一个名叫做SessionInterceptor的拦截器实体类，实现SpringMVC内部接口HandlerInterceptor，并且添加如果没有session状态直接跳转到/login/地址也就是我们对应的login.jsp页面,拦截器配置如下
  ```
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
  ```
##### 2.创建Configuration(将SessionInterceptor拦截器添加到SpringBoot的配置)
接下来我们需要将SessionInterceptor拦截器添加到SpringBoot的配置中，让SpringBoot项目有这么一个拦截器存在，我们新创建一个SessionConfiguration，将拦截器的配置以及拦截路径配置好，如下所示：
```
@Configuration
public class SessionConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").
		        excludePathPatterns("/captcha/captchaImage","/login/userLogin");
    }

    /*
    addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
    registry.addViewController("请求路径").setViewName("请求页面文件路径")
	*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/login/").setViewName("/login/login");
    }
}
```
再次访问127.0.0.1:8081/login/welcome，我们没有登录直接访问index.jsp，当然会被拦截器拦截，拦截后直接跳转到登录页面（login.jsp）