package com.kodlamaio.invoiceservice.business.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    private String cardHolder;
    private String productName;
    private int quantity;
    private double price;
    private LocalDate saledAt;
}
