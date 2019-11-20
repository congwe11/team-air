package com.team.air.config;

import com.team.air.conponent.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer w = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

                //主页url映射
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                //登录页面
                registry.addViewController("/user").setViewName("custom/sign_in");
                registry.addViewController("/user/").setViewName("custom/sign_in");
                registry.addViewController("/user/sign").setViewName("custom/sign_in");
                //注册页面
                registry.addViewController("/user/sign_up").setViewName("custom/sign_up");
                //用户个人信息页面
                registry.addViewController("/user/selfInfo").setViewName("custom/userInfo");
                //航班信息列表页面
                registry.addViewController("/book/airline").setViewName("custom/flightInfo");



                registry.addViewController("/index_text").setViewName("index_text");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源： *.css; *.js;
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/book","/user/book","/user/selfInfo","/user/upUserInfo",
                                "/user/upPsw")
                        .excludePathPatterns("/book/airline","/book/search");
            }

        };
        return w;
    }
}
