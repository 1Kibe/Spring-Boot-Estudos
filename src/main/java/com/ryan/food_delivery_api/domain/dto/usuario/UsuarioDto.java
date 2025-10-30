package com.ryan.food_delivery_api.domain.dto.usuario;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private OffsetDateTime dataCriacao;

}
