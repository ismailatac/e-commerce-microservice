package com.kodlamaio.saleservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateSalePaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.GetPaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient{
    @Override
    public GetPaymentResponse paymentGetById(UUID paymentId) {
        log.info("PAYMENT SERVICE IS DOWN!");
        throw new RuntimeException("PAYMENT-SERVICE NOT AVAILABLE RIGHT NOW!");
    }

    @Override
    public ClientResponse processSalePayment(CreateSalePaymentRequest request) {
        log.info("PAYMENT SERVICE IS DOWN!");
        throw new RuntimeException("PAYMENT-SERVICE NOT AVAILABLE RIGHT NOW!");
    }
}
