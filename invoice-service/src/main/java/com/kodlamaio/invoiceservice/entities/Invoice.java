package com.kodlamaio.invoiceservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    private UUID id;
    private String cardHolder;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private LocalDate saledAt;
}
