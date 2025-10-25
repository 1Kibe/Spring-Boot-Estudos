package com.ryan.food_delivery_api.exception.cidade;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class CidadeEmUsoException extends EntidadeEmUsoException {

    public CidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

    public CidadeEmUsoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public CidadeEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
