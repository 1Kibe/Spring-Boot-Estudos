package com.ryan.food_delivery_api.domain.dto.restaurante;

import java.math.BigDecimal;

import com.ryan.food_delivery_api.domain.dto.cozinha.CozinhaIdInputDto;
import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoInputDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInputDto {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInputDto cozinha;

    @Valid
    @NotNull
    private EnderecoInputDto endereco;

}
