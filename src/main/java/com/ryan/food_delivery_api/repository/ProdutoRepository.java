package com.ryan.food_delivery_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ryan.food_delivery_api.domain.Produto;
import com.ryan.food_delivery_api.domain.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca um produto específico dentro de um restaurante, garantindo que o
    // produto
    // realmente pertença ao restaurante informado. Essa query JPQL evita acesso
    // incorreto
    // a produtos de outros restaurantes.
    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long id, @Param("produto") Long atributoid);

    List<Produto> findByRestaurante(Restaurante restaurante);
}
