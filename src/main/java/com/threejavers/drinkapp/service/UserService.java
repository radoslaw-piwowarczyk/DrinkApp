package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.model.User;
import com.threejavers.drinkapp.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto userRegistrationDto);

    User get(String email);

    void manageFavourite(String drinkName, String userEmail);

    void addFavourite(String drinkName, String userEmail);

    void deleteFavourite(String drinkName, String userEmail);

    Optional<Drink> isFavourite(String drinkName, String userEmail);

    void createAdmin();
}
