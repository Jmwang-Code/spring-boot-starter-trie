package com.cn.jmw.security;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月24日 17:25
 * @Version 1.0
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
        registry.addViewController("/tire.html").setViewName("trie");
    }

    /**
     * 宽泛的 Allowed Origin 设置是指在跨域资源共享（CORS）中，将所有来源都设置为允许访问的方式。这通常被认为是一种不安全的做法，因为它允许来自任何域的请求都可以访问你的资源，这可能会导致安全风险。
     *
     * 具体来说，如果你在服务端设置了 Access-Control-Allow-Origin: *，那么任何来源的请求都将被允许访问你的资源，这包括恶意网站或攻击者的请求。这种做法会使你的应用程序容易受到跨站脚本攻击（XSS）和跨站请求伪造（CSRF）等攻击。
     *
     * 因此，在实际应用中，为了保证安全性，你应该尽量避免使用宽泛的 Allowed Origin 设置，而是只允许来自你信任的域名的请求访问你的资源。这可以通过在 Access-Control-Allow-Origin 中设置具体的来源，如 Access-Control-Allow-Origin: https://www.example.com 来实现。
     *
     * 如果你需要允许多个来源访问你的资源，可以将它们列在 Access-Control-Allow-Origin 的值中，如 Access-Control-Allow-Origin: https://www.example.com, https://www.anotherdomain.com。
     *
     * 总之，在设置 CORS 时，一定要谨慎选择允许的来源，以确保你的应用程序的安全性。
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedOrigins("*");
//    }
}

