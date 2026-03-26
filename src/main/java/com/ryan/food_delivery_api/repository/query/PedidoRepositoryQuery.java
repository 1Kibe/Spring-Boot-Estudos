package com.ryan.food_delivery_api.repository.query;

import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.repository.filter.PedidoRepositoryFilter;

import java.util.List;

public interface PedidoRepositoryQuery {

    List<Pedido> filtro(PedidoRepositoryFilter filter);
}
