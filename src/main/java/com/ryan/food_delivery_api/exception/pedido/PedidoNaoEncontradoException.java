package com.ryan.food_delivery_api.exception.pedido;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public PedidoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de pedido com código %d", id));
    }
}
