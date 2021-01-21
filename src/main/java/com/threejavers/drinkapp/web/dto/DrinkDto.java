package com.threejavers.drinkapp.web.dto;


import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto {

    private Long id;
    private String name;
    private Boolean isCustom;
    private Boolean isApproved;
    private String recipe;
    private String drinkType;
    private String glassType;
    private String modificationDate;
    private String imageUrl;
    private CategoryDto category;
    private List<IngredientDto> ingredientList;

    public static DrinkDto drinkToDto(Drink drink) {
        DrinkDto drinkDto = new DrinkDto();
        drinkDto.setId(drink.getId());
        drinkDto.setName(drink.getName());
        drinkDto.setIsCustom(drink.getIsCustom());
        drinkDto.setIsApproved(drink.getIsApproved());
        drinkDto.setRecipe(drink.getRecipe());
        drinkDto.setDrinkType(drink.getDrinkType());
        drinkDto.setGlassType(drink.getGlassType());
        drinkDto.setModificationDate(drink.getModificationDate());
        drinkDto.setImageUrl(drink.getImageUrl());
        drinkDto.setCategory(CategoryDto.categoryToDto(drink.getCategory()));

        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        drink.getIngredientList().forEach(ingredient -> {
            IngredientDto ingredientDTO = IngredientDto.ingredientToDto(ingredient);
            ingredientDtoList.add(ingredientDTO);
        });
        drinkDto.setIngredientList(ingredientDtoList);
        return drinkDto;
    }

    public static Drink dtoToDrink(DrinkDto drinkDTO) {
        Drink drink = new Drink();
        drink.setName(drinkDTO.getName());
        drink.setIsCustom(drinkDTO.getIsCustom());
        drink.setIsApproved(drinkDTO.getIsApproved());
        drink.setRecipe(drinkDTO.getRecipe());
        drink.setDrinkType(drinkDTO.getDrinkType());
        drink.setGlassType(drinkDTO.getGlassType());
        drink.setModificationDate(drinkDTO.getModificationDate());
        drink.setImageUrl(drinkDTO.getImageUrl());
        drink.setCategory(CategoryDto.dtoToCategory(drinkDTO.getCategory()));

        List<Ingredient> ingredientList = new ArrayList<>();
        drinkDTO.getIngredientList().forEach(ingredientDto -> {
            Ingredient ingredient = IngredientDto.dtoToIngredient(ingredientDto);
            ingredientList.add(ingredient);
        });
        drink.setIngredientList(ingredientList);
        return drink;
    }
}
