package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.repository.CozinhaRepository;

@Service
public class CozinhaService {

    private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Entidade não encontrada";

    @Autowired
    private CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public List<Cozinha> listar() {
        return repository.findAll();
    }

    public Optional<Cozinha> buscar(Long id) {
        return repository.findById(id);
    }

    public Cozinha buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_ENTIDADE_NAO_ENCONTRADA));
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ENTIDADE_NAO_ENCONTRADA, id));
        }
    }

}
