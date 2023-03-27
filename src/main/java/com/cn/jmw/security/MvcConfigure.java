package com.cn.jmw.security;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月24日 17:25
 * @Version 1.0
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 对 springmvc 进行自定义配置
 */
@Configuration
public class MvcConfigure implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/logout.html").setViewName("logout");
    }
}

