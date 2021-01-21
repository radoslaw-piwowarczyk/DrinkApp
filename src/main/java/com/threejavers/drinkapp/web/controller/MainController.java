package com.threejavers.drinkapp.web.controller;

import com.threejavers.drinkapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        userService.createAdmin();
        return "index";
    }

    @GetMapping("/start")
    public String start() {
        return "age-query";
    }

    @GetMapping("/adult-check")
    public String adultCheck(@RequestParam("age") String age) {
        if (age.equals("18+")) {
            return "redirect:/";
        } else {
            return "redirect:goodbye";
        }
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye";
    }

    @GetMapping("/navigation")
    public String navigation(@RequestParam("navigator") String navigator) {
        if (navigator.equals("Admin")) {
            return "redirect:admin-panel";
        } else
            return "redirect:registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
