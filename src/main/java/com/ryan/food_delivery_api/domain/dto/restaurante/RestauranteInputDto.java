package com.ryan.food_delivery_api.domain.dto.restaurante;

import java.math.BigDecimal;

import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaIdInputDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInputDto {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaIdInputDto cozinha;

}
