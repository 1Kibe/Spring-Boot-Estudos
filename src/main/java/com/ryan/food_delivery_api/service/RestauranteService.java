package com.ryan.food_delivery_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ryan.food_delivery_api.domain.Cozinha;
import com.ryan.food_delivery_api.domain.Restaurante;
import com.ryan.food_delivery_api.exception.EntidadeNaoEncontradaException;
import com.ryan.food_delivery_api.exception.restaurante.RestauranteEmUsoException;
import com.ryan.food_delivery_api.exception.restaurante.RestauranteNaoEncontradoException;
import com.ryan.food_delivery_api.repository.RestauranteRepository;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> listar() {
        return repository.findAllCat();
    }

    public Optional<Restaurante> buscar(Long id) {

        return repository.findById(id);

    }

    public Restaurante buscarOuFalhar(Long id) {
        // ResponseStatus
        return repository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    public Restaurante salvar(Restaurante obj) {
        Long atributoId = obj.getCozinha().getId();
        Cozinha atributo = cozinhaService.buscarOuFalhar(atributoId);

        obj.setCozinha(atributo);

        return repository.save(obj);
    }

    public void deletar(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new RestauranteNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e){
            throw new RestauranteEmUsoException(id,Restaurante.class);
        }
        
    }
}
