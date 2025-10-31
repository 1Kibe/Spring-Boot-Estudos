package com.ryan.food_delivery_api.domain.dto.pedido;

import java.util.List;

import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoInputDto;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoIdInputDto;
import com.ryan.food_delivery_api.domain.dto.itemPedido.ItemPedidoInputDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteIdInputDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {

    @NotNull
    @Valid
    private EnderecoInputDto endereco;

    @NotNull
    @Valid
    private FormaPagamentoIdInputDto formaPagamento;

    @NotNull
    @Valid
    private RestauranteIdInputDto restaurante;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<ItemPedidoInputDto> itensPedido;

}
