package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Estado;
import com.ryan.food_delivery_api.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;


    public List<Estado> listar(){
        return repository.findAll();
    }

   public EstadoService(EstadoRepository repository) {
    this.repository = repository;
}

 public Optional<Estado> buscar(Long id){
        return repository.findById(id);
    }

    public Estado salvar(Estado obj){
        return repository.save(obj);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
