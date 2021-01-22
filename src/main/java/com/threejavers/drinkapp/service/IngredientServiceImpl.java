package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Ingredient;
import com.threejavers.drinkapp.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Set<String> getUniqueIngredientNames() {
        return ingredientRepository.findAll().stream().map(Ingredient::getName).collect(Collectors.toSet());
    }
}
