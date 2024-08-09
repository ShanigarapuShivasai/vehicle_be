package com.shiv.vehicl.service;

import com.shiv.vehicl.model.Auto;
import com.shiv.vehicl.repo.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    private final AutoRepo autoRepository;

    @Autowired
    public AutoService(AutoRepo autoRepository) {
        this.autoRepository = autoRepository;
    }

    public List<Auto> getAllAutos() {
        return autoRepository.findAll();
    }

    public Auto getAutoById(Integer id) {
        return autoRepository.findById(id).orElse(null);
    }

    public Auto saveAuto(Auto auto) {
        return autoRepository.save(auto);
    }

    public void deleteAuto(Integer id) {
        autoRepository.deleteById(id);
    }
    public List<Auto> getBikesByTitle(String title) {
        return autoRepository.findByTitle(title);
    }
}
