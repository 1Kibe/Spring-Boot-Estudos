package com.ryan.food_delivery_api.domain.dto.formaPagamento;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInputDto {

    @NotBlank
    private String nome;

    private Boolean ativo;
}
