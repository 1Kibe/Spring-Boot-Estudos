package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.estado.EstadoNaoEncontradoException;
import com.ryan.food_delivery_api.repository.EstadoRepository;

@Service
public class EstadoService {

    private static final String MSG_ENTIDADE_EM_USO = "Entidade atual nao pode ser removida, pois esta em uso";

    @Autowired
    private EstadoRepository repository;

    public List<Estado> listar() {
        return repository.findAll();
    }

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public Optional<Estado> buscar(Long id) {
        return repository.findById(id);
    }

    public Estado buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    public Estado salvar(Estado obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EstadoNaoEncontradoException(id);
                    
        } catch (DataIntegrityViolationException e){
            throw new EntidadeNaoEncontradaException(MSG_ENTIDADE_EM_USO);
        }
    }
}
