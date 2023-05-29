package com.kodlamaio.commonpackage.utils.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetProductResponse {
    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private String isActive;
}
