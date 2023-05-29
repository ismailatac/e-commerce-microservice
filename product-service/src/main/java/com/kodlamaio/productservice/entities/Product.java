package com.kodlamaio.productservice.entities;


import com.kodlamaio.productservice.entities.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private State isActive;


}
