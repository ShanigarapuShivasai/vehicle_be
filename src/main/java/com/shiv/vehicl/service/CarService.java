package com.shiv.vehicl.service;

import com.shiv.vehicl.model.Car;
import com.shiv.vehicl.repo.CarRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    private final CarRepo carRepository;

    @Autowired
    public CarService(CarRepo carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car with ID " + id + " not found"));
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    public List<Car> getCarsByTitle(String title) {
        return carRepository.findByTitle(title);
    }
}
