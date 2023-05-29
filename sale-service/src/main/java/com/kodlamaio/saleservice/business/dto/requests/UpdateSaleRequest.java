package com.kodlamaio.saleservice.business.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSaleRequest {
    private LocalDateTime date;
    private int productId;
    private int quantityToBeSold;
}
