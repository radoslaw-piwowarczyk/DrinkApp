package com.threejavers.drinkapp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @NotNull
    private String measure;

    @ManyToMany(mappedBy = "ingredientList")
    private List<Drink> drinkList = new ArrayList<>();

}
