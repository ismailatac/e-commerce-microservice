package com.kodlamaio.productservice.repository;



import com.kodlamaio.productservice.entities.Product;
import com.kodlamaio.productservice.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByIsActive(State isActive);

    Product findByName(String productName);


}
