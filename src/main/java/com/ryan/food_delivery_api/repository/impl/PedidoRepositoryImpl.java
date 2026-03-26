package com.ryan.food_delivery_api.repository.impl;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.repository.filter.PedidoRepositoryFilter;
import com.ryan.food_delivery_api.repository.query.PedidoRepositoryQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Pedido> filtro(PedidoRepositoryFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);


        var predicates = new ArrayList<Predicate>();
        
        if (filter.getClienteId() != null && !filter.getClienteId().describeConstable().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("id").as(String.class)),
                    "%" + filter.getClienteId() + "%"));
        }
        
        if (filter.getRestautanteId() != null && !filter.getRestautanteId().describeConstable().isEmpty()) {
            predicates.add(builder.like(builder.lower(root.get("id").as(String.class)),
                    "%" + filter.getRestautanteId() + "%"));
        }
        
        if (filter.getDatacriacaoFim() != null) {
            Instant instant = filter.getDatacriacaoFim().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataemissao"), offsetDateTime));
        }
        
        if(filter.getDatacriacaoFim() != null) {
            Instant instant = filter.getDatacriacaoFim().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.lessThanOrEqualTo(root.get("dataemissao"), offsetDateTime));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
                
        TypedQuery<Pedido> query = manager.createQuery(criteria);
        return query.getResultList();

    }
}
