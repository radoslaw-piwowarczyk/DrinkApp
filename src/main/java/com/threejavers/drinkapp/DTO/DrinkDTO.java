package com.threejavers.drinkapp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class DrinkDTO {

    private UUID id;
    private String name;
    private Boolean isCustom;
    private Boolean isApproved;
    private String recipe;
    private String drinkType;
    private String glassType;
    private String modificationDate;
    private String imageUrl;
    private CategoryDTO category;
    private List<IngredientDTO> ingredientList;
}
