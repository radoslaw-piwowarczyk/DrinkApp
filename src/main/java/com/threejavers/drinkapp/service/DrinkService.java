package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrinkService {
    private RestTemplate restTemplate = new RestTemplate();
    private final DrinkRepository drinkRepository;

    public List<Drink> getDrinkList(){
        return drinkRepository.findAll();
    }

    public Drink getSingleDrink(UUID id) {
        return drinkRepository.findById(id).orElseThrow();
    }

    public Drink getDrink() {
        Drink response = restTemplate.getForObject("src/main/resources/Coctails/a.json", Drink.class);
        log.info(String.valueOf(response));
        return null;
    }
}
