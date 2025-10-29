package com.ryan.food_delivery_api.exception.formaPagamento;

import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public FormaPagamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradoException(Long id) {
        this(String.format("Não existe um cadastro com essa forma de pagamento com código %d", id));
    }
}
