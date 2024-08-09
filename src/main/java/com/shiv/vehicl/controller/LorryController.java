package com.shiv.vehicl.controller;

import com.shiv.vehicl.dto.LorryDto;
import com.shiv.vehicl.model.Lorry;
import com.shiv.vehicl.service.LorryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lorries")
public class LorryController {

    private final LorryService lorryService;

    @Autowired
    public LorryController(LorryService lorryService) {
        this.lorryService = lorryService;
    }

    @GetMapping("/alllorries")
    @ResponseBody
    public ResponseEntity<List<LorryDto>> getAllLorries() {
        List<Lorry> lorries = lorryService.getAllLorries();
        List<LorryDto> lorryDtos = lorries.stream()
                .map(this::convertLorryToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lorryDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LorryDto> getLorryById(@PathVariable Integer id) {
        Lorry lorry = lorryService.getLorryById(id);
        if (lorry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LorryDto lorryDto = convertLorryToDto(lorry);
        return new ResponseEntity<>(lorryDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lorry> addLorry(@RequestBody Lorry lorry) {
        Lorry newLorry = lorryService.saveLorry(lorry);
        return new ResponseEntity<>(newLorry, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lorry> updateLorry(@PathVariable Integer id, @RequestBody Lorry lorryDetails) {
        Lorry lorry = lorryService.getLorryById(id);
        if (lorry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lorry.setTitle(lorryDetails.getTitle());
        lorry.setPrice(lorryDetails.getPrice());
        lorry.setImageUrl(lorryDetails.getImageUrl());
        lorry.setFueltype(lorryDetails.getFueltype());
        lorry.setCapacity(lorryDetails.getCapacity());
        lorry.setFeatures(lorryDetails.getFeatures());

        Lorry updatedLorry = lorryService.saveLorry(lorry);
        return new ResponseEntity<>(updatedLorry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLorry(@PathVariable Integer id) {
        lorryService.deleteLorry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LorryDto convertLorryToDto(Lorry lorry) {
        LorryDto lorryDto = new LorryDto();
        lorryDto.setId(lorry.getId());
        lorryDto.setTitle(lorry.getTitle());
        lorryDto.setPrice(lorry.getPrice());
        lorryDto.setImageUrl(lorryDto.getImageUrl());
        lorryDto.setFueltype(lorry.getFueltype());
        lorryDto.setCapacity(lorry.getCapacity());
        lorryDto.setFeatures(lorry.getFeatures());
        return lorryDto;
    
    }
}