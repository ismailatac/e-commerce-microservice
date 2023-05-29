package com.kodlamaio.saleservice.api.client;

import com.kodlamaio.commonpackage.utils.dto.GetProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public GetProductResponse getProductById(UUID productId) {
        log.info("PRODUCT SERVICE IS DOWN!");
        throw new RuntimeException("PRODUCT-SERVICE NOT AVAILABLE RIGHT NOW!");
    }
}
