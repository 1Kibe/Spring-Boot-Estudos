package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.exception.cidade.CidadeEmUsoException;
import com.ryan.food_delivery_api.exception.cidade.CidadeNaoEncontradaException;
import com.ryan.food_delivery_api.repository.CidadeRepository;

import jakarta.transaction.Transactional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoService estadoService;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Cidade> listar() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Cidade> buscar(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cidade buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

    @Transactional
    public Cidade salvar(Cidade obj) {
        // verifica se existe um atributo presente
        Long atributoId = obj.getEstado().getId();
        Estado atributo = estadoService.buscarOuFalhar(atributoId);

        obj.setEstado(atributo);

        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e){
            throw new CidadeEmUsoException(id,Cidade.class);
        }
    }
}
