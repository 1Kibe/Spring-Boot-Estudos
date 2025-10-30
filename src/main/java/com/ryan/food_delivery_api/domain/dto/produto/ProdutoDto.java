package com.ryan.food_delivery_api.domain.dto.produto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
}
