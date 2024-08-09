package com.shiv.vehicl.controller;

import com.shiv.vehicl.dto.CarDto;
import com.shiv.vehicl.model.Car;
import com.shiv.vehicl.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars") // Base path for cars
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/allcars")
    @ResponseBody
    public ResponseEntity<List<CarDto>> getCars() {
        List<Car> cars = carService.getAllCars();
        List<CarDto> carDtos = cars.stream()
                .map(this::convertCarToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CarDto> getCarById(@PathVariable Integer id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CarDto carDto = convertCarToDto(car);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car carDetails) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.setTitle(carDetails.getTitle());
        car.setPrice(carDetails.getPrice());
        car.setImageUrl(carDetails.getImageUrl());
        car.setFueltype(carDetails.getFueltype());
        car.setCapacity(carDetails.getCapacity());
        car.setFeatures(carDetails.getFeatures());

        Car updatedCar = carService.saveCar(car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CarDto convertCarToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setTitle(car.getTitle());
        carDto.setPrice(car.getPrice());
        carDto.setImageUrl(car.getImageUrl());
        carDto.setFueltype(car.getFueltype());
        carDto.setCapacity(car.getCapacity());
        carDto.setFeatures(car.getFeatures());
        return carDto;
    }
}
