package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.estado.EstadoEmUsoException;
import com.ryan.food_delivery_api.exception.estado.EstadoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.EstadoRepository;

import jakarta.transaction.Transactional;


@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return repository.findAll();
    }

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Estado> buscar(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Estado buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    @Transactional
    public Estado salvar(Estado obj) {
        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EntidadeNaoEncontradaException e) {
            throw new EstadoNaoEncontradoException(id);
                    
        } catch (DataIntegrityViolationException e){
            throw new EstadoEmUsoException(id,Estado.class);
        }
    }
}
