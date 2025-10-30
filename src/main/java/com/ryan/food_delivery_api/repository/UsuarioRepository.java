package com.ryan.food_delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.food_delivery_api.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
