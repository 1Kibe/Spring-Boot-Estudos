package com.ryan.food_delivery_api.domain.dto.cidade;


import com.ryan.food_delivery_api.domain.dto.estado.EstadoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDto {
    private Long id;
    private String nome;
    private EstadoDto estado;
}
