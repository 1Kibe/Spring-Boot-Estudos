package com.ryan.food_delivery_api.exception.restaurante;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de restaurante com código %d", id));
    }
}
