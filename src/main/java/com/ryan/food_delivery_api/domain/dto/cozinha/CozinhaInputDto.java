package com.ryan.food_delivery_api.domain.dto.cozinha;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDto {
    
    @NotBlank
    private String nome;
}
