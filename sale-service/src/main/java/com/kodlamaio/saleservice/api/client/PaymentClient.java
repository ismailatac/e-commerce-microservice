package com.kodlamaio.saleservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.commonpackage.utils.dto.CreateSalePaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.GetPaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "sale-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {
    @GetMapping(value = "/api/payments/{paymentId}")
    GetPaymentResponse paymentGetById(@PathVariable UUID paymentId);

    @GetMapping(value = "/api/payments/process-sale-payment")
    ClientResponse processSalePayment(@RequestBody CreateSalePaymentRequest request);
}
