package com.ryan.food_delivery_api.repository;

import java.util.List;

import com.ryan.food_delivery_api.repository.filter.PedidoRepositoryFilter;
import com.ryan.food_delivery_api.repository.query.PedidoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryan.food_delivery_api.domain.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> , PedidoRepositoryQuery {
    
    List<Pedido> filtar (PedidoRepositoryFilter filter);

    @SuppressWarnings("null")
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha ")
    List<Pedido> findAll();
}


