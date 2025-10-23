package com.ryan.Conceitos.JpaRepositoryCustomizandoBase;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean           //faz com que o spring ignore esse repository
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscarPrimeiro();
}
