package com.ryan.food_delivery_api.exception.cozinha;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class CozinhaEmUsoException extends EntidadeEmUsoException{

    public CozinhaEmUsoException(String mensagem){
        super(mensagem);
    }

    public CozinhaEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public CozinhaEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
