package com.kodlamaio.invoiceservice.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceResponse {
    private UUID id;
    private String cardHolder;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
    private LocalDate saledAt;
}
