package com.ryan.food_delivery_api.domain.dto.cidade;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInputDto {
    @NotBlank
    private Long id;
}
