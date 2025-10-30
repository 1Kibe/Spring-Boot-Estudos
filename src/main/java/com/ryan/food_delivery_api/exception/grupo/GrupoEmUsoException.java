package com.ryan.food_delivery_api.exception.grupo;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class GrupoEmUsoException extends EntidadeEmUsoException{

    public GrupoEmUsoException(String mensagem){
        super(mensagem);
    }

    public GrupoEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public GrupoEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
