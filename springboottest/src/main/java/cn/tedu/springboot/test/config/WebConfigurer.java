package cn.tedu.springboot.test.config;

import cn.tedu.springboot.test.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register");
//        super.addInterceptors(registry);    //较新Spring Boot的版本中这里可以直接去掉，否则会报错

//        //添加拦截器
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/session.do");

//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/reg.html","/reg","/demo.html","/login.html","/login","/favicon.ico");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/success.html",
                        "home.html","_left.html","_right.html","_top.html","index.html")
                .excludePathPatterns("/reg.html","/reg","/demo.html","/login.html","/login","/favicon.ico");

        //或

//        //可以添加多个拦截器
//        registry.addInterceptor(new LoginInterceptor())
//                /*.addPathPatterns(  //逐个拦截
//                        "/session.do",
//                        "/cart.do",
//                        "/pay.do");*/
//                .addPathPatterns("/home/**")//通配拦截
//                .excludePathPatterns("/home/session.do");//设置拦截例外

    }

}
