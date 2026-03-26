package com.ryan.food_delivery_api.repository.filter;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;


@Data
public class PedidoRepositoryFilter {
    private Long clienteId;
    private Long restautanteId;
    private Date datacriacaoInicio;
    private Date datacriacaoFim;
}
