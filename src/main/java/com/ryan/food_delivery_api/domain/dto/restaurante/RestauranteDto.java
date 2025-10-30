package com.ryan.food_delivery_api.domain.dto.restaurante;

import java.math.BigDecimal;

import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaDto;
import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDto {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDto cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDto endereco;
}
