package com.threejavers.drinkapp.controller;

import com.threejavers.drinkapp.model.Drink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TemplateController {

    @GetMapping(path = "/add/drink")
    public String addDrink(){
        return "add-drink";
    }

    @PostMapping(path = "/view/drink")
    public String viewDrink(Model model, Drink drink) {

        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());
        model.addAttribute("name", drink.getName());

        return null;
    }
}
