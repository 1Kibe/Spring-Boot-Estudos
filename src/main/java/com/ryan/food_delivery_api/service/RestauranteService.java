package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public RestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> listar(){
        return repository.findAllCat();
    }

    public Optional<Restaurante> buscar(Long id){
        return repository.findById(id);
    }

    public Restaurante salvar(Restaurante obj){
        return repository.save(obj);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
