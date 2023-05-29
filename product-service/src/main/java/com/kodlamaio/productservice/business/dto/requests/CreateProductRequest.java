package com.kodlamaio.productservice.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private int quantity;
    private double price;
    private String description;
}
