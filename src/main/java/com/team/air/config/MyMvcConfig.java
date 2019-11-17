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

                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/sign").setViewName("user/sign_in");
                registry.addViewController("/register").setViewName("user/sign_up");
            }

            //注册拦截器z
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //静态资源： *.css; *.js;
//                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/index","/sign.html","/user/login");
//            }

        };
        return w;
    }
}
