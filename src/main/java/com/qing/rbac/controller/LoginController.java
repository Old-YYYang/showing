package com.qing.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        System.out.println(username+", "+password);
        if ("admin".equals(username) && "123456".equals(password)) {
            return "index";
        }
        return "login";
    }
}
