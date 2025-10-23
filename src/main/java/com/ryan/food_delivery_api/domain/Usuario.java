package com.ryan.food_delivery_api.domain;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
public class Usuario {
    private String nome;
    private String email;
    private String senha;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @ManyToMany
    @JoinTable(
        name = "usuario_grupo",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private List<Grupo> grupos = new ArrayList<>();
}
