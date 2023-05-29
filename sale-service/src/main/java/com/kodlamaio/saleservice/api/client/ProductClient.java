package com.kodlamaio.saleservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.GetProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping(value = "/api/products/{productId}")
    GetProductResponse getProductById(@PathVariable UUID productId);
}
