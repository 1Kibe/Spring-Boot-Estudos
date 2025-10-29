package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.FormaPagamento;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.formaPagamento.FormaPagamentoEmUsoException;
import com.ryan.food_delivery_api.exception.formaPagamento.FormaPagamentoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.FormaPagamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<FormaPagamento> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<FormaPagamento> buscar(Long id) {

        return repository.findById(id);

    }

    @Transactional
    public FormaPagamento buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradoException(id));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento obj) {

        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();// para tratar erro de lancar exception depois do metodo
        } catch (EntidadeNaoEncontradaException e) {
            throw new FormaPagamentoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new  FormaPagamentoEmUsoException(id,
                    FormaPagamento.class);
        }
    }

    // Sub Rotas

    @Transactional
    public void ativar(Long id) {
        FormaPagamento entityAtual = buscarOuFalhar(id);
        entityAtual.ativar();
    }

    @Transactional
    public void desativar(Long id) {
        FormaPagamento entityAtual = buscarOuFalhar(id);
        entityAtual.desativar();
    }

}
