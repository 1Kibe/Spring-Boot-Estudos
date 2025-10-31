package com.ryan.food_delivery_api.exception.permissao;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public PermissaoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public PermissaoNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de permissao com código %d", id));
    }
}
