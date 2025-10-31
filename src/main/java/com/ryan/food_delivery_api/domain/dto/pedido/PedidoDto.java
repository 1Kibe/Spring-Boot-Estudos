package com.ryan.food_delivery_api.domain.dto.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.ryan.food_delivery_api.domain.dto.endereco.EnderecoDto;
import com.ryan.food_delivery_api.domain.dto.formaPagamento.FormaPagamentoDto;
import com.ryan.food_delivery_api.domain.dto.itemPedido.ItemPedidoDto;
import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteResunDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {

    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private EnderecoDto endereco;
    private String statusPedido;
    private FormaPagamentoDto formaPagamentos;
    private List<ItemPedidoDto> itensPedidos;
    private RestauranteResunDto restaurante;

}
