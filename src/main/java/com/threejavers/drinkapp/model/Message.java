package com.threejavers.drinkapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Message {

    @Id
//    @Type(type = "org.hibernate.type.UUIDCharType")
    private Long id;
    private String message;

}
