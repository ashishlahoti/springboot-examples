package com.example.assertion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Boolean onSale;
    private Integer stockQuantity;
    private BigDecimal price;
    private List<String> labels;
}
