package com.ryan.food_delivery_api.exception.usuario;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class UsuarioEmUsoException extends EntidadeEmUsoException{

    public UsuarioEmUsoException(String mensagem){
        super(mensagem);
    }

    public UsuarioEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public UsuarioEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
