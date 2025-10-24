package com.ryan.food_delivery_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryan.food_delivery_api.domain.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{

    @Query(value = "SELECT * FROM restaurante", nativeQuery = true)
    List<Restaurante> findAllCat();
}
