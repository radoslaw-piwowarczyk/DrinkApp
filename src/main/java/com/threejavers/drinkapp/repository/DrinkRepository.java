package com.threejavers.drinkapp.repository;

import com.threejavers.drinkapp.model.Drink;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, UUID> {

    //this is a classic version for a simple query without join from other table
    @Query("SELECT n FROM Drink n WHERE n.name= :name")
    List<Drink> findAllDrinksByName(@Param("name")String name);

    //shorter version of the same query
    List<Drink> findAllByName(String name);

    //list of drinks with paging.
//    List<Drink> findAllDrinksWithPaging(Pageable page);






}
