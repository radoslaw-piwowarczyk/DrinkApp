package com.threejavers.drinkapp.file.mapper;

import com.threejavers.drinkapp.file.parser.DrinkAPI;
import com.threejavers.drinkapp.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientMapper {

    public List<Ingredient> mapIngredients(DrinkAPI drinkAPI) {
        List<Ingredient> ingredients = new ArrayList<>();
        drinkAPI.getIngredients().forEach((key, value) -> ingredients.add(Ingredient.builder()
                .name(key)
                .measure(value)
                .build()));
        return ingredients;
    }
}
