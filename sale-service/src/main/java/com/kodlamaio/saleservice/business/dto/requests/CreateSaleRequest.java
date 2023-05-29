package com.kodlamaio.saleservice.business.dto.requests;


import com.kodlamaio.commonpackage.utils.dto.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSaleRequest {
    private int productId;
    private PaymentRequest paymentRequest;
    private int quantityToBeSold;
}
