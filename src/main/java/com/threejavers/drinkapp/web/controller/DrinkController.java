package com.threejavers.drinkapp.web.controller;


import com.threejavers.drinkapp.service.DrinkService;
import com.threejavers.drinkapp.service.MessageService;
import com.threejavers.drinkapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/drink")
    public String drinkView(@RequestParam(name = "name") String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().isEmpty()) {
            return "redirect:login:logout";
        }
        model.addAttribute("drink", drinkService.get(name));
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("message", messageService.get(3L));
        model.addAttribute("favourite", userService.isFavourite(name, authentication.getName()));
        return "drink-view";
    }

    @PostMapping("favourite-drink")
    public String favouriteDrink(@RequestParam(name = "name") String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.manageFavourite(name, authentication.getName());
        model.addAttribute("drink", drinkService.get(name));
        model.addAttribute("drinks", drinkService.getAllDrinks());
        model.addAttribute("message", messageService.get(2L));
        model.addAttribute("favourite", userService.isFavourite(name, authentication.getName()));
        return "drink-view";
    }
}
