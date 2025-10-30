package com.ryan.food_delivery_api.domain.dto.produto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoIdInputDto {
    @NotBlank
    private Long id;
}
