package com.qwertyfox.thefox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String loadMain() {
        return "general/main";
    }

    @GetMapping("/login")
    public String loadLogin() {
        return "general/login";
    }

    @RequestMapping("/welcome")
    public String loadWelcome() {
        return "secured/welcome";
    }

    @GetMapping("/accessDenied")
    public String loadAccessDenied() {
        return "accessDenied";
    }



}
