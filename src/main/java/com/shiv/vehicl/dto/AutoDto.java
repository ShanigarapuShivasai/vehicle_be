package com.shiv.vehicl.dto;

import lombok.Data;

@Data
public class AutoDto {
    private Long id;
    private String title;
    private String price;
    private String imageUrl;
    private String fueltype;
    private String capacity;
    private String features;
}
