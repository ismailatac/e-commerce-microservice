package com.kodlamaio.invoiceservice.business.abstracts;


import com.kodlamaio.invoiceservice.business.dto.*;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();

    GetInvoiceResponse getById(UUID id);

    CreateInvoiceResponse add(CreateInvoiceRequest request);

    UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request);

    void delete(UUID id);
}