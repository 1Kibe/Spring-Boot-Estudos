package com.ryan.food_delivery_api.domain.dto.restaurante;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaDto;
import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoDto;

import com.ryan.food_delivery_api.domain.dto.views.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDto {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDto cozinha;
    private Boolean ativo;
    @JsonView(RestauranteView.Resumo.class)
    private Boolean aberto;
    @JsonView(RestauranteView.Resumo.class)
    private EnderecoDto endereco;
}
