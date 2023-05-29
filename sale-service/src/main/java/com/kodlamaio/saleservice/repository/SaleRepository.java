package com.kodlamaio.saleservice.repository;



import com.kodlamaio.saleservice.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {



}
