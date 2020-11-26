package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public List<Drink> getDrinkList(){
        return drinkRepository.findAll();
//        paging implementation from Repository
//        return drinkRepository.findAllDrinksWithPaging(PageRequest.of(0,4));
    }

    public Drink getSingleDrink(UUID id) {
        return drinkRepository.findById(id).orElseThrow();
    }
}
