package com.ryan.food_delivery_api.exception.estado;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class EstadoEmUsoException extends EntidadeEmUsoException{

    public EstadoEmUsoException(String mensagem){
        super(mensagem);
    }

    public EstadoEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public EstadoEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
