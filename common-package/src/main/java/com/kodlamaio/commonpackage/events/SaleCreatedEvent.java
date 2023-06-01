package com.kodlamaio.commonpackage.events;

import lombok.*;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleCreatedEvent implements Event{
    private UUID productId;
    private int quantityToBeSold;
}
