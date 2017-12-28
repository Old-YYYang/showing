package com.qing.rbac.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/user")
@Slf4j
public class UserController {

    @GetMapping
    public void permission() {}
}
