package com.threejavers.drinkapp.model;

import org.hibernate.engine.internal.Cascade;
import org.hibernate.stat.CacheableDataStatistics;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class DrinkList {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Drink> drinkList;


}
