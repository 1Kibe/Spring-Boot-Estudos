package com.ryan.food_delivery_api.exception.pedido;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class PedidoEmUsoException extends EntidadeEmUsoException{

    public PedidoEmUsoException(String mensagem){
        super(mensagem);
    }

    public PedidoEmUsoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

    public PedidoEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
