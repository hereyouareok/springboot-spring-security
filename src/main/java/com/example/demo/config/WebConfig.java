package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YanQin
 * @version v1.0.0
 * @Description :在WebConfig.java中添加默认请求根路径跳转到/login，此url为spring security提供：
 * @Create on : 2020/9/19 16:24
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");
    }
}
