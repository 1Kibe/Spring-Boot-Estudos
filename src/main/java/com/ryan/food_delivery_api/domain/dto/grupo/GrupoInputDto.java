package com.ryan.food_delivery_api.domain.dto.grupo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDto {
    @NotBlank
    private String nome;

}
