package com.ryan.food_delivery_api.domain.dto.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ryan.food_delivery_api.domain.dto.restaurante.RestauranteResunDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResunDto {
    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private String statusPedido;
    private RestauranteResunDto restaurante;
}
