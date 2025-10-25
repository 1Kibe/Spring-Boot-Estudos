package com.ryan.food_delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem){
        super(mensagem);
    }

    //retorna a causa da exception
    public NegocioException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
