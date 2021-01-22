package com.threejavers.drinkapp.service;


import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.web.dto.DrinkDto;

import java.util.List;
import java.util.Set;

public interface DrinkService {

    void save(DrinkDto drinkDto);

    void delete(String name);

    Drink get(String name);

    List<Drink> getAllDrinks();

    Set<String> getUniqueGlass();
}
