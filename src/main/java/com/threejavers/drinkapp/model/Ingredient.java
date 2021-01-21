package com.threejavers.drinkapp.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
//    @Type(type = "org.hibernate.type.UUIDCharType")
    private Long id;

    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @NotNull
    private String measure;

    @ManyToMany(mappedBy = "ingredientList")
    private List<Drink> drinkList = new ArrayList<>();

}
