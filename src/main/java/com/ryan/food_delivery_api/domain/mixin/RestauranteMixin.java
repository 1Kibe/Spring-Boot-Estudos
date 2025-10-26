package com.ryan.food_delivery_api.domain.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.domain.Pedido;
import com.ryan.food_delivery_api.domain.Produto;


public class RestauranteMixin {

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    @JsonIgnoreProperties(value = {"nome"},allowGetters = true) // ignora a propriedade nome da cozinha quando vor mostrar no json
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formaPagamento = new ArrayList<>();

}
