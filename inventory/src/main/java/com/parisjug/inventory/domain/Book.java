package com.parisjug.inventory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Book {
    private String id;
    private String name;
    private BigDecimal price = BigDecimal.ZERO;
    private int stock = 0;
}
