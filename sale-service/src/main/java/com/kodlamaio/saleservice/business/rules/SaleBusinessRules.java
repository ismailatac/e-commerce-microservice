package com.kodlamaio.saleservice.business.rules;


import com.kodlamaio.saleservice.common.Messages;
import com.kodlamaio.saleservice.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final SaleRepository repository;

    public void checkIfSaleExists(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException(Messages.NotExists);
    }
}
