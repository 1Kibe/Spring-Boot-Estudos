package com.ryan.food_delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException{

    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }

    public EntidadeEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
