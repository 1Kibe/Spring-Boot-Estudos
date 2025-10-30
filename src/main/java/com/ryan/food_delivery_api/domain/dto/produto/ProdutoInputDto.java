package com.ryan.food_delivery_api.domain.dto.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
    private BigDecimal preco;
}
