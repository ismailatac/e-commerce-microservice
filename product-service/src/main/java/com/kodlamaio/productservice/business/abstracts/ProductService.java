package com.kodlamaio.productservice.business.abstracts;



import com.kodlamaio.productservice.business.dto.requests.CreateProductRequest;
import com.kodlamaio.productservice.business.dto.requests.UpdateProductRequest;
import com.kodlamaio.productservice.business.dto.responses.CreateProductResponse;
import com.kodlamaio.productservice.business.dto.responses.GetAllProductsResponse;
import com.kodlamaio.productservice.business.dto.responses.GetProductResponse;
import com.kodlamaio.productservice.business.dto.responses.UpdateProductResponse;
import com.kodlamaio.productservice.entities.enums.State;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<GetAllProductsResponse> getAll(State showAll);

    CreateProductResponse add(CreateProductRequest request);

    void delete(UUID id);

    UpdateProductResponse update(UUID id, UpdateProductRequest request);

    GetProductResponse getById(UUID id);

    void changeProductState(UUID id, State state);

    GetProductResponse getByProductName(String productName);
}
