package com.threejavers.drinkapp.service;

import com.threejavers.drinkapp.model.Drink;
import com.threejavers.drinkapp.model.Role;
import com.threejavers.drinkapp.model.User;
import com.threejavers.drinkapp.repository.UserRepository;
import com.threejavers.drinkapp.web.dto.UserRegistrationDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DrinkService drinkService;
    private final MessageService messageService;

    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder, DrinkService drinkService, MessageService messageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.drinkService = drinkService;
        this.messageService = messageService;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = User.builder()
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .email(userRegistrationDto.getEmail())
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .roles(Collections.singletonList(Role.builder().name("USER").build()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User get(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void manageFavourite(String drinkName, String userEmail) {
        if (isFavourite(drinkName, userEmail).isPresent()) {
            deleteFavourite(drinkName, userEmail);
        } else {
            addFavourite(drinkName, userEmail);
        }
    }

    @Override
    public void addFavourite(String drinkName, String userEmail) {
        User user = get(userEmail);
        Collection<Drink> favouriteDrinkList = user.getFavouriteDrinkList();
        favouriteDrinkList.add(drinkService.get(drinkName));
        user.setFavouriteDrinkList(favouriteDrinkList);
        userRepository.save(user);
        messageService.leaveMessage(2L, "Drink was added to favourite");
    }

    @Override
    public void deleteFavourite(String drinkName, String userEmail) {
        User user = get(userEmail);
        Collection<Drink> favouriteDrinkList = user.getFavouriteDrinkList();
        favouriteDrinkList.remove(drinkService.get(drinkName));
        user.setFavouriteDrinkList(favouriteDrinkList);
        userRepository.save(user);
        messageService.leaveMessage(2L, "Drink was removed from favourite");
    }

    @Override
    public Optional<Drink> isFavourite(String drinkName, String userEmail) {
        return get(userEmail).getFavouriteDrinkList()
                .stream()
                .filter(drink -> drink.getName().equals(drinkName))
                .findFirst();
    }

    @Override
    public void createAdmin() {
        if (userRepository.findByEmail("admin@admin") == null) {
            User admin = User.builder()
                    .firstName("John")
                    .lastName("Wick")
                    .email("admin@admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(Collections.singletonList(Role.builder().name("ADMIN").build()))
                    .build();
            userRepository.save(admin);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}