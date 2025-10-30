package com.ryan.food_delivery_api.exception.usuario;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{

    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de usuario com código %d", id));
    }
}
