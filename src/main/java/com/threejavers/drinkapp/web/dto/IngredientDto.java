package com.threejavers.drinkapp.web.dto;

import com.threejavers.drinkapp.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private Long id;
    private String name;
    private String measure;

    public static IngredientDto ingredientToDto(Ingredient ingredient) {
        IngredientDto ingredientDTO = new IngredientDto();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setMeasure(ingredient.getMeasure());
        return ingredientDTO;
    }

    public static Ingredient dtoToIngredient(IngredientDto ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setMeasure(ingredientDTO.getMeasure());
        return ingredient;
    }
}

