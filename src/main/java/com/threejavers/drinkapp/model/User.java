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
public class User {

    @Id
    @GeneratedValue
//    @Type(type = "org.hibernate.type.UUIDCharType")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Column(name = "user_type")
    @NotNull
    private String userType;

    @Column(name = "login", unique = true)
    @NotNull
    private String login;

    @NotNull
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favourite_drink",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "drink_id", referencedColumnName = "id")}
    )
    private List<Drink> favouriteDrinkList = new ArrayList<>();
}
