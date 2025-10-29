package com.ryan.food_delivery_api.domain.dto.cidade;

import com.ryan.food_delivery_api.domain.dto.estado.EstadoDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputDto {
    @NotBlank
    private String nome;
    
    @Valid
    @NotNull
    private EstadoDto estado;
}
