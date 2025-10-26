package com.ryan.food_delivery_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cozinha {
    //@NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //RELACIONAMENTO BI-DIRECIONAL
    //@JsonIgnore //evita erros circularers
    //@OneToMany(mappedBy = "cozinha") //passa o mapeamento que foi declarado no outro lado da relacao
    //private List<Restaurante> restaurantes = new ArrayList<>();
}
