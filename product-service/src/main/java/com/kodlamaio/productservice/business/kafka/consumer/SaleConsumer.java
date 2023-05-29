package com.kodlamaio.productservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.productservice.business.abstracts.ProductService;
import com.kodlamaio.productservice.business.dto.requests.UpdateProductRequest;
import com.kodlamaio.productservice.entities.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumer {
    private final ProductService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "sale-created",
            groupId = "product-sale-create"
    )
    public void consume(UUID id){
        Product productRequest = mapper.forRequest().map(service.getById(id), Product.class);
        productRequest.setQuantity(productRequest.getQuantity() - 1);
        service.update(productRequest.getId(), mapper.forRequest().map(productRequest, UpdateProductRequest.class));
    }
}
