package com.ryan.food_delivery_api.exception.formaPagamento;

import com.ryan.food_delivery_api.exception.EntidadeEmUsoException;

public class FormaPagamentoEmUsoException extends EntidadeEmUsoException {
    public FormaPagamentoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoEmUsoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public FormaPagamentoEmUsoException(Long id, Class<?> clazz) {
        super(String.format("%s com id %d não pode ser removida, pois está em uso",
                clazz.getSimpleName(), id));
    }
}
