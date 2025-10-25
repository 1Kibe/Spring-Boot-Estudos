package com.ryan.food_delivery_api.exception.cidade;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{

    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id){
        this(String.format("Não existe um cadastro de cidade com código %d", id));
    }
}
