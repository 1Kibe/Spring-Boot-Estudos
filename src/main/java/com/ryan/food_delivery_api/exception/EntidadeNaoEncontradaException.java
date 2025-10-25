package com.ryan.food_delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//extenda RuntimeException
//anote a resposta desejada no ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class EntidadeNaoEncontradaException extends NegocioException{
    //extends com ResponseStatusException
    //gera um superconstrutor
    
    //public EntidadeNaoEncontradaException(HttpStatus status, String mensagem){
    //    super(status, mensagem);
    //}

    //----------------------------------------------------------------------------------

    //export com RunTimeExecption
    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
        //this(HttpStatus.NOT_FOUND, mensagem);
    }    
}
