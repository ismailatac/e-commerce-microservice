package com.kodlamaio.commonpackage.events;

import com.kodlamaio.commonpackage.utils.dto.CreateInvoiceRequest;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreatedEvent implements Event {
    private CreateInvoiceRequest invoiceRequest;
}
