package com.ryan.food_delivery_api.exception.grupo;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de grupo com código %d", id));
    }
}
