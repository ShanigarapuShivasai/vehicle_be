package com.shiv.vehicl.controller;

import com.shiv.vehicl.dto.BikeDto;
import com.shiv.vehicl.model.Bike;
import com.shiv.vehicl.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bikes") // Base path for bikes
public class BikeController {

    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/allbikes")
    @ResponseBody
    public ResponseEntity<List<BikeDto>> getBikes() {
        List<Bike> bikes = bikeService.getAllBikes();
        List<BikeDto> bikeDtos = bikes.stream()
                .map(this::convertBikeToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bikeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BikeDto> getBikeById(@PathVariable Integer id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BikeDto bikeDto = convertBikeToDto(bike);
        return new ResponseEntity<>(bikeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        Bike newBike = bikeService.saveBike(bike);
        return new ResponseEntity<>(newBike, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bike> updateBike(@PathVariable Integer id, @RequestBody Bike bikeDetails) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bike.setTitle(bikeDetails.getTitle());
        bike.setPrice(bikeDetails.getPrice());
        bike.setImageUrl(bikeDetails.getImageUrl());
        bike.setFueltype(bikeDetails.getFueltype());
        bike.setCapacity(bikeDetails.getCapacity());
        bike.setFeatures(bikeDetails.getFeatures());

        Bike updatedBike = bikeService.saveBike(bike);
        return new ResponseEntity<>(updatedBike, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable Integer id) {
        bikeService.deleteBike(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private BikeDto convertBikeToDto(Bike bike) {
        BikeDto bikeDto = new BikeDto();
        bikeDto.setId(bike.getId());
        bikeDto.setTitle(bike.getTitle());
        bikeDto.setPrice(bike.getPrice());
        bikeDto.setImageUrl(bike.getImageUrl());
        bikeDto.setFueltype(bike.getFueltype());
        bikeDto.setCapacity(bike.getCapacity());
        bikeDto.setFeatures(bike.getFeatures());
        return bikeDto;
    }
}
