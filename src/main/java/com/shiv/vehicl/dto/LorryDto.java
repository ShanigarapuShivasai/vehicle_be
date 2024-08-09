package com.shiv.vehicl.dto;

import lombok.Data;

@Data
public class LorryDto {
    private Long id;
    private String title;
    private String price;
    private String imageUrl;
    private String fueltype;
    private String capacity; // Might need to change type if capacity refers to weight
    private String features;
}

