package com.kodlamaio.saleservice.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSaleResponse {
    private UUID id;
    private double totalPrice;
    private LocalDateTime date;
    private int quantityToBeSold;
}
