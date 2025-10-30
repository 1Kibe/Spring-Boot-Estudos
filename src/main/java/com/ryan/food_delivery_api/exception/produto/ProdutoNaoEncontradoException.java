package com.ryan.food_delivery_api.exception.produto;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long id){
        this(String.format("Não existe um cadastro de produto com código %d", id));
    }
}
