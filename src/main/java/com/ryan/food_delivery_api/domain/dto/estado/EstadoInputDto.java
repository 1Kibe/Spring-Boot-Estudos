package com.ryan.food_delivery_api.domain.dto.estado;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInputDto {
    @NotBlank
    private String nome;
}
