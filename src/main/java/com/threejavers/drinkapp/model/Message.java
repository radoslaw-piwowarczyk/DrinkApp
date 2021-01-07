package com.threejavers.drinkapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Message {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String message;
}
