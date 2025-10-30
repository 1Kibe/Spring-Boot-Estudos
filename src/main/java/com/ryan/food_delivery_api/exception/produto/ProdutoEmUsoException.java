package com.ryan.food_delivery_api.exception.produto;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class ProdutoEmUsoException extends EntidadeEmUsoException{

    public ProdutoEmUsoException(String mensagem){
        super(mensagem);
    }

    public ProdutoEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public ProdutoEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
