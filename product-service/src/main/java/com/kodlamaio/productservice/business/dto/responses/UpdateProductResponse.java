package com.kodlamaio.productservice.business.dto.responses;


import com.kodlamaio.productservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductResponse {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private State isActive;
}
