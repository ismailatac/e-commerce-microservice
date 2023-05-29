package com.kodlamaio.productservice.business.rules;


import com.kodlamaio.commonpackage.exceptions.BusinessException;
import com.kodlamaio.productservice.common.Messages;
import com.kodlamaio.productservice.entities.Product;
import com.kodlamaio.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductBusinessRules {
    private final ProductRepository repository;

    public void checkIfProductExist(UUID id) {
        if (!repository.existsById(id)) throw new RuntimeException("Ürün bulunamadı");
    }

    public void validateProduct(Product p) {
        checkIfPriceValid(p);
        checkIfQuantityValid(p);
        checkIfDescriptionValid(p);
    }

    private void checkIfDescriptionValid(Product p) {
        if (p.getDescription().length() < 10 || p.getDescription().length() > 50) {
            throw new BusinessException(Messages.DescriptionLength);
        }
    }

    private void checkIfQuantityValid(Product p) {
        if (p.getQuantity() < 0) {
            throw new BusinessException(Messages.LessThanZero);
        }
    }

    private void checkIfPriceValid(Product p) {
        if (p.getPrice() <= 0) {
            throw new BusinessException(Messages.LessThanZero);
        }
    }


}
