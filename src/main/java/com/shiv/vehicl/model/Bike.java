package com.shiv.vehicl.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Component
@AllArgsConstructor
@Data
@Scope("prototype")
@NoArgsConstructor
public class Bike {
    @Id
    private Long id;
    private String title;
    private String price;
    private String imageUrl;
    private String fueltype;
    private String capacity;
    private String features;


    // Getters and Setters
}





