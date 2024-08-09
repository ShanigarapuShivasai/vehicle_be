package com.shiv.vehicl.service;

import com.shiv.vehicl.model.Lorry;
import com.shiv.vehicl.repo.LorryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LorryService {

    private final LorryRepo lorryRepository;

    @Autowired
    public LorryService(LorryRepo lorryRepository) {
        this.lorryRepository = lorryRepository;
    }

    public List<Lorry> getAllLorries() {
        return lorryRepository.findAll();
    }

    public Lorry getLorryById(Integer id) {
        return lorryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lorry with ID " + id + " not found"));
    }

    public Lorry saveLorry(Lorry lorry) {
        return lorryRepository.save(lorry);
    }

    public void deleteLorry(Integer id) {
        lorryRepository.deleteById(id);
    }

    public List<Lorry> getLorriesByTitle(String title) {
        return lorryRepository.findByTitle(title);
    }
}
