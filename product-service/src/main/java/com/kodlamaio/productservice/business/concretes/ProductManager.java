package com.kodlamaio.productservice.business.concretes;


import com.kodlamaio.productservice.business.abstracts.ProductService;
import com.kodlamaio.productservice.business.dto.requests.CreateProductRequest;
import com.kodlamaio.productservice.business.dto.requests.UpdateProductRequest;
import com.kodlamaio.productservice.business.dto.responses.CreateProductResponse;
import com.kodlamaio.productservice.business.dto.responses.GetAllProductsResponse;
import com.kodlamaio.productservice.business.dto.responses.GetProductResponse;
import com.kodlamaio.productservice.business.dto.responses.UpdateProductResponse;
import com.kodlamaio.productservice.business.rules.ProductBusinessRules;
import com.kodlamaio.productservice.entities.Product;
import com.kodlamaio.productservice.entities.enums.State;
import com.kodlamaio.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;
    private final ProductBusinessRules rules;


    @Override
    public List<GetAllProductsResponse> getAll(State showAll) {
        List<Product> products = filterProductsByisActive(showAll);
        List<GetAllProductsResponse> allProductResponse = products.stream()
                .map(product -> modelMapper
                        .map(product, GetAllProductsResponse.class)).toList();
        return allProductResponse;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest createProductRequest) {
        Product product = modelMapper.map(createProductRequest, Product.class);
        rules.validateProduct(product);
        product.setId(UUID.randomUUID());
        product.setIsActive(State.ACTIVE);
        var productResponse = repository.save(product);
        CreateProductResponse response = modelMapper.map(productResponse, CreateProductResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest updateProductRequest) {
        Product product = modelMapper.map(updateProductRequest, Product.class);
        rules.validateProduct(product);
        product.setId(id);
        repository.save(product);
        UpdateProductResponse response = modelMapper.map(product, UpdateProductResponse.class);
        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        rules.checkIfProductExist(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = modelMapper.map(product, GetProductResponse.class);
        return response;
    }

    @Override
    public void changeProductState(UUID id, State state) {
        rules.checkIfProductExist(id);
        Product product = repository.findById(id).orElseThrow();
        product.setIsActive(state);
        repository.save(product);
    }

    @Override
    public GetProductResponse getByProductName(String productName) {
        Product response = repository.findByName(productName);

        return modelMapper.map(response, GetProductResponse.class);
    }

    public List<Product> filterProductsByisActive(State showAll) {
        if (showAll.equals(State.ACTIVE)) {
            return repository.findAll();
        } else {
            return repository.findByIsActive(State.PASSIVE);
        }
    }


}
