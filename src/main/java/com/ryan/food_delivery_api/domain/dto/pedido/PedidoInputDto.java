package com.ryan.food_delivery_api.domain.dto.pedido;

import java.math.BigDecimal;

import com.ryan.food_delivery_api.domain.StatusPedido;
import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoDto;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoDto;
import com.ryan.food_delivery_api.domain.dto.itemPedido.ItemPedidoDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteResunDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {

    @NotNull
    private BigDecimal subTotal;

    @NotNull
    private BigDecimal taxaFrete;

    @NotNull
    private BigDecimal valorTotal;

    @NotNull
    private EnderecoDto endereco;

    @NotBlank
    private StatusPedido statusPedido;

    @NotNull
    private FormaPagamentoDto formaPagamento;

    @NotNull
    private ItemPedidoDto itensPedido;

    @NotNull
    private RestauranteResunDto restaurante;
}
