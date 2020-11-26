package com.threejavers.drinkapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    private UUID id;
    private String name;
    private String measure;
}
