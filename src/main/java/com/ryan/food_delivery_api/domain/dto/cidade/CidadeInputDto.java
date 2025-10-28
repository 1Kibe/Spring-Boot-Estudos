package com.ryan.food_delivery_api.domain.dto.cidade;

import com.ryan.food_delivery_api.domain.dto.estado.EstadoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputDto {
    private String nome;
    private EstadoDto estado;
}
