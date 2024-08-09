package com.shiv.vehicl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiv.vehicl.controller.BikeController;
import com.shiv.vehicl.exceptions.BikeNotFoundException;
import com.shiv.vehicl.model.Bike;
import com.shiv.vehicl.repo.BikeRepo;

import java.util.List;

@Service
public class BikeService {

    private static final Logger logger = LoggerFactory.getLogger(BikeController.class);
    private final BikeRepo bikeRepository;

    @Autowired
    public BikeService(BikeRepo bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(Integer id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new BikeNotFoundException("Bike with ID " + id + " not found"));
    }

    public Bike saveBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void deleteBike(Integer id) {
        bikeRepository.deleteById(id);
    }

    public List<Bike> getBikesByTitle(String title) {
        return bikeRepository.findByTitle(title);
    }
}
