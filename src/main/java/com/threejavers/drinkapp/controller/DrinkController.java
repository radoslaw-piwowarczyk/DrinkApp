package com.threejavers.drinkapp.controller;

import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping("/drinks")
    public List<Drink> getDrinkList() {
        return drinkService.getDrinkList();
    }

    @GetMapping("/drinks/{id}")
    public Drink getSingleDrink(@PathVariable UUID id) {
        return drinkService.getSingleDrink(id);

    }
    @GetMapping("/drinki")
    public List<Drink> getListOfDrinks(){
        return (List<Drink>) drinkService.getDrink();
    }
}
