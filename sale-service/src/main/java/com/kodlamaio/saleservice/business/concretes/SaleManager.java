package com.kodlamaio.saleservice.business.concretes;


import com.kodlamaio.commonpackage.events.InvoiceCreatedEvent;
import com.kodlamaio.commonpackage.events.SaleCreatedEvent;
import com.kodlamaio.commonpackage.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.dto.CreateInvoiceRequest;
import com.kodlamaio.commonpackage.utils.dto.CreateSalePaymentRequest;
import com.kodlamaio.commonpackage.utils.dto.GetProductResponse;
import com.kodlamaio.commonpackage.utils.enums.State;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.saleservice.api.client.PaymentClient;
import com.kodlamaio.saleservice.api.client.ProductClient;
import com.kodlamaio.saleservice.business.abstracts.SaleService;
import com.kodlamaio.saleservice.business.dto.requests.CreateSaleRequest;
import com.kodlamaio.saleservice.business.dto.requests.UpdateSaleRequest;
import com.kodlamaio.saleservice.business.dto.responses.CreateSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetAllSalesResponse;
import com.kodlamaio.saleservice.business.dto.responses.GetSaleResponse;
import com.kodlamaio.saleservice.business.dto.responses.UpdateSaleResponse;
import com.kodlamaio.saleservice.business.rules.SaleBusinessRules;
import com.kodlamaio.saleservice.entities.Sale;
import com.kodlamaio.saleservice.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {

    private final SaleRepository repository;
    private final ModelMapper mapper;
    private final SaleBusinessRules rules;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final KafkaProducer producer;


    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales = repository.findAll();
        List<GetAllSalesResponse> responseList = sales.stream()
                .map(sale -> mapper
                        .map(sale, GetAllSalesResponse.class)).toList();
        return responseList;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        checkProductAvailabilityForSale(request);
        Sale sale = mapper.map(request, Sale.class);
        sale.setId(UUID.randomUUID());
        sale.setDate(LocalDate.now());

        GetProductResponse product = productClient.getProductById(sale.getProductId());
        CreateSalePaymentRequest paymentRequest = new CreateSalePaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        paymentClient.processSalePayment(paymentRequest);

        sale.setTotalPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        repository.save(sale);
        reduceQuantityProducts(product.getId(),request.getQuantityToBeSold());
        CreateSaleResponse response = mapper.map(sale, CreateSaleResponse.class);


        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, invoiceRequest, sale);
        sendKafkaInvoiceCreatedEvent(invoiceRequest);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfSaleExists(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateSaleResponse update(UUID id, UpdateSaleRequest updateSaleRequest) {
        rules.checkIfSaleExists(id);
        Sale sale = mapper.map(updateSaleRequest, Sale.class);
        GetProductResponse product = productClient.getProductById(sale.getProductId());
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        sale = repository.save(sale);
        UpdateSaleResponse response = mapper.map(sale, UpdateSaleResponse.class);
        return response;
    }

    @Override
    public GetSaleResponse getById(UUID id) {
        rules.checkIfSaleExists(id);
        Sale sale = repository.findById(id).orElseThrow();
        GetSaleResponse response = mapper.map(sale, GetSaleResponse.class);
        return response;
    }

    private void checkProductAvailabilityForSale(CreateSaleRequest request) {
        var product = productClient.getProductById(request.getProductId());
        if (product.getIsActive().equals(State.PASSIVE)) {
            throw new BusinessException("Ürün pasif");
        }
        if (product.getQuantity() < 0) {
            throw new BusinessException("Ürün stokta yok");
        }
    }

    private double getTotalPrice(GetProductResponse product, int quantity) {
        return product.getPrice() * quantity;
    }

    private void reduceQuantityProducts(UUID id, int quantityToBeSold) {
        sendKafkaSaleCreatedEvent(id,quantityToBeSold);
    }

    private void sendKafkaSaleCreatedEvent(UUID id,int quantityToBeSold) {
        producer.sendMessage(new SaleCreatedEvent(id,quantityToBeSold),"sale-created");
    }

    private void createInvoiceRequest(CreateSaleRequest request, CreateInvoiceRequest invoiceRequest, Sale sale) {
        var product = productClient.getProductById(request.getProductId());

        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolderName());
        invoiceRequest.setSaledAt(sale.getDate());
        invoiceRequest.setProductName(product.getName());
        invoiceRequest.setPrice(getTotalPrice(product, sale.getQuantityToBeSold()));
        invoiceRequest.setQuantity(sale.getQuantityToBeSold());
    }
    private void sendKafkaInvoiceCreatedEvent(CreateInvoiceRequest invoiceRequest) {
        producer.sendMessage(new InvoiceCreatedEvent(invoiceRequest), "invoice-created");
    }

}



