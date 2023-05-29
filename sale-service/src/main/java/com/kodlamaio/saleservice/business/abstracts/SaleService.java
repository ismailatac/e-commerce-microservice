package com.kodlamaio.saleservice.business.abstracts;



import com.kodlamaio.saleservice.business.dto.requests.CreateSaleRequest;
import com.kodlamaio.saleservice.business.dto.requests.UpdateSaleRequest;
import com.kodlamaio.saleservice.business.dto.responses.CreateSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetAllSalesResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.UpdateSaleResponse;

import java.util.List;
import java.util.UUID;

public interface SaleService {

    List<GetAllSalesResponse> getAll();

    CreateSaleResponse add(CreateSaleRequest request);

    void delete(UUID id);

    UpdateSaleResponse update(UUID id, UpdateSaleRequest request);

    GetSaleResponse getById(UUID id);

}
