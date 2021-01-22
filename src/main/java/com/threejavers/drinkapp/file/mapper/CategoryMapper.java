package com.threejavers.drinkapp.file.mapper;

import com.threejavers.drinkapp.file.parser.DrinkAPI;
import com.threejavers.drinkapp.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category mapCategory(DrinkAPI drinkAPI) {
        Category category = new Category();
        category.setName(drinkAPI.getCategory());
        return category;
    }
}
