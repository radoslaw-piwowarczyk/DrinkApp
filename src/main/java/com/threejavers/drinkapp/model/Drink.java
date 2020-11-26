package com.threejavers.drinkapp.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name", unique = true, length = 100)

    private String name;

    @Column(name = "is_custom")
    private Boolean isCustom;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "recipe", length = 5000)
    private String recipe;

    @Column(name = "drink_type")
    private String drinkType;

    @Column(name = "glass_type")
    private String glassType;

    @Column(name = "date_of_modification")
    private String modificationDate;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category_id")
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "drink_to_ingredient",
            joinColumns = {@JoinColumn(name = "drink_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "id")}
    )
    private List<Ingredient> ingredientList = new ArrayList<>();

    @ManyToMany(mappedBy = "favouriteDrinkList")
    private List<User> users = new ArrayList<>();
}
