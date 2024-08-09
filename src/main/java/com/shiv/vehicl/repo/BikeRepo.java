package com.shiv.vehicl.repo;

import com.shiv.vehicl.model.Bike; // Update to your Bike model class

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepo extends JpaRepository<Bike, Integer> {
    List<Bike> findByTitle(String title);
}

