package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cidade;
import com.ryan.food_delivery_api.repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    
    public List<Cidade> listar(){
        return repository.findAll();
    }

    public Optional<Cidade> buscar(Long id){
        return repository.findById(id);
    }

    public Cidade salvar(Cidade obj){
        return repository.save(obj);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
