package com.shiv.vehicl.repo;

import com.shiv.vehicl.model.Lorry; // Use your Lorry model class

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LorryRepo extends JpaRepository<Lorry, Integer> {

    List<Lorry> findByTitle(String title);



    // Add additional custom query methods for lorries if needed
}

