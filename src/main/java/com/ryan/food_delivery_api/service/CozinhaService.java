package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.cozinha.CozinhaNaoEncontradaException;
import com.ryan.food_delivery_api.exception.cozinha.CozinhaEmUsoException;
import com.ryan.food_delivery_api.repository.CozinhaRepository;

import jakarta.transaction.Transactional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Cozinha> buscar(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cozinha buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(id));
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e){
            throw new CozinhaEmUsoException(id,Cozinha.class);
        }
    }

}
