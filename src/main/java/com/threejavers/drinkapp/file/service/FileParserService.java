package com.threejavers.drinkapp.file.service;

import com.threejavers.drinkapp.file.mapper.CategoryMapper;
import com.threejavers.drinkapp.file.mapper.DrinkMapper;
import com.threejavers.drinkapp.file.parser.DrinkAPI;
import com.threejavers.drinkapp.file.parser.ParserService;
import com.threejavers.drinkapp.model.Category;
import com.threejavers.drinkapp.service.CategoryService;
import com.threejavers.drinkapp.service.DrinkService;
import com.threejavers.drinkapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileParserService {

    private final ParserService parserService;
    private final DrinkService drinkService;
    private final MessageService messageService;
    private final DrinkMapper drinkMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public void parseDataToDatabase(File json) {
        List<DrinkAPI> drinkAPIList = parserService.parseFile(json);
        int size = drinkAPIList.size();
        int count = 0;
        for (DrinkAPI drinkAPI : drinkAPIList) {
            if (drinkService.getAllDrinks().stream().noneMatch(drink -> drink.getName().equals(drinkAPI.getName()))) {
                count++;
                Category category = Optional
                        .ofNullable(categoryService.getByName(drinkAPI.getCategory()))
                        .orElseGet(() -> categoryMapper.mapCategory(drinkAPI));
                category.getDrinkList().add(drinkMapper.mapDrink(drinkAPI, category));
                categoryService.save(category);
            }
        }
        messageService.leaveMessage(1L, count + " items was parsed from " + size);
    }
}
