package com.shiv.vehicl.repo;
import com.shiv.vehicl.model.Car;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    List<Car> findByTitle(String title);
}

