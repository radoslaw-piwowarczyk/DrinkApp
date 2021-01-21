package com.threejavers.drinkapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drink", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 40)
    private String name;

    @Column(name = "is_custom")
    private Boolean isCustom;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(length = 5000)
    private String recipe;

    @Column(name = "drink_type")
    private String drinkType;

    @Column(name = "glass_type")
    private String glassType;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "image_url", length = 1024)
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "drink_to_category",
            joinColumns = {@JoinColumn(name = "drink_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
    )
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "drink_to_ingredient",
            joinColumns = {@JoinColumn(name = "drink_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "id")}
    )
    private List<Ingredient> ingredientList = new ArrayList<>();
}
