package com.ryan.food_delivery_api.domain.dto.formaPagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoDto {

    private Long id;
    private String descricao;
    private Boolean ativo;
}
