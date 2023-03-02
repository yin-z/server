package com.example.server.config;

import com.example.server.interceptors.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yin
 * @Date 2023/3/2 17:07
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                // 拦截规则 ，拦截那些路径
                .addPathPatterns("/**")
                // 那些路径不拦截
                .excludePathPatterns("/user/login","/error");
    }

    @Bean
    public AuthInterceptor loginInterceptor(){
        return new AuthInterceptor();
    }
}
