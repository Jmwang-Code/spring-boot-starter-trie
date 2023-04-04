package com.cn.jmw.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jmw
 * @Description TODO
 * @date 2023年03月24日 16:50
 * @Version 1.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/trie")
    public String tire(Model model) {
        return "trie";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        //注销当前用户的登录状态
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }
        //重定向到登录页面
        return "redirect:/login?logout";
//        return "/logout";
    }

    @GetMapping("/api")
    public String api(Model model, HttpServletResponse response) {
        return "redirect:/doc.html#/home";
    }
}
