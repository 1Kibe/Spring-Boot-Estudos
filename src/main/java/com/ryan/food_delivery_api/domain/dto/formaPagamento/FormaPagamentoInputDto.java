package com.ryan.food_delivery_api.domain.dto.formaPagamento;

import com.ryan.food_delivery_api.domain.dto.pedido.PedidoDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInputDto {

    private String nome;
    private PedidoDto pedido;
    private Boolean ativo;
}
