package com.ryan.food_delivery_api.domain.dto.produto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoIdInputDto {
    @NotNull
    private Long id;
}
