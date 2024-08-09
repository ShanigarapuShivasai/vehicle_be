package com.shiv.vehicl.controller;

import com.shiv.vehicl.dto.AutoDto;
import com.shiv.vehicl.model.Auto;
import com.shiv.vehicl.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    private final AutoService autoService;

    @Autowired
    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping("/allautos")
    @ResponseBody
    public ResponseEntity<List<AutoDto>> getAllAutos() {
        List<Auto> autos = autoService.getAllAutos();
        List<AutoDto> autoDtos = autos.stream()
                .map(this::convertAutoToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(autoDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AutoDto> getAutoById(@PathVariable Integer id) {
        Auto auto = autoService.getAutoById(id);
        if (auto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AutoDto autoDto = convertAutoToDto(auto);
        return new ResponseEntity<>(autoDto, HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<Auto> addAuto(@RequestBody Auto auto) {
    Auto newAuto = autoService.saveAuto(auto);
    return new ResponseEntity<>(newAuto, HttpStatus.CREATED);
}


    @PutMapping("/{id}")
    public ResponseEntity<Auto> updateAuto(@PathVariable Integer id, @RequestBody Auto autoDetails) {
        Auto auto = autoService.getAutoById(id);
        if (auto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        auto.setTitle(autoDetails.getTitle());
        auto.setPrice(autoDetails.getPrice());
        auto.setImageUrl(autoDetails.getImageUrl());
        auto.setFueltype(autoDetails.getFueltype());
        auto.setCapacity(autoDetails.getCapacity());
        auto.setFeatures(autoDetails.getFeatures());

        Auto updatedAuto = autoService.saveAuto(auto);
        return new ResponseEntity<>(updatedAuto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuto(@PathVariable Integer id) {
        autoService.deleteAuto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private AutoDto convertAutoToDto(Auto auto) {
        AutoDto autoDto = new AutoDto();
        autoDto.setId(auto.getId());
        autoDto.setTitle(auto.getTitle());
        autoDto.setPrice(auto.getPrice());
        autoDto.setImageUrl(auto.getImageUrl());
        autoDto.setFueltype(auto.getFueltype());
        autoDto.setCapacity(auto.getCapacity());
        autoDto.setFeatures(auto.getFeatures());
        return autoDto;
    }
}
