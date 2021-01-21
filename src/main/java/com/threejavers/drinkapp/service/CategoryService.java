package com.threejavers.drinkapp.service;


import com.threejavers.drinkapp.model.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Set<String> getUniqueCategoryNames();

    Category getByName(String name);

    Optional<Category> getById(Long id);

    void save(Category category);
}
