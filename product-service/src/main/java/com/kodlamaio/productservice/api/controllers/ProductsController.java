package com.kodlamaio.productservice.api.controllers;


import com.kodlamaio.productservice.business.abstracts.ProductService;
import com.kodlamaio.productservice.business.dto.requests.CreateProductRequest;
import com.kodlamaio.productservice.business.dto.requests.UpdateProductRequest;
import com.kodlamaio.productservice.business.dto.responses.CreateProductResponse;
import com.kodlamaio.productservice.business.dto.responses.GetAllProductsResponse;
import com.kodlamaio.productservice.business.dto.responses.GetProductResponse;
import com.kodlamaio.productservice.business.dto.responses.UpdateProductResponse;
import com.kodlamaio.productservice.entities.enums.State;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam State showAll) {
        return service.getAll(showAll);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@RequestBody UpdateProductRequest product, @PathVariable UUID id) {
        return service.update(id, product);
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@RequestBody CreateProductRequest product) {
        return service.add(product);
    }

    @PutMapping("/changeState/{id}")
    public void changeProductState(@PathVariable UUID id, @RequestParam State state) {
        service.changeProductState(id, state);
    }


}
