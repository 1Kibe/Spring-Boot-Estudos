package com.ryan.food_delivery_api.exception.restaurante;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class RestauranteEmUsoException extends EntidadeEmUsoException{

    public RestauranteEmUsoException(String mensagem){
        super(mensagem);
    }

    public RestauranteEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public RestauranteEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
