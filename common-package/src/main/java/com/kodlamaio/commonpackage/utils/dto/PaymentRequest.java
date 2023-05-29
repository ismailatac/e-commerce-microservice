package com.kodlamaio.commonpackage.utils.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    @NotBlank(message = "Kart numarası boş bırakılamaz!")
    @Length(min = 16, max = 16, message = "Kart numarası 16 haneli olmalı!")
    private String cardNumber;

    @NotBlank
    @Length(min = 5)
    private String cardHolderName;


    @Min(value = 2023)
    private int cardExpirationYear;


    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @Length(min = 3, max = 3)
    private String cardCvv;
}
