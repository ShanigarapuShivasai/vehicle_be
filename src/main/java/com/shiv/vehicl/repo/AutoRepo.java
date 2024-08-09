package com.shiv.vehicl.repo;

import com.shiv.vehicl.model.Auto;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AutoRepo extends JpaRepository<Auto, Integer> {
    List<Auto> findByTitle(String title);
}

